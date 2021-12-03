package sample;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        Dictionary d = new Dictionary();
//        d.addWord("tomorrow", "завтра");
//        d.addWordManually();

        File engFile = new File("English.in");
        d.translate(engFile);



/*
        ---CODE to fill in English.in file---
        File englishText = new File("English.in");
        String text = "The weather is fine today in Kiev and not a winter at all";

        try (PrintWriter pw = new PrintWriter(new FileWriter(englishText))) {
            pw.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }

 */

    }
}

