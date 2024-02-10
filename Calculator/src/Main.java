import java.io.IOException;

import java.util.Scanner;
public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите пример из 2х операндов и 1 оператора, используя римские или арабские цифры от 1(I) до 10(X):");
        String primer = sc.nextLine();
        String result1 = calc (primer);
        System.out.println(result1);
    }

    public static String calc(String primer) throws IOException {
        String result = "";
        int oper = 0;
        String[] primerRazd = primer.split("[+\\-/*]");
        int x = primerRazd.length;
        if (x > 2) {

            throw new IOException("Формат математической операции не удовлетворяет заданию. Максимум 2 опперанда");


        } else if (x < 2) {

            throw new IOException("Строка не является математической опперацией");

        }


        int x1;
        String[] primerRazd1 = primer.split("[+]");
        x1 = primerRazd1.length;
        if (x1 > 1) {
            oper = 1; // +
        }
        primerRazd1 = primer.split("[-]");
        x1 = primerRazd1.length;
        if (x1 > 1) {
            oper = 2; // -
        }
        primerRazd1 = primer.split("[/]");
        x1 = primerRazd1.length;
        if (x1 > 1) {
            oper = 3; // /
        }
        primerRazd1 = primer.split("[*]");
        x1 = primerRazd1.length;
        if (x1 > 1) {
            oper = 4; // *
        }

        String operand1St = primerRazd[0];
        operand1St = operand1St.trim();
        String operand2St = primerRazd[1];
        operand2St = operand2St.trim();


        String[] romanKit = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] arabKit = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        boolean provarab1 = false;
        boolean provarab2 = false;
        boolean provroman1 = false;
        boolean provroman2 = false;
        for (int i = 0; i < 10; i++) {
            if (!provarab1) {
                provarab1 = operand1St.equals(arabKit[i]);
            }
            if (!provarab2) {
                provarab2 = operand2St.equals(arabKit[i]);
            }
        }
        if (operand2St.equals("0")) {
            provarab2 = true;
        }
        int operand1R = 0;
        int operand2R = 0;
        for (int i = 0; i < 10; i++) {
            if (!provroman1) {
                provroman1 = operand1St.equals(romanKit[i]);
                operand1R = i + 1;
            }
            if (!provroman2) {
                provroman2 = operand2St.equals(romanKit[i]);
                operand2R = i + 1;
            }
        }
        if (!provarab1 && !provarab2 && !provroman1 && !provroman2) {

            throw new IOException("Оба опперанда не удовлетворяют условию. Используйте 2 римских или арабских числа от 1(I) до 10(X) для получения результата");

        }
        if ((provarab1 && (!provarab2 && !provroman1 && !provroman2)) || (provarab2 && (!provarab1 && !provroman1 && !provroman2)) || (provroman2 && (!provarab1 && !provarab2 && !provroman1)) || (provroman1 && (!provarab1 && !provarab2 && !provroman2))) {

            throw new IOException("Один из опперандов не удовлетворяет условию. Используйте 2 римских или арабских числа от 1(I) до 10(X) для получения результата");

        }
        if ((provarab1 && (provroman1 || provroman2)) || (provarab2 && (provroman1 || provroman2))) {

            throw new IOException("Опперанды должны быть одной системы счисления. Используйте 2 римских или арабских числа от 1(I) до 10(X) для получения результата");

        }
        int answer = 0;
        if ((provarab2 == provarab1) && (provarab1)) {

            int operand1 = Integer.parseInt(operand1St);
            int operand2 = Integer.parseInt(operand2St);


            if (oper == 1) {
                answer = operand1 + operand2;
            }
            if (oper == 2) {
                answer = operand1 - operand2;
            }
            if (oper == 3) {
                if (operand2 == 0) {

                    throw new IOException("Опперанды арабской системы счисления должны быть от 1 до 10. (+на ноль делить нельзя)");

                }
                answer = operand1 / operand2;
            }
            if (oper == 4) {
                answer = operand1 * operand2;
            }

            result = ("Ваш ответ: " + answer);
        }

        if (provroman2 && provroman1) {
            if (oper == 1) {
                answer = operand1R + operand2R;
            }
            if (oper == 2) {
                answer = operand1R - operand2R;
            }
            if (oper == 3) {
                answer = operand1R / operand2R;
            }
            if (oper == 4) {
                answer = operand1R * operand2R;
            }
            if (answer < 0) {

                throw new IOException("В римской системе нет отрицательных чисел");

            }
            if (answer == 0) {

                throw new IOException("В римской системе нет нуля:(");

            }
            int j = answer / 10;
            int q = answer % 10;
            String[] romanKitBig = {"X", "XX", "XXX", "Xl", "L", "LX", "LXX", "LXXX", "XC"};
            if (j == 10) {
                result = ("Ваш ответ: C");
            }
            if (j == 0) {
                result = ("Ваш ответ: " + romanKit[q - 1]);
            }
            if (j > 0 && j < 10) {
                if (q > 0) {
                    result = ("Ваш ответ: " + romanKitBig[j - 1] + romanKit[q - 1]);
                } else {
                    result = ("Ваш ответ: " + romanKitBig[j - 1]);
                }
            }
        }
        return result;
    }
}


