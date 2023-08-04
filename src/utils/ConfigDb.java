package utils;

import java.sql.*;

public class ConfigDb {
    public String password = "123456";//密码
    public String username = "root";//用户名
    public String driver = "com.mysql.cj.jdbc.Driver";
    public String port = "3306";//端口
    public String host = "127.0.0.1";//主机ip，也可以写成localhost
    public String database = "demo2";//数据库名称

    public Connection connection = null;

    public Connection connectionMysql() {

        try {
            Class.forName(this.driver);
            this.connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?characterEncoding=utf-8&serverTimezone=UTC",
                    this.username, this.password);
        } catch (Exception e) {
            System.out.println(e);
        }
        return this.connection;
    }

    // 关闭资源
    public static void close(Connection con, Statement stat, ResultSet rs) throws SQLException {

        if (con != null && !con.isClosed()) {
            con.close();
        }
        if (stat != null && !stat.isClosed()) {
            stat.close();
        }
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
    }
}
