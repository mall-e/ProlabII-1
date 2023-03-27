import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Uygulama {
    JFrame fr = new JFrame();
    Container pnl = new JPanel();
    JLabel lb = new JLabel();
    JButton btn = new JButton();
    JButton btn2 = new JButton();

    JLabel lbl = new JLabel();

    JTextField txt = new JTextField("http://bilgisayar.kocaeli.edu.tr/prolab2/url1.txt");

    Izgara izgara;
    Uygulama (){

        pnl.setBackground(Color.green);
        pnl.setBounds(0,0, 500,500);
        fr.getContentPane().add(pnl);

        lb.setText("Robot Gezgin");
        lb.setBounds(200,50,100,50);

        txt.setBounds(100,150, 300, 25);
        lbl.setBounds(150, 200, 200, 40);
        lbl.setText("Problem 1 için URL giriniz.");

        btn.setText("Problem 1");
        btn.setBounds(150, 300, 90,40);

        btn2.setText("Problem 2");
        btn2.setBounds(275, 300, 90,40);

        btnClicked(fr);

        pnl.add(lb);
        pnl.add(btn);
        pnl.add(btn2);
        pnl.add(txt);
        pnl.add(lbl);

        pnl.setLayout(new BorderLayout());
        fr.setSize(500, 500);
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void btnClicked(JFrame fr){
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Problem 1 tıklandı !!");
                fr.setVisible(false);
                Problem1Page page1 = null;
                try {
                    String url = txt.getText();
                    Izgara izgara = new Izgara(url);
                    page1 = new Problem1Page(fr, izgara);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                page1.fr.setVisible(true);
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Problem 2 tıklandı !!");
                fr.setVisible(false);
                Problem2Page page2 = null;
                try {
                    page2 = new Problem2Page(fr);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                page2.fr.setVisible(true);
            }
        });
    }
}
