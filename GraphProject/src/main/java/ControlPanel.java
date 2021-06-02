
import Model.JGradientButton;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
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
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;


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
    DrawingPanel canvas;
    private static final Logger logger = Logger.getLogger(ControlPanel.class.getName());
    Color myColor1 = Color.decode("#b5d3f1");
    Color myColor2 = Color.decode("#0858a8");
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton exitBtn = new JGradientButton("Exit", myColor1, myColor2);
    JButton item1 = new JGradientButton("Save as PDF", myColor1, myColor2);
    JButton item2 = new JGradientButton("Save as PNG", myColor1, myColor2);
    JButton item3 = new JGradientButton("Save as SVG", myColor1, myColor2);

    public ControlPanel(MainFrame frame) {
        this.frame = frame;

        init();
    }

    private void init() {
        Color myColor = Color.decode("#b5d3f1");
        JPanel frame = new JPanel(new FlowLayout());
        // setLayout(new GridLayout(1, 4));
        item1.setPreferredSize(new Dimension(140, 40));
        item2.setPreferredSize(new Dimension(140, 40));
        item3.setPreferredSize(new Dimension(140, 40));
        exitBtn.setPreferredSize(new Dimension(140, 40));
        frame.setBackground(myColor);
        frame.add(item1);
        frame.add(item2);
        frame.add(item3);
        frame.add(exitBtn);
        add(frame);

        item1.addActionListener(this::savePDF);
        item2.addActionListener(this::savePNG);
        exitBtn.addActionListener(this::exit);
        item3.addActionListener(this::writeToSVG);
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

    public void writeToSVG(ActionEvent e) {
        try {
            File fileName = new File("/home/anna/Desktop/write.svg");
            int width = frame.canvas.image.getWidth();
            int height = frame.canvas.image.getWidth();
            logger.info(
                    () -> MessageFormat.format("Exporting panel to SVG file (width x height; {0} x {1}): {2}",
                            width, height, fileName.getAbsolutePath()));

            DOMImplementation domImpl = SVGDOMImplementation.getDOMImplementation();
            org.w3c.dom.Document document = domImpl.createDocument(null, "svg", null);
            SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
            svgGenerator.setSVGCanvasSize(new Dimension(width, height));
            frame.canvas.print(svgGenerator);

            boolean useCSS = true;

            Writer out = new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8");
            svgGenerator.stream(out, useCSS);

        } catch (IOException ex) {
            Logger.getLogger(ControlPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
