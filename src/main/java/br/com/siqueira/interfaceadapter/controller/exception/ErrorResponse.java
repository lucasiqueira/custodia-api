package br.com.siqueira.interfaceadapter.controller.exception;

public record ErrorResponse(
        String code,
        String message
) {}