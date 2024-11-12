/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.game_pong;
import javax.swing.*;
import com.mycompany.game_pong.ControlWindow;
/**
 *
 * @author nhannt
 */
public class Main extends JFrame {
    private ControlWindow ctr = new ControlWindow();
    public Main(){
        this.add(ctr);
        this.pack();//help for JFrame and Jpanel(COntrolWindow) same size
        this.setTitle("Pong Pong");
        this.setSize(Consts.WIDTH,Consts.HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);   
    }
    public static void main(String[] args){
       new Main();
       
    }
}
