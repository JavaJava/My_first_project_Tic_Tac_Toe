import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;

public class Player {

    private String playerName = "N/A";
    private final char playerSymbol;

    public String getPlayerName() {
        return playerName;
    }

    Player(char playerSymbol){
        this.playerSymbol = playerSymbol;
    }

    Player (char playerSymbol, String playerName){
        this.playerSymbol = playerSymbol;
        this.playerName = playerName;
    }

    public  char getActualPlayerSymbol() {
        return playerSymbol;
    }

    public int  selectRow(){
        System.out.println("Select row number");
        Scanner sc = new Scanner(System.in);
        int x =  sc.nextInt();
        return (x);

    }

    public int selectColumn(){
        System.out.println("Select column number");
        Scanner sc = new Scanner(System.in);
        int y = sc.nextInt();
        return(y);
    }
}