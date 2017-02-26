package uk.co.mmscomputing.application.imageviewer;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import java.text.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.*;
 
public class CompressionCode extends JComponent {
    private BufferedImage image, compressedImage;
    private int quality=0; //[0,100]
    private int numBytes;
 
    public CompressionCode(BufferedImage image) {
        this.image = image;
        createCompressedImage();
    }
 
    public int getQuality() {
        return quality;
    }
 
    public void setQuality(int quality) {
        if (this.quality == quality)
            return;
        if (quality < 0 || quality > 100)
            throw new IllegalArgumentException();
        this.quality = quality;
        createCompressedImage();
        repaint();
    }
 
    public int getNumBytes() {
        return numBytes;
    }
 
    private void createCompressedImage() {
        //byte[] bytes = ImageKit.getBytes(image, quality/100f);
        //numBytes = bytes.length;
        //compressedImage = ImageKit.read(bytes);
    }
 
    @Override
	public Dimension getPreferredSize() {
        Insets insets = getInsets();
        int w = insets.left + insets.right + 2*image.getWidth();
        int h = insets.top + insets.bottom + image.getHeight();
        return new Dimension(w, h);
    }
 
    @Override
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Insets insets = getInsets();
        int x0 = insets.left + (getWidth()-insets.left-insets.right-2*image.getWidth())/2;
        int y0 = insets.top + (getHeight()-insets.top-insets.bottom-image.getHeight())/2;
        g2.drawRenderedImage(image, AffineTransform.getTranslateInstance(x0,y0));
        x0 += image.getWidth();
        g2.drawRenderedImage(compressedImage, AffineTransform.getTranslateInstance(x0,y0));
    }
 
    public static void main(String[] args) throws IOException {
        BufferedImage image = ImageIO.read(new URL("http://blogs.sun.com/jag/resource/EastWest.jpg"));
        final CompressionCode example = new CompressionCode(image);
        final JSlider quality = new JSlider();
        quality.setMajorTickSpacing(25);
        quality.setMinorTickSpacing(10);
        quality.setPaintTicks(true);
        final JLabel percent = new JLabel("100 %"); //resizing label jiggles slider
        percent.setPreferredSize(percent.getPreferredSize());
        final JLabel bytes = new JLabel("8,888,888 bytes");
        bytes.setPreferredSize(bytes.getPreferredSize());
        quality.addChangeListener(new ChangeListener(){
            NumberFormat fmt = NumberFormat.getIntegerInstance();
 
            public void stateChanged(ChangeEvent e) {
                int value = quality.getValue();
                percent.setText(value+" %");
                if (!quality.getValueIsAdjusting()) {
                    example.setQuality(value);
                    bytes.setText(fmt.format(example.getNumBytes())+" bytes");
                }
            }
        });
        quality.setValue(example.getQuality());
        JPanel south = new JPanel();
        south.add(bytes);
        south.add(quality);
        south.add(percent);
        JFrame f = new JFrame("Example");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = f.getContentPane();
        cp.add(example);
        cp.add(south, BorderLayout.SOUTH);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}

/*
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.imageio.stream.*;
 
public class ImageKit {
    //quality means jpeg output, if quality is < 0 ==> use default quality
    public static void write(BufferedImage image, float quality, OutputStream out) throws IOException {
        Iterator writers = ImageIO.getImageWritersBySuffix("jpeg");
        if (!writers.hasNext())
            throw new IllegalStateException("No writers found");
        ImageWriter writer = (ImageWriter) writers.next();
        ImageOutputStream ios = ImageIO.createImageOutputStream(out);
        writer.setOutput(ios);
        ImageWriteParam param = writer.getDefaultWriteParam();
        if (quality >= 0) {
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(quality);
        }
        writer.write(null, new IIOImage(image, null, null), param);
    }
 
    public static BufferedImage read(byte[] bytes) {
        try {
            return ImageIO.read(new ByteArrayInputStream(bytes));
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static byte[] getBytes(BufferedImage image, float quality) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream(50000);
            write(image, quality, out);
            return out.toByteArray();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static BufferedImage compress(BufferedImage image, float quality) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream(50000);
            write(image, quality, out);
            return ImageIO.read(new ByteArrayInputStream(out.toByteArray()));
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
*/
