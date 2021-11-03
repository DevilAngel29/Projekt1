package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Model {
     ArrayList<Stat> list = new ArrayList<>();

    public static Model importFromTextFile(String fileName) throws ModelException{
        Model model = new Model();
        try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
            while(scanner.hasNextLine()) {
                String inputLine = scanner.nextLine();
                String[] polozky = inputLine.split("\t");
                if (polozky.length != 5)
                    throw new ModelException("Nespravny pocet polozek na radku:" + inputLine + polozky.length);
                Stat stat = new Stat(polozky[4], polozky[3], polozky[2], polozky[1], polozky[0]);


                System.out.println(stat.vypis());


                double limit = 20;
                if (stat.zakladniSazba >= limit) System.out.println(stat.vypis());








            }
        } catch (FileNotFoundException ex) {
            throw new ModelException("Soubor"+ fileName+" nenalezen:"+ex.getLocalizedMessage());
        }
        return model;
    }

    public ArrayList<Stat> splnujeKriteria(double limit) {
        ArrayList<Stat> result = new ArrayList<>();
        for (Stat stat : list) {
            if ( stat.getZakladniSazba() > limit) {
                result.add(stat);
            }
        }
        return result;
    }

}
