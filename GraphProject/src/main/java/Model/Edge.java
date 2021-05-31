package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anna
 */
import Model.Node;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;

public class Edge {

    private static final long serialVersionUID = -2550804644989255961L;

    public static final Integer[] STROKE_VALUES = {2, 3, 4, 6, 7, 8, 9, 10};
    protected Node nodeA;
    protected Node nodeB;
    protected int stroke;
    protected Color color;

    public Edge(Node a, Node b, Color c, int s) {
        color = c;
        stroke = s;
        nodeA = a;
        nodeB = b;
    }

    public Edge(Node a, Node b) {
        nodeA = a;
        nodeB = b;
    }

    public int getStroke() {
        return stroke;
    }

    public void setStroke(int stroke) {
        if (stroke < STROKE_VALUES[0]) {
            this.stroke = STROKE_VALUES[0];
        } else if (stroke > STROKE_VALUES[STROKE_VALUES.length - 1]) {
            this.stroke = STROKE_VALUES[STROKE_VALUES.length - 1];
        } else {
            this.stroke = stroke;
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

    public boolean isUnderCursor(int mx, int my) {

        if (mx < Math.min(nodeA.getX(), nodeB.getX())
                || mx > Math.max(nodeA.getX(), nodeB.getX())
                || my < Math.min(nodeA.getY(), nodeB.getY())
                || my > Math.max(nodeA.getY(), nodeB.getY())) {
            return false;
        }

        int A = nodeB.getY() - nodeA.getY();
        int B = nodeB.getX() - nodeA.getX();

        double distance = Math.abs(A * mx - B * my + nodeB.getX() * nodeA.getY() - nodeB.getY() * nodeA.getX()) / Math.sqrt(A * A + B * B);
        return distance <= 5;
    }

    public Node getNodeA() {
        return nodeA;
    }

    public void setNodeA(Node nodeA) {
        this.nodeA = nodeA;
    }

    public Node getNodeB() {
        return nodeB;
    }

    public void setNodeB(Node nodeB) {
        this.nodeB = nodeB;
    }

    public void changeStroke(int step) {
        setStroke(stroke + step);
    }

    public void move(int dx, int dy) {
        nodeA.move(dx, dy);
        nodeB.move(dx, dy);
    }

    public void draw(Graphics g) {
        int xa = nodeA.getX();
        int ya = nodeA.getY();
        int xb = nodeB.getX();
        int yb = nodeB.getY();

        Graphics2D g2 = (Graphics2D) g;
        // set stroke just for this line
        g2.setStroke(new BasicStroke(stroke));
        g2.setColor(color);
        g2.drawLine(xa, ya, xb, yb);
        // reset to default stroke value
        g2.setStroke(new BasicStroke());
    }

}
