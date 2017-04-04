package core.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库读取器.
 *
 * @author 李程鹏
 */
public class DatabaseReader {
    /**
     * 数据库连接对象
     */
    private Connection connection;

    /**
     * 数据库名
     */
    private String database;

    /**
     * <strong>Description:</strong>
     * <pre>
     * 构造实例化数据库读取器.
     * </pre>
     *
     * @param username 用户名
     * @param password 密码
     * @param database 数据库
     */
    public DatabaseReader(String username, String password, String database) throws Exception {
        // 将数据库名赋值给属性
        this.database = database;
        // 获取数据库连接
        connection = ConnectionFactory.getInstance().getConnection(username, password, database);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 读取数据库表格信息.
     * </pre>
     *
     * @return {@code java.util.List<core.database.Table>} - 表格信息
     */
    public List<Table> readTables() throws Exception {
        // 获取数据库中所有的表格名
        List<String> tableNames = readTableNames();
        // 定义并初始化表格对象集合
        List<Table> tables = new ArrayList<>();
        // 遍历表格名集合
        for (String tableName : tableNames) {
            // 新建表格对象
            Table table = new Table();
            // 设置表格名
            table.setName(tableName);
            // 设置列
            table.setColumns(readColumns(tableName));
            // 将表格对象添加到集合中
            tables.add(table);
        }
        // 返回表格对象集合
        return tables;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 读取数据库中表格的名字.
     * </pre>
     *
     * @return {@code java.util.List<java.lang.String>} - 表格名集合
     */
    private List<String> readTableNames() throws Exception {
        // 新建表格名集合
        List<String> tableNames = new ArrayList<>();
        // 获取数据库元信息对象
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        // 指定查询参数
        String[] types = {"TABLE"};
        // 查询数据库的表格信息,获取查询结果集.
        ResultSet resultSet = databaseMetaData.getTables(null, database, "%", types);
        // 遍历查询结果集
        while (resultSet.next()) {
            // 取出表格名,放入集合中.
            tableNames.add(resultSet.getString(3));
        }
        // 关闭查询结果集
        resultSet.close();
        // 返回表格名集合
        return tableNames;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 通过表格名读取表格列信息.
     * </pre>
     *
     * @param tableName
     * @return {@code java.util.List<core.database.Column>} - 列集合
     */
    private List<Column> readColumns(String tableName) throws Exception {
        // 获取数据库元信息对象
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        // 根据表格名查询表格中列的信息,获取查询结果集.
        ResultSet resultSet = databaseMetaData.getColumns(null, null, tableName, null);
        // 新建列集合
        List<Column> columns = new ArrayList<>();
        // 遍历查询结果集
        while (resultSet.next()) {
            // 新建列对象
            Column column = new Column();
            // 设置列名
            column.setName(resultSet.getString("COLUMN_NAME"));
            // 设置列的类型
            column.setType(resultSet.getInt("DATA_TYPE"));
            // 设置列的注释
            column.setRemark(resultSet.getString("REMARKS"));
            // 将列对象放入集合中
            columns.add(column);
        }
        // 关闭查询结果集
        resultSet.close();
        // 返回列集合
        return columns;
    }
}
