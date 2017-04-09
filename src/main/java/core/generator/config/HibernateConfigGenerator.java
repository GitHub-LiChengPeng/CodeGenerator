package core.generator.config;

import core.util.*;

/**
 * Hibernate配置文件生成器.
 *
 * @author 李程鹏
 */
public class HibernateConfigGenerator extends AbstractConfigGenerator {
    /**
     * 数据库
     */
    private String database;

    /**
     * 账户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * <strong>Description:</strong>
     * <pre>
     * 构造初始化生成器.
     * </pre>
     *
     * @param database 数据库
     * @param username 账户名
     * @param password 密码
     */
    public HibernateConfigGenerator(String database, String username, String password) {
        // 赋值
        this.database = database;
        // 赋值
        this.username = username;
        // 赋值
        this.password = password;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为数据映射对象放入数据.
     * </pre>
     */
    protected void putData() {
        // 获取实体对象所在的包名
        String entityPackage = StringUtils.removeLastDot(PackageUtils.ENTITY.getValue());
        // 获取Dao层所在的包名
        String daoPackage = StringUtils.removeLastDot(PackageUtils.DAO.getValue());
        // 放入实体对象包名
        data.put("entityPackage", entityPackage);
        // 放入Dao层包名
        data.put("daoPackage", daoPackage);
        // 放入数据库信息
        data.put("database", database);
        // 放入用户名
        data.put("username", username);
        // 放入密码
        data.put("password", password);
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
        // 获取Hibernate配置文件的文件名
        String fileName = NameUtils.HIBERNATE_CONFIG.getValue();
        // 获取Hibernate配置文件生成的相对路径
        String relativePath = PathUtils.HIBERNATE_CONFIG.getValue();
        // 使用模板引擎生成配置文件
        FreeMarkerUtils.generateFile(fileName, relativePath, data);
    }
}