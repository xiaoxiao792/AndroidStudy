package com.shaobing.miho.DB;
/**
 * @className : DBConstant
 * @description : 我的数据库常量
 * @date : 2020/5/11 17:01
 * @author : 邵文炳
 */
public class DBConstant {

    public static final int DB_VERSION = 1;// 数据库版本号
    public static final String DB_NAME = "miho.db";

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

    /**
     * @className : PlanConstant
     * @description : plan表常量
     * @date : 2020/5/25 14:18
     * @author : 邵文炳
     */
    public static class PlanConstant{
        public static final String PLAN_TABLE_NAME = "plantable";
        public static final String PLAN_ID = "planid";
        public static final String USER_ID = "userid";
        public static final String PLAN_NAME = "planname";
        public static final String ICON = "icon";
        public static final String COLOR = "color";
        public static final String TIME_CLASS = "timeclass";
        public static final String BEGIN_DATE = "begindate";
        public static final String END_DATE = "enddate";
        public static final String STATE = "state";
        public static final String FORCE_CLASS = "forceclass";
        public static final String DURATION = "duration";
        public static final String CLOCK_IN_NUM = "clockinnum";
        public static final String PLAN_NUM = "plannum";
    }

    /**
     * @className : RecordConstant
     * @description : record表常量
     * @date : 2020/5/25 14:19
     * @author : 邵文炳
     */
    public static class RecordConstant{
        public static final String RECORD_TABLE_NAME = "record";
        public static final String RECORD_ID = "recordid";
        public static final String PLAN_ID = "planid";
        public static final String USER_ID = "userid";
        public static final String DATE = "date";
        public static final String IS_CLOCK_IN = "isclockin";
    }

    /**
     * @className : DayStatisticsConstant
     * @description : daystatistics表常量
     * @date : 2020/5/25 14:19
     * @author : 邵文炳
     */
    public static class DayStatisticsConstant{
        public static final String DAY_STATISTICS_TABLE_NAME = "daystatistics";
        public static final String DAY_STATISTICS_ID = "daystatisticsid";
        public static final String USER_ID = "userid";
        public static final String DATE = "date";
        public static final String PLAN_NUM = "plannum";
        public static final String CLOCK_IN_NUM = "clockinnum";
        public static final String CLOCK_OUT_NUM = "clockoutnum";
        public static final String DURATION = "duration";
    }

    /**
     * @className : MonthStatisticsConstant
     * @description : MonthStatistics表常量
     * @date : 2020/5/25 14:20
     * @author : 邵文炳
     */
    public static class MonthStatisticsConstant{
        public static final String MONTH_STATISTICS_TABLE_NAME = "monthstatistics";
        public static final String MONTH_STATISTICS_ID = "monthstatisticsid";
        public static final String USER_ID = "userid";
        public static final String MONTH = "month";
        public static final String PLAN_NUM = "plannum";
        public static final String CLOCK_IN_NUM = "clockinnum";
        public static final String CLOCK_OUT_NUM = "clockoutnum";
        public static final String DURATION = "duration";
    }

    /**
     * @className : ShortCutConstant
     * @description : ShortCut表常量
     * @date : 2020/5/25 14:20
     * @author : 邵文炳
     */
    public static class ShortCutConstant{
        public static final String SHORT_CUT_TABLE_NAME = "shortcut";
        public static final String POSITION_ID = "positionid";
        public static final String ICON = "icon";
        public static final String COLOR = "color";
    }

}
