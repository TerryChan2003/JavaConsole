package module;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class FileUsing {
    static void fileWrite(String file, ArrayList<String> text) throws IOException {
        BufferedWriter wri = new BufferedWriter(new FileWriter(file, true));
        StringBuilder k = new StringBuilder();
        for (int i = 2; i < text.size(); i++) {
            if (text.get(i).equals("fn")) {
                k.append("\r\n");
            } else {
                k.append(text.get(i).concat(" "));
            }
        }
        wri.write(k.toString());
        wri.flush();
        wri.close();
    }

    static void fileRead(String file) {
        Scanner scan;
        try {
            scan = new Scanner(new File(file));
            while (scan.hasNextLine()) {
                System.out.println(scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }
    }
}
