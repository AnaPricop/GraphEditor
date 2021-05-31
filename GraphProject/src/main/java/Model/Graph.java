package Model;


import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author anna
 */
public class Graph {

    private String title;
    private List<Node> nodes;
    private List<Edge> edges;

    public Graph(String title) {
        setTitle(title);
        setNodes(new ArrayList<Node>());
        setEdges(new ArrayList<Edge>());

    }

    public void draw(Graphics g) {
        for (Edge edge : getEdges()) {
            edge.draw(g);
        }

        for (Node node : getNodes()) {
            node.draw(g);
        }
    }

    public void addNode(Node n) {
        nodes.add(n);
    }

    public void addEdge(Edge e) {
        for (Edge edge : edges) {
            if (e.equals(edge)) {
                return;
            }
        }
        edges.add(e);
    }

    public Node findnode_1(int mx, int my) {
        for (Node node : nodes) {
            if (node.isUnderCursor(mx, my)) {
                return node;
            }
        }
        return null;
    }

    public Edge findedge_1(int mx, int my) {
        for (Edge edge : edges) {
            if (edge.isUnderCursor(mx, my)) {
                return edge;
            }
        }
        return null;
    }

    public void removeNode(Node nodeUnderCursor) {
        removeEdge1(nodeUnderCursor);
        nodes.remove(nodeUnderCursor);
    }

    protected void removeEdge1(Node nodeUnderCursor) {
        edges.removeIf(e -> {
            return e.getNodeA().equals(nodeUnderCursor)
                    || e.getNodeB().equals(nodeUnderCursor);
        });
    }

    public void removeEdge(Edge edgeUnderCursor) {
        edges.remove(edgeUnderCursor);
    }

    public void moveGraph(int dx, int dy) {
        for (Node node : nodes) {
            node.move(dx, dy);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null) {
            title = "";
        } else {
            this.title = title;
        }
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

}
