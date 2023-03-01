import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {
    JFrame fr = new JFrame();
    Container pnl = new JPanel();
    JLabel lb = new JLabel();
    JButton btn = new JButton();
    JButton btn2 = new JButton();
    MainMenu (){

        pnl.setBackground(Color.green);
        pnl.setBounds(0,0, 500,500);
        fr.getContentPane().add(pnl);

        lb.setText("Robot Gezgin");
        lb.setBounds(200,50,100,50);

        btn.setText("Problem 1");
        btn.setBounds(150, 300, 90,40);

        btn2.setText("Problem 2");
        btn2.setBounds(275, 300, 90,40);

        btnClicked();

        pnl.add(lb);
        pnl.add(btn);
        pnl.add(btn2);

        pnl.setLayout(new BorderLayout());
        fr.setSize(500, 500);
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void btnClicked(){
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Problem 1 tıklandı !!");
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Problem 2 tıkalandı !!");
            }
        });
    }
}
