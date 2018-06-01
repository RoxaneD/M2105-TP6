/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package disque;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class TextLabel {
    private int x, y;
    private String text;
    private double angle;
    
    public TextLabel(String text, int x, int y, double angle) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.angle = angle;
    }
    
    public void draw(Graphics g, int dx, int dy) {
        Graphics2D g2 = (Graphics2D) g;
        
        AffineTransform transform;

        transform = g2.getTransform();
        g2.rotate(angle, x, y);
        g2.drawString(text, x + 10 + dx, y - 5 + dy);
        g2.rotate(angle, x, y);
        g2.setTransform(transform); 
    }
}
