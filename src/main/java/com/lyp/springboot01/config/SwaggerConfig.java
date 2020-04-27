package com.lyp.springboot01.config;

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
 * 配置swaggerUI
 * 1，导入依赖
 * 2，编写该配置
 * 3，在controller中使用ApiOperation注解
 */

//@Configuration
//@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("lypgourp")
                .apiInfo(apiInfo())
                .select()
                //controller所在路径
                .apis(RequestHandlerSelectors.basePackage("com.lyp.springboot01"))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfoBuilder().title("springboot结合swagger2构建Restful API")
                .description("api接口可视化管理")
                .termsOfServiceUrl("www.baidu.com")
//                .contact("李亚鹏") //在页面上显示创建人
                .version("0.1")
                .build();
        return apiInfo;
    }


}
