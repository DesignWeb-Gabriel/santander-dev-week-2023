package me.dio.exception;

import java.math.BigDecimal;

/**
 * Exceção para operações com saldo insuficiente
 */
public class InsufficientFundsException extends BusinessException {
    
    public InsufficientFundsException(BigDecimal currentBalance, BigDecimal requestedAmount) {
        super(String.format("Saldo insuficiente. Saldo atual: R$ %.2f, Valor solicitado: R$ %.2f", 
                currentBalance, requestedAmount));
    }
    
    public InsufficientFundsException(String message) {
        super(message);
    }
}
