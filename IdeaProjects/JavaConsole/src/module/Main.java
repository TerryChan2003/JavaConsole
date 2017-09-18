package module;

import module.Module.Comand;

import java.io.*;
import java.util.*;

import static module.Draws.getSquare;
import static module.FileUsing.fileRead;
import static module.FileUsing.fileWrite;
import static module.GameCom.createGame;
import static module.MathCommands.*;

class Main {
    private static void startConsole() throws Exception {
        while (!(kaut = Module.decryptor(re.readLine(), ' ')).get(0).equals("exit")) {
            try {
                comands.get(kaut.get(0)).command.run();
            } catch (Exception e) {
                try {
                    ToRead(kaut);
                } catch (Exception ex) {
                    System.out.println(kaut.get(0) + " - Ошибка прочтения команды");
                }
            }
        }
    }

    private static void initializeModule() {
        comands.put("привет", new Comand(() -> System.out.println("Hello World"), "Говорит вам первые слова программирования"));
        System.out.println("Приветвую тебя пользователь. Узнать все команды \"помощь\"");
    }

    private static void getReader(String decode) {
        try {
            re = new BufferedReader(new InputStreamReader(System.in, decode));
        } catch (UnsupportedEncodingException e) {
            System.out.println("Недопустимая кодировка");
        }
    }

    private static String getDecode() {
        System.out.println("Выберите кодировку:\n1. utf-8\n2. cp1251\n3. cp866");
        Decode.add("utf-8");
        Decode.add("cp1251");
        Decode.add("cp866");
        Scanner sc = new Scanner(System.in);
        String s;
        while (true) {
            try {
                s = Decode.get(sc.nextInt() - 1);
                break;
            } catch (Exception e) {
                System.out.println("Ошибка: Повторите ввод");
            }
        }
        return s;
    }

    static BufferedReader re = null;
    private static HashMap<String, Comand> comands = new HashMap<>();
    private static ArrayList<String> kaut;
    private static ArrayList<String> help = new ArrayList<>();
    private static ArrayList<String> Decode = new ArrayList<>();
    static String Decoderer = getDecode();

    private static void getModules() {
        Scanner file = null;
        try {
            file = new Scanner(new File("mycommands.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Файл модуля не найден");
        }
        assert file != null;
        while (file.hasNextLine()) {
            Comand me = Module.toRead(Module.decryptor(file.nextLine(), ';'));
            comands.put(me.name, me);
        }
    }

    private static void getHelp() {
        for (String s : help) {
            System.out.println(s);
        }
    }

    private static void getMustHave() {
        help.add("угадай_число (минимум) (максимум) - Угадывает число в таком интервале");
        help.add("считать_множители (число) - Выпишет число на множители");
        help.add("факториал (число) - Выделяет факториал числа");
        help.add("перевод_числа (вводное) (выводное) (число вводного типа) - Переводит число из вводной системы в выводной");
        help.add("считать_файл (файл) - Считывает вам нужный файл");
        help.add("записать_в (файл) (значение) - Записывает значения в файл, перенос строки fn раздельно");
        help.add("exit - Для того чтобы выйти из консоли");
        help.add("сделать_квадрат (x) (y) (symbol) - Делает вам квадрат из символов");
        for (Map.Entry<String, Comand> set : comands.entrySet()) help.add(set.getKey() + " - " + set.getValue().help);
        help.sort(Comparator.naturalOrder());
    }

    private static void ToRead(ArrayList<String> list) throws Exception {
        switch (list.get(0)) {
            case "помощь":
                getHelp();
                break;
            case "сделать_квадрат":
                if (kaut.size() > 3)
                    try {
                        getSquare(Math.abs(Integer.parseInt(kaut.get(1))), Math.abs(Integer.parseInt(kaut.get(2))), kaut.get(3));
                    } catch (Exception e) {
                        System.out.println("Вы ввели недопустимые параметры для команды");
                    }
                else System.out.println("Что-то тут не хватает");
                break;
            case "записать_в":
                fileWrite(kaut.get(1), kaut);
                break;
            case "считать_файл":
                fileRead(kaut.get(1));
                break;
            case "считать_множители":
                ToDelInt(Integer.parseInt(kaut.get(1)));
                break;
            case "факториал":
                System.out.println((factorial(Integer.parseInt(kaut.get(1)))).toString());
                break;
            case "перевод_числа":
                System.out.println(toBiter(Integer.parseInt(kaut.get(1)), Integer.parseInt(kaut.get(2)), kaut.get(3), true));
                break;
            case "угадай_число":
                createGame(Integer.parseInt(kaut.get(1)), Integer.parseInt(kaut.get(2)));
                break;
            default:
                throw new Exception();
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("");
        getModules();
        getMustHave();
        getReader(Decoderer);
        initializeModule();
        startConsole();
    }
}