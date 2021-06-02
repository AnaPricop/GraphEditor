
import Model.JGradientButton;
import Model.Node;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.BorderLayout.WEST;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

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
    JButton randomGraph;
    JButton verify;
    JButton nodesL;
    JButton edgesL;
    JButton bipartite;
    JButton random;

    public MainFrame() {
        super("My GraphMaker");

        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel frame = new JPanel(new FlowLayout());
        frame.setLayout(new GridLayout(6, 1));
        // menubar = new JMenuBar();
        Color myColor = Color.decode("#ffe5dc");
        frame.setBackground(myColor);

        Color myColor1 = Color.decode("#ffd8ca");
        Color myColor2 = Color.decode("#ff7f50");

        tree = new JGradientButton("Tree Example", myColor1, myColor2);
        //tree.setPreferredSize(new Dimension(140, 40));
        controlPanel = new ControlPanel(this);
        verify = new JGradientButton("Verify Tree", myColor1, myColor2);
        //verify.setPreferredSize(new Dimension(140, 40));
        bipartite = new JGradientButton("Verify Bipartite", myColor1, myColor2);
        // bipartite.setPreferredSize(new Dimension(140, 40));
        nodesL = new JGradientButton("Nodes List", myColor1, myColor2);
        //nodesL.setPreferredSize(new Dimension(140, 40));
        edgesL = new JGradientButton("Edges List", myColor1, myColor2);
        //edgesL.setPreferredSize(new Dimension(140, 40));
        random = new JGradientButton("Random Graph", myColor1, myColor2);

        frame.add(tree);
        frame.add(bipartite);
        frame.add(verify);
        frame.add(nodesL);
        frame.add(edgesL);
        frame.add(random);

        add(frame, WEST);
        add(canvas, CENTER); //this is BorderLayout.CENTER
        add(controlPanel, SOUTH);

        tree.addActionListener(this);
        nodesL.addActionListener(this);
        edgesL.addActionListener(this);
        verify.addActionListener(this);
        bipartite.addActionListener(this);
        random.addActionListener(this);

        pack();

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object eSource = e.getSource();

        if (eSource == tree) {
            canvas.showTree();
        }
        if (eSource == verify) {
            canvas.isTree();
        }
        if (eSource == nodesL) {
            canvas.showNodesList();
        }

        if (eSource == edgesL) {
            canvas.showEdgesList();
        }
        if (eSource == bipartite) {
            canvas.verifyBipartite();
        }
        if (eSource == random) {
            canvas.randomGraph();
        }

    }

}
