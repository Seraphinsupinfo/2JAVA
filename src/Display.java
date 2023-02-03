import javax.swing.*;

public class Display {
    public static JFrame frame;
    public static JFrame Errframe;

    public static JFrame Actionframe;
    public static void main(){
        //Initialisation page se connecter/s'inscrire et initialisation de la fenetre
        Display.frame = new JFrame("Istore");
        frame.setContentPane(new DisplayMain().panelMain);
        frame.setSize(300,220);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        actionPopUp("test 13232323232 2323 3333 3232");
    }

    protected static void signUp(){
        //Initialisation page s'inscrire
        frame.setContentPane(new SignUp().panelMain);
        frame.setSize(450,450);
    }
    protected static void logIn(){
        //Initialisation page se connecter
        frame.setContentPane(new Login().panelMain);
        frame.setSize(350,250);
    }

    protected static void homeAdmin(){
        //Initialisation accueil admin
        frame.setContentPane(new HomeAdmin().panelMain);
        frame.setSize(600,500);
    }
    protected static void homeSeller(){
        //Initialisation accueil vendeur
        frame.setContentPane(new HomeSeller().panelMain);
        frame.setSize(600,500);
    }
    protected static void homeUser(){
        //Initialisation accueil personne lambda
        frame.setContentPane(new HomeUser().panelMain);
        frame.setSize(600,350);
    }
    protected static void stockSeller(){
        //Initialisation page stock vendeur
        frame.setContentPane(new StockSeller().panelMain);
        frame.setSize(1000,750);
    }

    protected static void whiteList(){
        //Initialisation page gestion des whitelist
        frame.setContentPane(new WhiteListGestion().panelMain);
        frame.setSize(1000,750);
    }

    protected static void userManagement(){
        //Initialisation gestion des utilisateurs
        frame.setContentPane(new UserManagement().panelMain);
        frame.setSize(1000,750);
    }
    protected static void stockAdmin(){
        //Initialisation gestion des stock coté admin
        frame.setContentPane(new StockAdmin().panelMain);
        frame.setSize(1000,750);
    }
    protected static void shopManagement(){
        //Initialisation gestion des magasins
        frame.setContentPane(new ShopManagement().panelMain);
        frame.setSize(1000,750);
    }

    protected static void changeUserData(){
        //Initialisation gestion des magasins
        frame.setContentPane(new ChangeUserData().panelMain);
        frame.setSize(700,500);
    }
    public static void errorPopUp(String message){

        //Initialisation de la pop up d'erreur avec un message
        int w =message.length();
        ErrorPopUp.varText=message;
        Display.Errframe = new JFrame("Erreur");
        Errframe.setContentPane(new ErrorPopUp().panelMain);
        Errframe.setSize(220+(w * 4),180);
        Errframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Errframe.setVisible(true);
    }

    public static void actionPopUp(String message){

        //Initialisation de la pop up interaction
        int w =message.length();
        ActionPopUp.varText=message;
        Display.Actionframe = new JFrame("Istore");
        Actionframe.setContentPane(new ActionPopUp().panelMain);
        Actionframe.setSize(220+(w * 4),180);
        Actionframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Actionframe.setVisible(true);
    }
}
