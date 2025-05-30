/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.swing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author THANHTUNG
 */
public class MainPanel extends JPanel {

    /**
     * @return the image
     */
    public Icon getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Icon image) {
        this.image = image;
    }

    /**
     * @return the imageOld
     */
    public Icon getImageOld() {
        return imageOld;
    }

    /**
     * @param imageOld the imageOld to set
     */
    public void setImageOld(Icon imageOld) {
        this.imageOld = imageOld;
    }

    /**
     * @return the imageLocation
     */
    public Point getImageLocation() {
        return imageLocation;
    }

    /**
     * @param imageLocation the imageLocation to set
     */
    public void setImageLocation(Point imageLocation) {
        this.imageLocation = imageLocation;
        repaint();
    }

    /**
     * @return the imageSize
     */
    public Dimension getImageSize() {
        return imageSize;
    }

    /**
     * @param imageSize the imageSize to set
     */
    public void setImageSize(Dimension imageSize) {
        this.imageSize = imageSize;
    }

    /**
     * @return the targetLocation
     */
    public Point getTargetLocation() {
        return targetLocation;
    }

    /**
     * @param targetLocation the targetLocation to set
     */
    public void setTargetLocation(Point targetLocation) {
        this.targetLocation = targetLocation;
    }

    /**
     * @return the targetSize
     */
    public Dimension getTargetSize() {
        return targetSize;
    }

    /**
     * @param targetSize the targetSize to set
     */
    public void setTargetSize(Dimension targetSize) {
        this.targetSize = targetSize;
    }

    private Icon image;
    private Icon imageOld;
    private Point imageLocation;
    private Dimension imageSize;
    private Point targetLocation = new Point(35, 0);
    private Dimension targetSize = new Dimension(250, 250);

    public MainPanel() {
        setOpaque(false);
    }

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);

        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        if (imageOld != null) {
            Rectangle size = getAutoSize(imageOld, targetSize);
            g2.drawImage(toImage(imageOld), targetLocation.x, targetLocation.y, size.getSize().width, size.getSize().height, null);
        }

        if (image != null) {
            Rectangle size = getAutoSize(image, imageSize);
            g2.drawImage(toImage(image), imageLocation.x, imageLocation.y, size.getSize().width, size.getSize().height, null);
        }
        g2.dispose();
    }

    private Rectangle getAutoSize(Icon image, Dimension size) {
        int w = size.width;
        int h = size.height;
        if (w > image.getIconWidth()) {
            w = image.getIconWidth();
        }
        if (h > image.getIconHeight()) {
            h = image.getIconHeight();
        }
        int iw = image.getIconWidth();
        int ih = image.getIconHeight();
        double xScale = (double) w / iw;
        double yScale = (double) h / ih;
        double scale = Math.max(xScale, yScale);
        int width = (int) (scale * iw);
        int height = (int) (scale * ih);
        int x = getWidth() / 2 - (width / 2);
        int y = getHeight() / 2 - (height / 2);
        return new Rectangle(new Point(x, y), new Dimension(width, height));
    }

    private Image toImage(Icon icon) {
        return ((ImageIcon) icon).getImage();
    }

}
