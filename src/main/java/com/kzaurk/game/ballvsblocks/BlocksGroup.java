package com.kzaurk.game.ballvsblocks;

import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;

public class BlocksGroup {
    @Getter
    private Set<Block> blocks;
    private final static int START_X = 50;
    private final static int START_Y = 50;

    public BlocksGroup() {
        this.blocks = new HashSet<>();
        for (int x = START_X; x < GamePanel.WIDTH - Block.WIDTH; x += Block.WIDTH) {
            for (int y = START_Y; y < (START_Y + 5 * Block.HEIGHT); y += Block.HEIGHT) {
                var block = new Block(x, y);
                blocks.add(block);
            }
        }
    }

    public void draw(Graphics g) {
        for (var block : blocks) {
            if (!block.isDeleted()) {
                g.fillRect(block.x(), block.y(), Block.WIDTH - 1, Block.HEIGHT - 1);
            }
        }
    }
}
