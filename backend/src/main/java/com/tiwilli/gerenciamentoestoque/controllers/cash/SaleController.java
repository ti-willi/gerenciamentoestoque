package com.tiwilli.gerenciamentoestoque.controllers.cash;

import com.tiwilli.gerenciamentoestoque.dto.cash.SaleDTO;
import com.tiwilli.gerenciamentoestoque.services.cash.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

    @Autowired
    private SaleService service;

    @GetMapping
    public ResponseEntity<Page<SaleDTO>> findAll(
            @RequestParam(required = false, defaultValue = "") Instant startMoment,
            @RequestParam(required = false, defaultValue = "") Instant endMoment,
            @RequestParam(required = false, defaultValue = "") String period,
            Pageable pageable
    ) {
        Page<SaleDTO> result = service.findAll(startMoment, endMoment, period, pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> findById(@PathVariable Long id) {
        SaleDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<SaleDTO> insert(@RequestBody SaleDTO dto) {
        SaleDTO newSale = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return  ResponseEntity.created(uri).body(dto);
    }
}
