package dictionaries;

import java.io.*;
import java.util.HashMap;

public abstract class Dictionary {
    protected String file_path = "";

    protected HashMap<String, String> hMDictionary = new HashMap<>();

    public void WritePairToDict(String key, String value) throws Exception { }

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
            throw new Exception("Такого ключ-слова пока что нет в словаре...\n");
        }

        return hMDictionary.get(key);
    }

    public static Boolean serializeHashMap(HashMap<String, String> hashMap, String FILE_PATH) {
        try {
            // Проверка наличия файла, если его нет - создаем
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }

            // Сериализация HashMap в файл
            FileOutputStream fileOut = new FileOutputStream(FILE_PATH);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(hashMap);
            out.close();
            fileOut.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static HashMap<String, String> deserializeHashMap(String FILE_PATH) {
        try {
            // Десериализация HashMap из файла
            FileInputStream fileIn = new FileInputStream(FILE_PATH);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            HashMap<String, String> hashMap = (HashMap<String, String>) in.readObject();
            in.close();
            fileIn.close();
            return hashMap;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }
}
