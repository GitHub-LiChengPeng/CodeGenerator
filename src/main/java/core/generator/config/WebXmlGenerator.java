package core.generator.config;

import core.util.FreeMarkerUtils;
import core.util.NameUtils;
import core.util.PathUtils;

/**
 * web.xml生成器.
 *
 * @author 李程鹏
 */
public class WebXmlGenerator extends AbstractConfigGenerator {
    /**
     * <strong>Description:</strong>
     * <pre>
     * 为数据映射对象放入数据.
     * </pre>
     */
    protected void putData() {
        // 放入SpringMVC配置文件的文件名
        data.put("springMVCConfig", NameUtils.SPRING_MVC_CONFIG.getValue());
        // 放入Spring配置文件的文件名
        data.put("springConfig", NameUtils.SPRING_CONFIG.getValue());
        // 放入Hibernate配置文件的文件名
        data.put("hibernateConfig", NameUtils.HIBERNATE_CONFIG.getValue());
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
        // 获取配置文件的文件名
        String fileName = NameUtils.WEB_XML.getValue();
        // 获取配置文件生成的相对路径
        String relativePath = PathUtils.WEB_XML.getValue();
        // 使用模板引擎生成配置文件
        FreeMarkerUtils.generateFile(fileName, relativePath, data);
    }
}