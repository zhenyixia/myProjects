package com.lyp.springboot01.common.bean;

import lombok.Data;

/**
 * 统一的响应结果集
 */
@Data
/**
 * @Data 注解来自于lombok，注解在类上为类提供读写属性，另外还提供equals,toString,hashCode方法
 * lombok是什么？从官网上可以看到，lombok是一个项目，用来提供java类库，可以在插件中安装，可以在你编辑或
 * 构建的时候自动为你拼写一些功能代码，如get set 方法等。
 * 安装方法： file -- settings -- plugins -- 搜索lombok
 * lombok常用注解还有： @Getter/@Setter : 注解在类上, 为类提供读写属性； @ToString : 注解在类上, 为类提供 toString() 方法
 * @Log4j : 注解在类上, 为类提供一个属性名为 log 的 log4j 的日志对象
 * @Slf4j : 注解在类上, 为类提供一个属性名为 log 的 log4j 的日志对象
 * @NonNull : 注解在参数上, 如果该类参数为 null , 就会报出异常,  throw new NullPointException(参数名)
 *
 * 以上注解有包含关系，可根据需要选择使用
 */
public class JsonResult {

    public static final boolean SUCCESS = true;

    public static final boolean FAIL = false;

    private boolean status;

    private int code;

    private String msg;

    private Object data;

    public JsonResult(boolean status, int code, String msg, Object data) {
        this.status = status;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static JsonResult success(Object data) {
        return new JsonResult(SUCCESS, 200, "ok", data);
    }

    public static JsonResult success(String msg) {
        return new JsonResult(SUCCESS, 200, msg, null);
    }

    public static JsonResult success(String msg, Object data) {
        return new JsonResult(SUCCESS, 200, msg, data);
    }

    public static JsonResult fail(String msg) {
        return fail(400, msg);
    }

    public static JsonResult fail(int code, String msg) {
        return new JsonResult(FAIL, code, msg, null);
    }
}

