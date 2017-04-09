package core.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 数据库读取器.
 *
 * @author 李程鹏
 */
public class DatabaseReader {
    /**
     * 数据库名
     */
    private final String database;

    /**
     * 数据库连接对象
     */
    private Connection connection;

    /**
     * 数据库元数据
     */
    private DatabaseMetaData databaseMetaData;

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
        // 获取数据库元数据
        databaseMetaData = connection.getMetaData();
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
            // 设置表格注释
            table.setRemark(getTableRemark(tableName));
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
        // 指定查询参数
        String[] types = {"TABLE"};
        // 查询数据库的表格信息,获取查询结果集.
        ResultSet resultSet = databaseMetaData.getTables(null, database, "%", types);
        // 遍历查询结果集
        while (resultSet.next()) {
            // 取出表格名,放入集合中.
            tableNames.add(resultSet.getString("TABLE_NAME"));
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
     * @param tableName 表格名
     * @return {@code java.util.List<core.database.Column>} - 列集合
     */
    private List<Column> readColumns(String tableName) throws Exception {
        // 获取表格中的主键
        Set<String> primaryKeys = getPrimaryKeys(tableName);
        // 根据表格名查询表格中列的信息,获取查询结果集.
        ResultSet resultSet = databaseMetaData.getColumns(null, null, tableName, null);
        // 新建列集合
        List<Column> columns = new ArrayList<>();
        // 遍历查询结果集
        while (resultSet.next()) {
            // 新建列对象
            Column column = new Column();
            // 获取列名
            String columnName = resultSet.getString("COLUMN_NAME");
            // 如果该列是主键列
            if (primaryKeys.contains(columnName)) {
                // 设置列主键标志
                column.setPrimaryKey(true);
            }
            // 设置列名
            column.setName(columnName);
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

    /**
     * <strong>Description:</strong>
     * <pre>
     * 根据表格名获取表格注释.因为使用API获取不到信息,所以只能自己写.
     * </pre>
     *
     * @param tableName 表名
     * @return {@code java.lang.String} - 表格注释
     */
    private String getTableRemark(String tableName) throws Exception {
        // 获取语句执行对象
        Statement statement = connection.createStatement();
        // 查询表格的创建语句,获取查询结果集.
        ResultSet resultSet = statement.executeQuery("show create table " + tableName);
        // 如果查询结果集不为空
        if (resultSet != null && resultSet.next()) {
            // 定义返回结果
            String remark;
            // 取出表格的创建语句
            String createStatement = resultSet.getString(2);
            // 获取表格注释标志的下标位置
            int index = createStatement.indexOf("COMMENT='");
            // 如果表格创建语句中没有指定注释
            if (index < 0) {
                // 返回空串
                return "";
            }
            // 截取出最后的字符串
            remark = createStatement.substring(index + 9);
            // 去掉最后一个引号后就是注释本身了
            remark = remark.substring(0, remark.length() - 1);
            // 返回表格注释
            return remark;
        } else {// 如果查询结果集为空
            // 返回空串
            return "";
        }
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 获取表格中的主键.
     * </pre>
     *
     * @param tableName 表格名
     * @return {@code java.util.Set<java.lang.String>} - 主键集合
     */
    private Set<String> getPrimaryKeys(String tableName) throws Exception {
        // 定义主键集合
        Set<String> primaryKeys = new HashSet<>();
        // 查询主键信息
        ResultSet resultSet = databaseMetaData.getPrimaryKeys(null, null, tableName);
        // 遍历查询结果集
        while (resultSet.next()) {
            // 将主键列的列名放入主键集合中
            primaryKeys.add(resultSet.getString("COLUMN_NAME"));
        }
        // 返回主键集合
        return primaryKeys;
    }
}