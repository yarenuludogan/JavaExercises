package com.yaren;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Game2048 game = new Game2048();
            GamePanel panel = new GamePanel(game);

            JFrame f = new JFrame("2048");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setResizable(false);
            f.setContentPane(panel);
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);

            // Tuş bağlama
            bindKeys(panel, game);

            panel.requestFocusInWindow();
        });
    }

    private static void bindKeys(JComponent comp, Game2048 game) {
        var im = comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        var am = comp.getActionMap();

        im.put(KeyStroke.getKeyStroke("LEFT"), "LEFT");
        im.put(KeyStroke.getKeyStroke("RIGHT"), "RIGHT");
        im.put(KeyStroke.getKeyStroke("UP"), "UP");
        im.put(KeyStroke.getKeyStroke("DOWN"), "DOWN");
        im.put(KeyStroke.getKeyStroke('R'), "RESET");

        am.put("LEFT", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                if (game.moveLeft()) afterMove(comp, game);
            }
        });
        am.put("RIGHT", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                if (game.moveRight()) afterMove(comp, game);
            }
        });
        am.put("UP", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                if (game.moveUp()) afterMove(comp, game);
            }
        });
        am.put("DOWN", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                if (game.moveDown()) afterMove(comp, game);
            }
        });
        am.put("RESET", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                game.reset();
                comp.repaint();
            }
        });
    }

    private static void afterMove(JComponent comp, Game2048 game) {
        comp.repaint();
        if (game.has2048()) {
            JOptionPane.showMessageDialog(comp, "You win " + game.getScore());
        } else if (!game.move()) {
            JOptionPane.showMessageDialog(comp, "You lost" + game.getScore());
        }
    }
}
