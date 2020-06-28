package com.shaobing.runner.Tools;

public class VideoDetailInfo implements IVideoInfo {

    public String title;
    public String videoPath;

    @Override
    public String getVideoTitle() {
        return title;
    }

    @Override
    public String getVideoPath() {
        return videoPath;
    }
}
