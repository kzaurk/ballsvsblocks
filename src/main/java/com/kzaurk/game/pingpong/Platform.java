package com.kzaurk.game.pingpong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import lombok.Getter;
import lombok.experimental.Accessors;

public class Platform implements Rect{
    @Getter
    @Accessors(fluent = true)
    private int x;
    @Getter
    @Accessors(fluent = true)
    private int y;
    @Getter
    @Accessors(fluent = true)
    private int width;
    @Getter
    @Accessors(fluent = true)
    private int height;

    private int windowHeight;
    private int windowWidth;
    private final int SPEED = 3;
    public Platform() {
        this.windowHeight = GamePanel.HEIGHT;
        this.windowWidth = GamePanel.WIDTH;
        width = 60;
        height = 7;
        x = 0;
        y = windowHeight - height - 40;
    }

    public void moveRight() {
        if (x < windowWidth - width) {
            x += SPEED;
        }
    }

    public void moveLeft() {
        if (x > 0) {
            x -= SPEED;
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);
        g2d.fillRect(x, y, width, height);
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }
}
