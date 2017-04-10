package core.generator;

import core.util.FileUtils;
import core.util.NameUtils;
import core.util.PathUtils;

/**
 * SwaggerUI文件生成器.
 *
 * @author 李程鹏
 */
public class SwaggerUIGenerator {
    /**
     * <strong>Description:</strong>
     * <pre>
     * 生成SwaggerUI.实际上就是将本工程下准备好的UI文件拷贝到新生成的工程下.
     * </pre>
     */
    public void generate() {
        // 获取本工程下准备好的UI文件路径
        String from = PathUtils.PROJECT.getValue() + PathUtils.SWAGGER_UI_FROM.getValue();
        // 获取新生成工程的项目路径
        String projectPath = "\\" + NameUtils.PROJECT.getValue();
        // 获取UI文件在新工程下的目的路径
        String to = PathUtils.DESKTOP.getValue() + projectPath + PathUtils.SWAGGER_UI_TO.getValue();
        // 将所有UI文件拷贝过去
        FileUtils.copyDirectory(from, to);
    }
}