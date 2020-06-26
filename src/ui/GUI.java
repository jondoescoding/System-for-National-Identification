package ui;

import app.SNIDApp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JPanel{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public GUI(SNIDApp app) {
        //BUTTONS
        JButton clearButton = new JButton ("Clear");
        JButton searchButton = new JButton("Search");
        Icon iconQuit = new ImageIcon("C:/Users/fuse1/CODING/JAVA/COMP_PROJECT_ALI/src/ui/resources/quit.png");
        JButton quitButton = new JButton(iconQuit);
        quitButton.setBorderPainted( false );
        quitButton.setFocusPainted( false );
        quitButton.setContentAreaFilled(false);
        //Construct Components
        JRadioButton idButton = new JRadioButton("Search by ID");
        JRadioButton nameButton = new JRadioButton("Search by Name");
        JRadioButton biometric_searchButton = new JRadioButton("Biometric Search");
        //BUTTONS GROUP
        ButtonGroup searchGroup= new ButtonGroup();
        searchGroup.add(idButton);
        searchGroup.add(nameButton);
        searchGroup.add(biometric_searchButton);
        //TEXT BARS
        JTextArea mainTextArea = new JTextArea(5, 5);
        JTextArea mainDisplay = new JTextArea(5, 5);
        mainDisplay.setText("DETAILS OF SELECTED RECORD(S) \nARE DISPLAYED HERE");

        DefaultListModel<String> idModel= new DefaultListModel<String>();
        // ActionListeners are constructed here
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputBar = mainTextArea.getText();
                if(!(idModel.isEmpty())){
                    idModel.clear();
                }
                if(idButton.isSelected()){
                    String result = app.searchById(inputBar);
                    String[] displayed_result= result.split(",");
                    if(displayed_result.length>2){
                        idModel.add(0, displayed_result[0]);
                        mainDisplay.setText("ID: "+displayed_result[0]+"\n"+ "Gender: "+ displayed_result[1] +"\n"+ "Name: "+displayed_result[2]+" "+displayed_result[3]+" "+displayed_result[4]);
                    }
                    
                }else if(nameButton.isSelected()){
                    String[] result_array= app.searchByLastName(inputBar);
                    String[][] result= new String[5][100];
                    String[] holder;
                    int j=0;
                    for(int i=0; i<result_array.length; i++) {
                        if(result_array[i]!=null) {
                            holder = result_array[i].split(",");
                            result[0][j] = holder[0];
                            result[1][j] = holder[1];
                            result[2][j] = holder[2];
                            result[3][j] = holder[3];
                            result[4][j] = holder[4];
                            j++;
                        }
                    }
                    String id_list= "";
                    for(int k=0;k<j;k++){idModel.add( k, id_list+result[0][k]);}
                    mainDisplay.setText("ID: "+result[0][0]+"\n"+"Gender: "+ result[1][0]+ "\n"+ "Name: "+result[2][0]+" "+result[3][0]+" "+result[4][0]                            );
                }
            }
        });

        JList idDisplay= new JList(idModel);
        MouseListener mouseID= new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()>= 1) {
                    super.mouseClicked(e);
                    String selected = idDisplay.getSelectedValue().toString();
                    String result = app.searchById(selected);
                    String[] displayed_result = result.split(",");
                    mainDisplay.setText("Name: " + displayed_result[2] + " " + displayed_result[3] + " " + displayed_result[4] + "\n" +
                            "Gender: " + displayed_result[1]);
                }
            }
        };
        idDisplay.addMouseListener(mouseID);
        clearButton.addActionListener(e -> {
            idModel.removeAllElements();
            mainDisplay.setText("DETAILS OF SELECTED RECORD(S) \nARE DISPLAYED HERE");
            mainTextArea.setText("");
        });
        
        quitButton.addActionListener(e -> {
            System.exit(0);
        });

        //Adjust Window Size
        setPreferredSize (new Dimension (495, 388));
        setLayout (null);
        //Add Components
        add (idButton);
        add (nameButton);
        add (biometric_searchButton);
        add (mainTextArea);
        add (idDisplay);
        add (mainDisplay);
        add (searchButton);
        add (clearButton);
        add (quitButton);
        
        //Set Component Bounds
        idButton.setBounds (9, 25, 100, 25);
        nameButton.setBounds (138, 25, 120, 25);
        biometric_searchButton.setBounds (280, 25, 125, 25);
        mainTextArea.setBounds (10, 65, 345, 30);
        idDisplay.setBounds (10, 120, 95, 255);
        mainDisplay.setBounds (115, 120, 240, 255);
        searchButton.setBounds (365, 64, 103, 27);
        clearButton.setBounds (365, 109, 103, 27);
        quitButton.setBounds (365, 154, 103, 50);
        

        //Colour
        setOpaque(true);
        setBackground(Color.lightGray);
    }
}