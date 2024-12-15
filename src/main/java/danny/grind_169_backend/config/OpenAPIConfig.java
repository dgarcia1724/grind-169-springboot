package danny.grind_169_backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    
    @Bean
    public OpenAPI myOpenAPI() {
        Contact contact = new Contact();
        contact.setEmail("dannyg17017@gmail.com");
        contact.setName("Daniel Garcia");

        Info info = new Info()
                .title("Grind 169 API")
                .version("1.0")
                .contact(contact)
                .description("API for managing leetcode problems and solutions.");

        return new OpenAPI().info(info);
    }
} 