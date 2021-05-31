
import Model.Node;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.BorderLayout.WEST;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Predicate;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author anna
 */
public class MainFrame extends JFrame implements ActionListener {

    private ArrayList<Node> nodes;
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas = new DrawingPanel(null);
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    final static int W = 800, H = 600;
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the "tools" needed to draw in the image
    private Color color = Color.BLACK;       //draw color
    JPanel controls = new JPanel();
    JButton select = new JButton("Select");
    JButton button;
    JMenuBar menubar;
    JButton tree;

    public MainFrame() {
        super("My GraphMaker");

        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        menubar = new JMenuBar();
        tree = new JButton("Tree");
        menubar.add(tree);
        add(menubar, NORTH);
        controlPanel = new ControlPanel(this);
        add(canvas, CENTER); //this is BorderLayout.CENTER
        //add(configPanel, NORTH);
        add(controlPanel, SOUTH);
        tree.addActionListener(this);

        pack();

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object eSource = e.getSource();

        if (eSource == tree) {
            canvas.showTree();
        }

    }
}
