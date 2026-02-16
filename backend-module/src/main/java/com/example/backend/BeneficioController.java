package com.example.backend;

import com.example.backend.dtos.TransferDTO;
import com.example.backend.dtos.TransferResponse;
import com.example.ejb.BeneficioEjbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/beneficios")
public class BeneficioController {
    @Autowired
    BeneficioEjbService beneficioEjbService;

    @GetMapping
    public List<String> list() {
        return Arrays.asList("Beneficio A", "Beneficio B");
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferResponse> transfer(@RequestBody() TransferDTO transferDTO) {
        this.beneficioEjbService.transfer(transferDTO.fromId(), transferDTO.toId(), transferDTO.amount());
        return ResponseEntity.ok(new TransferResponse("Transfer successful"));
    }
}
