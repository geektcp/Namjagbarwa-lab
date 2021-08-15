package com.geektcp.alpha.util.graphics;

import lombok.extern.slf4j.Slf4j;
import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.swing.JSVGCanvas;
import org.junit.Test;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Mr.Tang on 2021/3/18 10:40.
 */
@Slf4j
public class GraphicsAppTest {

    private static final String PATH = "output/";

    /*
     * 生成png
     * */
    @Test
    public void generatePNG() {
        try {
            int width = 200;
            int height = 250;
            //创建图片对象
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
            //基于图片对象打开绘图
            Graphics2D graphics = image.createGraphics();
            //绘图逻辑 START （基于业务逻辑进行绘图处理）……

            //绘制圆形
            graphics.setColor(Color.BLACK);
            Ellipse2D.Double ellipse = new Ellipse2D.Double(20, 20, 100, 100);
            graphics.draw(ellipse);

            // 绘图逻辑 END
            graphics.dispose();

            //将绘制好的图片写入到图片
            ImageIO.write(image, "png", new File(PATH + "abc.png"));
            log.info("generatePNG successful !");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /*
     * 生成空的svg图
     *
     * */
    @Test
    public void generateSVG() {
        try {
            DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();

            // Create an instance of org.w3c.dom.Document.
            String svgNS = "http://www.w3.org/2000/svg";
            Document document = domImpl.createDocument(svgNS, "svg", null);

            // Create an instance of the SVG Generator.
            SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

            svgGenerator.setPaint(Color.red);
            svgGenerator.fill(new Rectangle(10, 10, 100, 100));

            // Finally, stream out SVG to the standard output using
            // UTF-8 encoding.
            boolean useCSS = true; // we want to use CSS style attributes
            Writer out = new OutputStreamWriter(System.out, StandardCharsets.UTF_8);
            svgGenerator.stream(out, useCSS);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /*
     * 生成有图的svg文件
     * */
    @Test
    public void exportSVG() {
        DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
        String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
        SVGDocument doc = (SVGDocument) impl.createDocument(svgNS, "svg", null);

        // Create a converter for this document.
        SVGGraphics2D svgGenerator = new SVGGraphics2D(doc);

        // Do some drawing.
        Shape circle = new Ellipse2D.Double(0, 0, 50, 50);
        svgGenerator.setPaint(Color.red);
        svgGenerator.fill(circle);
        svgGenerator.translate(60, 0);
        svgGenerator.setPaint(Color.green);
        svgGenerator.fill(circle);
        svgGenerator.translate(60, 0);
        svgGenerator.setPaint(Color.blue);
        svgGenerator.fill(circle);
        svgGenerator.setSVGCanvasSize(new Dimension(280, 150));

        try {
            boolean useCSS = false; // we want to use CSS style attributes
            Writer out = new FileWriter(new File( PATH + "test.svg"), true);
            svgGenerator.stream(out, useCSS);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /*
     * 生成有图的svg，不导出，用swing展示出来
     * */
    @Test
    public void swingSVG() {
        DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
        String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
        SVGDocument doc = (SVGDocument) impl.createDocument(svgNS, "svg", null);

        // Create a converter for this document.
        SVGGraphics2D svgGenerator = new SVGGraphics2D(doc);

        // Do some drawing.
        Shape circle = new Ellipse2D.Double(0, 0, 50, 50);
        svgGenerator.setPaint(Color.red);
        svgGenerator.fill(circle);
        svgGenerator.translate(60, 0);
        svgGenerator.setPaint(Color.green);
        svgGenerator.fill(circle);
        svgGenerator.translate(60, 0);
        svgGenerator.setPaint(Color.blue);
        svgGenerator.fill(circle);
        svgGenerator.setSVGCanvasSize(new Dimension(280, 150));

        // Populate the document root with the generated SVG content.
        Element root = doc.getDocumentElement();
        svgGenerator.getRoot(root);

        // display
        JSVGCanvas canvas = new JSVGCanvas();
        JFrame f = new JFrame();
        f.getContentPane().add(canvas);
        canvas.setSVGDocument(doc);
        f.pack();
        f.setVisible(true);

        // 暂停，弹出swing窗口
        LockSupport.park();
    }
}
