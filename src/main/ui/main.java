package ui;

import model.Board;

import java.util.Scanner;

public class main {
    Scanner scanner;

    public static void main(String[] args) {
        Board board = new Board(10);
        System.out.println(board.boardToString());
    }
}
