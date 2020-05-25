package homeworkX0;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static final int SIZE = 5;// игровое поле;
    static final char[][] DOTS_TO_WIN = new char[5][5];// вводимые координаты;
    static final char DOT_EMPTY = '.';
    // игроки;
    static final char DOT_X = 'X';// человек;
    static final char DOT_O = 'O';// первый компьютер;
    static final char DOT_Y = 'Y';// второй компьютер;
    static final char DOT_Z = 'Z';// третий компьютер;
    static char[][] map;
    static char symbol;

    public static void main(String[] args)
    {
        // тест программы;
        initMap();
        printMap();

        while (true)
        {
            // тест хода человека;
            humanTurn();
            printMap();

            if (isWinner(DOT_X))
            {
                System.out.println("Человек победил.");
                break;
            }
            else if (isMapFull())
            {
                System.out.println("Ничья.");
                break;
            }
            //тест хода компьютера 1;
            aiTurn();
            printMap();

            if (isWinner(DOT_O))
            {
                System.out.println("Компьютер победил.");
                break;
            }
            else if (isMapFull())
            {
                System.out.println("Ничья.");
                break;
            }
            // тест хода 2 компьютера;
            aiTurnTwo();
            printMap();

            if (isWinner(DOT_Y))
            {
                System.out.println("Компьютер победил.");
                break;
            }
            else if (isMapFull())
            {
                System.out.println("Ничья.");
                break;
            }
            // тест хода 3 компьютера;
            aiTurnThree();
            printMap();

            if (isWinner(DOT_Z))
            {
                System.out.println("Компьютер победил.");
                break;
            }
            else if (isMapFull())
            {
                System.out.println("Ничья.");
                break;
            }
        }
        System.out.println("Игра закончена.");
    }

    static void initMap() //инициализация пустого игрового поля;
    {
        map = new char[SIZE][SIZE];

        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map[i].length; j++)
            {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    static void printMap() //вывод поля в консоль(внешний вид);
    {
        for (int i = 0; i <= SIZE; i++)
        {
            System.out.print(i + " ");
        }

        System.out.println();

        for (int i = 0; i < SIZE; i++)
        {
            System.out.print((i + 1) + " ");

            for (int j = 0; j < SIZE; j++)
            {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void humanTurn() // ход чеовека;
    {
        int x;
        int y;
        Scanner scanner = new Scanner(System.in);

        do
        {
            System.out.println("Введите координаты X Y");
            y = scanner.nextInt() - 1;
            x = scanner.nextInt() - 1;
        }
        while (!isCellValid(x, y));

        map[y][x] = DOT_X;
    }

    public static void aiTurn() // ход первого компьютера;
    {
        int x;
        int y;

        Random random = new Random();

        do
        {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
            
//          НЕ РАБОТАЕТ!! НЕ МОГУ ПОНЯТЬ, КАК ЗАСТАВИТЬ ЕГО ХОДИТЬ В УКАЗАННУЮ КООРДИНАТУ.
//            if(DOT_X == map[0][0] || DOT_X == map[2][2] || DOT_X == map[4][4]){ // первый компьютер "O" будет блокировать указанные ходы игрока если эти клетки будут свободны;
//                x = 2;
//                y = 2;
//                //DOT_O = map[1][1];
//            }else if(DOT_X == map[0][4] || DOT_X == map[2][2] || DOT_X == map[4][0] ){
//                x = 4;
//                y = 2;
//                //DOT_O = map[1][3];
//            }else if(DOT_X == map[2][0] || DOT_X == map[2][2] || DOT_X == map[2][4]) {
//                x = 2;
//                y = 3;
//                //DOT_O = map[2][1];
//            }else if(DOT_X == map[0][2] || DOT_X == map[2][2] || DOT_X == map[4][2]) {
//                x = 3;
//                y = 2;
//                //DOT_O = map[1][2];
//            }
        }
        while (!isCellValid(x, y));
        System.out.println("Первый компьютер сделал ход по координатам X Y: " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
    }

    public static void aiTurnTwo() // ход второго компьютера;
    {
        int x;
        int y;

        Random random = new Random();

        do
        {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        }
        while (!isCellValid(x, y));
        System.out.println("Второй компьютер сделал ход по координатам X Y: " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_Y;
    }

    public static void aiTurnThree() //ход третьего компьютера;
    {
        int x;
        int y;

        Random random = new Random();

        do
        {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        }
        while (!isCellValid(x, y));
        System.out.println("Третий компьютер сделал ход по координатам X Y: " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_Z;
    }

    public static boolean isCellValid(int x, int y) // проверка возможности устаовки фишки в указанную ячейку;
    {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE)
        {
            return false;
        }
        if (map[y][x] == DOT_EMPTY)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    static boolean isWinner(char symbol) // проверка победы;
    {
        boolean isDiagOne, isDiagTwo; // установили логическое значение 1,2
        isDiagOne = true;
        isDiagTwo = true;
        boolean isCols, isRows; // установили логическое значение 3,4

        for (int diagOne = 0; diagOne < DOTS_TO_WIN.length; diagOne++) {

            for(int diagTwo = 0; diagTwo < DOTS_TO_WIN.length; diagTwo++) {
                isDiagOne &= (map[diagOne][diagOne] == symbol);
                isDiagTwo &= (map[diagTwo][(DOTS_TO_WIN.length - 1) - diagTwo] == symbol);
            }
        }
        if (isDiagOne|| isDiagTwo) return true;//вернули (true), если во всех клетках нам встретились символы symbol;

        for (int col=0; col < DOTS_TO_WIN.length; col++){
            isCols = true;
            isRows = true;
            for(int row=0; row < DOTS_TO_WIN.length; row++){
                isCols &= (map[col][row] == symbol);
                isRows &= (map[row][col] == symbol);
            }
            if (isCols || isRows) return true; //вернули (true), если во всех клетках нам встретились символы symbol;
        }
        return false;
    }

    static boolean isMapFull() // инициализация полностью заполненного игрового поля без возможности хода (ничья);
    {
        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
                if (map[i][j] == DOT_EMPTY)
                    return false;
            }
        }
        return true;
    }
}