package com.shaobing.runner.DB;
/**
 * @className : DBConstant
 * @description : 我的数据库常量
 * @date : 2020/5/11 17:01
 * @author : 邵文炳
 */
public class DBConstant {

    public static final int DB_VERSION = 1;// 数据库版本号
    public static final String DB_NAME = "runner.db";

    /**
     * @className : UserConstant
     * @description : user表常量
     * @date : 2020/5/11 17:02
     * @author : 邵文炳
     */
    public static class UserConstant{
        public static final String USER_TABLE_NAME = "user";
        public static final String USER_ID = "userid";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String REGISTERTIME = "registertime";
        public static final String USERLEVEL = "userlevel";
        public static final String PHONE = "phone";
        public static final String OTHER = "other";
    }
}
