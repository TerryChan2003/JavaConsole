package module;

class Draws {
    static void getSquare(int x, int y, String sym) {
        for (int newy = 0; newy < y; newy++) {
            for (int newx = 0; newx < x; newx++) {
                System.out.print(sym);
            }
            System.out.println("");
        }
        System.out.println("Готово!");
    }
}
