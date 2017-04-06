package core.util;

/**
 * 路径枚举类.
 *
 * @author 李程鹏
 */
public enum PathUtils {
    // controller类生成的相对路径
    CONTROLLER("\\src\\main\\java\\core\\controller\\"),
    // service接口生成的相对路径
    SERVICE("\\src\\main\\java\\core\\service\\"),
    // service接口实现类生成的相对路径
    SERVICE_IMPL("\\src\\main\\java\\core\\service\\impl\\"),
    // dao接口生成的相对路径
    DAO("\\src\\main\\java\\core\\dao\\"),
    // dao接口实现类生成的相对路径
    DAO_IMPL("\\src\\main\\java\\core\\dao\\impl\\"),
    // 实体类生成的相对路径
    ENTITY("\\src\\main\\java\\core\\entity\\");

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
    PathUtils(String value) {
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
