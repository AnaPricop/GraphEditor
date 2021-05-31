
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JButton;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author anna
 */
public class ConfigPanel extends JPanel {

    final MainFrame frame;
    JLabel label; // we’re drawing regular polygons
    JSpinner sidesField; // number of sides
    JComboBox colorCombo; // the color of the shape
    public static String color = "";
    public static int value;
    private DrawingPanel canvas;
    JButton select = new JButton("Select");
    JButton button = new JButton("ADD NODE");

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;

        init();
    }

    public void setColor(String color) {
        this.color = color;
    }

    public static void setValue(int value) {
        ConfigPanel.value = value;
    }

    private void init() {

    }

}
