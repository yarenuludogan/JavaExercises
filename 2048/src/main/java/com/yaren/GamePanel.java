package com.yaren;


import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private final Game2048 game;


    private static final int TILE_SIZE = 100;
    private static final int TILE_GAP = 10;
    private static final int HEADER_H = 60;
    private static final Font FONT = new Font("SansSerif", Font.BOLD, 24);

    public GamePanel(Game2048 game) {
        this.game = game;
        setPreferredSize(new Dimension(
                Game2048.SIZE * TILE_SIZE + (Game2048.SIZE + 1) * TILE_GAP,
                Game2048.SIZE * TILE_SIZE + (Game2048.SIZE + 1) * TILE_GAP + HEADER_H
        ));
        setBackground(new Color(0xFAF8EF));
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawHeader(g);
        drawBoard(g);
    }

    private void drawHeader(Graphics g) {
        g.setColor(new Color(0x776E65));
        g.setFont(FONT);
        g.drawString(" " + game.getScore() + "   (R: Restart)", TILE_GAP, 40);
    }

    private void drawBoard(Graphics g) {
        int[][] board = game.getBoardCopy();


        g.setColor(new Color(0xBBADA0));
        int top = HEADER_H;
        g.fillRoundRect(TILE_GAP, top, getWidth() - 2*TILE_GAP, getHeight() - top - TILE_GAP, 12, 12);


        for (int r = 0; r < Game2048.SIZE; r++) {
            for (int c = 0; c < Game2048.SIZE; c++) {
                int x = TILE_GAP + c * TILE_SIZE + (c + 1) * TILE_GAP;
                int y = top + r * TILE_SIZE + (r + 1) * TILE_GAP;
                int val = board[r][c];
                drawTile(g, x, y, val);
            }
        }
    }

    private void drawTile(Graphics g, int x, int y, int val) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Color bg = tileColor(val);
        g2.setColor(bg);
        g2.fillRoundRect(x, y, TILE_SIZE, TILE_SIZE, 12, 12);

        if (val != 0) {
            g2.setColor(val <= 4 ? new Color(0x776E65) : Color.WHITE);
            int fontSize = (val < 128) ? 28 : (val < 1024 ? 24 : 22);
            g2.setFont(new Font("SansSerif", Font.BOLD, fontSize));
            String text = String.valueOf(val);
            FontMetrics fm = g2.getFontMetrics();
            int tx = x + (TILE_SIZE - fm.stringWidth(text)) / 2;
            int ty = y + (TILE_SIZE + fm.getAscent() - fm.getDescent()) / 2;
            g2.drawString(text, tx, ty);
        }
    }

    private Color tileColor(int val) {
        return switch (val) {
            case 0 -> new Color(0xCDC1B4);
            case 2 -> new Color(0xEEE4DA);
            case 4 -> new Color(0xEDE0C8);
            case 8 -> new Color(0xF2B179);
            case 16 -> new Color(0xF59563);
            case 32 -> new Color(0xF67C5F);
            case 64 -> new Color(0xF65E3B);
            case 128 -> new Color(0xEDCF72);
            case 256 -> new Color(0xEDCC61);
            case 512 -> new Color(0xEDC850);
            case 1024 -> new Color(0xEDC53F);
            case 2048 -> new Color(0xEDC22E);
            default -> new Color(0x3C3A32);
        };
    }
}
