package com.tiwilli.gerenciamentoestoque.services.cash;

import com.tiwilli.gerenciamentoestoque.dto.cash.SaleDTO;
import com.tiwilli.gerenciamentoestoque.dto.cash.SaleItemDTO;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

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
            SaleItem item = new SaleItem(sale, product, itemDTO.getQuantity(), product.getPrice());
            sale.getItems().add(item);
            total += item.getQuantity() * product.getPrice();
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
}
