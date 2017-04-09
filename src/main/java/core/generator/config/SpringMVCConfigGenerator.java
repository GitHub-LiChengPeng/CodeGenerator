package core.generator.config;

import core.util.*;

/**
 * SpringMVC配置文件生成器.
 *
 * @author 李程鹏
 */
public class SpringMVCConfigGenerator extends AbstractConfigGenerator {
    /**
     * <strong>Description:</strong>
     * <pre>
     * 为数据映射对象放入数据.
     * </pre>
     */
    protected void putData() {
        // 获取Controller层所在的包名
        String controllerPackage = StringUtils.removeLastDot(PackageUtils.CONTROLLER.getValue());
        // 放入Controller层的包名
        data.put("controllerPackage", controllerPackage);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 生成配置文件.
     * </pre>
     */
    protected void generate() throws Exception {
        // 为数据映射对象放入数据
        putData();
        // 获取SpringMVC配置文件的文件名
        String fileName = NameUtils.SPRING_MVC_CONFIG.getValue();
        // 获取SpringMVC配置文件生成的相对路径
        String relativePath = PathUtils.SPRING_MVC_CONFIG.getValue();
        // 使用模板引擎生成配置文件
        FreeMarkerUtils.generateFile(fileName, relativePath, data);
    }
}