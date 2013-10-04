import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public  class GameParameters {

    private static final int PvP = 1;
    private static final int PvE = 2;
    private static char xo = ' ';

    public static int gameModSelect(){
        String buf;
        System.out.println(" Select game mode: \n \" " + PvP + " \" - player vs player \n \"" + PvE + "\" - player vs computer ");
        Scanner sc = new Scanner(System.in);
        int i = 0;
            do {
                buf =  sc.next();

                if ( buf.equals(String.valueOf(PvP))) {
                    System.out.println( "Your game mode is \"player vs player\" " );
                    i = 1;
                    return (PvP);
                }
                if (buf.equals(String.valueOf(PvE))) {
                    System.out.println( "Your game mode is \"player vs computer\" " );
                    i = 1;
                    return (PvE);
                }
                else {
                    System.out.println( "Failed to choose game mode.Try again " );
                    i = 0;
                }
            }
            while (i == 0);
        return (0);
    }

    public static int getPvP() {
        return PvP;
    }

    public static int getPvE() {
        return PvE;
    }

    public static char symbolSelect(String playerName){
        System.out.println(playerName + " press key to choose X or O: \n \"1\" - X \n \"2\" - O ");
        Scanner readCmd = new Scanner ( System.in  ) ;
        do{
            String str = readCmd.next();
            if ( "1".equals(str)){
                System.out.println( "Your choose \"X\"" );
                xo = 'X';
                return (xo);
            }
            else if ("2".equals(str)){
                System.out.println( "Your choose \"O\"" );
                xo = 'O';
                return (xo);
            }
            else {
                System.out.println( "Failed to choose X or O. Try again " );
            }
        } while (xo ==' ');
        return (0);
    }

    public static char setSecondPlayerSymbol(char symb){
        if (symb == 'X'){
            symb = 'O';
        }
        else symb = 'X';
        return (symb);
    }
}