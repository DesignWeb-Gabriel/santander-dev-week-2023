package me.dio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuração da documentação OpenAPI/Swagger
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Santander Dev Week 2023 - API Bancária")
                        .description("API RESTful completa para sistema bancário digital desenvolvida com Spring Boot 3 e Java 17. " +
                                "Inclui operações CRUD completas, transferências bancárias, validações robustas e tratamento de exceções profissional.")
                        .version("2.0.0")
                        .contact(new Contact()
                                .name("Equipe de Desenvolvimento")
                                .email("dev@santander.com.br")
                                .url("https://github.com/DesignWeb-Gabriel/santander-dev-week-2023"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor de Desenvolvimento"),
                        new Server()
                                .url("https://santander-dev-week-2023-api.railway.app")
                                .description("Servidor de Produção (Railway)")
                ));
    }
}
