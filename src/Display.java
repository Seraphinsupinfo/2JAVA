import javax.swing.*;

public class Display {
    public static JFrame frame;
    public static void main(){
        //Initialisation menu
        Display.frame = new JFrame("Istore");
        frame.setContentPane(new DisplayMain().panelMain);
        frame.setSize(300,220);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    protected static void signUp(){
        //Initialisation menu
        frame.setContentPane(new SignUp().panelMain);
        frame.setSize(400,300);
    }
    protected static void logIn(){
        //Initialisation menu
        frame.setContentPane(new Login().panelMain);
        frame.setSize(350,250);
    }

    protected static void test(){
        //Initialisation menu
      stockSeller();
    }

    protected static void homeAdmin(){
        //Initialisation menu
        frame.setContentPane(new HomeAdmin().panelMain);
        frame.setSize(600,500);
    }
    protected static void homeSeller(){
        //Initialisation menu
        frame.setContentPane(new HomeSeller().panelMain);
        frame.setSize(600,500);
    }
    protected static void homeUser(){
        //Initialisation menu
        frame.setContentPane(new HomeUser().panelMain);
        frame.setSize(600,350);
    }
    protected static void stockSeller(){
        //Initialisation menu
        frame.setContentPane(new StockSeller().panelMain);
        frame.setSize(1000,850);
    }
}
