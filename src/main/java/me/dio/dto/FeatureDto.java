package me.dio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Funcionalidade disponível no aplicativo bancário")
public class FeatureDto {

    @Schema(description = "ID único da funcionalidade", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Ícone da funcionalidade é obrigatório")
    @Schema(description = "URL ou nome do ícone da funcionalidade", example = "https://digitalinnovationone.github.io/santander-dev-week-2023-api/icons/pix.svg", required = true)
    private String icon;

    @NotBlank(message = "Descrição da funcionalidade é obrigatória")
    @Size(min = 3, max = 100, message = "Descrição deve ter entre 3 e 100 caracteres")
    @Schema(description = "Descrição da funcionalidade", example = "PIX", required = true)
    private String description;

    // Constructors
    public FeatureDto() {}

    public FeatureDto(String icon, String description) {
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
