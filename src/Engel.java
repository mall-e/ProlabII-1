import java.util.Random;

public class Engel {

    int[][] engeller;

    char labirent[][];

    Engel(char map[][]){
        labirent = map;
    }

    Engel(int satirSayisi, int sutunSayisi) {
        this.engeller = new int[satirSayisi][sutunSayisi];

        // tüm hücreleri engelli olarak işaretle
        for (int i = 0; i < satirSayisi; i++) {
            for (int j = 0; j < sutunSayisi; j++) {
                this.engeller[i][j] = 1;
            }
        }
        // giriş ve çıkış noktalarını belirle
        int girisSatir = 0;
        int girisSutun = 0;
        int cikisSatir = satirSayisi - 1;
        int cikisSutun = sutunSayisi - 1;

        // giriş ve çıkış noktalarını engellerden kaldır
        this.engeller[girisSatir][girisSutun] = 0;
        this.engeller[cikisSatir][cikisSutun] = 0;

        // giriş ve çıkış arasında bir yol oluştur
        Random rand = new Random();
        int yatay = girisSutun;
        int dikey = girisSatir;
        while (yatay != cikisSutun || dikey != cikisSatir) {
            if (yatay == cikisSutun) {
                dikey++;
            } else if (dikey == cikisSatir) {
                yatay++;
            } else {
                if (rand.nextBoolean()) {
                    yatay++;
                } else {
                    dikey++;
                }
            }
            engeller[dikey][yatay] = 0;
        }
    }

    void find3(){
        Random random = new Random();
        char sayi;
        for (int i = 0; i < labirent.length; i++) {
            for (int j = 0; j < labirent[0].length; j++) {
                sayi = (char)(random.nextInt(0,2) + '0');
                if (labirent[i][j] == '3' || labirent[i][j] == '2')
                    labirent[i][j] = sayi;
                if (sayi == '1')
                    j+=2;
            }
        }
        System.out.println();
        System.out.println();
        for (int i = 0; i < labirent.length; i++) {
            System.out.println(labirent[i]);
        }
    }

    public void engelKaldir(int satir, int sutun) {
        engeller[satir][sutun] = 0;
    }

    public void engelEkle(int satir, int sutun) {
        engeller[satir][sutun] = 1;
    }

    public boolean engelDurumu(int satir, int sutun) {
        return engeller[satir][sutun] == 1;
    }

    public int getDeger(int satir, int sutun) {
        return engeller[satir][sutun];
    }

    public int engelSayisi() {
        int sayac = 0;
        for (int i = 0; i < engeller.length; i++) {
            for (int j = 0; j < engeller[0].length; j++) {
                if (engeller[i][j] == 1) {
                    sayac++;
                }
            }
        }
        return sayac;
    }


        public boolean cikisaUlasilamazMi(int satir, int sutun) {
        if (satir < 0 || satir >= engeller.length || sutun < 0 || sutun >= engeller[0].length || engeller[satir][sutun] == 1) {
            // Bu hücre, engel olarak işaretlenmiş veya labirentin dışında
            return true;
        } else if (satir == engeller.length - 1 && sutun == engeller[0].length - 1) {
            // Bu hücre, çıkış hücresi
            return false;
        } else {
            // Bu hücre, engel değil ve çıkış hücresi de değil.
            // Bu hücrenin komşularını kontrol et ve recursive bir şekilde işlem yaparak çıkışa ulaşılamayacak hücreleri bul.
            engeller[satir][sutun] = 1; // Hücreyi ziyaret edildi olarak işaretle
            boolean yukari = cikisaUlasilamazMi(satir - 1, sutun);
            boolean asagi = cikisaUlasilamazMi(satir + 1, sutun);
            boolean sol = cikisaUlasilamazMi(satir, sutun - 1);
            boolean sag = cikisaUlasilamazMi(satir, sutun + 1);
            return yukari && asagi && sol && sag;
        }
    }
    public void rastgeleEngellerEkle(int engelSayisi) {
        Random rand = new Random();
        int sayac = 0;
        while (sayac < engelSayisi) {
            int satir = rand.nextInt(engeller.length);
            int sutun = rand.nextInt(engeller[0].length);
            if (engeller[satir][sutun] == 1) {
                // Bu hücre henüz engel olarak işaretlenmemiş, engel olarak işaretle
                engeller[satir][sutun] = 0;
                if (engeller.length > satir + 1 && engeller.length > satir + 2)
                {
                    engeller[satir+1][sutun] = 0;
                    engeller[satir+2][sutun] = 0;
                }

                if (engeller[0].length > sutun + 1 && engeller[0].length > sutun + 2)
                {
                    engeller[satir][sutun+1] = 0;
                    engeller[satir][sutun+2] = 0;
                }

                if (2 < sutun)
                {
                    engeller[satir][sutun-1] = 0;
                }
                if (2 < satir)
                {
                    engeller[satir-1][sutun] = 0;
                }

                sayac++;
            }
        }
    }

}
