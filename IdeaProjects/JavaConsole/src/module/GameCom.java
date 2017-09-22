package module;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static module.Main.Decoderer;

class GameCom {
    static void createGame(int min, int max) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in, Decoderer));
        System.out.println("Вы запустили игру угадай число. Если вы захотите выйти пишите \"exit\"");
        Random rnd = new Random();
        int random;
        int range;
        String read;
        int get;
        Set<Integer> geted = new HashSet<>();
        int antibag = 0;
        min = Math.abs(min);
        max = Math.abs(max);
        System.out.println("Если число подходит то пишите \"да\",если это меньше чем вы загадали пишите \"больше\" , а если наоборот то \"меньше\".");
        System.out.println("Но учтите то что если вы скажете больше 137 и меньше 67 программа закончит свою работу");
        label:
        while (true) {
            range = max - min;
            if (range < 0) {
                System.out.println("Максимальное не может быть меньше минимального");
                break;
            }
            while (true) {
                random = rnd.nextInt() % range;
                get = Math.abs(random) + min;
                if (!geted.contains(get)) {
                    break;
                }
                if (antibag == 25) {
                    break;
                }
                antibag++;
            }
            if (antibag == 25) {
                break;
            }
            antibag = 0;
            System.out.println(get);
            read = r.readLine();
            switch (read) {
                case "exit":
                    break label;
                case "да":
                    System.out.println("Ваше загаданное число: " + get);
                    break label;
                case "меньше":
                    max = get;
                    geted.add(get);
                    break;
                case "больше":
                    min = get;
                    geted.add(get);
                    break;
                default:
                    System.out.println("Повторите свою команду");
                    break;
            }
        }
        System.out.println("Игра закончилась");
    }
}
