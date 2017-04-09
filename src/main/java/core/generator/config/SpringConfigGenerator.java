package core.generator.config;

import core.util.*;

/**
 * Spring配置文件生成器.
 *
 * @author 李程鹏
 */
public class SpringConfigGenerator extends AbstractConfigGenerator {
    /**
     * <strong>Description:</strong>
     * <pre>
     * 为数据映射对象放入数据.
     * </pre>
     */
    protected void putData() {
        // 获取Service层所在的包名
        String servicePackage = StringUtils.removeLastDot(PackageUtils.SERVICE.getValue());
        // 放入Service层包名
        data.put("servicePackage", servicePackage);
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
        // 获取Spring配置文件的文件名
        String fileName = NameUtils.SPRING_CONFIG.getValue();
        // 获取Spring配置文件生成的相对路径
        String relativePath = PathUtils.SPRING_CONFIG.getValue();
        // 使用模板引擎生成配置文件
        FreeMarkerUtils.generateFile(fileName, relativePath, data);
    }
}