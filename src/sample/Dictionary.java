package sample;

import java.io.*;
import java.util.*;

public class Dictionary {

    private Map<String, String> words;

    public Dictionary() {
        this.words = getDictionaryFromFile(new File("Dictionary.csv"));
    }

    public Map<String, String> getWords() {
        return words;
    }


    public void addWord(String eng, String rus) {

        File file = new File("Dictionary.csv");
        String delimiter = ",";
        try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) // дозапись
        {
            pw.println(eng + delimiter + rus);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void addWordManually() {
        System.out.println("Enter a word in English");
        Scanner sc1 = new Scanner(System.in);
        String eng = sc1.nextLine();
        System.out.println("Enter a word in Russian");
        Scanner sc2 = new Scanner(System.in);
        String rus = sc2.nextLine();

        File file = new File("Dictionary.csv");
        String delimiter = ",";
        try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) // дозапись
        {
            pw.println(eng + delimiter + rus);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void saveDictionaryToCSV() {
        String delimiter = ",";

        File file = new File("Dictionary.csv");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Map.Entry<String, String> entry : words.entrySet()) {
            String eng = entry.getKey();
            String rus = entry.getValue();
            try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) // дозапись
            {
                pw.println(eng + delimiter + rus);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public Map<String, String> getDictionaryFromFile(File file) {
        Map<String, String> words = new HashMap<>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String wordsPair = null;
        for (; ; ) {
            try {
                wordsPair = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (wordsPair == null) break;
            String[] word = wordsPair.split(",");
            words.put(word[0], word[1]);
        }
        return words;
    }


    public void translate(File file) {
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<String> englishList = new ArrayList<>();
        String englishText = "";
        if (sc != null) {
            while (sc.hasNextLine()) {
                englishText = englishText + sc.nextLine();
            }
        }
        for (String a : englishText.split(" ")) {
            englishList.add(a);
        }

        String russianText = "";
        for (String word : englishList) {

            if (words.get(word) != null)
                russianText = russianText + " " + words.get(word);

        }
        String russianTextTrimmed = russianText.trim() + "."; // Хотелось бы аккуратнее
        String russianTextFormatted = russianTextTrimmed.substring(0, 1).toUpperCase() + russianTextTrimmed.substring(1);
        System.out.println(russianTextFormatted);

        File russianFile = new File("Russian.out");

        try (PrintWriter pw = new PrintWriter(new FileWriter(russianFile))) {
            pw.println(russianTextFormatted);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "words=" + words +
                '}';
    }
}
