package com.kzaurk.game.ballvsblocks;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("Ball vs blocks");
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel, BorderLayout.CENTER);

        window.setVisible(true);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel.startGame();
    }
}
