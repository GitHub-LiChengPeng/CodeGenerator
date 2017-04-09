package core.util;

/**
 * 名字枚举类.
 *
 * @author 李程鹏
 */
public enum NameUtils {
    // 项目名
    PROJECT("Project"),
    // SpringMVC配置文件名
    SPRING_MVC_CONFIG("applicationContext-controller.xml"),
    // Spring配置文件名
    SPRING_CONFIG("applicationContext-service.xml"),
    // Hibernate配置文件名
    HIBERNATE_CONFIG("applicationContext-dao.xml"),
    // web.xml文件名
    WEB_XML("web.xml"),
    // pom.xml文件名
    POM_XML("pom.xml");

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
    NameUtils(String value) {
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