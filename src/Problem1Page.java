import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Problem1Page {

    Izgara izgara = new Izgara();;
    JFrame fr = new JFrame();
    JPanel panel = new JPanel();
    JButton startBtn = new JButton("Start");
    JFrame previousFrame;

    Problem1Page(JFrame pFrame) throws Exception {
        previousFrame = pFrame;
        panel.setBackground(Color.black);
        panel.setBounds(0,0,500,500);
        startFinishPoint(true);
        startFinishPoint(false);
        for (int i = 0; i < izgara.row; i++) {
            System.out.println(izgara.map[i]);
        }

        mapUI(izgara);

        fr.setSize(575,625);
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
                if (map[i][j] == '1' || map[i][j] == '2' || map[i][j] == '3')
                    createPanel(x, y, Color.RED);
                else if (map[i][j] == '0')
                    createPanel(x,y,Color.green);
                else if (map[i][j] == '4')
                    createPanel(x,y,Color.blue);
                else if (map[i][j] == '5')
                    createPanel(x,y,Color.white);
                x+=engelClSize;
            }
            y+=engelRowSize;
        }
        JButton btn = new JButton("Back");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fr.setVisible(false);
                previousFrame.setVisible(true);
            }
        });
        btn.setBounds(475,550,75,40);
        fr.add(btn);

        JButton btn2 = new JButton("Back");
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    startGame();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btn2.setBounds(125,550,75,40);
        fr.add(btn2);

        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startedMapUI();
            }
        });
        startBtn.setBounds(250,550,75,40);
        fr.add(startBtn);
    }

    void createPanel(int x, int y,Color color){
        int engelClSize = 500 / izgara.column;
        int engelRowSize = 500 / izgara.row;
        JPanel engel = new JPanel();
        engel.setBounds(x,y,engelClSize - 2 ,engelRowSize - 2);
        fr.add(engel);
        engel.setBackground(color);
        engel.setVisible(true);
    }

    void startedMapUI() {
        int engelClSize = 500 / izgara.column;
        int engelRowSize = 500 / izgara.row;
        int x = 37;
        int y = 37;
        char map[][] = izgara.map;
        for (int i = 0; i < izgara.row; i++) {
            x = 37;
            for (int j = 0; j < izgara.column; j++) {
                if ((map[i][j] == '1' || map[i][j] == '2' || map[i][j] == '3')
                        && ((j > 0 && map[i][j - 1] == '4') || (j + 1 < izgara.column && map[i][j + 1] == '4')
                        || (i > 0 && map[i - 1][j] == '4') || (i + 1 < izgara.row && map[i + 1][j] == '4')))
                    createPanel(x, y, Color.RED);
                else if (map[i][j] == '0'
                        && ((j > 0 && map[i][j - 1] == '4') || (j + 1 < izgara.column && map[i][j + 1] == '4')
                        || (i > 0 && map[i - 1][j] == '4') || (i + 1 < izgara.row && map[i + 1][j] == '4')))
                    createPanel(x,y,Color.green);
                else if (map[i][j] == '4')
                    createPanel(x,y,Color.blue);
                else if (map[i][j] == '5'
                        && ((j > 0 && map[i][j - 1] == '4') || (j + 1 < izgara.column && map[i][j + 1] == '4')
                        || (i > 0 && map[i - 1][j] == '4') || (i + 1 < izgara.row && map[i + 1][j] == '4')))
                    createPanel(x,y,Color.white);
                else if (map[i][j] == '6')
                    createPanel(x,y,Color.green);
                else
                    createPanel(x, y, Color.black);
                x+=engelClSize;
            }
            y+=engelRowSize;
        }
    }

    void startFinishPoint(boolean start) {
        char map[][] = izgara.map;
        Random random = new Random();
        int pointX = random.nextInt(izgara.column);
        int pointY = random.nextInt(izgara.row);

        if (start && map[pointY][pointX] == '0'){
            map[pointY][pointX] = '4';
            return ;
        }
        if (!start && map[pointY][pointX] == '0'){
            map[pointY][pointX] = '5';
            return ;
        }
        else
            startFinishPoint(start);
    }

    void startGame() throws Exception {
        char map[][] = izgara.map;
        Robot robot = new Robot(izgara);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (robot.positionX != 0 && map[robot.positionY][robot.positionX - 1] != '1'
                        && map[robot.positionY][robot.positionX - 1] != '2'
                        && map[robot.positionY][robot.positionX - 1] != '3')
                {
                    robot.moveLeft();
                    startedMapUI();
                }
                else
                    timer.cancel();
            }
        };
        timer.schedule(task,0,1000);

        /*while (robot.positionX != 0 && map[robot.positionY][robot.positionX - 1] != '1'
                && map[robot.positionY][robot.positionX - 1] != '2'
                && map[robot.positionY][robot.positionX - 1] != '3')
        {
            robot.moveLeft();
            startedMapUI();
        }*/
        startedMapUI();
    }

}
