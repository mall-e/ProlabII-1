import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.Timer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Problem1Page {

    Izgara izgara;
    JFrame fr = new JFrame();
    JPanel panel = new JPanel();
    JButton startBtn = new JButton("Start");
    JFrame previousFrame;
    JLabel lbl = new JLabel();
    JLabel lbl2 = new JLabel();
    int count = 0;
    long time = 0;

    Problem1Page(JFrame pFrame, Izgara izg) throws Exception {
        lbl.setBounds(345,551, 150,30);
        lbl2.setBounds(345,531, 150,30);
        fr.add(lbl);
        fr.add(lbl2);
        izgara = izg;
        previousFrame = pFrame;
        panel.setBackground(Color.black);
        panel.setBounds(0,0,500,500);
        startFinishPoint(true);
        startFinishPoint(false);
        Engel engel = new Engel(izgara.map);
        engel.find3();
        mapUI(izgara);

        fr.setSize(575,635);
        fr.getContentPane().add(panel);
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void mapUI(Izgara izgara) throws Exception {
        Robot robot = new Robot(izgara);
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

        JButton btn2 = new JButton("->");
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    robot.moveRight();
                    startedMapUI();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btn2.setBounds(125,550,50,35);
        fr.add(btn2);

        JButton btn3 = new JButton("^");
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    robot.moveUp();
                    startedMapUI();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btn3.setBounds(75,535,50,35);
        fr.add(btn3);

        JButton btn4 = new JButton("v");
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    robot.moveDown();
                    startedMapUI();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btn4.setBounds(75,575,50,35);
        fr.add(btn4);

        JButton btn5 = new JButton("<-");
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    robot.moveLeft();
                    startedMapUI();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btn5.setBounds(25,550,50,35);
        fr.add(btn5);

        JButton btn6 = new JButton("Hesapla");
        btn6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lbl.setText("Kare sayısı: " + count);
                lbl.setForeground(Color.WHITE);
                lbl.setVisible(true);
                lbl2.setText("Geçen süre: " + time);
                lbl2.setForeground(Color.WHITE);
                lbl2.setVisible(true);
            }
        });
        btn6.setBounds(365,574,75,35);
        fr.add(btn6);

        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    long before = System.currentTimeMillis();
                    test();
                    long after = System.currentTimeMillis();
                    startedMapUI();
                    time = after - before;
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
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
                else if (map[i][j] == '9')
                    createPanel(x,y,Color.MAGENTA);
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

    void test() throws Exception {
        LabirentCozum lab = new LabirentCozum();
        Robot robot = new Robot(izgara);
        int[] baslangic = {robot.positionY, robot.positionX};
        startedMove(robot);
        lab.start(izgara, new Robot(izgara), baslangic);
        startedMapUI();
    }


    void startedMove(Robot robot) throws InterruptedException {
        char labirent[][] = izgara.map;
        int hedef[] = findFinish(labirent);
        boolean[][] visited = new boolean[labirent.length][labirent[0].length];
        dfs(robot.positionY, robot.positionX, labirent, hedef, visited);
    }

    boolean dfs(int y, int x, char[][] labirent, int[] hedef, boolean[][] visited) {
        if (y == hedef[0] && x == hedef[1]) {
            System.out.println("Çıkışa ulaşıldı!");
            labirent[y][x] = '5';
            return true;
        }
        count++;
        visited[y][x] = true;
        labirent[y][x] = '6';

        int[][] hareket = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        List<int[]> hareketListesi = new ArrayList<>();

        for (int[] h : hareket) {
            hareketListesi.add(h);
        }
        Collections.shuffle(hareketListesi);

        for (int[] h : hareketListesi) {
            int yeniY = y + h[0];
            int yeniX = x + h[1];

            if (0 <= yeniY && yeniY < labirent.length && 0 <= yeniX && yeniX < labirent[0].length
                    && labirent[yeniY][yeniX] != '1' && labirent[yeniY][yeniX] != '2' && labirent[yeniY][yeniX] != '3'
                    && !visited[yeniY][yeniX]) {
                if (dfs(yeniY, yeniX, labirent, hedef, visited)) {
                    return true;
                }
            }
        }
        labirent[y][x] = ' ';
        return false;
    }

    static int[] findFinish(char map[][]){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == '5')
                {
                    int[] hedef = {i, j};
                    return hedef;
                }
            }
        }
        return new int[0];
    }

}
class LabirentCozum {
    static class Node {
        int y;
        int x;
        List<int[]> path;

        Node(int y, int x, List<int[]> path) {
            this.y = y;
            this.x = x;
            this.path = path;
        }
    }

    static int[] findFinish(char map[][]){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == '5')
                {
                    int[] hedef = {i, j};
                    return hedef;
                }
            }
        }
        return new int[0];
    }

    public static void start(Izgara izgara, Robot robot, int[] baslangic) throws Exception {
        char[][] labirent = izgara.map;

        int[] hedef = findFinish(izgara.map);

        List<int[]> cikisYolu = bfs(labirent, baslangic, hedef);

        if (cikisYolu != null) {
            for (int[] konum : cikisYolu) {
                labirent[konum[0]][konum[1]] = '9';
            }
        } else {
            System.out.println("Çıkış yolunu bulamadı.");
        }
    }

    public static List<int[]> bfs(char[][] labirent, int[] baslangic, int[] hedef) {
        int yukseklik = labirent.length;
        int genislik = labirent[0].length;

        int[][] hareket = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        Queue<Node> kuyruk = new LinkedList<>();
        kuyruk.add(new Node(baslangic[0], baslangic[1], new ArrayList<>()));

        Set<String> ziyaretEdilen = new HashSet<>();
        ziyaretEdilen.add(Arrays.toString(baslangic));

        while (!kuyruk.isEmpty()) {
            Node simdiki = kuyruk.poll();

            for (int[] h : hareket) {
                int yeniY = simdiki.y + h[0];
                int yeniX = simdiki.x + h[1];
                int[] yeniKonum = {yeniY, yeniX};

                if (0 <= yeniY && yeniY < yukseklik && 0 <= yeniX && yeniX < genislik && labirent[yeniY][yeniX] != '1' && labirent[yeniY][yeniX] != '2' && labirent[yeniY][yeniX] != '3' && !ziyaretEdilen.contains(Arrays.toString(yeniKonum))) {
                    if (Arrays.equals(yeniKonum, hedef)) {
                        List<int[]> cikisYolu = new ArrayList<>(simdiki.path);
                        cikisYolu.add(yeniKonum);
                        return cikisYolu;
                    }

                    ziyaretEdilen.add(Arrays.toString(yeniKonum));
                    List<int[]> yeniYol = new ArrayList<>(simdiki.path);
                    yeniYol.add(yeniKonum);
                    kuyruk.add(new Node(yeniY, yeniX, yeniYol));
                }
            }
        }
        return null;
    }
}

