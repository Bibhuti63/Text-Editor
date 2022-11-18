package NotePad_Application;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.io.*;
import java.io.FileReader;//manual

public class NotepadApp extends JFrame implements ActionListener {

    //to create menubar of notpad
    JMenuBar menubar=new JMenuBar();

    //to add menu into menubar
    JMenu file=new JMenu("File");
    JMenu edit=new JMenu("Edit");
    JMenu help=new JMenu("Help");

    //to add menu items to menu
    JMenuItem newFile=new JMenuItem("New");
    JMenuItem openFile=new JMenuItem("Open");
    JMenuItem saveFile=new JMenuItem("Save");
    JMenuItem printFile=new JMenuItem("Print");
    JMenuItem exitFile=new JMenuItem("Exit");

    JMenuItem cutEdit=new JMenuItem("Cut");
    JMenuItem copyEdit=new JMenuItem("Copy");
    JMenuItem pasteEdit=new JMenuItem("Paste");
    JMenuItem selectallEdit=new JMenuItem("Select All");

    JMenuItem aboutHelp=new JMenuItem("About");

    //to add textarea in the JFrame where we can write the text
    JTextArea textArea=new JTextArea();


    NotepadApp(){   //constructor
        //title of the application to show
        setTitle("Notepad Application");//try to align the title to center
        //location(left,top,width,height)
        setBounds(350,100,800,600);
        //set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


//        JFrame frame = new JFrame();
//        frame.setPreferredSize(new Dimension(300, 200));
//        frame.setTitle("Hello Center");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //changing icon of the application
        ImageIcon icon=new ImageIcon(getClass().getResource("Notepad_icon.png"));
        setIconImage(icon.getImage());//adding icon to the application

        //add menubar into Notepad
        setJMenuBar(menubar);
        //add filemenu to menubar
        menubar.add(file);
        menubar.add(edit);
        menubar.add(help);

        //adding menu items to its respective menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        file.add(printFile);
        file.add(exitFile);

        edit.add(cutEdit);
        edit.add(copyEdit);
        edit.add(pasteEdit);
        edit.add(selectallEdit);

        help.add(aboutHelp);


        //setting default font setting for textArea
        textArea.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20)); //3 parameter are (fontfamily,fonttype,fontsize)

        //we can add textArea to the Frame but we will add it to scrollpane to get the functionality of scrolling
//        add(textArea);

        //ading scrollpane to JFrame which will give us option to scroll
        JScrollPane scrollPane=new JScrollPane(textArea);   //adding textArea to scrollpane
        add(scrollPane);    //adding scrollpane to JFrame

        // //by default only both scrollpane is there so to disable it and enable vertical scrollpane
        //
        //     //a. disable horizontal scroll bar
        // scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //     //b. enable vertical scrollbar when required
        // scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        // textArea.setLineWrap(true);
        // textArea.setWrapStyleWord(true);


        //we have to remove border of scrollPane for better UI
        scrollPane.setBorder(BorderFactory.createEmptyBorder());


        //EVENT HANDLING WITH HELP OF "ActionListner"
        //Make sure that NotePadApp class Implement ActionListner interface and necessary library imported
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        printFile.addActionListener(this);
        exitFile.addActionListener(this);

        cutEdit.addActionListener(this);
        copyEdit.addActionListener(this);
        pasteEdit.addActionListener(this);
        selectallEdit.addActionListener(this);

        aboutHelp.addActionListener(this);

        //adding keyboard shortcut to the menu items
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        printFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        exitFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK));

        cutEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        copyEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        pasteEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
        selectallEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));

        aboutHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, KeyEvent.CTRL_DOWN_MASK));

    }


    //here the class implements the method of ActionListner interface
    @Override
    public void actionPerformed(ActionEvent e) {
        //all event handling related code will done here
        //e will tel which menu item is clicked
        //we have used .equalsIgonreCase() so it will not check for upper and lowe case
        if(e.getActionCommand().equalsIgnoreCase("new")){
            //reset the textArea
            textArea.setText(null);

        }else if(e.getActionCommand().equalsIgnoreCase("open")){

            JFileChooser fileChooser=new JFileChooser(); //it will use to choose the file
            //applying filter show that it will only accept .txt file
            FileNameExtensionFilter textFilter=new FileNameExtensionFilter("Only Text Files(.txt)","txt");
            fileChooser.setAcceptAllFileFilterUsed(false); //it will accept all types of files
            fileChooser.addChoosableFileFilter(textFilter); //it will only choose file which sepecified in fileChooser

            int action=fileChooser.showOpenDialog(null);
            if(action!=JFileChooser.APPROVE_OPTION){
                return;
            }else{
                try {
                    BufferedReader br=new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
                    textArea.read(br,null);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }

        }else if(e.getActionCommand().equalsIgnoreCase("save")){

            JFileChooser fileChooser=new JFileChooser(); //it will use to choose the file
            //applying filter show that it will only accept .txt file
            FileNameExtensionFilter textFilter=new FileNameExtensionFilter("Only Text Files(.txt)","txt");
            fileChooser.setAcceptAllFileFilterUsed(false); //it will accept all types of files
            fileChooser.addChoosableFileFilter(textFilter); //it will only choose file which sepecified in fileChooser

            int action=fileChooser.showSaveDialog(null);
            if(action!=JFileChooser.APPROVE_OPTION){
                return;
            }else{
                String fileName=fileChooser.getSelectedFile().getAbsolutePath().toString();
                //if user have not written etension it will add extension , if written it will do nothing
                if(!fileName.contains(".txt")){
                    fileName+=".txt";
                }

                //it will write all thing written in the textArea into the saved file
                //some time filename will give exceprion thats why try&catch block used
                try {
                    BufferedWriter bw=new BufferedWriter(new FileWriter(fileName));
                    textArea.write(bw);
                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
                    ex.printStackTrace();
                }
            }

        }else if(e.getActionCommand().equalsIgnoreCase("print")){

            //inbuilt method for print is there in textArea
            try {
                textArea.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }

        }else if(e.getActionCommand().equalsIgnoreCase("exit")){
            //built in method available
            System.exit(0);

        }else if(e.getActionCommand().equalsIgnoreCase("cut")){
            //built in method
            textArea.cut();

        }else if(e.getActionCommand().equalsIgnoreCase("copy")){
            //built in method
            textArea.copy();
        }else if(e.getActionCommand().equalsIgnoreCase("paste")){
            //built in method
            textArea.paste();
        }else if(e.getActionCommand().equalsIgnoreCase("select all")){
            //built in method
            textArea.selectAll();
        }else if(e.getActionCommand().equalsIgnoreCase("about")){
            //create instance of About class
            new About();
//            new About().setVisible(true);
        }

    }
    //An event is an action that takes place when a user interacts with a program.
    //Event Handling is the mechanism that controls the event and decides what should happen if an event occurs.

    public static void main(String[] args) throws Exception {

        //to change the UI of the application//this will have same UI as the system has
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //this may throw exception so main method throws exception
        //after this make sure to remove border of scrollPane in constructor

        //create instance of NotepadApp class inside main method and show NotepadApp
        //by default the setVisible is hidden so make it true to see the output
        new NotepadApp().setVisible(true);

        //we can make setVisible(true) in side constructor also
//        new NotepadApp();
    }

}

