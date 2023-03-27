import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

class Izgara {
    char map[][];
    int column;
    int row;
    int satirSayisi;
    int sutunSayisi;
    int[][] izgara;
    String url;

    Izgara (String url) throws Exception {
        mapSize(url);
        map = new char[row][column];
        readURL(url);
        System.out.println(column + "" + row);
    }

    Izgara(int satirSayisi, int sutunSayisi) {
        this.satirSayisi = satirSayisi;
        this.sutunSayisi = sutunSayisi;
        this.izgara = new int[satirSayisi][sutunSayisi];


        Random random = new Random();
        for (int i = 0; i < satirSayisi; i++) {
            for (int j = 0; j < sutunSayisi; j++) {
                if (random.nextDouble() < 0.3) {

                    this.izgara[i][j] = 1;
                } else {
                    this.izgara[i][j] = 0;
                }
            }
        }
    }

    void readMap() throws Exception {
        String row;
        int i = 0;
        File file = new File("/Users/mali/Desktop/ProlabII-1/src/map.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while ((row = reader.readLine()) != null)
        {
            map[i] = row.toCharArray();
            i++;
        }
    }

    void readURL(String link) throws IOException {
        String row;
        int i = 0;
        URL url = new URL(link);
        URLConnection urlConnection = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        while ((row = reader.readLine()) != null)
        {
            map[i] = row.toCharArray();
            i++;
        }
    }

    void mapSize(String link) throws Exception{
        int size = 0;
        String row;
        URL url = new URL(link);
        URLConnection urlConnection = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        while ((row = reader.readLine()) != null)
        {
            if (size == 0)
                column = row.length();
            size++;
        }
        this.row = size;
    }

    public boolean getDeger(int satir, int sutun) {
        if(izgara[satir][sutun]==1){
            return true;
        }
        else {
            return false;
        }
    }
}
