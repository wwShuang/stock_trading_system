package com.nueda.financial_portfolio.config;

import com.nueda.financial_portfolio.controller.StockController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @projectName: nueda_training
 * @package: com.nueda.financial_portfolio.config
 * @className: SwaggerConfig
 * @author: Team3
 * @description: TODO
 * @date: 2023/8/9 9:41
 * @version: 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Financial Portfolio")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(StockController.class.getPackageName()))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Financial Portfolio")
                .description("Assessment Financial Portfolio Service API")
                .build();
    }
}
