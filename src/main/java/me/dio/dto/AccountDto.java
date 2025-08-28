package me.dio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

@Schema(description = "Dados da conta bancária")
public class AccountDto {

    @Schema(description = "ID único da conta", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Número da conta é obrigatório")
    @Pattern(regexp = "\\d{8}-\\d{1}", message = "Número da conta deve seguir o padrão: 12345678-9")
    @Schema(description = "Número da conta bancária", example = "12345678-9", required = true)
    private String number;

    @NotBlank(message = "Agência é obrigatória")
    @Pattern(regexp = "\\d{4}", message = "Agência deve conter exatamente 4 dígitos")
    @Schema(description = "Número da agência", example = "0001", required = true)
    private String agency;

    @NotNull(message = "Saldo é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "Saldo deve ser maior que zero")
    @Schema(description = "Saldo atual da conta", example = "1500.50", required = true)
    private BigDecimal balance;

    @NotNull(message = "Limite é obrigatório")
    @DecimalMin(value = "0.0", message = "Limite deve ser maior ou igual a zero")
    @Schema(description = "Limite adicional da conta", example = "1000.00", required = true)
    private BigDecimal limit;

    // Constructors
    public AccountDto() {}

    public AccountDto(String number, String agency, BigDecimal balance, BigDecimal limit) {
        this.number = number;
        this.agency = agency;
        this.balance = balance;
        this.limit = limit;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }
}
