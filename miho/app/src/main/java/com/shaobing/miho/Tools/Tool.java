package com.shaobing.miho.Tools;

import android.app.Activity;

/**
 * @className : Tool
 * @description : 个人的tool集合
 * @date : 2020/5/11 16:22
 * @author : 邵文炳
 */
public  class Tool {

    /**
     * @methodName : steep
     * @description : 设置沉浸式状态栏
     * @param : Activity activity, boolean unPhotoSteep = false 图片沉浸 | true 颜色沉浸,
     *          boolean dark = false 深色主题，即字体颜色为白 | true 浅色主题，即字体颜色为灰
     * @return : 无
     * @date : 2020/5/11 16:18
     * @author : 邵文炳
     */
    public static void steep(Activity activity, boolean unPhotoSteep,boolean dark){

        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        //如果设置图片，视频等沉浸时，则需要设置成false，不预留出状态栏高度的 padding，因为这个高度由我们的view组件提供
        StatusBarUtil.setRootViewFitsSystemWindows(activity, unPhotoSteep);

        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(activity);

        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        //当然如果你的手机有主题切换，由浅色主题到深色主题，将dark设置为false即可
        //一般来说StatusBarUtil.setStatusBarDarkTheme(this, true)可以正常使用，直接使用
        //StatusBarUtil.setStatusBarDarkTheme(this, true);即可
        //当然如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
        //这样半透明+白=灰, 状态栏的文字能看得清
        //if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
        //    StatusBarUtil.setStatusBarColor(this, 0x55000000);
        //}
        StatusBarUtil.setStatusBarDarkTheme(activity, dark);
    }
}
