package com.kzaurk.game.pingpong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.imageio.ImageIO;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;

public class Ball implements Rect{
    public static final int WIDTH = 20;
    @Getter
    @Accessors(fluent = true)
    private int x = 150;
    @Getter
    @Accessors(fluent = true)
    private int y = 150;
    private int xDirection;
    private int yDirection;
    private double speed;
    private BufferedImage image;

    @SneakyThrows
    public Ball() {
        image = ImageIO.read(getClass().getClassLoader().getResource("ball.png"));
        xDirection = 1;
        yDirection = 1;
        speed = 1;
    }
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
//        g2d.setColor(Color.RED);
//        g2d.fillRect(x, y, WIDTH, WIDTH);
        g2d.drawImage(image, x, y, WIDTH, WIDTH, null, null);
    }

    public void update(List<Rectangle> rectangles) {
        updatePosition();
    }

    private void updatePosition() {
        x += speed * xDirection;
        y += speed * yDirection;
    }

    public void swapXDirection() {
        xDirection *= -1;
    }

    public void swapYDirection() {
        yDirection *= -1;
    }

    @Override
    public int width() {
        return WIDTH;
    }

    @Override
    public int height() {
        return WIDTH;
    }
}
