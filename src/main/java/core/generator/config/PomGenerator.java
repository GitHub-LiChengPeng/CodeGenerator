package core.generator.config;

import core.util.*;

/**
 * pom.xml生成器.
 *
 * @author 李程鹏
 */
public class PomGenerator extends AbstractConfigGenerator {
    /**
     * <strong>Description:</strong>
     * <pre>
     * 为数据映射对象放入数据.
     * </pre>
     */
    protected void putData() {
        // 放入项目名
        data.put("projectName", NameUtils.PROJECT.getValue());
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
        // 获取配置文件名
        String fileName = NameUtils.POM_XML.getValue();
        // 使用模板引擎生成配置文件
        FreeMarkerUtils.generateFile(fileName, "\\", data);
    }
}