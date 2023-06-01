//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



import java.util.ArrayList;
import java.util.Random;

public class Key {
    Random r = new Random();
    int[] p10 = new int[10];
    int[] p8 = new int[8];
    int temp;
    ArrayList<Integer> key = new ArrayList();
    static ArrayList<Integer> P10_key = new ArrayList();
    ArrayList<Integer> P8_key_k1 = new ArrayList();
    ArrayList<Integer> P8_key_k2 = new ArrayList();

    public Key() {
        this.key.add(0);
        this.key.add(0);
        this.key.add(0);
        this.key.add(0);
        this.key.add(1);
        this.key.add(0);
        this.key.add(1);
        this.key.add(0);
        this.key.add(0);
        this.key.add(1);
        Random r = new Random();

        int i;
        int j;
        for(i = 0; i < 10; ++i) {
            this.p10[i] = r.nextInt(10) + 1;

            for(j = 0; j < i; ++j) {
                if (this.p10[i] == this.p10[j]) {
                    --i;
                }
            }
        }

        for(i = 0; i < this.key.size(); ++i) {
            for(j = 1; j < this.key.size() + 1; ++j) {
                if (this.p10[i] == j) {
                    P10_key.add((Integer)this.key.get(j - 1));
                }
            }
        }

        this.temp = (Integer)P10_key.get(0);
        P10_key.remove(0);
        P10_key.add(4, this.temp);
        this.temp = (Integer)P10_key.get(5);
        P10_key.remove(5);
        P10_key.add(9, this.temp);

        for(i = 0; i < 8; ++i) {
            this.p8[i] = r.nextInt(10) + 1;

            for(j = 0; j < i; ++j) {
                if (this.p8[i] == this.p8[j]) {
                    --i;
                }
            }
        }

        for(i = 0; i < this.p8.length; ++i) {
            for(j = 1; j < this.key.size() + 1; ++j) {
                if (this.p8[i] == j) {
                    this.P8_key_k1.add((Integer)P10_key.get(j - 1));
                }
            }
        }

        this.temp = (Integer)P10_key.get(0);
        P10_key.remove(0);
        P10_key.add(4, this.temp);
        this.temp = (Integer)P10_key.get(5);
        P10_key.remove(5);
        P10_key.add(9, this.temp);
        this.temp = (Integer)P10_key.get(0);
        P10_key.remove(0);
        P10_key.add(4, this.temp);
        this.temp = (Integer)P10_key.get(5);
        P10_key.remove(5);
        P10_key.add(9, this.temp);

        for(i = 0; i < this.p8.length; ++i) {
            for(j = 1; j < this.key.size() + 1; ++j) {
                if (this.p8[i] == j) {
                    this.P8_key_k2.add((Integer)P10_key.get(j - 1));
                }
            }
        }

    }

    void Shift(ArrayList<Integer> P10_key) {
        this.temp = (Integer)P10_key.get(0);
        P10_key.remove(0);
        P10_key.add(4, this.temp);
        this.temp = (Integer)P10_key.get(5);
        P10_key.remove(5);
        P10_key.add(9, this.temp);
    }
}
