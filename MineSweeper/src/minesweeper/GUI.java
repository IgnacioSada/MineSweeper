package minesweeper;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Color;
/**
 *
 * @author nacho
 */
public class GUI extends JFrame
{
    int spacing = 1;
    int mouseX = -100, mouseY = -100;
    
    Random rand = new Random();
    
    int[][] mines = new int[49][44];
    int[][] neighbours = new int[49][44];
    boolean[][] reveal = new boolean[49][44];
    boolean[][] flagged = new boolean[49][44];
    
    public GUI()
    {
        // This code is based on the youtube tutorial on MINESWEEPER
        this.setTitle("MineSweeper_HD");    // Set Title
        this.setSize(1000, 1000);    // Set default display size
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // Exit app when closed
        this.setVisible(true);  // GUI visibility (DO NOT MODIFY)
        this.setResizable(false);   // Not resizable
        
        for(int i=0; i<49; i++)
            {
                for(int j=0; j<44; j++)
                {
                    if (rand.nextInt(100) < 20) mines[i][j] = 1;
                    else mines[i][j] = 0;
                }
            }
        
        Grid board = new Grid();
        this.setContentPane(board);
        
        Move move = new Move();
        this.addMouseMotionListener(move);
        
        Click click = new Click();
        this.addMouseListener(click);
    }
    
    public class Grid extends JPanel
    {
        // Creating a grid environment
        public void paintComponent(Graphics g)
        {
            // Setting BG color
            g.setColor(Color.black);
            g.fillRect(0, 0, 1000, 1000);
            
            // Creating a Grid
            for(int i=0; i<49; i++)
            {
                for(int j=0; j<44; j++)
                {
                    g.setColor(Color.LIGHT_GRAY);
//                    Uncomment for testing.
//                    if (mines[i][j] == 1) g.setColor(Color.yellow);
                    
                    if (mouseX >= 8+spacing+i*20  &&  
                        mouseX < i*20 + 28-spacing &&
                        mouseY >= 30+spacing+20*(j+4) &&
                        mouseY < 50 - spacing + 20*(j+4))
                        g.setColor(Color.red);
                    
                    g.fillRect(+spacing+i*20, spacing+20*(j+4), 
                                20-2*spacing, 20-2*spacing);
                }
            }
            
        }
        
    }
    
    
    
    
    public class Move implements MouseMotionListener 
    {

        @Override
        public
        void mouseDragged(MouseEvent e)
        {
            // Uncomment for testing
            // System.out.println("Mouse was Dragged.");
        }

        @Override
        public
        void mouseMoved(MouseEvent e)
        {
//            Uncomment for testing
//            System.out.print("Mouse moved: position");
            mouseX = e.getX();
            mouseY = e.getY();
//            System.out.println(" X: "+mouseX+", Y: "+mouseY);
        }
    }
    
    public class Click implements MouseListener
    {

        @Override
        public
        void mouseClicked(MouseEvent e)
        {
            if(inBoxX() != -1   &&
                inBoxY() != -1)
            {
                // Uncomment for testing
                System.out.print("Mouse was Clicked. ");
                System.out.println("Coordinates: ("+inBoxX()+", "+inBoxY()+")");
            } else
            {
                System.out.println("Mouse was Clicked, but isn't in a box.");
            }

            
        }

        @Override
        public
        void mousePressed(MouseEvent e)
        {
            // Uncomment for testing
            // System.out.println("Mouse was Pressed.");
        }

        @Override
        public
        void mouseReleased(MouseEvent e)
        {
             // Uncomment for testing
            // System.out.println("Mouse was Released.");       
        }

        @Override
        public
        void mouseEntered(MouseEvent e)
        {
            // Uncomment for testing
            // System.out.println("Mouse has Entered.");
        }

        @Override
        public
        void mouseExited(MouseEvent e)
        {
            // Uncomment for testing
            // System.out.println("Mouse has Exited.");
        }
    
    }
    
    public int inBoxX()
    {
        for(int i=0; i<49; i++)
            {
                for(int j=0; j<44; j++)
                {
                    if (mouseX >= 8+spacing+i*20  &&  
                        mouseX < i*20 + 28-spacing &&
                        mouseY >= 30+spacing+20*(j+4) &&
                        mouseY < 50 - spacing + 20*(j+4)) 
                        return i;
                }
            }
        return -1;
    }
    
    public int inBoxY()
    {
        for(int i=0; i<49; i++)
            {
                for(int j=0; j<44; j++)
                {
                    if (mouseX >= 8+spacing+i*20  &&  
                        mouseX < i*20 + 28-spacing &&
                        mouseY >= 30+spacing+20*(j+4) &&
                        mouseY < 50 - spacing + 20*(j+4)) 
                        return j;
                }
            }
        return -1;
    }
    
}
