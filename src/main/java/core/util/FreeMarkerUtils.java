package core.util;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;

/**
 * FreeMarker模板引擎工具类.
 *
 * @author 李程鹏
 */
public class FreeMarkerUtils {
    /**
     * <strong>Description:</strong>
     * <pre>
     * 使用模板引擎生成文件.
     * </pre>
     *
     * @param fileName     需要生成的文件名
     * @param relativePath 文件生成的相对路径
     * @param data         数据参数
     */
    public static void generateFile(String fileName, String relativePath, Map<String, Object> data) throws Exception {
        // 新建配置对象
        Configuration configuration = new Configuration();
        // 获取模板文件的绝对路径
        String templatePath = PathUtils.PROJECT.getValue() + PathUtils.FTL_TEMPLATE.getValue();
        // 设置读取模板文件的路径
        configuration.setDirectoryForTemplateLoading(new File(templatePath));
        // 读取模板文件并生成模板对象
        Template template = configuration.getTemplate(fileName + ".ftl");
        // 获取工程的相对路径
        String projectPath = "\\" + NameUtils.PROJECT.getValue();
        // 拼接出输出文件的绝对路径
        String filePath = PathUtils.DESKTOP.getValue() + projectPath + relativePath;
        // 定义文件路径对象
        File directory = new File(filePath);
        // 如果输出路径不存在
        if (!directory.exists()) {
            // 创建路径
            directory.mkdirs();
        }
        // 获取文件写入器
        FileWriter fileWriter = new FileWriter(new File(filePath + fileName));
        // 根据数据参数生成文件
        template.process(data, fileWriter);
    }
}