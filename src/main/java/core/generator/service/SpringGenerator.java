package core.generator.service;

import core.file.java.Class;
import core.database.Table;
import core.file.java.Interface;
import core.util.FileUtils;
import core.util.PathUtils;
import core.util.StringUtils;

import java.util.List;

/**
 * Spring业务层代码生成器.
 *
 * @author 李程鹏
 */
public class SpringGenerator {
    /**
     * 表格集合
     */
    private List<Table> tables;

    /**
     * <strong>Description:</strong>
     * <pre>
     * 构造初始化生成器.
     * </pre>
     *
     * @param tables 表格对象
     */
    public SpringGenerator(List<Table> tables) {
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
            // 生成Service层接口文件
            generateService(table);
            // 生成Service层接口实现类文件
            generateServiceImpl(table);
        }
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 生成Service层接口文件.
     * </pre>
     *
     * @param table 表格对象
     */
    private void generateService(Table table) throws Exception {
        // 获取生成的Service接口
        Interface service = new ServiceGenerator(table).generate();
        // 根据接口对象内容生成文件
        FileUtils.generateFile(PathUtils.SERVICE.getValue(), StringUtils.getJavaFileName(service), service.toString());
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 生成Service层接口实现类文件.
     * </pre>
     *
     * @param table 表格对象
     */
    private void generateServiceImpl(Table table) throws Exception {
        // 获取生成的Service接口实现类
        Class serviceImpl = new ServiceImplGenerator(table).generate();
        // 根据实现类的内容生成文件
        FileUtils.generateFile(PathUtils.SERVICE_IMPL.getValue(), StringUtils.getJavaFileName(serviceImpl), serviceImpl.toString(0));
    }
}