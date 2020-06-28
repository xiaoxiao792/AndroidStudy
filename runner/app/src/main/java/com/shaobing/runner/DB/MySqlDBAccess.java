package com.shaobing.runner.DB;

import com.shaobing.runner.Bean.CourseBean;
import com.shaobing.runner.Bean.StepBean;
import com.shaobing.runner.Bean.UserBean;
import com.shaobing.runner.Bean.UserSubBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @className : MySqlDBAccess
 * @description : 提供静态可用的数据库操作接口
 * @date : 2020/5/11 18:23
 * @author : 邵文炳
 */
public class MySqlDBAccess {

    /**
     * @methodName : insert
     * @description : 插入User，注册用户（此处未设置用户信息）
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
            String sql = "insert into user values(?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, userBean.getUserId());
            ps.setString(2, userBean.getUserPassword());
            ps.setString(3, null);
            ps.setString(4, null);
            ps.setString(5, null);
            ps.setString(6, null);
            ps.setString(7, null);
            ps.setString(8, null);
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
     * @param : String userName（userid）手机号
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
            String sql = "select * from user where userid=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            if(rs.next()) {
                UserBean userBean = new UserBean();
//            private String userId;
//            private String userPassword;
//            private String userName;
//            private String userImg;
//            private double userHeight;
//            private double userWeight;
//            private int userAge;
//            private int userSex;
                userBean.setUserId(rs.getString("userid"));
                userBean.setUserPassword(rs.getString("userpassword"));
                userBean.setUserName(rs.getString("username"));
                userBean.setUserImg(rs.getString("userimg"));
                userBean.setUserHeight(rs.getDouble("userheight"));
                userBean.setUserWeight(rs.getDouble("userweight"));
                userBean.setUserAge(rs.getInt("userage"));
                userBean.setUserSex(rs.getInt("usersex"));
                return userBean;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            MySqlDBUtil.closeConn(conn, ps, rs);
        }
        return null;
    }

    /**
     * @methodName : insert
     * @description : 插入step，用于步数排行榜
     * @param : StepBean stepBean
     * @return : 0表示失败
     * @date : 2020/6/26 18:25
     * @author : 邵文炳
     */
    public static int insert(StepBean stepBean) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = MySqlDBUtil.getConn();
            String sql = "insert into step values(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, stepBean.getStepId());
            ps.setString(2, stepBean.getUserId());
            ps.setInt(3, stepBean.getStepNum());
            ps.setString(4, stepBean.getStepDate());
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
     * @methodName : queryStep
     * @description : 查询步数排行榜
     * @param : String date日期
     * @return : List<StepBean>步数实体类列表
     * @date : 2020/6/27 10:34
     * @author : 邵文炳
     */
    public static List<StepBean> queryStep(String date) {
        List<StepBean> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = MySqlDBUtil.getConn();
            String sql = "select * from step where stepdate=? order by stepnum desc";
            ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            rs = ps.executeQuery();
            while (rs.next()) {
                StepBean stepBean = new StepBean(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4));
                list.add(stepBean);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            MySqlDBUtil.closeConn(conn, ps, rs);
        }
        return null;
    }

    /**
     * @methodName : updateStep
     * @description : 更新步数
     * @param : StepBean stepBean
     * @date : 2020/6/27 10:57
     * @author : 邵文炳
     */
    public static int updateStep(StepBean stepBean){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = MySqlDBUtil.getConn();
            String sql = "update step set stepnum=? where userid=? and stepdate=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, stepBean.getStepNum());
            ps.setString(2, stepBean.getUserId());
            ps.setString(3, stepBean.getStepDate());
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
     * @methodName : queryCourse
     * @description : 查询所以课程
     * @date : 2020/6/27 17:23
     * @author : 邵文炳
     */
    public static List<CourseBean> queryCourse() {
        List<CourseBean> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = MySqlDBUtil.getConn();
            String sql = "SELECT course.*,coursesub.coursesubnum FROM course INNER JOIN coursesub ON course.courseid = coursesub.courseid";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                CourseBean courseBean = new CourseBean(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getInt(5),
                        rs.getString(6),rs.getInt(7));
                list.add(courseBean);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            MySqlDBUtil.closeConn(conn, ps, rs);
        }
        return null;
    }

    /**
     * @methodName : queryUserSub
     * @description : 查询客户订阅课程
     * @date : 2020/6/27 17:24
     * @author : 邵文炳
     */
    public static List<CourseBean> queryUserSub(String userId) {
        List<CourseBean> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = MySqlDBUtil.getConn();
            String sql = "SELECT course.*,coursesub.coursesubnum FROM course INNER JOIN coursesub ON course.courseid = coursesub.courseid INNER JOIN usersub ON course.courseid = usersub.courseid WHERE usersub.userid = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                CourseBean courseBean = new CourseBean(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getInt(5),
                        rs.getString(6),rs.getInt(7));
                list.add(courseBean);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            MySqlDBUtil.closeConn(conn, ps, rs);
        }
        return null;
    }

    /**
     * @methodName : insert
     * @description : 添加usersub即订阅课程
     * @date : 2020/6/27 19:17
     * @author : 邵文炳
     */
    public static int insert(UserSubBean userSubBean) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = MySqlDBUtil.getConn();
            String sql = "insert into usersub values(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, userSubBean.getUserSubId());
            ps.setString(2, userSubBean.getUserId());
            ps.setString(3, userSubBean.getCourseId());
            int row = ps.executeUpdate();
            return row;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            MySqlDBUtil.closeConn(conn, ps, null);
        }
        return 0;
    }
}
