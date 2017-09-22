package module;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class Mathic {
    private final int down;
    private int up;
    private int ceil = 0;

    private Mathic(int up, int down) {
        this.up = up;
        this.down = down;
    }

    private Mathic(int up, int down, int ceil) {
        this.up = up;
        this.down = down;
        this.ceil = ceil;
    }

    private static HashMap<Integer, Integer> ToStrip(ArrayList<Integer> a) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        Integer am;
        for (Integer i : a) {
            am = hm.get(i);
            hm.put(i, am == null ? 1 : am + 1);
        }
        return hm;
    }

    private static ArrayList<Integer> ToDelInt(int a) {
        int i = 2;
        ArrayList<Integer> list = new ArrayList<>();
        boolean minus = a < 0;
        a = Math.abs(a);
        while (a != 1 || i <= a) {
            if (a % i == 0) {
                list.add(i);
                a /= i;
            } else {
                i++;
            }
        }
        if (minus) list.add(-1);
        return list;
    }

    public String toString() {
        return this.ceil + " Целых, и " + this.up + " деленное на " + this.down;
    }

    private Mathic plus(Mathic right) {
        Mathic[] jk = compare(this, right);
        return (new MathicIn(ToDelInt(jk[0].up + jk[1].up), ToDelInt(jk[0].down))).scrub().ToSlow();
    }

    private Mathic minus(Mathic right) {
        Mathic[] jk = compare(this, right);
        return (new MathicIn(ToDelInt(jk[0].up - jk[1].up), ToDelInt(jk[0].down))).scrub().ToSlow();
    }

    private Mathic[] compare(Mathic left, Mathic right) {
        MathicIn left1 = (new MathicIn(ToDelInt(left.up), ToDelInt(left.down))).scrub();
        MathicIn right1 = (new MathicIn(ToDelInt(right.up), ToDelInt(right.down))).scrub();
        MathicIn[] j = left1.ToRound(right1);
        return new Mathic[]{j[0].ToSlow(), j[1].ToSlow()};
    }

    private void ToInt() {
        this.ceil += this.up / this.down;
        this.up %= this.down;
    }

    class MathicIn {
        private final ArrayList<Integer> up;
        private final ArrayList<Integer> down;

        MathicIn(ArrayList<Integer> up, ArrayList<Integer> down) {
            this.up = up;
            this.down = down;
        }

        MathicIn scrub() {
            HashMap<Integer, Integer> scrap = ToStrip(this.down);
            for (Map.Entry<Integer, Integer> lk : scrap.entrySet()) {
                if (this.up.contains(lk.getKey())) {
                    while (this.up.contains(lk.getKey()) && this.down.contains(lk.getKey())) {
                        this.down.remove(lk.getKey());
                        this.up.remove(lk.getKey());
                    }
                }
            }
            this.up.sort(Comparator.naturalOrder());
            this.down.sort(Comparator.naturalOrder());
            return this;
        }

        MathicIn[] ToRound(MathicIn b) {
            Integer am;
            Integer key;
            HashMap<Integer, Integer> a1 = ToStrip(this.down);
            HashMap<Integer, Integer> b1 = ToStrip(b.down);
            for (Map.Entry<Integer, Integer> k : a1.entrySet()) {
                key = k.getKey();
                if (b1.get(k.getKey()) == null) {
                    for (int i = 0; i < k.getValue(); i++) {
                        b.down.add(key);
                        b.up.add(key);
                    }
                } else if (b1.get(k.getKey()) < k.getValue()) {
                    am = b1.get(key);
                    for (int i = 0; i < k.getValue() - am; i++) {
                        b.down.add(key);
                        b.up.add(key);
                    }
                }
            }
            for (Map.Entry<Integer, Integer> k : b1.entrySet()) {
                key = k.getKey();
                if (a1.get(k.getKey()) == null) {
                    for (int i = 0; i < k.getValue(); i++) {
                        this.down.add(key);
                        this.up.add(key);
                    }
                } else if (b1.get(k.getKey()) < k.getValue()) {
                    am = a1.get(key);
                    for (int i = 0; i < k.getValue() - am; i++) {
                        this.down.add(key);
                        this.up.add(key);
                    }
                }
            }
            return new MathicIn[]{this, b};
        }

        Mathic ToSlow() {
            int up = 1;
            int down = 1;
            for (int jk : this.up) {
                up *= jk;
            }
            for (int jk : this.down) {
                down *= jk;
            }
            return new Mathic(up, down);
        }
    }

    /* FOR TESTING Library
    public static void main(String[] args) {
        Mathic l = new Mathic(4384,583).minus(new Mathic(3895,2845));
        l.ToInt();
        System.out.println(l);
    }
    */
}
