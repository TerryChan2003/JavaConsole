package module;

import java.io.IOException;
import java.util.ArrayList;

class Module {

    static class Comand {
        String name;
        Runnable command;
        String help;

        Comand(String name, Runnable command, String help) {
            this.name = name;
            this.command = command;
            this.help = help;
        }

        Comand(Runnable command, String help) {
            this.help = help;
            this.command = command;
        }
    }

    static ArrayList<String> decryptor(String crypt, char decrypt) {
        StringBuilder sum = new StringBuilder();
        char a;
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < crypt.length(); i++) {
            if ((a = crypt.charAt(i)) != decrypt) {
                sum.append(a);
            } else {
                strings.add(sum.toString());
                sum = new StringBuilder();
            }
        }
        if (sum.length() > 0) {
            strings.add(sum.toString());
        }
        return strings;
    }

    static Comand toRead(ArrayList<String> forComands) {
        Comand help = null;
        switch (forComands.get(0)) {
            case "Написать":
                help = new Comand(forComands.get(1), () -> System.out.println(forComands.get(2)), forComands.get(3));
                break;
            case "Вопрос":
                ArrayList<ArrayList<String>> list = new ArrayList<>();
                ArrayList<String> s;
                s = decryptor(forComands.get(2), '?');
                String quest = s.get(0);
                s.remove(0);
                s = decryptor(s.get(0), '|');
                for (String value : s) {
                    list.add(decryptor(value, ':'));
                }
                ArrayList<String> Answer = new ArrayList<>();
                ArrayList<String> Message = new ArrayList<>();
                for (ArrayList<String> aList : list) {
                    for (int b = 0; b < aList.size(); b++) {
                        if (b % 2 == 0) {
                            Answer.add(aList.get(b));
                        } else {
                            Message.add(aList.get(b));
                        }
                    }
                }
                help = new Comand(forComands.get(1), () -> {
                    System.out.println(quest + "?");
                    for (int i = 0; i < Answer.size(); i++) {
                        System.out.println((i + 1) + " - " + Answer.get(i));
                    }
                    int as = 0;
                    try {
                        as = Integer.parseInt(Main.re.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < Answer.size(); i++) {
                        if (as == (i + 1)) {
                            System.out.println(Message.get(i));
                            break;
                        }
                    }
                }, forComands.get(3));
                break;
            case "Файл":
                break;
            default:
                System.out.println("Ошибка прочтения команды");
                break;
        }
        return help;
    }
}