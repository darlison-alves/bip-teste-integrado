package com.example.backend.dtos;

public record ErrorDTO (
    int status,
    String error,
    String message,
    String path
){ }
