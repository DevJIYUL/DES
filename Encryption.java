//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Encryption {
    Key k = new Key();
    String text;
    String stop;
    char[] message;
    int[] IP = new int[]{2, 6, 3, 1, 4, 8, 5, 7};
    int[] IP_1 = new int[]{4, 1, 3, 5, 7, 2, 8, 6};
    int[] EP = new int[]{4, 1, 2, 3, 2, 3, 4, 1};
    int[] P4 = new int[]{2, 4, 3, 1};
    int[][] S0 = new int[][]{{1, 0, 3, 2}, {3, 2, 1, 0}, {0, 2, 1, 3}, {3, 1, 3, 2}};
    int[][] S1 = new int[][]{{0, 1, 2, 3}, {2, 0, 1, 3}, {3, 0, 1, 0}, {2, 1, 0, 3}};
    ArrayList<Integer> key1 = new ArrayList();
    ArrayList<Integer> key2 = new ArrayList();
    ArrayList<String> strToInt = new ArrayList();
    ArrayList<String> intToStr = new ArrayList();
    ArrayList<Integer> charToInt = new ArrayList();
    ArrayList<Integer> mat = new ArrayList();
    ArrayList<Integer> IP_mat = new ArrayList();
    ArrayList<Integer> EP_mat = new ArrayList();
    ArrayList<Integer> S_mat = new ArrayList();
    ArrayList<Integer> fk_mat = new ArrayList();
    ArrayList<Integer> Encry = new ArrayList();
    ArrayList<Integer> Switch_IP_mat = new ArrayList();
    ArrayList<Integer> Switch_EP_mat = new ArrayList();
    ArrayList<Integer> Switch_S_mat = new ArrayList();
    ArrayList<Integer> d = new ArrayList();
    ArrayList<Integer> Encry_result = new ArrayList();
    ArrayList<Integer> Switch_Encry_result = new ArrayList();
    ArrayList<Integer> Star = new ArrayList();

    public Encryption() {
        this.input();

        int i;
        for(i = 0; i < this.k.P8_key_k1.size(); ++i) {
            this.key1.add((Integer)this.k.P8_key_k1.get(i));
        }

        for(i = 0; i < this.k.P8_key_k2.size(); ++i) {
            this.key2.add((Integer)this.k.P8_key_k2.get(i));
        }

        while(this.d.size() > 0) {
            this.cutting();
            this.process();
            this.mat.clear();
            this.IP_mat.clear();
            this.EP_mat.clear();
            this.S_mat.clear();
            this.Switch_IP_mat.clear();
            this.Switch_EP_mat.clear();
            this.Switch_S_mat.clear();
            this.Encry_result.clear();
            this.Switch_Encry_result.clear();
        }

    }

    public void input() {
        Scanner scan = new Scanner(System.in);
        System.out.println("암호화 문자를 한 글자씩 입력하시요(Only English)");
        this.text = scan.nextLine();
        this.message = this.text.toCharArray();

        int i;
        for(i = 0; i < this.message.length; ++i) {
            System.out.print("[" + this.message[i] + "] : 암호화할 문자입니다");
        }

        for(i = 0; i < this.message.length; ++i) {
            this.charToInt.add(Integer.valueOf(this.message[i]));
        }

        System.out.println();
        System.out.println(this.charToInt + "암호화 할 문자의 10진수입니다");

        for(i = 0; i < this.charToInt.size(); ++i) {
            if ((Integer)this.charToInt.get(i) == 32) {
                this.strToInt.add("00" + Integer.toBinaryString((Integer)this.charToInt.get(i)));
            } else {
                this.strToInt.add("0" + Integer.toBinaryString((Integer)this.charToInt.get(i)));
            }
        }

        System.out.println(this.strToInt + "암호화 문자의 2진수");
        String a = "";

        for(i = 0; i < this.strToInt.size(); ++i) {
            a = (String)this.strToInt.get(i);

            for(int j = 0; j < a.length(); ++j) {
                this.d.add(Integer.parseInt(String.valueOf(a.charAt(j))));
            }
        }

    }

    public void cutting() {
        int i;
        for(i = 0; i < 8; ++i) {
            this.mat.add((Integer)this.d.get(i));
        }

        if (this.d.size() > 0) {
            for(i = 0; i < 8; ++i) {
                this.d.remove(0);
            }
        }

    }

    public void process() {
        int i;
        int j;
        for(i = 0; i < this.mat.size(); ++i) {
            for(j = 1; j < this.IP.length + 1; ++j) {
                if (this.IP[i] == j) {
                    this.IP_mat.add((Integer)this.mat.get(j - 1));
                }
            }
        }

        System.out.println(this.IP_mat + " IP행렬을 거친 암호문");

        for(i = 0; i < this.IP_mat.size(); ++i) {
            for(j = 1; j < this.EP.length / 2 + 1; ++j) {
                if (this.EP[i] == j) {
                    this.EP_mat.add((Integer)this.IP_mat.get(3 + j));
                }
            }
        }

        System.out.println("EP는 EP행렬을 거친 암호문입니다");
        System.out.println(this.EP_mat + " : EP를거친 암호문 ");
        System.out.println(this.k.P8_key_k1 + " KEY1입니다");
        System.out.println("EP와 KEY1의 xor연산");

        for(i = 0; i < this.EP_mat.size(); ++i) {
            if (this.EP_mat.get(i) == this.k.P8_key_k1.get(i)) {
                this.S_mat.add(0);
            } else {
                this.S_mat.add(1);
            }
        }

        System.out.println(this.S_mat + "EP_mat과 p8.key_k1의 xor연산결과");
        System.out.println("S_box 시작");
        this.s_box(this.S_mat);
        System.out.println(this.Encry + "S_BOX의 결과");

        for(i = 0; i < this.IP_mat.size() / 2; ++i) {
            if (this.IP_mat.get(i) == this.Encry.get(i)) {
                this.Encry_result.add(0);
            } else {
                this.Encry_result.add(1);
            }
        }

        for(i = 4; i < 8; ++i) {
            this.Encry_result.add((Integer)this.IP_mat.get(i));
        }

        System.out.println(this.Encry_result + " : Switch 전");

        for(i = 0; i < 4; ++i) {
            Collections.swap(this.Encry_result, i, i + 4);
        }

        System.out.println(this.Encry_result + " : Switch 후 ");

        for(i = 0; i < this.Encry_result.size(); ++i) {
            for(j = 1; j < this.EP.length / 2 + 1; ++j) {
                if (this.EP[i] == j) {
                    this.Switch_EP_mat.add((Integer)this.Encry_result.get(3 + j));
                }
            }
        }

        System.out.println(this.Switch_EP_mat + " Switch되고 EP행렬을 거친 암호문");
        System.out.println(this.k.P8_key_k2 + " : KEY2");

        for(i = 0; i < this.Switch_EP_mat.size(); ++i) {
            if (this.Switch_EP_mat.get(i) == this.k.P8_key_k2.get(i)) {
                this.Switch_S_mat.add(0);
            } else {
                this.Switch_S_mat.add(1);
            }
        }

        System.out.println("Switch_EP과 KEY2의 xor연산결과");
        System.out.println(this.Switch_S_mat + "Switch된 S_BOX전");
        System.out.println("S_box 시작");
        this.s_box(this.Switch_S_mat);
        System.out.println(this.Encry_result + " : S_BOX결과");

        for(i = 0; i < this.Encry_result.size() / 2; ++i) {
            if (this.Encry_result.get(i) == this.Encry.get(i)) {
                this.Switch_Encry_result.add(0);
            } else {
                this.Switch_Encry_result.add(1);
            }
        }

        for(i = 4; i < 8; ++i) {
            this.Switch_Encry_result.add((Integer)this.Encry_result.get(i));
        }

        System.out.println(this.Switch_Encry_result);

        for(i = 0; i < this.Switch_Encry_result.size(); ++i) {
            for(j = 1; j < this.IP_1.length + 1; ++j) {
                if (this.IP_1[i] == j) {
                    this.Star.add((Integer)this.Switch_Encry_result.get(j - 1));
                }
            }
        }

        System.out.println(this.Star + " 완성된 암호문 ");
        System.out.println("---------------------------------------");
    }

    public ArrayList<Integer> s_box(ArrayList<Integer> S_mat) {
        byte s0_x;
        if ((Integer)S_mat.get(0) == 0 && (Integer)S_mat.get(3) == 0) {
            s0_x = 0;
        } else if ((Integer)S_mat.get(0) == 0 && (Integer)S_mat.get(3) == 1) {
            s0_x = 1;
        } else if ((Integer)S_mat.get(0) == 1 && (Integer)S_mat.get(3) == 0) {
            s0_x = 2;
        } else {
            s0_x = 3;
        }

        byte s0_y;
        if ((Integer)S_mat.get(1) == 0 && (Integer)S_mat.get(2) == 0) {
            s0_y = 0;
        } else if ((Integer)S_mat.get(1) == 0 && (Integer)S_mat.get(2) == 1) {
            s0_y = 1;
        } else if ((Integer)S_mat.get(1) == 1 && (Integer)S_mat.get(2) == 0) {
            s0_y = 2;
        } else {
            s0_y = 3;
        }

        int i = this.S0[s0_x][s0_y];
        System.out.println(i + " i의 값");
        if (i == 0) {
            this.fk_mat.add(0);
            this.fk_mat.add(0);
        } else if (i == 1) {
            this.fk_mat.add(0);
            this.fk_mat.add(1);
        } else if (i == 2) {
            this.fk_mat.add(1);
            this.fk_mat.add(0);
        } else {
            this.fk_mat.add(1);
            this.fk_mat.add(1);
        }

        byte s1_x;
        if ((Integer)S_mat.get(4) == 0 && (Integer)S_mat.get(7) == 0) {
            s1_x = 0;
        } else if ((Integer)S_mat.get(4) == 0 && (Integer)S_mat.get(7) == 1) {
            s1_x = 1;
        } else if ((Integer)S_mat.get(4) == 1 && (Integer)S_mat.get(7) == 0) {
            s1_x = 2;
        } else {
            s1_x = 3;
        }

        byte s1_y;
        if ((Integer)S_mat.get(5) == 0 && (Integer)S_mat.get(6) == 0) {
            s1_y = 0;
        } else if ((Integer)S_mat.get(5) == 0 && (Integer)S_mat.get(6) == 1) {
            s1_y = 1;
        } else if ((Integer)S_mat.get(5) == 1 && (Integer)S_mat.get(6) == 0) {
            s1_y = 2;
        } else {
            s1_y = 3;
        }

        int j = this.S1[s1_x][s1_y];
        System.out.println(j + " : j의 값");
        if (j == 0) {
            this.fk_mat.add(0);
            this.fk_mat.add(0);
        } else if (j == 1) {
            this.fk_mat.add(0);
            this.fk_mat.add(1);
        } else if (j == 2) {
            this.fk_mat.add(1);
            this.fk_mat.add(0);
        } else {
            this.fk_mat.add(1);
            this.fk_mat.add(1);
        }

        System.out.println(this.fk_mat + "fk_mat");
        this.Encry.clear();

        for(int a = 0; a < this.fk_mat.size(); ++a) {
            for(int b = 1; b < this.P4.length + 1; ++b) {
                if (this.P4[a] == b) {
                    this.Encry.add((Integer)this.fk_mat.get(b - 1));
                }
            }
        }

        System.out.println(this.Encry + ":Encry");
        this.fk_mat.clear();
        return this.Encry;
    }
}
