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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<SaleDTO> findAll(Pageable pageable) {
        Page<Sale> result = repository.findAll(pageable);
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

            SaleItem item = new SaleItem(sale, product, itemDTO.getQuantity(), product.getValue());
            sale.getItems().add(item);

            total += item.getQuantity() * product.getValue();
        }

        sale.setTotal(total);

        repository.save(sale);
        itemRepository.saveAll(sale.getItems());

        return new SaleDTO(sale);

    }

}
