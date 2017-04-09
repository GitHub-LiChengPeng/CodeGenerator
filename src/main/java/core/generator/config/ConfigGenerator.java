package core.generator.config;

/**
 * 配置文件生成器.
 *
 * @author 李程鹏
 */
public class ConfigGenerator {
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
    public ConfigGenerator(String database, String username, String password) {
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
     * 生成配置文件.
     * </pre>
     */
    public void generate() throws Exception {
        // 生成SpringMVC配置文件
        new SpringMVCConfigGenerator().generate();
        // 生成Spring配置文件
        new SpringConfigGenerator().generate();
        // 生成Hibernate配置文件
        new HibernateConfigGenerator(database, username, password).generate();
        // 生成web.xml
        new WebXmlGenerator().generate();
        // 生成pom.xml
        new PomGenerator().generate();
    }
}