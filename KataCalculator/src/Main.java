import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите выражение:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();              // ввод с клавиатуры


        System.out.println(calc(input));
    }

    public static String calc(String input){
        String[] romanArray = {"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"
                , "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX"
                , "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX"
                , "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL"
                , "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L"
                ,"LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX"
                ,"LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX"
                ,"LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX"
                ,"LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXIII", "LXXXIX", "XC"
                , "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

        String operation = null;
        int num1, num2;
        boolean romanNum1 = false, romanNum2 = false;

        String [] strings = input.split(" ");

        try{
            operation = strings[1];
        } catch (ArrayIndexOutOfBoundsException e){
            return "Строка не является математической операцией.";
        }


        if (strings.length < 3){
            return "Строка не является математической операцией.";
        }
        if (strings.length > 3){
            return "формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *).";
        }


        try{
            num1 = Integer.parseInt (strings[0]);
        } catch (NumberFormatException e){
            num1 = Roman.isRoman(strings[0], romanArray);
            romanNum1 = true;
            }

        try{
            num2 = Integer.parseInt (strings[2]);
        } catch (NumberFormatException e){
            num2 = Roman.isRoman(strings[2], romanArray);
            romanNum2 = true;
        }


        if(num1 > 10 || num1 <1 || num2 > 10 || num2 < 1 || num1 == 101 || num2 == 101){
            return "Вы ввели некорректные значения. Введите пожалуйста числа от 1 до 10 включительно (Римские или арабские).";
        }

        if(romanNum1!=romanNum2){
            return "Используются одновременно разные системы счисления.";
        }

        int result = 0;

        switch (operation){
            case "+":
                result = num1 + num2;
                break;
            case "-":
                if(romanNum1 == true && num1 - num2 < 1){
                    return "В римской системе нет отрицательных чисел. Выполните другую операцию или введите другие числа.";
                }
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
        }
        if(romanNum1 == true){
            return Roman.converterRoman(result, romanArray);
        }

        return Integer.toString(result);
    }

    class Roman {
        static String converterRoman(int i, String[] romanArray){
            return romanArray[i];
        }
        static int isRoman(String num, String[] romanArray){
            for (int i = 0; i < romanArray.length; i++){
                if( romanArray[i].equals(num)){
                    return i;
                }
            }
            return 101;
        }
    }
}