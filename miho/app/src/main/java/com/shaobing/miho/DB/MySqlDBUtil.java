package com.shaobing.miho.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @className : MySqlDBUtil
 * @description :
 * @date : 2020/5/11 15:31
 * @author : 邵文炳
 */
public class MySqlDBUtil {

    private static String driver="com.mysql.jdbc.Driver";
    private static String url="jdbc:mysql://192.168.0.108:3306/miho?useSSL=false";
    private static String user="root";
    private static String password="123456";

    /**
     * @methodName : getConn
     * @description : 创建数据库链接
     * @param : 无
     * @return : Connection conn
     * @date : 2020/5/11 15:55
     * @author : 邵文炳
     */
    public static Connection getConn(){
        Connection conn;
        try{
            //加载连接驱动
            Class.forName(driver);
            //连接mysql数据库
            conn = DriverManager.getConnection(url,user,password);
            System.out.println("数据库连接成功");
            return conn;
        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        System.out.println("数据库连接失败");
        return null;
    }

    /**
     * @methodName : closeConn
     * @description : 关闭数据库链接
     * @param : Connection conn, Statement st, ResultSet rs
     * @return : 无
     * @date : 2020/5/11 15:56
     * @author : 邵文炳
     */
    public static void closeConn(Connection conn, Statement st, ResultSet rs){
        if(rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(st != null) {
            try {
                st.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("数据库关闭成功");
    }
}
