package ru.vityazev.voting.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//https://sabljakovich.medium.com/adding-basic-auth-authorization-option-to-openapi-swagger-documentation-java-spring-95abbede27e9
@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
@OpenAPIDefinition(
        info = @Info(
                title = "REST API documentation",
                version = "1.0",
                description = """
                        Приложение для голосования за рестораны <br>
                        <br>                        
                        Сценарий:<br>
                                                
                        0. Администратор добавляет меню для ресторанов на завтра //TODO in development <br>
                        1. Пользователь смотрит на имеющиеся меню у ресторанов на сегодня <a href="#/dish-restaurant-controller/getAll_3">GET /api/admin/dish</a> <br>
                        2. Пользователь голосует за понравившийся ресторан <a href="#/user-voting-controller/voteForRestaurant">POST /api/users/vote/{id}</a> <br>
                        <br>
                        <p><b>Тестовые креденшелы:</b><br>
                        - user@yandex.ru / password<br>
                        - admin@gmail.com / admin<br>
                        - guest@gmail.com / guest</p>
                        """
        ),
        security = @SecurityRequirement(name = "basicAuth")
)
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("REST API")
                .pathsToMatch("/api/**")
                .build();
    }
}
