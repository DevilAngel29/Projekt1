package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static final String FILENAME = "vat-eu,csv.txt";


    public static void main(String[] args) {
        Model stat = new Model();

        try {
            stat = Model.importFromTextFile(FILENAME);
        } catch (ModelException e) {
            System.err.println("Nepodarilo se nacist data ze souboru. " + e.getMessage());
        }
        System.out.println("Vlozte limit sazby DPH:");
        Scanner input = new Scanner(System.in);
        double limit = input.nextDouble();
        ArrayList<Stat> vypisStaty = stat.splnujeKriteria(limit);

        System.out.println("Staty s limitem nad: " + limit + ":");
        for (Stat s : vypisStaty) {
            System.out.println(s.zkratkaStatu +"("+ s.getZakladniSazba()+"%)");
        }
    }
}
