
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author anna
 */
public class ControlPanel extends JPanel {

    final MainFrame frame;
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton exitBtn = new JButton("Exit");
    //create all buttons (Load, Reset, Exit)
    JButton item1 = new JButton("Save as PDF");
    JButton item2 = new JButton("Save as PNG");
    DrawingPanel canvas;

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        //change the default layout manager (just for fun)
        setLayout(new GridLayout(1, 4));
        //add(saveBtn);
        add(item1);
        add(item2);
        //add(loadBtn);
        add(exitBtn);
        //configure listeners for all buttons
        //saveBtn.addActionListener(this::save);
        item1.addActionListener(this::savePDF);
        item2.addActionListener(this::savePNG);
        exitBtn.addActionListener(this::exit);
    }
    JFrame frameP = new JFrame();

    private void savePDF(ActionEvent e) {

        try {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("/home/anna/Desktop/hello1.pdf"));
            document.open();
            PdfContentByte cb = writer.getDirectContent();
            PdfTemplate tp = cb.createTemplate(800, 600);
            Graphics2D g2 = tp.createGraphics(800, 600);
            g2.scale(0.6, 1.0);
            frame.canvas.print(g2);
            g2.dispose();
            cb.addTemplate(tp, 30, 300);
            document.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControlPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ControlPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void savePNG(ActionEvent e) {
        try {
            JFileChooser fc = new JFileChooser();
            int val = fc.showSaveDialog(null);
            if (val == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                String string = file.getAbsolutePath();
                ImageIO.write((BufferedImage) frame.canvas.image, "PNG", new File(string));
            }
        } catch (IOException ex) {
            Logger.getLogger(ControlPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void exit(ActionEvent e) {
        System.exit(0);
    }

}
