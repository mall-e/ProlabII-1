import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Problem1Page {

    Izgara izgara;
    JFrame fr = new JFrame();
    JPanel panel = new JPanel();

    Problem1Page() throws Exception {

        panel.setBackground(Color.black);
        panel.setBounds(0,0,500,500);
        izgara = new Izgara();
        for (int i = 0; i < izgara.row; i++) {
            System.out.println(izgara.map[i]);
        }
        mapUI(izgara);

        fr.setSize(575,575);
        fr.getContentPane().add(panel);
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void mapUI(Izgara izgara){
        int engelClSize = 500 / izgara.column;
        int engelRowSize = 500 / izgara.row;
        int x = 37;
        int y = 37;
        char map[][] = izgara.map;
        for (int i = 0; i < izgara.row; i++) {
            x = 37;
            for (int j = 0; j < izgara.column; j++) {
                if (map[i][j] == '1'){
                    JPanel engel = new JPanel();
                    engel.setBounds(x,y,engelClSize - 2, engelRowSize - 2);
                    fr.add(engel);
                    engel.setBackground(Color.orange);
                    engel.setVisible(true);
                }
                else if (map[i][j] == '0'){
                    JPanel engel = new JPanel();
                    engel.setBounds(x,y,engelClSize - 2 ,engelRowSize - 2);
                    fr.add(engel);
                    engel.setBackground(Color.GREEN);
                    engel.setVisible(true);
                }
                x+=engelClSize;
            }
            y+=engelRowSize;
        }
    }

}
