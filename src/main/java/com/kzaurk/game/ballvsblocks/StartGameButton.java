package com.kzaurk.game.ballvsblocks;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class StartGameButton {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 60;
    private static final int X = GamePanel.WIDTH / 2 - WIDTH / 2;
    private static final int Y = GamePanel.HEIGHT / 2 - HEIGHT / 2;

    private boolean pressed = false;

    public void draw(Graphics g) {
        var g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(X, Y, WIDTH, HEIGHT);
        if (pressed) {
            g2.setColor(new Color(150, 150, 150));
        } else {
            g2.setColor(new Color(225, 142, 142));
        }
        g2.fillRect(X, Y, WIDTH, HEIGHT);
        g2.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 16));
        var textX = X + (WIDTH / 2 - 30);
        var textY = Y + (HEIGHT / 2 + 5);
        g2.drawString("Start Game", textX, textY);
    }

    public void pressed(int mouseX, int mouseY) {
        if (mouseX > X && mouseY > Y && mouseX < (X + WIDTH) && mouseY < (Y + HEIGHT)) {
            pressed = true;
        }
    }

    public void released(int mouseX, int mouseY) {
        if (mouseX > X && mouseY > Y && mouseX < (X + WIDTH) && mouseY < (Y + HEIGHT)) {
            pressed = false;
        }
    }
}
