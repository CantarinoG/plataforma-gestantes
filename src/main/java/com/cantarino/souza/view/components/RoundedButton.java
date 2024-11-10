package com.cantarino.souza.view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoundedButton extends JButton {
    private boolean isHovered = false;
    private boolean isPressed = false;
    private final int radius;
    private Color backgroundColor;
    private Color hoverColor;
    private Color pressedColor;

    public RoundedButton(String text, int radius) {
        super(text);
        this.radius = radius;

        // Initial setup
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);

        // Default colors
        backgroundColor = AppColors.BUTTON_PINK;
        hoverColor = backgroundColor.darker();
        pressedColor = hoverColor.darker();

        setupMouseListeners();
    }

    private void setupMouseListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                isPressed = true;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isPressed = false;
                repaint();
            }
        });
    }

    @Override
    public void setBackground(Color bg) {
        this.backgroundColor = bg;
        this.hoverColor = new Color(
                Math.max(bg.getRed() - 20, 0),
                Math.max(bg.getGreen() - 20, 0),
                Math.max(bg.getBlue() - 20, 0));
        this.pressedColor = new Color(
                Math.max(bg.getRed() - 40, 0),
                Math.max(bg.getGreen() - 40, 0),
                Math.max(bg.getBlue() - 40, 0));
        super.setBackground(bg);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Paint background
        if (isPressed) {
            g2d.setColor(pressedColor);
        } else if (isHovered) {
            g2d.setColor(hoverColor);
        } else {
            g2d.setColor(backgroundColor);
        }
        g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);

        // Paint text
        FontMetrics metrics = g2d.getFontMetrics(getFont());
        Rectangle stringBounds = metrics.getStringBounds(getText(), g2d).getBounds();

        int x = (getWidth() - stringBounds.width) / 2;
        int y = (getHeight() - stringBounds.height) / 2 + metrics.getAscent();

        g2d.setColor(getForeground());
        g2d.setFont(getFont());
        g2d.drawString(getText(), x, y);

        g2d.dispose();
    }
}