package module;

import java.math.BigInteger;

class MathCommands {
    private static char[] digits = {
            '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b',
            'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'
    };

    private static BigInteger toTen(boolean com, int in, String byt) {
        int step;
        BigInteger j;
        BigInteger byter = BigInteger.valueOf(0);
        if (com) System.out.println("Форматировка числа:");
        for (int i = 0; i < byt.length(); i++) {
            for (int b = 0; b < in; b++) {
                if (byt.charAt(i) != '0' && byt.charAt(i) != '-') {
                    if (byt.charAt(i) == digits[b]) {
                        step = (byt.length() - 1 - i);
                        j = BigInteger.valueOf(in).pow(step);
                        j = j.multiply(BigInteger.valueOf(b));
                        if (com) {
                            if (i == 0) {
                                System.out.print(in + "^" + step + "*" + b + "(" + j + ")");
                            } else {
                                System.out.print(" + " + in + "^" + step + "*" + b + "(" + j + ")");
                            }
                        }
                        byter = byter.add(j);
                        break;
                    }
                }
            }
        }
        if (com) System.out.println(" = " + byter);
        return byter;
    }

    static StringBuilder ToHexer(boolean com, BigInteger byter, StringBuilder str, int to, String byt) {
        BigInteger[] jk;
        short ost;
        if (byter.toString().equals("0")) byter = new BigInteger(byt).abs();
        while (!byter.toString().equals("0")) {
            jk = byter.divideAndRemainder(BigInteger.valueOf(to));
            ost = Short.valueOf(String.valueOf(jk[1]));
            str.insert(0, digits[ost]);
            if (com)
                System.out.println("Вычисляем остаток: " + byter + " / " + to + " = " + jk[0] + " (остаток " + ost + ", в системе " + digits[ost] + ").");
            byter = jk[0];
        }
        return str;
    }

    static String toBiter(int in, int to, String byt, boolean com) throws Exception {
        StringBuilder str = new StringBuilder("");
        BigInteger byter;
        byter = BigInteger.valueOf(0);
        boolean isTenIn = in == 10;
        boolean isTenTo = to == 10;
        if (to >= 2 && to <= digits.length) {
            if (in >= 2 && in <= digits.length) {
                if (!isTenIn) byter = toTen(com, in, byt);
                if (!isTenTo) str = ToHexer(com, byter, str, to, byt);
                if (isTenTo && com) str.insert(0, "Получаем: " + byter);
                if (com && !isTenTo) str.insert(0, "Соединяем остатки снизу вверх, получаем: ");
                return str.toString();
            } else System.out.println("Неверный ввод первого параметра");
        } else System.out.println("Неверный ввод второго параметра");
        return str.toString();
    }


    static BigInteger factorial(int g) {
        return g > 1 ? factorial(g - 1).multiply(BigInteger.valueOf(g)) : BigInteger.valueOf(1);
    }

    static void ToDelInt(int n) {
        int i = 2;
        while (n != 1 || i < n) {
            if (n % i == 0) {
                System.out.print(i + " ");
                n /= i;
            } else {
                i++;
            }
        }
        System.out.println();
    }
}
