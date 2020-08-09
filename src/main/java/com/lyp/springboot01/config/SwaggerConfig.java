package com.lyp.springboot01.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 配置swaggerUI 1，导入依赖 2，编写该配置 3，在controller中使用ApiOperation注解
 * 2020/07/05 修改为knife4j
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

  @Bean(value = "defaultApi2")
  public Docket defaultApi2() {
    Docket docket = new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        //分组名称
        .groupName("2.X版本")
        .select()
        //这里指定Controller扫描包路径(项目路径也行)
        .apis(RequestHandlerSelectors.basePackage("com.lyp.springboot01"))
        .paths(PathSelectors.any())
        .build();
    return docket;
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("不重要")
        .description("测试名称不重要")
        .termsOfServiceUrl("http://localhost:8888/")
        .contact("10086@mail.com")
        .version("1.0")
        .build();
  }
}
