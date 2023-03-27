import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Problem2Page {
    JFrame fr = new JFrame();
    JPanel panel = new JPanel();
    int satir;
    int sutun;
    long gecenSure;
    int kareSayisi;

    Problem2Page(JFrame pFrame){

        JTextField txtSat = new JTextField("Satır sayısı giriniz");
        JTextField txtSut = new JTextField("Sütun sayısı giriniz");
        JButton btn = new JButton("OK");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                satir = Integer.parseInt(txtSat.getText());
                sutun = Integer.parseInt(txtSut.getText());
                try {
                    startProblem2();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        JButton btn2 = new JButton("Back");
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fr.setVisible(false);
                pFrame.setVisible(true);
            }
        });
        JPanel panel = new JPanel();
        panel.setBackground(Color.black);
        panel.setBounds(0,0,500,500);
        btn2.setBounds(475,550,75,40);
        fr.add(btn2);
        txtSat.setBounds(75,540,125,35);
        txtSut.setBounds(75,570,125,35);
        btn.setBounds(250,550,75,40);
        fr.getContentPane().add(panel);
        fr.add(txtSat);
        fr.add(txtSut);
        fr.add(btn);


        fr.setSize(575,635);
        fr.getContentPane().add(panel);
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void startProblem2() throws Exception {



        long baslangicZamani = System.currentTimeMillis();
        kareSayisi = 0;
        Izgara izgara = new Izgara(satir, sutun);
        Engel engel = new Engel(satir, sutun);
        engel.rastgeleEngellerEkle(sutun *5);
        Robot robot = new Robot(engel, satir, sutun);
        robot.cozLabirent();

        mapUI(satir,sutun, engel.engeller);

        for (int i = 0; i < engel.engeller.length; i++) {
            for (int j = 0; j < engel.engeller[0].length; j++) {
                if(engel.engeller[i][j]==2){
                    kareSayisi++;
                }
                System.out.print((char)(engel.engeller[i][j]+'0'));
            }
            System.out.println();
        }
        robot.izleriYazdir();
        long bitisZamani = System.currentTimeMillis();
        gecenSure = bitisZamani - baslangicZamani;



        System.out.println("Hedefe ulaşmak için geçen süre: " + gecenSure + " milisaniye");
        System.out.println("Labirentte toplam " + kareSayisi + " adet kare üzerinden geçildi.");
    }
    void createPanel(int x, int y, Color color, int satir, int sutun){
        int engelClSize = 500 / sutun;
        int engelRowSize = 500 / satir;
        JPanel engel = new JPanel();
        engel.setBounds(x,y,engelClSize - 2 ,engelRowSize - 2);
        fr.add(engel);
        engel.setBackground(color);
        engel.setVisible(true);
    }

    void mapUI(int satir, int sutun, int engel[][]) throws Exception {
        int engelClSize = 500 / sutun;
        int engelRowSize = 500 / satir;
        int x = 37;
        int y = 37;
        int map[][] = engel;
        for (int i = 0; i < satir; i++) {
            x = 37;
            for (int j = 0; j < sutun; j++) {
                if (map[i][j] == 1)
                    createPanel(x, y, Color.BLUE, satir, sutun);
                else if (map[i][j] == 0)
                    createPanel(x, y, Color.white, satir, sutun);
                else if (map[i][j] == 2)
                    createPanel(x, y, Color.yellow, satir, sutun);
                x += engelClSize;
            }
            y += engelRowSize;
        }
    }
}
