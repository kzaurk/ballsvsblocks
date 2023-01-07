package com.kzaurk.game.ballvsblocks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import lombok.Getter;
import lombok.experimental.Accessors;

public class Platform implements Rect {
    private static final int SPEED = 3;
    private static final int WIDTH = 40;
    private static final int HEIGHT = 7;
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


    public Platform() {
        this.windowHeight = GamePanel.HEIGHT;
        this.windowWidth = GamePanel.WIDTH;
        width = WIDTH;
        height = HEIGHT;
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
