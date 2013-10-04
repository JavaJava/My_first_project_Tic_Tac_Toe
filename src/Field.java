public class Field{

    private static char DEFAULT_CELL_VALUE = ' ';
    private char[][] field;
    private final int fieldSize;
    private static int DEFAULT_FIELD_SIZE = 3;

    public Field(){
        this(DEFAULT_FIELD_SIZE);
    }

    public Field(int size){
        fieldSize = size;
        field = new char [fieldSize][fieldSize];
        for (int i = 0; i < fieldSize; i++){
            for (int j = 0; j < fieldSize; j++){
                field[i][j] = DEFAULT_CELL_VALUE;
            }
        }
    }

    public boolean setFieldCell (int x, int y, char ch){
        if (field[x][y] == DEFAULT_CELL_VALUE) {
            field[x][y] = ch;
            return (true);
        }
        else {
            System.out.println("Cell (" + x + ";" + y + ") not empty, select another cell");
            return (false);
        }
    }

    public char getFieldCell (int x, int y){
        return (field[x][y]);
    }

    public char[][] getField() {
        return field;
    }

    public char getDEFAULT_CELL_VALUE() {
        return DEFAULT_CELL_VALUE;
    }

    public void showCell(int x, int y){
        System.out.print( "[" + field[x][y] + "]");
    }

    public void showLine (int lineNumber){
        for (int i = 0; i < fieldSize; i++){
            showCell(lineNumber, i);
        }
    }

    public void showField(){
        System.out.print("   ");
        for (int i = 0; i < fieldSize; i++){
            System.out.print("[" + i + "]");
        }
        System.out.println();
        for (int i = 0; i < fieldSize; i++){
            System.out.print("[" + i + "]");
            showLine(i);
        System.out.println();
        }
    }

    public boolean checkCoordinate(int number){
        if (number == -1) {
            System.out.println("Try enter number between 0 and " + (fieldSize-1));
            return (false);
        }
        if ((number < -1)||(number >= fieldSize))  {
            System.out.println(number + " is out of range. Enter number between 0 and " + (fieldSize-1));
            return (false);
        }
        return (true);
   }

    public boolean allCellFill(){
        for(int i = 0; i < fieldSize; i++){
            for (int j = 0; j < fieldSize; j++){
                if (field[i][j] == DEFAULT_CELL_VALUE){
                    return (false);
                }
            }
        }
        return (true);
    }

    public int filledCellNumber(){
        int count = 0;
        for(int i = 0; i < fieldSize; i++){
            for (int j = 0; j < fieldSize; j++){
                if (field[i][j] != DEFAULT_CELL_VALUE){
                    count++;
                }
            }
        }
        return (count);
    }

    public boolean allCellEmpty(){
        for(int i = 0; i < fieldSize; i++){
            for (int j = 0; j < fieldSize; j++){
                if (field[i][j] == DEFAULT_CELL_VALUE){
                    return (true);
                }
            }
        }
        return (false);
    }

    public boolean findWinRow(String player){
         //  System.out.println("Try to find win row");
        boolean stop = false;
        for(int rowNumb = 0; rowNumb < fieldSize; rowNumb++){
            for (int i = 0; i < (fieldSize - 1); i++){
                if ((field[rowNumb][i] == DEFAULT_CELL_VALUE) || (field[rowNumb][i] != field[rowNumb][(i + 1)])) {
                    break;
                }
                if  ((field[rowNumb][i] != DEFAULT_CELL_VALUE) && (field[rowNumb][i] == field[rowNumb][(i + 1)])){

                    if ((i+2) != fieldSize){
                        continue;
                    }
                    else {
                        System.out.println(player + " win");
                        stop = true;
                        return (true);
                    }
                }
            }
            if ((!stop)&&(rowNumb == (fieldSize - 1))){
            //    System.out.println("Win row was not found");
            }
        }
        return (false);
    }

    public boolean findWinCol(String player){
       // System.out.println("Try to find win column");
        boolean stop = false;
        for(int colNumb = 0; colNumb < fieldSize; colNumb++){
            for (int i = 0; i < (fieldSize - 1); i++){
                if ((field[i][colNumb] == DEFAULT_CELL_VALUE) || (field[i][colNumb] != field[(i + 1)][colNumb])) {
                    break;
                }
                if  ((field[i][colNumb] != DEFAULT_CELL_VALUE) && (field[i][colNumb] == field[(i + 1)][colNumb])){
                    if ((i+2) != fieldSize){
                        continue;
                    }
                    else {
                        System.out.println(player + " win");
                        stop = true;
                        return (true);
                    }
                }
            }
            if ((!stop)&&(colNumb == (fieldSize - 1))){
              //  System.out.println("Win column was not found");
            }
        }
        return (false);
    }

    public boolean findWinMainDiagonal(String player){
       // System.out.println("Try to find win \\ diagonal");

        int j = 0;
        for(int i = 0; i < fieldSize; i++){
            if ((field[i][j] == DEFAULT_CELL_VALUE) || (field[i][j] != field[(i + 1)][(j + 1)])) {
              //  System.out.println("Win main \\ diagonal was not found");
                break;
            }
            if ((field[i][j] != DEFAULT_CELL_VALUE) && (field[i][j] == field[(i + 1)][(j + 1)])) {
                if ((j+2) != fieldSize){
                    j++;
                    continue;
                }
                else {
                    System.out.println(player + " win");
                    return (true);
                }
            }
        }
        return (false);
    }

    public boolean findWinBackDiagonal(String player){
        //System.out.println("Try to find win / diagonal");

        int j = (fieldSize - 1);
        for(int i = 0; i < fieldSize; i++){
            if ((field[i][j] == DEFAULT_CELL_VALUE) || (field[i][j] != field[(i + 1)][(j - 1)])) {
              //  System.out.println("Win / diagonal was not found");
                break;
            }
            if ((field[i][j] != DEFAULT_CELL_VALUE) && (field[i][j] == field[(i + 1)][(j - 1)])) {
                if ((j-1) != 0){
                    j--;
                    continue;
                }
                else {
                    System.out.println(player + " win");
                    return (true);
                }
            }
        }
        return (false);
    }

    public boolean findWinLine(String player){
        return (findWinBackDiagonal(player) | findWinCol(player) | findWinMainDiagonal(player) | findWinRow(player));

    }

    public int[] findAnyEmptyCell(){
        int[] arr = new int[2];
        if (!allCellFill())   {
            for(int i = 0; i < fieldSize; i++){
                for (int j = 0; j < fieldSize; j++){
                    if (getFieldCell(i,j) == DEFAULT_CELL_VALUE){
                        arr[0] = i;
                        arr[1] = j;
                        break;
                    }
                }
            }
            return arr;
        }
        else {
            arr[0] = -1;
            return arr;
        }
    }
}