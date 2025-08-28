package me.dio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Notícia ou oferta personalizada para o usuário")
public class NewsDto {

    @Schema(description = "ID único da notícia", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Ícone da notícia é obrigatório")
    @Schema(description = "URL ou nome do ícone da notícia", example = "https://digitalinnovationone.github.io/santander-dev-week-2023-api/icons/credit.svg", required = true)
    private String icon;

    @NotBlank(message = "Descrição da notícia é obrigatória")
    @Size(min = 10, max = 200, message = "Descrição deve ter entre 10 e 200 caracteres")
    @Schema(description = "Descrição ou título da notícia", example = "Santander tem o melhor cartão de crédito para você!", required = true)
    private String description;

    // Constructors
    public NewsDto() {}

    public NewsDto(String icon, String description) {
        this.icon = icon;
        this.description = description;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
