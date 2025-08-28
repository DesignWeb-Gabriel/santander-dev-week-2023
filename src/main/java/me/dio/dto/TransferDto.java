package me.dio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

@Schema(description = "Dados para operação de transferência bancária")
public class TransferDto {

    @NotBlank(message = "Conta de origem é obrigatória")
    @Pattern(regexp = "\\d{8}-\\d{1}", message = "Conta de origem deve seguir o padrão: 12345678-9")
    @Schema(description = "Número da conta de origem", example = "12345678-9", required = true)
    private String fromAccount;

    @NotBlank(message = "Conta de destino é obrigatória")
    @Pattern(regexp = "\\d{8}-\\d{1}", message = "Conta de destino deve seguir o padrão: 12345678-9")
    @Schema(description = "Número da conta de destino", example = "98765432-1", required = true)
    private String toAccount;

    @NotNull(message = "Valor da transferência é obrigatório")
    @DecimalMin(value = "0.01", message = "Valor da transferência deve ser maior que zero")
    @Schema(description = "Valor a ser transferido", example = "150.75", required = true)
    private BigDecimal amount;

    @Schema(description = "Descrição da transferência", example = "Pagamento de conta")
    private String description;

    // Constructors
    public TransferDto() {}

    public TransferDto(String fromAccount, String toAccount, BigDecimal amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    // Getters and Setters
    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
