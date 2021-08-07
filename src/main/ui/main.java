package ui;

import model.Board;
import model.Ship;
import player.HumanPlayer;
import player.Player;

import java.util.ArrayList;

public class main {
//    public static void main(String[] args) {
//        BattleShipGame bsg = new BattleShipGame();
//    }

    public static void main(String[] args) {
        Player humanPlayer = new HumanPlayer(10);
        Ship ship = new Ship(0, 0, Board.DOWN_ORIENTATION, 10);
        Ship ship2 = new Ship(0, 0, Board.DOWN_ORIENTATION, 10);
        Ship ship3 = new Ship(0, 0, Board.DOWN_ORIENTATION, 10);
        Ship ship4 = new Ship(0, 0, Board.DOWN_ORIENTATION, 10);


        humanPlayer.placeShipRandomly(ship, new ArrayList<>(), new ArrayList<>());
        humanPlayer.placeShipRandomly(ship2, new ArrayList<>(), new ArrayList<>());
        humanPlayer.placeShipRandomly(ship3, new ArrayList<>(), new ArrayList<>());
        humanPlayer.placeShipRandomly(ship4, new ArrayList<>(), new ArrayList<>());
        System.out.println(humanPlayer.getPlayerBoard().boardToString());

    }


}
