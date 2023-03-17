import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

class Izgara {
    char map[][];
    int column;
    int row;

    Izgara () throws Exception {
        mapSize();
        map = new char[row][column];
        readURL();
        System.out.println(column + "" + row);
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

    void readURL() throws IOException {
        String row;
        int i = 0;
        URL url = new URL("http://bilgisayar.kocaeli.edu.tr/prolab2/url1.txt");
        URLConnection urlConnection = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        while ((row = reader.readLine()) != null)
        {
            map[i] = row.toCharArray();
            i++;
        }
    }

    void mapSize() throws Exception{
        int size = 0;
        String row;
        URL url = new URL("http://bilgisayar.kocaeli.edu.tr/prolab2/url1.txt");
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
}
