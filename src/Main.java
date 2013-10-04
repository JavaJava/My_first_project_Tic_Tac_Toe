public class Main {

    public static void main(String[] args) {
        //************************************************
        char symbol;
        int row;
        int col;
        int gameMod;
        int flag = 0;
        gameMod = GameParameters.gameModSelect();   //select game mod - PVP or PVE

        System.out.println("Player 1 enter your name");
        Player playerOne = new Player();
        playerOne.setPlayerName();
        System.out.println(playerOne.getPlayerName()  + " select your symbol to play");
        symbol = GameParameters.symbolSelect(playerOne.getPlayerName());             //player select symbol - X or O
        playerOne.setPlayerSymbol(symbol);
        Field field = new Field(3);
        String player1Name = playerOne.getPlayerName();
  //     int[] historyMatrix = new int[field.getFieldSize()*field.getFieldSize()];
  //     int[][][] historyMatrixStep = new int[1][1][1];

        //      coordinates    symbol
        //      [int][int]     [char]
        //
        //
        //


        if (gameMod == GameParameters.getPvP()){
            ///////////////////////////////////////////////////////////////////
            //  PvP
            //////////////////////////////////////////////////////////////////
            symbol = playerOne.getPlayerSymbol();
            symbol = GameParameters.setSecondPlayerSymbol(symbol);
            System.out.println("Player 2 enter your name");
            Player playerTwo = new Player();
            playerTwo.setPlayerName();
            playerTwo.setPlayerSymbol(symbol);
            System.out.println(playerTwo.getPlayerName() + " your symbol is " + playerTwo.getPlayerSymbol());
            String player2Name = playerTwo.getPlayerName();
            field.showField();

            if (symbol == 'X'){
                playerTurn(playerTwo, field);
            }
            do{
                if( playerTurn(playerOne, field) == null){
                    break;
                }
                if ( playerTurn(playerTwo, field) == null){
                    break;
                }

            }while(true);

        }
        ///////////////////////////////////////////////////////////////////
        //  PvE
        //////////////////////////////////////////////////////////////////
        else {

            symbol = playerOne.getPlayerSymbol();
            symbol = GameParameters.setSecondPlayerSymbol(symbol);
            Player computer = new Player(symbol);
            if (symbol == 'O'){
                playerTurn(playerOne, field);
            }


                do{
                    System.out.println("Computer make turn");
                    if( computerTurn(computer, field) == null){
                        break;
                    }
                    if ( playerTurn(playerOne, field) == null){
                        break;
                    }
                }while(true);
        }
    }

    public static int[] playerTurn (Player player, Field field){
        int row;
        int col;
        if (!field.allCellFill())   {
            System.out.println(player.getPlayerName() + " make your turn");
            do{
                do{
                row = player.selectRow();
                } while (!field.checkCoordinate(row));
                do{
                    col = player.selectColumn();
                } while (!field.checkCoordinate(col));
            } while (!(field.setFieldCell(row, col, player.getPlayerSymbol())));
            if  (field.findWinLine(player.getPlayerName())    ){
                field.showField();
                System.out.println("Game over");
                return null;
            }
            int[] arr = {row, col};
            field.showField();
            return arr;
        }
        else {
            System.out.println("Draw");
            return null;
        }

    }

    public static int[] computerTurn(Player comp, Field field){
        if (!field.allCellFill())   {
            int[] arr = field.findAnyEmptyCell() ;
            field.setFieldCell(arr[0], arr[1], comp.getPlayerSymbol());
            if  (field.findWinLine("Computer")){
                field.showField();
                System.out.println("Game over");
                return null;
            }
            field.showField();
            return arr;
        }
        else {
            System.out.println("Draw");
            return null;
        }
    }
}
