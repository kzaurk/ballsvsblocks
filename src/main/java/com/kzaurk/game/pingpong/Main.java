package com.kzaurk.game.pingpong;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("PingPong");
        window.setVisible(true);
        GamePanel board = new GamePanel();
        window.add(board, BorderLayout.CENTER);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        board.startGame();
    }
}
