package com.example.backend.dtos;

import java.math.BigDecimal;

public record TransferDTO (
    Long fromId,
    Long toId,
    BigDecimal amount
) { }
