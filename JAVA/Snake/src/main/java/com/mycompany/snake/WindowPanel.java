/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author nhannt
 */
public class WindowPanel extends JPanel implements ActionListener,KeyListener {
    
    public static final int M_TILE =20;
    public static final int M_WIDTH = M_TILE*20;
    public static final int M_HEIGHT = M_TILE*20;
    
    private int[] snakex,snakey;
    
    private int duoi =1;
    
    private int loc_x,loc_y;
    
    public static final int FPS = 5;
    public static final int DELAY = 1000/FPS;
    
    private char huong = 'r';
    private Timer timer = new Timer(DELAY,this);
    public WindowPanel(){
        this.setFocusable(true);
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(M_WIDTH,M_HEIGHT));
        this.setBackground(Color.BLACK);
        this.addKeyListener(this);
        
        loc_x = new java.util.Random().nextInt(M_WIDTH/M_TILE);
        loc_y = new java.util.Random().nextInt(M_HEIGHT/M_TILE);
        loc_x *= M_TILE;
        loc_y *= M_TILE;
        snakex = new int[200];
        snakey = new int[200];
        snakex[0] = M_WIDTH / 2;  // Giữa màn hình theo chiều ngang
        snakey[0] = M_HEIGHT / 2; // Giữa màn hình theo chiều dọc
        
        this.addKeyListener(this);
        timer.start();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        
        //Snake
        g.setColor(Color.GREEN);
        for(int i =0 ; i<duoi;i++){
            g.fillRect(snakex[i], snakey[i], M_TILE, M_TILE);    
        }
        
        ///Grid
        g.setColor(Color.WHITE);
        for(int i =0 ; i< M_WIDTH;i+= M_TILE){
            for(int j =0 ; j<M_HEIGHT;j+= M_TILE ){
                g.drawRect(i, j, M_TILE, M_TILE);
            }
        }
        
    
        //Fruit
        g.setColor(Color.red);
        g.fillOval(loc_x,loc_y , M_TILE, M_TILE);
        
        
        
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = duoi ;i>0;i--){
            snakex[i] = snakex[i-1];
            snakey[i] = snakey[i-1];            
        }
        
        //decrease or increase 
        if(huong == 'r'){ 
            snakex[0] += M_TILE;
        }
        if(huong == 'l'){ 
            snakex[0] -= M_TILE;
        }
        if(huong == 'u'){ 
            snakey[0] -= M_TILE;
        }
        if(huong == 'd'){ 
            snakey[0] += M_TILE;
        }
        
        //check snake pass end tile and start tile. 
        if(snakex[0] < 0 ) snakex[0] = M_WIDTH-M_TILE;
        if(snakex[0] > M_WIDTH-M_TILE) snakex[0] = 0; 
        if(snakey[0] < 0 ) snakey[0] = M_WIDTH-M_TILE;
        if(snakey[0] > M_WIDTH-M_TILE) snakey[0] = 0;
        
                
        if(snakex[0] == loc_x && snakey[0] == loc_y ){
            duoi++;
            for(int i = 0 ;i<duoi;i++){
            if(loc_x == snakex[i]&& loc_y == snakey[i] ){
                loc_x = new java.util.Random().nextInt(M_WIDTH/M_TILE);
                loc_y = new java.util.Random().nextInt(M_HEIGHT/M_TILE);
                loc_x *= M_TILE;
                loc_y *= M_TILE;  
            }
        }
        }
        
          
        
        
        for(int i = duoi ;i>0;i--){
        if(snakex[0] == snakex[i]&& snakey[0] == snakey[i] ){
                timer.stop();
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(huong != 'l'){
                huong ='r';
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(huong != 'r'){
                huong ='l';
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            if(huong != 'd'){
                huong ='u';
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            if(huong != 'u'){
                huong ='d';
            }
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
