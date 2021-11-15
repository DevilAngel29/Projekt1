package com.company;

import java.util.*;

public class Main {

    public static final String FILENAME = "vat-eu,csv.txt";

    public static void main(String[] args) {
        Model model = new Model();
        try {
            model.importFromTextFile(FILENAME);
        } catch (ModelException e) {
            System.err.println("Nepodarilo se nacist data ze souboru. " + e.getMessage());
        }

        System.out.println("Vlozte limit sazby DPH:");
        Scanner input = new Scanner(System.in);
        double limit = input.nextDouble();
        System.out.println();
        List<Stat> vypisStaty = model.splnujeKriteria(limit);
        Collections.addAll(vypisStaty);
        Collections.reverse(vypisStaty);
        System.out.println("Staty, ktere maji zakladni sazbu dane z pridane hodnoty vyssi nez " + "  % a pritom nepouzivaji specialni sazbu dane:");
        for (Stat s: vypisStaty) {
            System.out.println(s.nazevStatu +" ("+ s.zkratkaStatu + "): " + s.getZakladniSazba()+" %");
        }

        System.out.println("====================");

        ArrayList<Stat> nesplnuje = model.nesplnujeKriteria(limit);
        System.out.print("Sazba DPH " +limit+ " % nebo nizsi nebo pouzivaji specialni sazbu:");
        for (Stat s: nesplnuje) {
            System.out.print(s.zkratkaStatu+", ");
        }

        try {
            model.exportToFile("vat-over-20.txt");
        } catch (ModelException e) {
            System.err.println("Nepodarilo se nacist data do souboru. " + e.getMessage());
        }

    }
}
