package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static final String FILENAME = "vat-eu,csv.txt";

    public static void main(String[] args) {
        Model model = new Model();
        try {
            model.importFromTextFile(FILENAME);
        } catch (ModelException e) {
            System.err.println("Nepodarilo se nacist data ze souboru. " + e.getMessage());
        }

        try {
            model.exportToFile(FILENAME);
        } catch (ModelException e) {
            System.err.println("Nepodarilo se nacist data do souboru. " + e.getMessage());
        }
        System.out.println("Vlozte limit sazby DPH:");
        Scanner input = new Scanner(System.in);
        double limit = input.nextDouble();
        System.out.println();
        ArrayList<Stat> vypisStaty = model.splnujeKriteria(limit);
        System.out.println("Staty s limitem nad " + limit + "% a nepouziva specialni sazbu z dane:");
        for (Stat s: vypisStaty) {
            System.out.println(s.nazevStatu +"("+ s.getZakladniSazba()+"%)");
        }

        ArrayList<Stat> nesplnuje = model.nesplnujeKriteria(limit);
        System.out.println("Staty s limitem pod " + limit + "% nebo pouzivaji specialni sazbu z dane:");
        for (Stat s: nesplnuje) {
            System.out.println(s.zkratkaStatu);
        }

    }
}
