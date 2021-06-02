package Model;

import java.awt.*;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 * @author anna
 */
public class JGradientButton extends JButton {

    private static final long serialVersionUID = 1L;
    private Color stopTop;
    private Color stopBottom;
    private Paint colorGradient;
    private Point[] stopPoints = new Point[2];

    public JGradientButton(Color stopTop, Color stopBottom) {
        this("", stopTop, stopBottom);
    }

    public JGradientButton(String text, Color stopTop, Color stopBottom) {
        this(text, null, stopTop, stopBottom);
    }

    public JGradientButton(String text, Icon icon, Color stopTop, Color stopBottom) {
        super(text, icon);

        setContentAreaFilled(false);
        setFocusPainted(false);

        this.stopTop = stopTop;
        this.stopBottom = stopBottom;
    }

    public void invalidate() {
        super.invalidate();

        stopPoints[0] = new Point(0, 0);
        stopPoints[1] = new Point(0, getHeight());

        if (getModel().isPressed()) {
            colorGradient = new GradientPaint(stopPoints[0], stopBottom, stopPoints[1], stopTop);
        } else {
            colorGradient = new GradientPaint(stopPoints[0], stopTop, stopPoints[1], stopBottom);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setPaint(colorGradient);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();

        super.paintComponent(g);
    }
}
