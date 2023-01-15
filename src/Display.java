import javax.swing.*;

public class Display {
    public static void main(){
        //Initialisation menu
        JFrame frame = new JFrame("Istore");
        frame.setContentPane(new Login().panelMain);
        frame.setSize(300,220);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }

    private static void signUp(){
        //Initialisation menu
        JFrame frame = new JFrame("Istore");
        frame.setContentPane(new SignUp().panelMain);
        frame.setSize(400,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
