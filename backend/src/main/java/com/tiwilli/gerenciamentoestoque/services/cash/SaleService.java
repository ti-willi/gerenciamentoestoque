package com.tiwilli.gerenciamentoestoque.services.cash;

import com.tiwilli.gerenciamentoestoque.dto.cash.SaleDTO;
import com.tiwilli.gerenciamentoestoque.dto.cash.SaleItemDTO;
import com.tiwilli.gerenciamentoestoque.dto.cash.SaleReportDTO;
import com.tiwilli.gerenciamentoestoque.dto.cash.SalesReportDTO;
import com.tiwilli.gerenciamentoestoque.entities.cash.CashMovement;
import com.tiwilli.gerenciamentoestoque.entities.cash.CashSession;
import com.tiwilli.gerenciamentoestoque.entities.cash.Sale;
import com.tiwilli.gerenciamentoestoque.entities.cash.SaleItem;
import com.tiwilli.gerenciamentoestoque.entities.enums.MovementType;
import com.tiwilli.gerenciamentoestoque.entities.inventory.Product;
import com.tiwilli.gerenciamentoestoque.repositories.cash.CashMovementRepository;
import com.tiwilli.gerenciamentoestoque.repositories.cash.CashSessionRepository;
import com.tiwilli.gerenciamentoestoque.repositories.cash.SaleItemRepository;
import com.tiwilli.gerenciamentoestoque.repositories.cash.SaleRepository;
import com.tiwilli.gerenciamentoestoque.repositories.inventory.ProductRepository;
import com.tiwilli.gerenciamentoestoque.services.exceptions.ResourceNotFoundException;
import com.tiwilli.gerenciamentoestoque.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private SaleItemRepository saleItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CashSessionRepository cashSessionRepository;

    @Autowired
    private CashMovementRepository cashMovementRepository;

    @Transactional(readOnly = true)
    public SaleDTO findById(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return new SaleDTO(sale);
    }

    @Transactional(readOnly = true)
    public Page<SaleDTO> findAll(Instant startMoment, Instant endMoment, String period, Pageable pageable) {
        Pair<Instant, Instant> range = determineDateRange(startMoment, endMoment, period);
        Page<Sale> result = saleRepository.searchByDateBetween(range.getFirst(), range.getSecond(), pageable);
        return result.map(SaleDTO::new);
    }

    @Transactional
    public SaleDTO insert(SaleDTO dto) {
        Sale sale = createSale(dto);
        saleRepository.save(sale);
        saleItemRepository.saveAll(sale.getItems());

        registerCashMovement(sale);

        return new SaleDTO(sale);
    }

    private Pair<Instant, Instant> determineDateRange(Instant startMoment, Instant endMoment, String period) {
        if (startMoment != null && endMoment != null) {
            return Pair.of(startMoment, endMoment);
        } else if (period != null && !period.isEmpty()) {
            Pair<Instant, Instant> range = Utils.dateRange(period, Instant.now());
            if (range == null) {
                throw new IllegalArgumentException("Invalid period");
            }
            return range;
        } else {
            Instant start = Instant.EPOCH;
            Instant end = Instant.now();
            return Pair.of(start, end);
        }
    }

    private Sale createSale(SaleDTO dto) {
        Sale sale = new Sale();
        sale.setMoment(Instant.now());
        sale.setPaymentType(dto.getPaymentType());

        double total = 0.0;
        for (SaleItemDTO itemDTO : dto.getItems()) {
            Product product = getProductAndUpdateStock(itemDTO);

            double unitPrice = product.getPrice();
            double subTotal = unitPrice * itemDTO.getQuantity();
            SaleItem item = new SaleItem(sale, product, itemDTO.getName(), itemDTO.getQuantity(), unitPrice, subTotal);

            sale.getItems().add(item);
            total += subTotal;
        }

        sale.setTotal(total);
        return sale;
    }

    private Product getProductAndUpdateStock(SaleItemDTO itemDTO) {
        Product product = productRepository.getReferenceById(itemDTO.getProductId());
        if (product.getQuantity() < itemDTO.getQuantity()) {
            throw new IllegalArgumentException("Product out of inventory");
        }
        product.setQuantity(product.getQuantity() - itemDTO.getQuantity());
        productRepository.save(product);
        return product;
    }

    private void registerCashMovement(Sale sale) {
        CashSession openCash = cashSessionRepository.findByClosingTimeIsNull()
                .orElseThrow(() -> new IllegalArgumentException("No open cash session"));

        CashMovement movement = new CashMovement();
        movement.setAmount(sale.getTotal());
        movement.setType(MovementType.INCOME);
        movement.setDescription("Sale #" + sale.getId());
        movement.setMoment(Instant.now());
        movement.setCashSession(openCash);

        cashMovementRepository.save(movement);
    }

    @Transactional(readOnly = true)
    public SalesReportDTO generateReport(Instant minDate, Instant maxDate, Pageable pageable) {
        Page<Sale> salesPage = saleRepository.searchByDateBetween(minDate, maxDate, pageable);

        List<SaleReportDTO> saleReports = salesPage.stream()
                .map(sale -> new SaleReportDTO(
                        sale.getId(),
                        sale.getMoment(),
                        sale.getTotal(),
                        sale.getItems().stream()
                                .map(item -> new SaleItemDTO(
                                        item.getProduct().getId(),
                                        item.getProduct().getName(),
                                        item.getQuantity(),
                                        item.getUnitPrice(),
                                        item.getSubTotal()
                                ))
                                .toList()
                ))
                .toList();

        Integer totalQuantity = saleReports.stream()
                .flatMap(r -> r.items().stream())
                .mapToInt(SaleItemDTO::getQuantity)
                .sum();

        Double totalAmount = saleReports.stream()
                .mapToDouble(SaleReportDTO::totalAmount)
                .sum();

        return new SalesReportDTO(totalQuantity, totalAmount, new PageImpl<>(saleReports, pageable, salesPage.getTotalElements()));
    }

}
