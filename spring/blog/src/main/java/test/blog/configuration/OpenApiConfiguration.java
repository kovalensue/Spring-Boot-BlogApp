package test.blog.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI springBlogOpenAPI() {
        return new OpenAPI()
            .info(new Info().title("SpringBootBlogApp API")
                .description("Sample API documentation for my Spring Boot Blog App experiment")
                .version("v0.0.1")
                .license(new License().name("MIT").url("https://choosealicense.com/licenses/mit")));
    }
}
