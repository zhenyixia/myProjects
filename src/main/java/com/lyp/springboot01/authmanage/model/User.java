package com.lyp.springboot01.authmanage.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Data 注解来自于lombok，注解在类上为类提供读写属性，另外还提供equals,toString,hashCode方法
 * lombok是什么？从官网上可以看到，lombok是一个项目，用来提供java类库，可以在插件中安装，可以在你编辑或 构建的时候自动为你拼写一些功能代码，如get set 方法等。 安装方法：
 * file -- settings -- plugins -- 搜索lombok lombok常用注解还有： @Getter/@Setter : 注解在类上,
 * 为类提供读写属性； @ToString : 注解在类上, 为类提供 toString() 方法 @Log4j : 注解在类上, 为类提供一个属性名为 log 的 log4j
 * 的日志对象 @Slf4j : 注解在类上, 为类提供一个属性名为 log 的 log4j 的日志对象 @NonNull : 注解在参数上, 如果该类参数为 null , 就会报出异常,
 * throw new NullPointException(参数名)
 *
 * <p>以上注解有包含关系，可根据需要选择使用
 */
@Data
public class User implements Serializable{

  private Integer id;

  @NotEmpty(message = "User name can't be null.")
  @Length(
    min = 4,
    max = 10,
    message = "User name's length is illegal, length must be between in 4 and 10."
  )
  private String name;

  @NotNull(message = "User Password can't be null.")
  @Length(
    min = 6,
    max = 16,
    message = "Password length is illegal, length must be between in 6 and 10."
  )
  private String password;

  private String phone;

  private String role;

  private List<Book> books;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT-8")
  private Date modifyTime;
}
