package com.shaobing.miho.Bean;

import java.io.Serializable;

public class ShortCutBean implements Serializable {

    private String positionId;
    private String icon;
    private String color;

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ShortCutBean() {
    }

    public ShortCutBean(String positionId, String icon, String color) {
        this.positionId = positionId;
        this.icon = icon;
        this.color = color;
    }
}
