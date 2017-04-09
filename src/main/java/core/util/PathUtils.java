package core.util;

import javax.swing.filechooser.FileSystemView;

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
    ENTITY("\\src\\main\\java\\core\\entity\\"),
    // 模板文件存放的相对路径
    FTL_TEMPLATE("\\src\\main\\resources\\ftl\\"),
    // 本机桌面的绝对路径
    DESKTOP(FileSystemView.getFileSystemView().getHomeDirectory().getPath()),
    // 本工程文件夹所在的绝对路径
    PROJECT(System.getProperty("user.dir")),
    // web.xml生成的相对路径
    WEB_XML("\\web\\WEB-INF\\"),
    // SpringMVC配置文件生成的相对路径
    SPRING_MVC_CONFIG("\\src\\main\\resources\\"),
    // Spring配置文件生成的相对路径
    SPRING_CONFIG("\\src\\main\\resources\\"),
    // Hibernate配置文件生成的相对路径
    HIBERNATE_CONFIG("\\src\\main\\resources\\");

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
