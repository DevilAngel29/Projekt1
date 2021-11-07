package com.company;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;
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



       System.out.println("Vlozte limit sazby DPH:");
        Scanner input = new Scanner(System.in);
        double limit = input.nextDouble();
        ArrayList<Stat> vypisStaty = model.splnujeKriteria(limit);


        System.out.println("Staty s limitem nad: " + limit + "%:");
        for (Stat s: vypisStaty) {
            System.out.println(s.nazevStatu +"("+ s.getZakladniSazba()+"%)");
        }

    }
}
