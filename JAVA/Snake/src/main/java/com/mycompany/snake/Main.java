/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake;


// show fame 
import javax.swing.*;
// show graphics
import java.awt.*;
//catch action buton
import java.awt.event.*;
/**
 *
 * @author nhannt
 */
public class Main extends JFrame {
    public Main(){
        this.add(new WindowPanel());
        this.pack();
        this.setTitle("Snake");
        this.setLocationRelativeTo(null);

        this.setVisible(true);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String arg[]){
        new Main();
    }
}
