package com.embed.detection.model;


import java.io.Serializable;

public class Box implements Serializable {
    private String type;
    private Float possible;
    private Float x;
    private Float y;
    private Float width;
    private Float height;

    public Box() {}

    public Box(String type, Float possible, Float x, Float y, Float width, Float height) {
        this.type = type;
        this.possible = possible;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return type + " " + possible + " " + x + " " + y + " " + width + " " + height;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getPossible() {
        return possible;
    }

    public void setPossible(Float possible) {
        this.possible = possible;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }
}
