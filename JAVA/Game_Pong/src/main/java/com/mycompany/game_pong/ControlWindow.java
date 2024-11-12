/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.game_pong;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import com.mycompany.game_pong.Ball;
/**
 *
 * @author nhannt
 */
public class ControlWindow extends JPanel implements ActionListener ,KeyListener{
    private int t = 30;
    private Ball ball = new Ball(Consts.WIDTH / 2 , Consts.HEIGHT / 2 , 20);
    private Timer time  = new Timer(t,this);
    
    private Player n1 = new Player(0,Consts.HEIGHT / 2);
    
    private Player n2 = new Player(Consts.WIDTH - 20,Consts.HEIGHT / 2);
    
    //count hour 
    private int dem = 0;
    
    @Override // this is method paint geometry
    public void paintComponent(Graphics g){
       super.paintComponent(g);
       g.setColor(Color.WHITE);
       //this prameter are public
       g.fillOval(ball.x, ball.y, ball.Radius, ball.Radius);
       
       g.setColor(Color.red);
       g.fillRect(n1.x, n1.y, n1.width, n1.height);
       
       g.setColor(Color.blue);
       g.fillRect(n2.x, n2.y, n2.width, n2.height);
       
       
       g.setColor(Color.GREEN);
       g.setFont(new Font("Consolas" , Font.PLAIN ,30));
       g.drawString(n1.score+"|"+n2.score, Consts.WIDTH / 2 -5, 25);
       
       
       
       
    }
    
    //set color is black.
    public ControlWindow(){
        time.start();
        this.setBackground(Color.BLACK);
        this.addKeyListener(this);
        this.setFocusable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ball.x += ball.speedX;
        ball.y += ball.speedY;
        ////
        // two object impact
        Rectangle rectball = new Rectangle(ball.x, ball.y, ball.Radius, ball.Radius);
        Rectangle rectn1 = new Rectangle(n1.x, n1.y, n1.width, n1.height);
        Rectangle rectn2 = new Rectangle(n2.x, n2.y, n2.width, n2.height);
        
        if(rectball.intersects(rectn2)){// intersects(giao nhau)
            ball.speedX = -ball.speedX;
            dem++;
            if(dem%10 == 0 && t != 5){
                t -= 5;
                time.setDelay(t); // Update timer delay
                time.restart();// Restart timer with the new delay
            }
        }
        if(rectball.intersects(rectn1)){
            ball.speedX = -ball.speedX;
            dem++;
            if(dem%10 == 0 && t != 5){
                t -= 5;
                time.setDelay(t); // Update timer delay
                time.restart();// Restart timer with the new delay
            }
        }
        
        n2.y= ball.y; 
        n1.y= ball.y; 
        if(ball.y >= Consts.HEIGHT - ball.Radius*2-10 || ball.y == 0){
            ball.speedY *= -1;
        }else if(ball.x >= Consts.WIDTH - ball.Radius ){
            n1.score++;
            t = 30;
            time.setDelay(t); // Update timer delay
            time.restart();// Restart timer with the new delay
            dem =0;
            ball.x = Consts.WIDTH/2;
            ball.y = Consts.HEIGHT/2;
        }else if( ball.x == 0){
            n2.score++;
            t = 30;
            time.setDelay(t); // Update timer delay
            time.restart();// Restart timer with the new delay
            dem =0;
            ball.x = Consts.WIDTH/2;
            ball.y = Consts.HEIGHT/2;
        }
        
        repaint();//replace images
    }


    
/////////////////////////////////
//catch action button     
    @Override
    public void keyTyped(KeyEvent e) {//catch action press and release. 
        
        
        
    }

    @Override
    public void keyPressed(KeyEvent e) {//catch action press.It will run meantime.
        if(e.getKeyCode() == KeyEvent.VK_W){
            n1.y -= n1.speedY;
        }else if(e.getKeyCode() == KeyEvent.VK_S){
            n1.y += n1.speedY;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            n2.y -= n2.speedY;
        }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            n2.y += n2.speedY;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {//catch action release.
        
    }
    
//////////////////////////////////
}

