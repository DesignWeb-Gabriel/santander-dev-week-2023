package me.dio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

@Schema(description = "Dados do cartão bancário")
public class CardDto {

    @Schema(description = "ID único do cartão", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Número do cartão é obrigatório")
    @Pattern(regexp = "\\*{4} \\*{4} \\*{4} \\d{4}", message = "Número do cartão deve estar mascarado: **** **** **** 1234")
    @Schema(description = "Número mascarado do cartão", example = "**** **** **** 1234", required = true)
    private String number;

    @NotNull(message = "Limite do cartão é obrigatório")
    @DecimalMin(value = "0.0", message = "Limite deve ser maior ou igual a zero")
    @Schema(description = "Limite do cartão", example = "2000.00", required = true)
    private BigDecimal limit;

    // Constructors
    public CardDto() {}

    public CardDto(String number, BigDecimal limit) {
        this.number = number;
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

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }
}
