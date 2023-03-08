import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

class Izgara {
    char map[][];
    int column;
    int row;

    Izgara () throws Exception {
        mapSize();
        map = new char[row][column];
        readMap();
        System.out.println(column + "" + row);
    }

    void readMap() throws Exception {
        String row;
        int i = 0;
        int j = 0;
        File file = new File("/Users/mali/Desktop/ProlabII-1/src/map.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while ((row = reader.readLine()) != null)
        {
            map[i] = row.toCharArray();
            i++;
        }
    }

    void mapSize() throws Exception{
        int size = 0;
        String row;
        File file = new File("/Users/mali/Desktop/ProlabII-1/src/map.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while ((row = reader.readLine()) != null)
        {
            if (size == 0)
                column = row.length();
            size++;
        }
        this.row = size;
    }
}
