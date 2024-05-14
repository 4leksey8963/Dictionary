package dictionaries;

import java.io.File;
import java.util.HashMap;

public class NumbsDict extends Dictionary {
    protected String file_path = "numbs.ser";
    protected HashMap<String, String> hMDictionary;


    public NumbsDict(){
        File file = new File(file_path);
        if (!file.exists()) {
            hMDictionary = new HashMap<>();
        }
        else
            hMDictionary = deserializeHashMap(file_path);
    }


    public void WritePairToDict(String key, String value) throws Exception {
        if (key.length() != 5 || !isAllDigits(key)){
            throw new Exception("Ключ-слово должно состоять только из цифр и быть длинной в 5 символа!");
        }
        if (!checkLangChars(value)){
            throw new Exception("Второе слово должно состоять только из символов русской раскладки!");
        }
        if (hMDictionary.containsKey(key)){
            throw new Exception("Такое ключ-слово уже имеется в словаре!\n");
        }

        hMDictionary.put(key, value);

        serializeHashMap(hMDictionary, file_path);
    }

    public void DelPairFromDict(String key) throws Exception {
        if (!hMDictionary.containsKey(key)){
            throw new Exception("Такого ключ-слова пока что нет в словаре...\n");
        }

        hMDictionary.remove(key);

        serializeHashMap(hMDictionary, file_path);
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
            throw new Exception("Такого ключ-слова пока что нет в словаре...");
        }

        return hMDictionary.get(key);
    }

    private boolean checkLangChars(String input) {
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (!isRussianChar(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAllDigits(String s) {
        return s.matches("\\d+");
    }

    private boolean isRussianChar(char c) {
        return (c >= 'а' && c <= 'я') || (c >= 'А' && c <= 'Я') || c == 'ё' || c == 'Ё';
    }
}
