/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comp2112_yaz_proje1_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author aylinbaki
 */
public class Game {

    List<Room> rooms;
    List<Integer> treasures;
    Stack<Integer> visitedRooms;
    Stack<Integer> path;

    public Game(String filename) throws IOException {
        rooms = new LinkedList<>();
        treasures = new LinkedList<>();
        visitedRooms = new Stack<>();
        path = new Stack<>();
        loadGame(filename);
    }

    private void loadGame(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("gamemap.txt"));
        String line;
        int roomId = 1;
       

        
        while ((line = br.readLine()) != null) {
            rooms.add(new Room(roomId));
            roomId++;
        }
       
        br.close();

        br = new BufferedReader(new FileReader("gamemap.txt"));
        roomId = 1;

        while ((line = br.readLine()) != null) {
            Room room = rooms.get(roomId - 1);
            String[] parts = line.split(",");

            for (String part : parts) {
                int connectedRoomId = Integer.parseInt(part.trim());

                
                if (connectedRoomId >= rooms.size()) {
                    while (rooms.size() < connectedRoomId) {
                        rooms.add(new Room(rooms.size() + 1));
                    }
                }
                room.addConnection(connectedRoomId);
            }
            roomId++;
        }

        br.close();
        assignTreasuresAndTraps();
    }

    private void assignTreasuresAndTraps() {
        for (Room room : rooms) {
            if (Math.random() < 0.5) {
                room.setTreasure();
            } else {
                room.setTrap();
            }
        }
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Available rooms to start from: " + getAvailableRooms());
        System.out.print("Enter starting room ID: ");
        int startRoom = scanner.nextInt();

        if (!getAvailableRooms().contains(startRoom)) {
            System.out.println("Invalid room ID. Exiting game.");
            return;
        }

        int currentRoom = startRoom; 
        path.push(currentRoom);
        visitedRooms.push(currentRoom);

        while (treasures.size() < rooms.size()) {

            Room room = rooms.get(currentRoom - 1); 
            System.out.println("You are in room " + currentRoom);

            if (room.hasTreasure() && !treasures.contains(currentRoom)) {
                System.out.println("You found a treasure!");
                treasures.add(currentRoom);

            } else if (room.hasTrap()) {
                System.out.println("You encountered a trap!");

                if (!handleTrap()) {
                    System.out.println("You failed to pass the trap. Returning to previous room.");                    
                    getPreviousRoom(currentRoom);
                    continue;
                }

            }

            System.out.println("Current path: " + path);
            System.out.println("Collected treasures: " + treasures);

            System.out.println("Available connections: " + room.connections);
            System.out.print("Enter next room: ");
            int nextRoom = scanner.nextInt();

            if (room.connections.contains(nextRoom)) {
                path.push(nextRoom);
                visitedRooms.push(nextRoom);
                currentRoom = nextRoom;
            } else {
                System.out.println("Invalid connection. Try again.");
            }
            if (treasures.size() == getTotalTreasures()) {
                System.out.println(" ");
                System.out.println("Congratulations! You have collected all treasures!");
                System.out.println("Final path: " + path);
                System.out.println("Total treasures collected: " + treasures.size());
                break;
            }
        }
        scanner.close();
    }

    private boolean handleTrap() {
        int diceRoll = (int) (Math.random() * 6) + 1;
        System.out.println("You rolled a " + diceRoll);
        return diceRoll % 2 == 0;
    }

    private int getPreviousRoom(int currentRoom) {
        if (path.size() > 1) {
            path.pop();  
            return path.peek();  
        }
        return path.peek();
    }

    private List<Integer> getAvailableRooms() {
        List<Integer> availableRooms = new LinkedList<>();
        for (Room room : rooms) {
            availableRooms.add(room.id);
        }
        return availableRooms;
    }

    private int getTotalTreasures() {
        int totalTreasures = 0;
        for (Room room : rooms) {
            if (room.hasTreasure()) {
                totalTreasures++;
            }
        }
        return totalTreasures;
    }

}
