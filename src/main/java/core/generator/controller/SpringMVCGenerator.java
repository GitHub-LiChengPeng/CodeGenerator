package core.generator.controller;

import core.file.java.Class;
import core.database.Table;
import core.util.FileUtils;
import core.util.PathUtils;
import core.util.StringUtils;

import java.util.List;

/**
 * SpringMVC控制层代码生成器.
 *
 * @author 李程鹏
 */
public class SpringMVCGenerator {
    /**
     * 表格集合
     */
    private List<Table> tables;

    /**
     * <strong>Description:</strong>
     * <pre>
     * 构造实例化生成器.
     * </pre>
     *
     * @param tables 表格集合
     */
    public SpringMVCGenerator(List<Table> tables) {
        // 赋值
        this.tables = tables;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 生成代码.
     * </pre>
     */
    public void generate() throws Exception {
        // 遍历表格集合
        for (Table table : tables) {
            // 获取生成的类
            Class controller = new ControllerGenerator(table).generate();
            // 根据类的内容生成文件
            FileUtils.generateFile(PathUtils.CONTROLLER.getValue(), StringUtils.getJavaFileName(controller), controller.toString(0));
        }
    }
}