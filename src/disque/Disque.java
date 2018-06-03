/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package disque;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.*;
import javax.swing.*;

public class Disque extends JPanel {

    // attributs
    private GregorianCalendar calendar = new GregorianCalendar();
    private DisqueHoraire disqueHoraire;
    private JButton moisPred;
    private JButton moisProc;
    private JButton jourPred;
    private JButton jourProc;
    private JButton heurPred;
    private JButton heurProc;

    // constructeur
    public Disque() {
        DisqueHoraire disqueH = new DisqueHoraire();
        setDisque(disqueH);
        setDate();
        // pour colorier le fond du DisqueHoraire
        this.setBackground(Color.DARK_GRAY);

        // (1) Creer les boutons pour controler le disque horaire
        setMoisPred(new JButton("< Mois précédent"));
        setMoisProc(new JButton("Mois prochain >"));
        setJourPred(new JButton("< Jour précédent"));
        setJourProc(new JButton("Jour suivant >"));
        setHeurPred(new JButton("< Heure précédente"));
        setHeurProc(new JButton("Heure suivante >"));

        // (2) Creer l'interface pour ajouter les boutons et le disque
        BorderLayout layout1 = new BorderLayout();
        this.setLayout(layout1);
        this.add(getDisque(), BorderLayout.WEST);

        JPanel boutons = new JPanel(new GridLayout(6, 1));
        boutons.add(moisPred);
        boutons.add(moisProc);
        boutons.add(jourPred);
        boutons.add(jourProc);
        boutons.add(heurPred);
        boutons.add(heurProc);
        JPanel conteneurBoutons = new JPanel(new BorderLayout());
        conteneurBoutons.add(boutons, BorderLayout.NORTH);
        this.add(conteneurBoutons, BorderLayout.EAST);
        
        // pour colorier le fond des boutons
        conteneurBoutons.setBackground(Color.DARK_GRAY);
        
        // (3) S'abonner aux boutons pour controler la date : utiliser
        // les méthodes ci-dessous
        
    }

    // setteurs
    public void setCalendar(GregorianCalendar calendar) {
        this.calendar = calendar;
    }

    public void setDisque(DisqueHoraire disque) {
        this.disqueHoraire = disque;
    }

    public void setMoisPred(JButton moisPred) {
        this.moisPred = moisPred;
    }

    public void setMoisProc(JButton moisProc) {
        this.moisProc = moisProc;
    }

    public void setJourPred(JButton jourPred) {
        this.jourPred = jourPred;
    }

    public void setJourProc(JButton jourProc) {
        this.jourProc = jourProc;
    }

    public void setHeurPred(JButton heurPred) {
        this.heurPred = heurPred;
    }

    public void setHeurProc(JButton heurProc) {
        this.heurProc = heurProc;
    }

    private void setDate() {
        boolean bissextile;
        int annee, mois, jour, heure, minutes;

        annee = calendar.get(Calendar.YEAR);
        mois = calendar.get(Calendar.MONTH);
        jour = calendar.get(Calendar.DAY_OF_MONTH);
        heure = calendar.get(Calendar.HOUR_OF_DAY);
        minutes = calendar.get(Calendar.MINUTE);
        bissextile = calendar.isLeapYear(annee);

        disqueHoraire.setDate(mois, jour, heure, minutes, bissextile);
    }

    // getteurs
    public GregorianCalendar getCalendar() {
        return calendar;
    }

    public DisqueHoraire getDisque() {
        return disqueHoraire;
    }

    public JButton getMoisPred() {
        return moisPred;
    }

    public JButton getMoisProc() {
        return moisProc;
    }

    public JButton getJourPred() {
        return jourPred;
    }

    public JButton getJourProc() {
        return jourProc;
    }

    public JButton getHeurPred() {
        return heurPred;
    }

    public JButton getHeurProc() {
        return heurProc;
    }

    // autres méthodes
    private void moisSuivant() {
        calendar.add(Calendar.MONTH, 1);
        setDate();
    }

    private void moisPrecedent() {
        calendar.add(Calendar.MONTH, -1);
        setDate();
    }

    private void heureSuivante() {
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        setDate();
    }

    private void heurePrecedente() {
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        setDate();
    }

    private void jourSuivant() {
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        setDate();
    }

    private void jourPrecedent() {
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        setDate();
    }

    // main
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Disque horaire");
        frame.setSize(900, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Disque disque = new Disque();
        frame.add(disque);
        frame.setVisible(true);
    }
}
