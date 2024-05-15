import dictionaries.Dictionary;
import dictionaries.LatinDict;
import dictionaries.NumbsDict;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

     static Dictionary dictLatin = new LatinDict();
     static Dictionary dictNumbs = new NumbsDict();
    public static void main(String[] args) throws Exception {
        List<String> selNames = new ArrayList<>();
        selNames.add("Латинский-русский");
        selNames.add("Цифровой-русский");
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n***Выберите, с каким словарем Вы хотите работать:\n1 - латинский-русский,\n2 - цифры-русский\n");
        int selectDict = 0;
        while (true) {
            String userInput = scanner.nextLine();
            if (isInteger(userInput)) {
                int userNumber = Integer.parseInt(userInput);
                if (userNumber == 1 || userNumber == 2) {
                    selectDict = userNumber;
                    break;
                } else {
                    System.out.println("Требуется ввести число (1 или 2)!");
                }
            } else {
                System.out.println("Требуется ввести число (1 или 2)!");
            }
        }
        while(true){
            int mode = 0;
            System.out.println("\n###Выбранный словарь: " + selNames.get(selectDict-1));
            System.out.println("---Функции: \n1 - Добавить запись по ключу;\n2 - Удалить запись по ключу;\n3 - Поиск запись по ключу;\n4 - Выбрать другой словарь;\n5 - Вывести словарь; \n123 - Закрыть программу.");
            while (true){
                String userInput = scanner.nextLine();
                if (isInteger(userInput)) {
                    int userNumber = Integer.parseInt(userInput);
                    if (userNumber == 1 || userNumber == 2 || userNumber == 3 || userNumber == 4 || userNumber == 5 || userNumber == 123) {
                        mode = userNumber;
                        break;
                    } else {
                        System.out.println("Требуется ввести число!");
                    }
                } else {
                    System.out.println("Требуется ввести число!");
                }
            }
            if (mode == 1){
                while (true){
                    System.out.println("+++Требуется ввести ключ-слово и перевод.\n");
                    System.out.print("Ключ-слово: ");
                    String key = scanner.nextLine();
                    System.out.print("Перевод: ");
                    String value = scanner.nextLine();
                    try{
                        if(selectDict == 1)
                            dictLatin.WritePairToDict(key, value);
                        else
                            dictNumbs.WritePairToDict(key, value);
                        break;
                    }
                    catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                }
            }
            else if (mode == 2){
                while (true){
                    System.out.println("+++Требуется ввести ключ-слово для удаления.\n");
                    System.out.print("Ключ-слово: ");
                    String key = scanner.nextLine();
                    try{
                        if(selectDict == 1)
                            dictLatin.DelPairFromDict(key);
                        else
                            dictNumbs.DelPairFromDict(key);
                        break;
                    }
                    catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                }
            }
            else if (mode == 3){
                while (true){
                    System.out.println("+++Требуется ввести ключ-слово для просмотра перевода.\n");
                    System.out.print("Ключ-слово: ");
                    String key = scanner.nextLine();
                    try{
                        if(selectDict == 1)
                            dictLatin.SearchPairInDict(key);
                        else
                            dictNumbs.SearchPairInDict(key);
                        break;
                    }
                    catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                }
            }
            else if (mode == 4){
                System.out.println("\n***Выберите, с каким словарем Вы хотите работать:\n1 - латинский-русский,\n2 - цифры-русский\n");
                selectDict = 0;
                while (true) {
                    String userInput = scanner.nextLine();
                    if (isInteger(userInput)) {
                        int userNumber = Integer.parseInt(userInput);
                        if (userNumber == 1 || userNumber == 2) {
                            selectDict = userNumber;
                            break;
                        } else {
                            System.out.println("Требуется ввести число (1 или 2)!");
                        }
                    } else {
                        System.out.println("Требуется ввести число (1 или 2)!");
                    }
                }
            }
            else if (mode == 5){
                while (true){
                    System.out.println("\n");
                    try{
                        if(selectDict == 1)
                            dictLatin.ShowAllDict();
                        else
                            dictNumbs.ShowAllDict();
                    }
                    catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                }
            }
            else if (mode == 123){
                break;
            }
        }
    }

    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}