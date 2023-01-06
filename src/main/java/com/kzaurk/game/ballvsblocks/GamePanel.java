package com.kzaurk.game.ballvsblocks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import static com.kzaurk.game.ballvsblocks.KeyPressed.VK_LEFT;
import static com.kzaurk.game.ballvsblocks.KeyPressed.VK_LEFT_RELEASE;
import static com.kzaurk.game.ballvsblocks.KeyPressed.VK_RIGHT;
import static com.kzaurk.game.ballvsblocks.KeyPressed.VK_RIGHT_RELEASE;

public class GamePanel extends JPanel implements Runnable {
    public static final int WIDTH = 700;
    public static final int HEIGHT = 500;
    private AtomicBoolean stopGame;

    public Thread gameThread;
    private boolean leftPressed;
    private boolean rightPressed;

    public Platform platform;
    public Ball ball;
    public BlocksPanel blocksPanel;

    public GamePanel() {
        stopGame = new AtomicBoolean(false);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(new Color(117, 116, 164));
        setDoubleBuffered(true);
        setFocusable(true);
        requestFocusInWindow();
        setKeyBindings();

        platform = new Platform();
        ball = new Ball();
        blocksPanel = new BlocksPanel();
    }

    public void startGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        blocksPanel.draw(g);
        platform.draw(g);
        ball.draw(g);
        g.dispose();
    }

    public void update() {
        if (leftPressed) {
            platform.moveLeft();
        }
        if (rightPressed) {
            platform.moveRight();
        }
        collisionDetect();
        ball.update(List.of(platform.getRect()));
    }

    private void collisionDetect() {
        panelCollision();
        platformCollision();
        blockCollision();
    }

    private boolean rectCollision(Rect rect1, Rect rect2) {
        return rect1.x() < rect2.x() + rect2.width() &&
                rect1.x() + rect1.width() > rect2.x() &&
                rect1.y() < rect2.y() + rect2.height() &&
                rect1.height() + rect1.y() > rect2.y();
    }

    private void panelCollision() {
        if ((ball.x() >= GamePanel.WIDTH - Ball.WIDTH) || (ball.x() < 1)) {
            ball.swapXDirection();
        }
        if ((ball.y() >= GamePanel.HEIGHT - Ball.WIDTH) || (ball.y() < 1)) {
            ball.swapYDirection();
        }
    }

    private void platformCollision() {
        if(rectCollision(ball, platform)){
                ball.swapYDirection();
            }
    }

    private void blockCollision() {
        for (Block block: blocksPanel.getBlocks()) {
            if (!block.isDeleted() && rectCollision(block, ball)) {
                ball.swapYDirection();
                block.setDeleted(true);
            }
        }
    }

    private void setKeyBindings() {
        ActionMap actionMap = getActionMap();
        int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = getInputMap(condition);

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), VK_LEFT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), VK_RIGHT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), VK_LEFT_RELEASE);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), VK_RIGHT_RELEASE);

        actionMap.put(VK_LEFT, new KeyAction(VK_LEFT));
        actionMap.put(VK_RIGHT, new KeyAction(VK_RIGHT));
        actionMap.put(VK_LEFT_RELEASE, new KeyAction(VK_LEFT_RELEASE));
        actionMap.put(VK_RIGHT_RELEASE, new KeyAction(VK_RIGHT_RELEASE));
    }

    private class KeyAction extends AbstractAction {
        public KeyAction(KeyPressed actionCommand) {
            putValue(ACTION_COMMAND_KEY, actionCommand.name());
        }

        @Override
        public void actionPerformed(ActionEvent actionEvt) {
            if (actionEvt.getActionCommand().equals(VK_LEFT.name())){
                leftPressed = true;
            }
            if (actionEvt.getActionCommand().equals(VK_LEFT_RELEASE.name())){
                leftPressed = false;
            }
            if (actionEvt.getActionCommand().equals(VK_RIGHT.name())){
                rightPressed = true;
            }
            if (actionEvt.getActionCommand().equals(VK_RIGHT_RELEASE.name())){
                rightPressed = false;
            }
        }
    }

    @Override
    public void run() {
        try {
            while (!stopGame.get()) {
                update();
                repaint();
                Thread.sleep(5);
            }
        } catch (Exception e) {
            System.exit(1);
        }
    }
}
