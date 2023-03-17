public class Robot {
    int positionX;
    int positionY;
    char map[][];

    Robot(Izgara izgara) throws Exception {
        map = izgara.map;
        findPosition(izgara);
    }

    void findPosition(Izgara izgara){
        for (int i = 0; i < izgara.row; i++) {
            for (int j = 0; j < izgara.column; j++) {
                if (map[i][j] == '4'){
                    positionY = i;
                    positionX = j;
                }
            }
        }
    }

    void moveUp(){
        map[positionY][positionX] = '6';
        map[positionY - 1][positionX] = '4';
        positionY = positionY - 1;
    }
    void moveDown(){
        map[positionY][positionX] = '6';
        map[positionY + 1][positionX] = '4';
        positionY = positionY + 1;
    }
    void moveLeft() {
        map[positionY][positionX] = '6';
        map[positionY][positionX - 1] = '4';
        positionX = positionX - 1;
    }
    void moveRight(){
        map[positionY][positionX] = '6';
        map[positionY][positionX + 1] = '4';
        positionX = positionX + 1;
    }
}
