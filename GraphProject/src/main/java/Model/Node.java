package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.Serializable;

public class Node {

    public static final Integer[] RADIUS_VALUES = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 48, 50};
    protected Color color;
    private String text;
    protected int aux = 1;
    protected int x;
    protected int y;
    protected int r;

    public Node(int x, int y, Color c, int r, String text, int aux) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.aux = aux;
        color = c;
        setText(text);
        // adj[aux] = new LinkedList();
    }

    public int getAux() {
        return aux;
    }

    public void setAux(int aux) {
        this.aux = aux;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        if (r < RADIUS_VALUES[0]) {
            this.r = RADIUS_VALUES[0];
        } else if (r > RADIUS_VALUES[RADIUS_VALUES.length - 1]) {
            this.r = RADIUS_VALUES[RADIUS_VALUES.length - 1];
        } else {
            this.r = r;
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        if (color == null) {
            this.color = Color.BLACK;
        } else {
            this.color = color;
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if (text == null) {
            this.text = "";
        } else {
            this.text = text;
        }

    }

    public boolean isUnderCursor(int mx, int my) {
        int a = x - mx;
        int b = y - my;

        return a * a + b * b <= r * r;
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void changeRadius(int step) {
        setR(r + step);
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x - r, y - r, r + r, r + r);
        g.setColor(Color.BLACK);
        g.drawOval(x - r, y - r, r + r, r + r);

        FontMetrics fm = g.getFontMetrics();
        int tx = x - fm.stringWidth(text) / 2;
        int ty = y - fm.getHeight() / 2 + fm.getAscent();
        g.drawString(text, tx, ty);
    }

    @Override
    public String toString() {
        String colorHex = "#" + Integer.toHexString(color.getRGB()).substring(2).toUpperCase();
        return "(" + Integer.toString(x) + ", " + Integer.toString(y) + ") " + "{r: " + Integer.toString(r) + ", c: " + colorHex + ", t: " + text + "}";
    }

}
