package com.tiwilli.gerenciamentoestoque.services.cash;

import com.tiwilli.gerenciamentoestoque.dto.cash.SaleDTO;
import com.tiwilli.gerenciamentoestoque.dto.cash.SaleItemDTO;
import com.tiwilli.gerenciamentoestoque.entities.inventory.Product;
import com.tiwilli.gerenciamentoestoque.entities.cash.Sale;
import com.tiwilli.gerenciamentoestoque.entities.cash.SaleItem;
import com.tiwilli.gerenciamentoestoque.repositories.inventory.ProductRepository;
import com.tiwilli.gerenciamentoestoque.repositories.cash.SaleItemRepository;
import com.tiwilli.gerenciamentoestoque.repositories.cash.SaleRepository;
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
    private SaleRepository repository;

    @Autowired
    private SaleItemRepository itemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public SaleDTO findById(Long id) {
        Sale sale = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource not found"));
        return new SaleDTO(sale);
    }

    @Transactional(readOnly = true)
    public Page<SaleDTO> findAll(Instant startMoment, Instant endMoment, String period, Pageable pageable) {
        Instant start;
        Instant end;

        if (startMoment != null && endMoment != null) {
            start = startMoment;
            end = endMoment;
        }
        else if (period != null && !period.isEmpty()) {
            Pair<Instant, Instant> range = Utils.dateRange(period, Instant.now());
            if (range == null) {
                throw new IllegalArgumentException("Invalid period");
            }
            start = range.getFirst();
            end = range.getSecond();
        }
        else {
            throw  new IllegalArgumentException("You should enter either period or the date range");
        }

        Page<Sale> result = repository.searchByDateBetween(start, end, pageable);
        return result.map(SaleDTO::new);
    }

    @Transactional
    public SaleDTO insert(SaleDTO dto) {
        Sale sale = new Sale();

        sale.setmoment(Instant.now());
        sale.setPaymentType(dto.getPaymentType());

        double total = 0.0;
        for (SaleItemDTO itemDTO : dto.getItems()) {
            Product product = productRepository.getReferenceById(itemDTO.getProductId());
            if (product.getQuantity() < itemDTO.getQuantity()) {
                throw new IllegalArgumentException("Product out of inventory");
            }
            product.setQuantity(product.getQuantity() - itemDTO.getQuantity());
            productRepository.save(product);

            SaleItem item = new SaleItem(sale, product, itemDTO.getQuantity(), product.getPrice());
            sale.getItems().add(item);

            total += item.getQuantity() * product.getPrice();
        }

        sale.setTotal(total);

        repository.save(sale);
        itemRepository.saveAll(sale.getItems());

        return new SaleDTO(sale);

    }

}
