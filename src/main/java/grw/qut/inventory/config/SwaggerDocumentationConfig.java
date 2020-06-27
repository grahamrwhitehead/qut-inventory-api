package grw.qut.inventory.config;

import org.joda.time.DateTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.annotation.Generated;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static springfox.documentation.builders.PathSelectors.regex;

@Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2020-06-26T20:48:51.890+10:00")
@Configuration
public class SwaggerDocumentationConfig {

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Simple Inventory API")
            .description("This test is designed to demonstrate real world problem solving skills. The task is to build a simple Spring Boot application and submit it for review via a git repository.  It should include: - Spring MVC REST controller with mock mvc test - JPA mapping using H2 embedded database - maven build - unit tests  Optional - Implement in Groovy - Secured via Spring Security HTTP Basic - JSR-303 Bean Validation ")
            .license("Apache 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .termsOfServiceUrl("")
            .version("1.0.0")
            .contact(new Contact("", "", ""))
            .build();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("grw.qut.inventory.api"))
            .paths(PathSelectors.any())
            .build()
            .directModelSubstitute(LocalDate.class, java.sql.Date.class)
            .directModelSubstitute(LocalDateTime.class, java.util.Date.class)
            .apiInfo(apiInfo());
    }
}
