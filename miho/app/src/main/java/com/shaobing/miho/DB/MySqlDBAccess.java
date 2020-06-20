package com.shaobing.miho.DB;

import com.shaobing.miho.Bean.UserBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @className : MySqlDBAccess
 * @description : 提供静态可用的数据库操作接口
 * @date : 2020/5/11 18:23
 * @author : 邵文炳
 */
public class MySqlDBAccess {

    /**
     * @methodName : insert
     * @description : 插入User
     * @param : UserBean userBean
     * @return : 0表示失败
     * @date : 2020/5/11 18:24
     * @author : 邵文炳
     */
    public static int insert(UserBean userBean) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = MySqlDBUtil.getConn();
            String sql = "insert into user values(?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, userBean.getUserId());
            ps.setString(2, userBean.getEmail());
            ps.setString(3, userBean.getPassword());
            ps.setString(4, userBean.getRegisterTime());
            ps.setInt(5, userBean.getUserLevel());
            ps.setString(6, userBean.getPhone());
            ps.setString(7, null);
            int row = ps.executeUpdate();
            return row;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            MySqlDBUtil.closeConn(conn, ps, null);
        }
        return 0;
    }

    /**
     * @methodName : update
     * @description : 修改User密码
     * @param : UserBean userBean
     * @return : 0表示失败
     * @date : 2020/5/11 18:26
     * @author : 邵文炳
     */
    public static int update(UserBean userBean) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = MySqlDBUtil.getConn();
            String sql = "update user set password=? where userid=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, userBean.getPassword());
            ps.setString(2, userBean.getUserId());
            int row = ps.executeUpdate();
            return row;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            MySqlDBUtil.closeConn(conn, ps, null);
        }
        return 0;
    }

    /**
     * @methodName : queryUser
     * @description : 查询用户信息
     * @param : String userName（userid/email）
     * @return : UserBean对象
     * @date : 2020/5/11 18:27
     * @author : 邵文炳
     */
    public static UserBean queryUser(String userName) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = MySqlDBUtil.getConn();
            String sql = "select * from user where userid=? or email=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, userName);
            rs = ps.executeQuery();
            if(rs.next()) {
                UserBean userBean = new UserBean();
                userBean.setUserId(rs.getString("userid"));
                userBean.setEmail(rs.getString("email"));
                userBean.setPassword(rs.getString("password"));
                userBean.setRegisterTime(rs.getString("registertime"));
                userBean.setUserLevel(rs.getInt("userlevel"));
                userBean.setPhone(rs.getString("phone"));
                return userBean;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            MySqlDBUtil.closeConn(conn, ps, rs);
        }
        return null;
    }

}
