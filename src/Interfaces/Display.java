package Interfaces;

import Interfaces.Main;
import Interfaces.SignUp;
import Interfaces.HomeSeller;

import javax.swing.*;

public class Display {
    public static JFrame frame;
    public static void main(){
        //Initialisation menu
        Display.frame = new JFrame("Istore");
        frame.setContentPane(new Main().panelMain);
        frame.setSize(300,220);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    protected static void signUp(){
        //Initialisation menu
        frame.setContentPane(new SignUp().panelMain);
        frame.setSize(400,600);
    }
    protected static void loggIn(){
        //Initialisation menu
        frame.setContentPane(new Login().panelMain);
        frame.setSize(300,220);
    }

    private static void home_seller(){
        //Initialisation menu
        frame.setContentPane(new HomeSeller().panelMain);
        frame.setSize(600,500);
    }
}
