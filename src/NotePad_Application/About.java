package NotePad_Application;

import javax.swing.*;
import java.awt.*;

public class About extends JFrame {

    About(){
        setBounds(450,200,600,400);
        setTitle("About Notepad Application");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        //changing icon of the application
        ImageIcon icon=new ImageIcon(getClass().getResource("Notepad_icon.png"));
        setIconImage(icon.getImage());


        JLabel iconLabel=new JLabel(new ImageIcon(getClass().getResource("About_image.png")));
        iconLabel.setBounds(130,60,80,80);
        add(iconLabel);

        //as we can not use \n in JLabel,So html code is used here
        JLabel textLabel=new JLabel("<html><br><br><br><br><b>&nbsp;&nbsp;&nbsp; Welcome to NotePad </b><br>&nbsp; &nbsp; This application is programmed by : <i>Bibhuti Biswal</i><br>&nbsp; &nbsp; This app is Made using Java Programming Language<br>&nbsp; &nbsp; For any feedback contact : <i>biswalbibhuti8@gmail.com</i></html>");
        textLabel.setBounds(110,20,350,300);
        textLabel.setFont(new Font(Font.DIALOG ,Font.PLAIN,13));
        add(textLabel);

        setVisible(true);
    }



    public static void main(String[] args) {

//        new About().setVisible(true);
        new About();
    }

}
