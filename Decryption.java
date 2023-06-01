//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



import java.util.ArrayList;
import java.util.Collections;

public class Decryption {
    int[] IP = new int[]{2, 6, 3, 1, 4, 8, 5, 7};
    int[] IP_1 = new int[]{4, 1, 3, 5, 7, 2, 8, 6};
    int[] EP = new int[]{4, 1, 2, 3, 2, 3, 4, 1};
    int[] P4 = new int[]{2, 4, 3, 1};
    int[][] S0 = new int[][]{{1, 0, 3, 2}, {3, 2, 1, 0}, {0, 2, 1, 3}, {3, 1, 3, 2}};
    int[][] S1 = new int[][]{{0, 1, 2, 3}, {2, 0, 1, 3}, {3, 0, 1, 0}, {2, 1, 0, 3}};
    ArrayList<Integer> store = new ArrayList();
    ArrayList<Integer> Dec_IP = new ArrayList();
    ArrayList<Integer> Dec_EP = new ArrayList();
    ArrayList<Integer> Dec_S = new ArrayList();
    ArrayList<Integer> Dec_Encry = new ArrayList();
    ArrayList<Integer> Switch_Dec_EP = new ArrayList();
    ArrayList<Integer> Switch_Dec_S = new ArrayList();
    ArrayList<Integer> Switch_Dec_Encry = new ArrayList();
    ArrayList<Integer> target_bit = new ArrayList();
    ArrayList<String> target_text = new ArrayList();
    Encryption e;

    public Decryption() {
        int count = 0;

        while(count != 15) {
            this.Decryt();
            this.Dec_IP.clear();
            this.Dec_EP.clear();
            this.Dec_S.clear();
            this.Dec_Encry.clear();
            this.Switch_Dec_EP.clear();
            this.Switch_Dec_S.clear();
            this.Switch_Dec_Encry.clear();
            this.target_bit.clear();
            ++count;
            System.out.println("남은 입력수는 " + (15 - count) + "입니다");
        }

        System.out.println("프로그램을 종료합니다");
    }

    public void Decryt() {
        Encryption e = new Encryption();
        System.out.println(e.Star + " 복호화 해야할 비트");

        int i;
        int j;
        for(i = 0; i < 8; ++i) {
            for(j = 1; j < this.IP.length + 1; ++j) {
                if (this.IP[i] == j) {
                    this.Dec_IP.add((Integer)e.Star.get(j - 1));
                }
            }
        }

        for(i = 0; i < 8; ++i) {
            e.Star.remove(0);
        }

        System.out.println(this.Dec_IP + " : 복호화에서 IP행렬을 거침");

        for(i = 0; i < this.Dec_IP.size(); ++i) {
            for(j = 1; j < this.EP.length / 2 + 1; ++j) {
                if (this.EP[i] == j) {
                    this.Dec_EP.add((Integer)this.Dec_IP.get(3 + j));
                }
            }
        }

        System.out.println(this.Dec_EP + " : 복호화에서 EP행렬을 거침");

        for(i = 0; i < this.Dec_EP.size(); ++i) {
            if (this.Dec_EP.get(i) == e.key2.get(i)) {
                this.Dec_S.add(0);
            } else {
                this.Dec_S.add(1);
            }
        }

        System.out.println(e.key2 + " : KEY2");
        System.out.println(this.Dec_S + " : S_BOX전");
        e.s_box(this.Dec_S);

        for(i = 0; i < this.Dec_IP.size() / 2; ++i) {
            if (this.Dec_IP.get(i) == e.Encry.get(i)) {
                this.Dec_Encry.add(0);
            } else {
                this.Dec_Encry.add(1);
            }
        }

        System.out.println(this.Dec_Encry + "S_BOX를 거침");

        for(i = 1; i < this.Dec_IP.size() / 2 + 1; ++i) {
            this.Dec_Encry.add((Integer)this.Dec_IP.get(3 + i));
        }

        System.out.println(this.Dec_Encry + "switch전 ");

        for(i = 0; i < 4; ++i) {
            Collections.swap(this.Dec_Encry, i, i + 4);
        }

        System.out.println(this.Dec_Encry + "switch후 ");

        for(i = 0; i < this.Dec_Encry.size(); ++i) {
            for(j = 1; j < this.EP.length / 2 + 1; ++j) {
                if (this.EP[i] == j) {
                    this.Switch_Dec_EP.add((Integer)this.Dec_Encry.get(3 + j));
                }
            }
        }

        System.out.println(e.key1 + " : KEY1");
        System.out.println(this.Switch_Dec_EP + ":Switch하고 EP를 거침");

        for(i = 0; i < this.Switch_Dec_EP.size(); ++i) {
            if (this.Switch_Dec_EP.get(i) == e.key1.get(i)) {
                this.Switch_Dec_S.add(0);
            } else {
                this.Switch_Dec_S.add(1);
            }
        }

        System.out.println(this.Switch_Dec_S + "S_BOX전");
        e.s_box(this.Switch_Dec_S);

        for(i = 0; i < this.Dec_Encry.size() / 2; ++i) {
            if (this.Dec_Encry.get(i) == e.Encry.get(i)) {
                this.Switch_Dec_Encry.add(0);
            } else {
                this.Switch_Dec_Encry.add(1);
            }
        }

        for(i = 4; i < 8; ++i) {
            this.Switch_Dec_Encry.add((Integer)this.Dec_Encry.get(i));
        }

        for(i = 0; i < this.Switch_Dec_Encry.size(); ++i) {
            for(j = 1; j < this.IP_1.length + 1; ++j) {
                if (this.IP_1[i] == j) {
                    this.target_bit.add((Integer)this.Switch_Dec_Encry.get(j - 1));
                }
            }
        }

        System.out.println(this.target_bit + " 마지막 복호화된 비트 ");
        String a = "";

        for(j = 0; j < this.target_bit.size(); ++j) {
            a = a + String.valueOf(this.target_bit.get(j));
        }

        j = Integer.parseInt(a, 2);
        this.store.add(j);
        char re_text = (char)j;
        System.out.println(this.store + "입력하신 문자의 10진수");
        System.out.println(re_text);
    }
}
