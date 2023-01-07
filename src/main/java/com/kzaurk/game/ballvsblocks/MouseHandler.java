package com.kzaurk.game.ballvsblocks;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.function.Consumer;

public class MouseHandler implements MouseListener {
    Consumer<MouseEvent> pressedConsumer;
    Consumer<MouseEvent> releasedConsumer;

    public MouseHandler(Consumer<MouseEvent> pressed, Consumer<MouseEvent> released) {
        pressedConsumer = pressed;
        releasedConsumer = released;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressedConsumer.accept(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        releasedConsumer.accept(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
