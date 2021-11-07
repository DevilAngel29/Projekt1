package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Model {
     ArrayList<Stat> statDph = new ArrayList<>();

    public Model importFromTextFile(String fileName) throws ModelException{
        Model model = new Model();
        try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
            while(scanner.hasNextLine()) {
                String inputLine = scanner.nextLine();
                String[] polozky = inputLine.split("\t");
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


    public ArrayList<Stat> splnujeKriteria(double limit) {
        ArrayList<Stat> result = new ArrayList<>();
        for (Stat stat : statDph) {
            if ( stat.zakladniSazba > limit) {
                result.add(stat);
            }
        }
        return result;
    }

}
