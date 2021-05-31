
import Model.Edge;
import Model.Node;
import Model.Graph;
import static java.awt.BorderLayout.NORTH;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/**
 *
 * @author anna
 */
public final class DrawingPanel extends JPanel implements MouseListener, ComponentListener, MouseMotionListener {

    // final MainFrame frame;
    final static int W = 800, H = 600;
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the "tools" needed to draw in the image
    private Graph graph;
    private boolean mouseLeftButton = false;
    private boolean mouseRightButton = false;
    private Node node_1;
    private Edge edge_1;
    String text1;
    private int selectex1;
    private int selectey1;
    private Node edge_A;
    private Node edge_B;
    private boolean choose_B = false;
    JMenuBar menubar;
    JButton tree;

    public DrawingPanel(Graph graph) {
        // this.frame = frame;
        createOffscreenImage();
        init();
    }

    private void init() {

        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
        if (graph == null) {
            graph = new Graph("Graf");
        } else {
            setGraph(graph);
        }

        addComponentListener(this);
        addMouseListener(this);
        setFocusable(true);
        requestFocus();
        addMouseMotionListener(this);

    }

    public void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, W, H);
    }

    public void showTree() {
        graph = new Graph("Example");

        Node t = new Node(360, 100, Color.RED, 20, "1");
        Node b = new Node(550, 100, Color.GREEN, 20, "2");
        Node bb = new Node(550, 180, Color.GREEN, 20, "3");
        Node c = new Node(550, 400, Color.MAGENTA, 20, "4");
        Node d = new Node(250, 400, Color.ORANGE, 20, "5");
        Node e = new Node(300, 360, Color.RED, 20, "6");
        Node f = new Node(450, 200, Color.GREEN, 20, "7"); //root	
        Node g = new Node(450, 300, Color.MAGENTA, 20, "8");
        Node cc = new Node(550, 320, Color.MAGENTA, 20, "9");
        Node h = new Node(350, 500, Color.ORANGE, 20, "10");
        Node hh = new Node(210, 530, Color.ORANGE, 20, "11");

        Edge ab1 = new Edge(f, t);
        Edge ab11 = new Edge(f, bb);
        Edge fg = new Edge(f, g);
        Edge gh = new Edge(g, h);
        Edge he = new Edge(h, e);
        Edge he1 = new Edge(g, cc);
        Edge bf = new Edge(b, f);
        Edge cg = new Edge(c, g);
        Edge dh = new Edge(d, h);
        Edge hhh = new Edge(hh, h);
        
        graph.addNode(t);
        graph.addNode(hh);
        graph.addNode(b);
        graph.addNode(c);
        graph.addNode(d);
        graph.addNode(e);
        graph.addNode(f);
        graph.addNode(g);
        graph.addNode(h);
        graph.addNode(bb);
        graph.addNode(cc);

        graph.addEdge(ab11);
        graph.addEdge(hhh);
        graph.addEdge(fg);
        graph.addEdge(gh);
        graph.addEdge(he);
        graph.addEdge(ab1);
        graph.addEdge(bf);
        graph.addEdge(cg);
        graph.addEdge(dh);
        graph.addEdge(he1);
        repaint();
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        if (graph == null) {
            this.graph = new Graph("Graph");
        } else {
            this.graph = graph;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (graph != null) {
            graph.draw(g);
        }
    }

    public void createNewGraph() {
        setGraph(new Graph("NewGraph"));
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (mouseLeftButton) {
            moveG(e.getX(), e.getY());
        } else {
            setcursor(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        setcursor(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseLeftButton = true;
        }

        if (e.getButton() == MouseEvent.BUTTON3) {
            mouseRightButton = true;
        }

        setcursor(e);

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseLeftButton = false;
            addEdge2();
        }

        if (e.getButton() == MouseEvent.BUTTON3) {
            mouseRightButton = false;
            choose_B = false;
            if (node_1 != null) {
                nodeMenu(e, node_1);
            } else if (edge_1 != null) {
                edgeMenu(e, edge_1);
            } else {
                Menu(e);
            }
        }
        setcursor(e);
    }

    private void Menu(MouseEvent e) {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem item1 = new JMenuItem("New node");
        popupMenu.add(item1);
        item1.addActionListener((action) -> {
            createNewNode(e.getX(), e.getY());
        });
        popupMenu.show(e.getComponent(), e.getX(), e.getY());

    }

    private void nodeMenu(MouseEvent e, Node n) {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem remove1 = new JMenuItem("Remove node");
        popupMenu.add(remove1);
        remove1.addActionListener((action) -> {
            removeNode(n);
        });

        popupMenu.addSeparator();

        JMenuItem item2 = new JMenuItem("Add edge");
        popupMenu.add(item2);
        item2.addActionListener((action) -> {
            initializeAddEdge(n);
        });

        popupMenu.show(e.getComponent(), e.getX(), e.getY());

    }

    private void edgeMenu(MouseEvent event, Edge e) {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem remove1 = new JMenuItem("Remove edge");
        popupMenu.add(remove1);
        remove1.addActionListener((action) -> {
            removeEdge(e);
        });
    }

    public void setcursor(MouseEvent e) {
        if (e != null) {
            node_1 = graph.findnode_1(e.getX(), e.getY());
            if (node_1 == null) {
                edge_1 = graph.findedge_1(e.getX(), e.getY());
            }
            selectex1 = e.getX();
            selectey1 = e.getY();
        }
        int cursor;
        if (node_1 != null) {
            cursor = Cursor.HAND_CURSOR;
        } else if (mouseLeftButton) {
            cursor = Cursor.MOVE_CURSOR;
        } else if (choose_B) {
            cursor = Cursor.WAIT_CURSOR;
        } else if (edge_1 != null) {
            cursor = Cursor.CROSSHAIR_CURSOR;
        } else {
            cursor = Cursor.DEFAULT_CURSOR;
        }
        setCursor(Cursor.getPredefinedCursor(cursor));

    }

    private void moveG(int x, int y) {

        int x1 = x - selectex1;
        int y1 = y - selectey1;

        if (node_1 != null) {
            node_1.move(x1, y1);
        } else if (edge_1 != null) {
            edge_1.move(x1, y1);
        } else {
            graph.moveGraph(x1, y1);
        }

        selectex1 = x;
        selectey1 = y;
        repaint();
    }

    private void createNewNode(int x, int y) {
        try {
            Color color = JColorChooser.showDialog(this, "Choose color", Color.GRAY);
            int radius = ((Integer) JOptionPane.showInputDialog(this, "Choose size", "New node", JOptionPane.DEFAULT_OPTION, null, Node.RADIUS_VALUES, Node.RADIUS_VALUES[0]));
            text1 = JOptionPane.showInputDialog(this, "New node");
            graph.addNode(new Node(x, y, color, radius, text1));
            repaint();
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Operation canceled.");
        }

    }

    private void removeNode(Node n) {
        graph.removeNode(n);
        repaint();
    }

    private void removeEdge(Edge e) {
        graph.removeEdge(e);
        repaint();
    }

    private void initializeAddEdge(Node n) {
        if (node_1 != null) {
            edge_A = n;
            choose_B = true;
            setcursor(null);
        }
    }

    private void addEdge2() {
        if (choose_B) {
            if (node_1 != null) {
                if (node_1.equals(edge_A)) {
                    JOptionPane.showMessageDialog(this, "Choose a different node.");
                } else {
                    try {
                        edge_B = node_1;

                        Color color = JColorChooser.showDialog(this, "Choose color", Color.BLACK);
                        int stroke = ((Integer) JOptionPane.showInputDialog(this, "Choose the size you want", "New edge", JOptionPane.DEFAULT_OPTION, null, Edge.STROKE_VALUES, Edge.STROKE_VALUES[0]));
                        graph.addEdge(new Edge(edge_A, edge_B, color, stroke));
                        repaint();
                    } catch (NullPointerException e) {
                        JOptionPane.showMessageDialog(this, "Operation canceled.");
                    }

                }
            }
            choose_B = false;
        }
    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentResized(ComponentEvent ce) {

    }
}
