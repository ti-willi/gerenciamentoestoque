package com.tiwilli.gerenciamentoestoque.controllers.cash;

import com.tiwilli.gerenciamentoestoque.dto.cash.CashSessionDTO;
import com.tiwilli.gerenciamentoestoque.services.cash.CashSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cash-sessions")
public class CashSessionController {

    @Autowired
    private CashSessionService service;

    @GetMapping
    public ResponseEntity<Page<CashSessionDTO>> findAll(Pageable pageable) {
        Page<CashSessionDTO> result = service.findAll(pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    private ResponseEntity<CashSessionDTO> findById(@PathVariable Long id) {
        CashSessionDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/open")
    public ResponseEntity<CashSessionDTO> openCash(@RequestParam Double initialBalance) {
        CashSessionDTO dto = service.openCash(initialBalance);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/close")
    public ResponseEntity<CashSessionDTO> closeCash() {
        CashSessionDTO dto = service.closeCash();
        return ResponseEntity.ok(dto);
    }


}
