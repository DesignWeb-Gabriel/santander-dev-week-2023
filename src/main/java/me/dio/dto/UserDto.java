package me.dio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Schema(description = "Dados do usuário bancário")
public class UserDto {

    @Schema(description = "ID único do usuário", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Nome do usuário é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    @Schema(description = "Nome completo do usuário", example = "João Silva Santos", required = true)
    private String name;

    @NotNull(message = "Dados da conta são obrigatórios")
    @Valid
    @Schema(description = "Dados da conta bancária do usuário", required = true)
    private AccountDto account;

    @Valid
    @Schema(description = "Dados do cartão do usuário")
    private CardDto card;

    @Valid
    @Schema(description = "Lista de funcionalidades disponíveis para o usuário")
    private List<FeatureDto> features;

    @Valid
    @Schema(description = "Lista de notícias personalizadas para o usuário")
    private List<NewsDto> news;

    // Constructors
    public UserDto() {}

    public UserDto(String name, AccountDto account) {
        this.name = name;
        this.account = account;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccountDto getAccount() {
        return account;
    }

    public void setAccount(AccountDto account) {
        this.account = account;
    }

    public CardDto getCard() {
        return card;
    }

    public void setCard(CardDto card) {
        this.card = card;
    }

    public List<FeatureDto> getFeatures() {
        return features;
    }

    public void setFeatures(List<FeatureDto> features) {
        this.features = features;
    }

    public List<NewsDto> getNews() {
        return news;
    }

    public void setNews(List<NewsDto> news) {
        this.news = news;
    }
}
