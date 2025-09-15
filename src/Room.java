/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comp2112_yaz_proje1_2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aylinbaki
 */

class Room {
    int id;
    List<Integer> connections;
    boolean hasTreasure;
    boolean hasTrap;

    Room(int id) {
        this.id = id;
        this.connections = new ArrayList<>();
        this.hasTreasure = false;
        this.hasTrap = false;
    }

    void addConnection(int roomId) {
        connections.add(roomId);
    }

    void setTreasure() {
        this.hasTreasure = true;
    }

    void setTrap() {
        this.hasTrap = true;
    }

    boolean hasTreasure() {
        return this.hasTreasure;
    }

    boolean hasTrap() {
        return this.hasTrap;
    }

    @Override
    public String toString() {
        return  "Room{" +
                "id=" + id +
                ", connections=" + connections +
                ", hasTreasure=" + hasTreasure +
                ", hasTrap=" + hasTrap +
                '}';
    }
    
    
}
