package dictionaries;

import javax.swing.*;
import java.io.File;
import java.util.HashMap;
import java.nio.file.Paths;


public class LatinDict extends Dictionary {
    protected String file_path = "latin.ser";


    public LatinDict(){
        File file = new File(file_path);
        if (!file.exists()) {
            hMDictionary = new HashMap<>();
        }
        else
            hMDictionary = deserializeHashMap(file_path);
    }


    public void WritePairToDict(String key, String value) throws Exception {
        if (key.length() != 4 || !checkLangChars(false, key)){
            throw new Exception("Ключ-слово должно состоять только из символов латинской раскладки и быть длинной в 4 символа!");
        }
        if (!checkLangChars(true, value)){
            throw new Exception("Второе слово должно состоять только из символов русской раскладки!");
        }
        if (hMDictionary.containsKey(key)){
            throw new Exception("Такое ключ-слово уже имеется в словаре!");
        }

        hMDictionary.put(key, value);

        serializeHashMap(hMDictionary, file_path);
    }

    public void DelPairFromDict(String key) throws Exception {
        if (!hMDictionary.containsKey(key)){
            throw new Exception("Такого ключ-слова пока что нет в словаре...\n");
        }

        hMDictionary.remove(key);

        serializeHashMap(hMDictionary,file_path);
    }

    public void ShowAllDict() throws Exception {
        if (hMDictionary.isEmpty())
            throw new Exception("Словарь пуст, выводить нечего...\n");
        for (String key : hMDictionary.keySet()) {
            System.out.println(key + " - " + hMDictionary.get(key) + ";");
        }
    }

    public String SearchPairInDict(String key) throws Exception {
        if (!hMDictionary.containsKey(key)){
            throw new Exception("Такого ключ-слова пока что нет в словаре...\n");
        }

        return hMDictionary.get(key);
    }



    private boolean checkLangChars(Boolean russ, String input) {
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (!russ){
                if (!isLatinChar(c)) {
                    return false;
                }
            }
            else{
                if (!isRussianChar(c)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isLatinChar(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private boolean isRussianChar(char c) {
        return (c >= 'а' && c <= 'я') || (c >= 'А' && c <= 'Я') || c == 'ё' || c == 'Ё';
    }
}
