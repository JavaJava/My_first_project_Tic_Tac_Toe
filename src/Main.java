import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //************************************************
        char symbol;
        int row;
        int col;
        int gameMod;
        int flag = 0;
        gameMod = GameParameters.gameModSelect();   //select game mod - PVP or PVE
        Scanner sc = new Scanner(System.in);
        System.out.println("Player 1 enter your name");
        String player1Name = sc.nextLine();
        System.out.println(player1Name  + "Select your symbol to play");
        symbol = GameParameters.symbolSelect(player1Name);             //player select symbol - X or O
        Player playerOne = new Player(symbol, player1Name);

        Field field = new Field(3);

        if (gameMod == GameParameters.getPvP()){
            ///////////////////////////////////////////////////////////////////
            //  PvP
            //////////////////////////////////////////////////////////////////
            symbol = playerOne.getActualPlayerSymbol();
            symbol = GameParameters.setSecondPlayerSymbol(symbol);
            System.out.println("Player 2 enter your name");
            String player2Name = sc.nextLine();
            Player playerTwo = new Player(symbol,player2Name);
            field.showField();
            if (symbol == 'X'){
                playerTurn(playerTwo, field);
                field.showField();
            }
            do{
                if (!field.allCellFill())    {
                    playerTurn(playerOne, field);
                    field.showField();
                   if (field.findWinLine(player1Name) ){
                       System.out.println("Game over");
                       flag = 2;
                       break;
                   }
                }
                else flag = 1;
                if  (!field.allCellFill())    {
                    playerTurn(playerTwo, field);
                    field.showField();
                    if (field.findWinLine(player2Name) ){
                        System.out.println("Game over");
                        flag = 2;
                        break;
                    }
                }
                else flag = 1;
            }
            while (flag == 0);
            if (field.allCellFill()&&(flag != 2)){
                System.out.println("Draw");
            }
        }
        ///////////////////////////////////////////////////////////////////
        //  PvE
        //////////////////////////////////////////////////////////////////
        else {

            symbol = playerOne.getActualPlayerSymbol();
            symbol = GameParameters.setSecondPlayerSymbol(symbol);
            Player computer = new Player(symbol);
            if (symbol == 'O'){
                playerTurn(playerOne, field);
                field.showField();
            }
            do{
                if (!field.allCellFill())    {
                    System.out.println("Computer make turn");
                    computerTurn(computer, field);
                    field.showField();
                    if (field.findWinLine("Computer") ){
                        System.out.println("Game over");
                        flag = 2;
                        break;
                    }
                }
                else flag = 1;
                if  (!field.allCellFill())    {
                    playerTurn(playerOne, field);
                    field.showField();
                    if (field.findWinLine("playerTwo") ){
                        System.out.println("Game over");
                        flag = 2;
                        break;
                     }
                }
                else flag = 1;
            }
            while (flag == 0);
            if (field.allCellFill()&&(flag != 2)){
                System.out.println("Draw");
            }
        }
    }

    public static void playerTurn (Player player, Field field){
        int row;
        int col;
        System.out.println(player.getPlayerName() + " make your turn");
        do{
            do{
            row = player.selectRow();
            } while (!field.checkCoordinate(row));
            do{
                col = player.selectColumn();
            } while (!field.checkCoordinate(col));
        } while (!(field.setFieldCell(row, col, player.getActualPlayerSymbol())));
    }

    public static void computerTurn(Player comp, Field field){
        int[] arr = field.findAnyEmptyCell() ;

       field.setFieldCell(arr[0], arr[1], comp.getActualPlayerSymbol());
    }
}
