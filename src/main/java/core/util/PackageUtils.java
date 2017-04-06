package core.util;

/**
 * 包名枚举类.
 *
 * @author 李程鹏
 */
public enum PackageUtils {
    // controller类所在的包
    CONTROLLER("core.controller."),
    // service接口所在的包
    SERVICE("core.service."),
    // service接口实现类所在的包
    SERVICE_IMPL("core.service.impl."),
    // dao接口所在的包
    DAO("core.dao."),
    // dao接口实现类所在的包
    DAO_IMPL("core.dao.impl."),
    // 实体类所在的包
    ENTITY("core.entity.");

    /**
     * 枚举值
     */
    private String value;

    /**
     * <strong>Description:</strong>
     * <pre>
     * 构造初始化枚举对象.
     * </pre>
     *
     * @param value 枚举值
     */
    PackageUtils(String value) {
        // 赋值
        this.value = value;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 获取枚举值
     * </pre>
     *
     * @return {@code java.lang.String} - 枚举值
     */
    public String getValue() {
        // 返回枚举值
        return value;
    }
}