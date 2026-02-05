package br.com.siqueira.account.interfaceadapter.controller.exception;

public record ErrorResponse(
        String code,
        String message
) {}