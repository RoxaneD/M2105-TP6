/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package disque;

import java.awt.Color;
import java.awt.Graphics;

public class Circle {

    // attributs
    private int centreX, centreY, rayon;
    private Color couleur;

    // constructeurs
    public Circle(int centreX, int centreY, int rayon, Color couleur) {
        this.centreX = centreX;
        this.centreY = centreY;
        this.rayon = rayon;
        this.couleur = couleur;
    }

    // setteurs
    public void setCentreX(int centreX) {
        this.centreX = centreX;
    }

    public void setCentreY(int centreY) {
        this.centreY = centreY;
    }

    public void setRayon(int rayon) {
        this.rayon = rayon;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    // getteurs
    public int getCentreX() {
        return centreX;
    }

    public int getCentreY() {
        return centreY;
    }

    public int getRayon() {
        return rayon;
    }

    public Color getCouleur() {
        return couleur;
    }

    // autres méthodes
    public void draw(Graphics g) {
        g.setColor(getCouleur());
        g.drawOval(getCentreX() - getRayon(), getCentreY() - getRayon(), 2 * getRayon(), 2 * getRayon());
    }

}
