package com.kzaurk.game.ballvsblocks;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
public class Block implements Rect{
    @Getter
    @Accessors(fluent = true)
    private int x;
    @Getter
    @Accessors(fluent = true)
    private int y;
    public static final int WIDTH = 50;
    public static final int HEIGHT = 20;
    @Getter
    @Setter
    private boolean deleted;

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
        deleted = false;
    }

    @Override
    public int width() {
        return WIDTH;
    }

    @Override
    public int height() {
        return HEIGHT;
    }
}
