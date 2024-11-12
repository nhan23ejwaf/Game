/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.game_pong;

/**
 *
 * @author nhannt
 */
public class Player {
    public int x,y;
    public int width = 20 ,height = 100;
    public int speedY=15;
    public int score =0;
    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        
    }
}
