package HW2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class testUI_Display {
	   private JFrame mainFrame;
	   private JLabel headerLabel;
	   private JLabel statusLabel;
	   private JPanel controlPanel;
	   //Stores a reference to the calling client, so it can test password input
	   private testUI testUI;
	   
	   
	   public testUI_Display(testUI caller){
		  this.testUI = caller;
	      setupFrame();      
	      setupTest();
	   }
	   
	   
	   
	   private void setupFrame(){
	      mainFrame = new JFrame("Java Swing Examples");
	      mainFrame.setSize(400,400);
	      mainFrame.setLayout(new GridLayout(3, 1));
	      
	      mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      });    
	      headerLabel = new JLabel("", JLabel.CENTER);
	      statusLabel = new JLabel("",JLabel.CENTER);
	      statusLabel.setSize(350,100);

	      controlPanel = new JPanel();
	      controlPanel.setLayout(new FlowLayout());

	      mainFrame.add(headerLabel);
	      mainFrame.add(controlPanel);
	      mainFrame.add(statusLabel);
	      mainFrame.setVisible(true);  
	   }
	   
	   
	   
	   private void setupTest(){
	      headerLabel.setText("OTP Test UI"); 

	      JLabel  passwordLabel = new JLabel("Enter password: ", JLabel.CENTER);
	      final JTextField passwordInput = new JTextField(6);      

	      JButton enterButton = new JButton("Enter");
	      enterButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {   
	        	 
	        	//Read in the user's password entry
	        	String enteredPassword = new String(passwordInput.getText());
	            
	            //Check whether entered password is correct
	            boolean passwordCorrect = testUI.passwordMatch(enteredPassword);
	            if(passwordCorrect){
	            	statusLabel.setText("Access Granted");
	            }else{
	            	statusLabel.setText("Incorrect password");
	            }
	         }
	      });
	      controlPanel.add(passwordLabel);       
	      controlPanel.add(passwordInput);
	      controlPanel.add(enterButton);
	      mainFrame.setVisible(true);  
	   }
	}