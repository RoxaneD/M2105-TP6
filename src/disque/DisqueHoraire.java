/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package disque;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import javax.swing.JPanel;

/**
 *
 * @author laurillau
 */
public class DisqueHoraire extends JPanel {

    // attributs
    private final String[] MOIS = {"Janvier", "Février", "Mars", "Avril",
        "Mai", "Juin", "Juillet", "Août",
        "Septembre", "Octobre", "Novembre",
        "Décembre"};

    private final int[] NB_JOURS = {31, 28, 31, 30, 31, 30,
        31, 31, 30, 31, 30, 31};

    private final int[] HEURES = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};

    private final int[] MINUTES = {00, 30};

    private static final int RAYON_JOUR = 50;
    private static final int RAYON_MOIS = RAYON_JOUR + 100;
    private static final int RAYON_HEURES = RAYON_MOIS + 100;
    private static final int RAYON_CONTOUR = RAYON_HEURES + 80;

    private static final int CENTRE_X = RAYON_CONTOUR + 20;
    private static final int CENTRE_Y = RAYON_CONTOUR + 20;

    private boolean bissextile = false;
    private int mois = 4;
    private int jour = 4;
    private int heure = 4;
    private int minutes = 4;

    private int indiceMois;

    private Circle cercleJour;
    private Circle cercleMois;
    private Circle cercleHeures;
    private Circle cercleContour;

    // constructeurs
    public DisqueHoraire() {
        setBackground(Color.yellow);
        setDoubleBuffered(true);
        setPreferredSize(new Dimension(2 * CENTRE_X, 2 * CENTRE_Y));

        // Création de 4 objets Circle chargés de dessiner les cercles concentriques
        Circle cercle1 = new Circle(CENTRE_X, CENTRE_Y, RAYON_JOUR, Color.BLACK);
        Circle cercle2 = new Circle(CENTRE_X, CENTRE_Y, RAYON_MOIS, Color.BLACK);
        Circle cercle3 = new Circle(CENTRE_X, CENTRE_Y, RAYON_HEURES, Color.BLACK);
        Circle cercle4 = new Circle(CENTRE_X, CENTRE_Y, RAYON_CONTOUR, Color.BLACK);

        setCercleJour(cercle1);
        setCercleMois(cercle2);
        setCercleHeures(cercle3);
        setCercleContour(cercle4);

    }

    // setteurs
    public void setCercleJour(Circle cercleJour) {
        this.cercleJour = cercleJour;
    }

    public void setCercleMois(Circle cercleMois) {
        this.cercleMois = cercleMois;
    }

    public void setCercleHeures(Circle cercleHeures) {
        this.cercleHeures = cercleHeures;
    }

    public void setCercleContour(Circle cercleContour) {
        this.cercleContour = cercleContour;
    }

    // getteurs
    public Circle getCercleJour() {
        return cercleJour;
    }

    public Circle getCercleMois() {
        return cercleMois;
    }

    public Circle getCercleHeures() {
        return cercleHeures;
    }

    public Circle getCercleContour() {
        return cercleContour;
    }

    public int getMois() {
        return mois;
    }

    public int getJour() {
        return jour;
    }

    public int getHeure() {
        return heure;
    }

    public int getMinutes() {
        return minutes;
    }

    // autres méthodes
    public void paintComponent(Graphics g) {
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Dessiner le curseur
        afficherCurseur(g);

        // Dessiner les cercles concentriques   
        getCercleJour().draw(g);
        getCercleMois().draw(g);
        getCercleHeures().draw(g);
        getCercleContour().draw(g);

        // Dessiner les sections pour les mois            
        afficherMois(g);

        // Dessiner les sections pour les jours
        afficherJours(g);

        // Dessiner les sections pour les heures
        afficherHeures(g);
    }

    private void afficherMois(Graphics g) {
        TextLabel label;
        int x1, y1, x2, y2;
        int i = 0;
        while (i < 12) {
            // Calcul de l'angle (en radians)
            double angle = Math.toRadians(((i * 360.0) / 12) + 15);

            // calcul de x1, y1, x2 et y2
            x1 = (int) (CENTRE_X + RAYON_MOIS * cos(angle));
            x2 = (int) (CENTRE_X + RAYON_HEURES * cos(angle));
            y1 = (int) (CENTRE_Y + RAYON_MOIS * sin(angle));
            y2 = (int) (CENTRE_Y + RAYON_HEURES * sin(angle));

            // Dessiner les sections
            g.setColor(Color.BLACK);
            g.drawLine(x1, y1, x2, y2);

            // Afficher les mois
            if (i + getMois() >= 12) {
                g.setColor(Color.BLACK);
                label = new TextLabel(MOIS[0 - (12 - getMois()) + i].toString(), CENTRE_X, CENTRE_Y, angle);
                label.draw(g, RAYON_MOIS, 0);
            } else {
                g.setColor(i == 0 ? Color.RED : Color.BLACK);
                label = new TextLabel(MOIS[i + getMois()].toString(), CENTRE_X, CENTRE_Y, angle);
                label.draw(g, RAYON_MOIS, 0);
            }
            i += 1;
        }
    }

    private void afficherJours(Graphics g) {
        TextLabel label;
        int x1, y1, x2, y2;
        Integer num = 0;
        Integer num2 = 0;
        int nombreJour;

        if (bissextile && getMois() == 1) {
            nombreJour = 29;
        } else {
            nombreJour = NB_JOURS[getMois()];
        }
        for (int i = 0; i < nombreJour; i++) {
            // Calcul de l'angle (en radians)
            double angle = Math.toRadians(((i * 360.0) / nombreJour) + ((360 / nombreJour) * 0.5));

            // calcul de x1, y1, x2 et y2
            x1 = (int) (CENTRE_X + RAYON_JOUR * cos(angle));
            x2 = (int) (CENTRE_X + RAYON_MOIS * cos(angle));
            y1 = (int) (CENTRE_Y + RAYON_JOUR * sin(angle));
            y2 = (int) (CENTRE_Y + RAYON_MOIS * sin(angle));

            // Dessiner les sections
            g.setColor(Color.BLACK);
            g.drawLine(x1, y1, x2, y2);

            // Afficher les jours
            if (i + getJour() > nombreJour) {
                g.setColor(Color.BLACK);
                num2 = 0 - (nombreJour) + i + getJour();
                label = new TextLabel(num2.toString(), CENTRE_X, CENTRE_Y, angle);
                label.draw(g, RAYON_JOUR + 3, 3);
            } else {
                g.setColor(i == 0 ? Color.RED : Color.BLACK);
                num2 = num + jour;
                label = new TextLabel(num2.toString(), CENTRE_X, CENTRE_Y, angle);
                label.draw(g, RAYON_JOUR + 3, 3);
            }
            num += 1;
        }
    }

    private void afficherHeures(Graphics g) {
        TextLabel labelHeure;
        TextLabel labelMinute;
        int x1, y1, x2, y2;

        Integer i1;
        Integer i2;
        int i3;
        int heureActuelle = heure;

        for (int i = 0; i < 48; i++) {
            // Calcul de l'angle (en radians)
            double angle = Math.toRadians(((i * 360.0) / 48) + 3.75);

            // calcul de x1, y1, x2 et y2
            x1 = (int) (CENTRE_X + RAYON_HEURES * cos(angle));
            x2 = (int) (CENTRE_X + RAYON_CONTOUR * cos(angle));
            y1 = (int) (CENTRE_Y + RAYON_HEURES * sin(angle));
            y2 = (int) (CENTRE_Y + RAYON_CONTOUR * sin(angle));

            // Dessiner les sections
            g.setColor(Color.BLACK);
            g.drawLine(x1, y1, x2, y2);

            // Afficher les heures
            
            i3 = i;
            if (i3 != 0 || i3 != 1) {
                i3 = i3 % 2;
            }

            if (minutes == MINUTES[0]) {
                i2 = MINUTES[i3];
            } else {
                i2 = MINUTES[(i3 + 1) % 2];
            }

            if (heureActuelle >= 24) {
                i1 = HEURES[24-heureActuelle];
            } else {
                i1 = HEURES[i / 2];
            }
            
            if (i2 == minutes && i1 == heure){
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLACK);
            }
            

            labelHeure = new TextLabel(i1 + "h", CENTRE_X, CENTRE_Y, angle);
            labelHeure.draw(g, RAYON_HEURES, 0);
            labelMinute = new TextLabel(i2.toString(), CENTRE_X, CENTRE_Y, angle);
            labelMinute.draw(g, RAYON_HEURES + 20, 0);
        }
    }

    private void afficherCurseur(Graphics g) {
        // pour colorier les différentes zones du DisqueHoraire

        // pour colorier l'ensemble des heures
        g.setColor(Color.GRAY);
        g.fillOval(20, 20, 2 * RAYON_CONTOUR, 2 * RAYON_CONTOUR);
        // pour colorier les heures selectionnées
        g.setColor(Color.LIGHT_GRAY);
        g.fillArc(20, 20,
                2 * RAYON_CONTOUR,
                2 * RAYON_CONTOUR,
                (int) (-360 / 48.0 - 3.75), (3 * 360) / 48);

        // pour colorier l'ensemble des mois
        g.setColor(Color.GRAY);
        g.fillOval(20 + (RAYON_CONTOUR - RAYON_HEURES),
                20 + (RAYON_CONTOUR - RAYON_HEURES),
                2 * RAYON_HEURES,
                2 * RAYON_HEURES);
        // pour colorier les mois selectionnées
        g.setColor(Color.LIGHT_GRAY);
        g.fillArc(20 + (RAYON_CONTOUR - RAYON_HEURES),
                20 + (RAYON_CONTOUR - RAYON_HEURES),
                2 * RAYON_HEURES,
                2 * RAYON_HEURES, -15, 360 / 12);

        // pour colorier l'ensemble des jours
        g.setColor(Color.GRAY);
        g.fillOval(20 + (RAYON_CONTOUR - RAYON_MOIS),
                20 + (RAYON_CONTOUR - RAYON_MOIS),
                2 * RAYON_MOIS,
                2 * RAYON_MOIS);
        // pour colorier le jour selectionné
        int nbJours = NB_JOURS[mois] + ((mois == 1) && bissextile ? 1 : 0);
        g.setColor(Color.LIGHT_GRAY);
        g.fillArc(20 + (RAYON_CONTOUR - RAYON_MOIS),
                20 + (RAYON_CONTOUR - RAYON_MOIS),
                2 * RAYON_MOIS,
                2 * RAYON_MOIS, -180 / nbJours, 360 / nbJours);

        // pour colorier l'interieur
        g.setColor(Color.DARK_GRAY);
        g.fillOval(20 + (RAYON_CONTOUR - RAYON_JOUR),
                20 + (RAYON_CONTOUR - RAYON_JOUR),
                2 * RAYON_JOUR,
                2 * RAYON_JOUR);
    }

    public void setDate(int mois, int jour, int heure, int minutes, boolean bis) {
        this.mois = mois;
        this.jour = jour;
        this.heure = heure;
        this.minutes = minutes;
        this.bissextile = bis;
    }
}
