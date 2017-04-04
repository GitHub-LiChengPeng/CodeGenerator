package core.database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库连接工厂.
 *
 * @author 李程鹏
 */
public class ConnectionFactory {
    /**
     * 工厂实例
     */
    private static ConnectionFactory instance = new ConnectionFactory();

    /**
     * <strong>Description:</strong>
     * <pre>
     * 获取工厂实例.
     * </pre>
     *
     * @return {@code core.database.ConnectionFactory} - 工厂实例
     */
    public static ConnectionFactory getInstance() {
        // 返回工厂实例
        return instance;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 获取连接驱动.
     * </pre>
     *
     * @return {@code java.sql.Driver} - 连接驱动
     */
    private Driver getDriver() {
        // 驱动名
        String driverClass = "com.mysql.jdbc.Driver";
        // 定义驱动对象
        Driver driver;
        try {
            // 加载驱动
            Class<?> clazz = Class.forName(driverClass);
            // 创建驱动对象
            driver = (Driver) clazz.newInstance();
        } catch (Exception e) {
            // 如果创建失败就抛出异常
            throw new RuntimeException("加载数据库驱动失败!");
        }
        // 返回连接驱动
        return driver;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 根据数据库信息获取连接对象.
     * </pre>
     *
     * @param username 用户名
     * @param password 密码
     * @param database 数据库
     * @return {@code java.sql.Connection} - 连接对象
     */
    public Connection getConnection(String username, String password, String database) throws SQLException {
        // 获取连接驱动
        Driver driver = getDriver();
        // 定义初始化属性对象
        Properties properties = new Properties();
        // 设置用户名属性
        properties.setProperty("user", username);
        // 设置密码属性
        properties.setProperty("password", password);
        // 创建连接数据库的URL
        String url = "jdbc:mysql://localhost:3306/" + database;
        // 创建连接对象
        Connection connection = driver.connect(url, properties);
        // 如果创建失败
        if (connection == null) {
            // 抛出异常
            throw new SQLException("连接数据库失败!");
        }
        // 返回连接对象
        return connection;
    }
}
