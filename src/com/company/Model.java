package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Model {
    public static final String TAB = "\t";
    ArrayList<Stat> statDph = new ArrayList<>();

    public Model importFromTextFile(String fileName) throws ModelException{
        Model model = new Model();
        System.out.println("Vypis vsech statu:");
        try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
            while(scanner.hasNextLine()) {
                String inputLine = scanner.nextLine();
                String[] polozky = inputLine.split(TAB);
                if (polozky.length != 5)
                    throw new ModelException("Nespravny pocet polozek na radku:" + inputLine + polozky.length);
                Stat stat = new Stat(polozky[4], polozky[3], polozky[2], polozky[1], polozky[0]);
                statDph.add(stat);
                System.out.println(stat.vypis());
            }
        } catch (FileNotFoundException ex) {
            throw new ModelException("Soubor"+ fileName+" nenalezen:"+ex.getLocalizedMessage());
        }
        return model;
    }

    public void exportToFile(String filename) throws ModelException {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(filename))) {
            for (Stat stat : statDph) {
                writer.println(
                        stat.getZkratkaStatu()+ TAB
                        +stat.getNazevStatu()+ TAB
                        +stat.getZakladniSazba()+ TAB
                        +stat.getSnizenaSazba()+ TAB
                        +stat.parkingRate);
            }
        } catch (FileNotFoundException e) {
            throw new ModelException("Soubor "+filename+" nenalezen: "+e.getLocalizedMessage());
        }
    }

    public ArrayList<Stat> splnujeKriteria(double limit) {
        ArrayList<Stat> result = new ArrayList<>();

        for (Stat stat : statDph) {
            if ( stat.zakladniSazba > limit && stat.parkingRate == false) {
                result.add(stat);
            }
        }
        return result;
    }

    public ArrayList<Stat> nesplnujeKriteria(double limit) {
        ArrayList<Stat> result = new ArrayList<>();

        for (Stat stat : statDph) {
            if ( stat.zakladniSazba < limit || stat.parkingRate == true) {
                result.add(stat);
            }
        }
        return result;
    }

}
