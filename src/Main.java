/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package comp2112_yaz_proje1_2;

import java.io.IOException;

/**
 *
 * @author aylinbaki
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    try {
            Game game = new Game("gamemap.txt");
            game.startGame();
        } catch (IOException e) {
            System.out.println("Error loading game file: " + e.getMessage());
        }
    }
}