import java.util.Stack;

public class Robot {
    int positionX;
    int positionY;
    char map[][];
    Izgara izg;

    private int[][] izler;
    private int izSayisi;
    int satirSayisi;
    int sutunSayisi;
    Engel engel;

    Robot(Izgara izgara) throws Exception {
        map = izgara.map;
        izg = izgara;
        findPosition(izgara);
    }

    Robot(Engel en, int satir, int sutun) {
        engel = en;
        sutunSayisi=sutun;
        satirSayisi=satir;
    }
    void cozLabirent(){
        izler = new int[satirSayisi][sutunSayisi];
        izSayisi = 1;

        for (int i = 0; i < satirSayisi; i++) {
            for (int j = 0; j < sutunSayisi; j++) {
                izler[i][j] = 0;
            }
        }

        // Labirentin başlangıç koordinatları
        int robotSatir = 0;
        int robotSutun = 0;

        // Labirentteki giriş ve çıkış koordinatları
        int girisSatir = 0;
        int girisSutun = 0;
        int cikisSatir = satirSayisi - 1;
        int cikisSutun = sutunSayisi - 1;

        // Giriş ve çıkışın engel olmadığından emin olun


        // Derinlik öncelikli arama algoritması kullanarak labirenti çöz

        Stack<Dugum> stack = new Stack<>();
        Dugum dugum = new Dugum(robotSatir, robotSutun);
        stack.push(dugum);

        while (!stack.isEmpty()) {
            dugum = stack.pop();

            if (dugum.getSatir() == cikisSatir && dugum.getSutun() == cikisSutun) {
                break;
            }

            int satir = dugum.getSatir();
            int sutun = dugum.getSutun();

            if (satir >= 0 && sutun >= 0 && satir < satirSayisi && sutun < sutunSayisi && izler[satir][sutun] == 0 && engel.engelDurumu(satir, sutun) == false) {
                izler[satir][sutun] = izSayisi;
                izSayisi++;
                engel.engeller[satir][sutun]=2;
                stack.push(new Dugum(satir, sutun - 1)); // Sola git
                stack.push(new Dugum(satir, sutun + 1)); // Sağa git

                stack.push(new Dugum(satir - 1, sutun)); // Yukarı git
                stack.push(new Dugum(satir + 1, sutun)); // Aşağı git
            }
        }

    }

    boolean obstacle(int direction){
        findPosition(izg);
        if ((map[positionY - 1][positionX] == '1' || map[positionY-1][positionX] == '2'
                || map[positionY-1][positionX] == '3') && direction == 1){
            return (true);
        }
        if ((map[positionY + 1][positionX] == '1' || map[positionY + 1][positionX] == '2'
                || map[positionY + 1][positionX] == '3') && direction == 2)
                return true;
        if ((map[positionY][positionX-1] == '1' || map[positionY][positionX-1] == '2'
                || map[positionY][positionX - 1] == '3') && direction == 3)
            return true;
        if ((map[positionY][positionX+1] == '1' || map[positionY][positionX+1] == '2'
                || map[positionY][positionX+1] == '3') && direction == 4)
            return true;

        return (false);
    }

    void findPosition(Izgara izgara){
        for (int i = 0; i < izgara.row; i++) {
            for (int j = 0; j < izgara.column; j++) {
                if (map[i][j] == '4'){
                    positionY = i;
                    positionX = j;
                }
            }
        }
    }

    void moveUp(){
        if (obstacle(1))
            return;
        map[positionY][positionX] = '6';
        map[positionY - 1][positionX] = '4';
        positionY = positionY - 1;
    }
    void moveDown(){
        if (obstacle(2))
            return;
        map[positionY][positionX] = '6';
        map[positionY + 1][positionX] = '4';
        positionY = positionY + 1;
    }
    void moveLeft() {
        if (obstacle(3))
            return;
        map[positionY][positionX] = '6';
        map[positionY][positionX - 1] = '4';
        positionX = positionX - 1;
    }
    void moveRight(){
        if (obstacle(4))
            return;
        map[positionY][positionX] = '6';
        map[positionY][positionX + 1] = '4';
        positionX = positionX + 1;
    }

    public void izleriYazdir() {
        System.out.println("Izler:");
        for (int i = 0; i < izler.length; i++) {
            for (int j = 0; j < izler[0].length; j++) {
                System.out.print(izler[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void hedefeUlasmaSuresiYazdir() {
        int hedefIz = izler[izler.length - 1][izler[0].length - 1];
    }

    class Dugum {
        private int satir;
        private int sutun;

        public Dugum(int satir, int sutun) {
            this.satir = satir;
            this.sutun = sutun;
        }

        public int getSatir() {
            return satir;
        }

        public int getSutun() {
            return sutun;
        }
    }
}
