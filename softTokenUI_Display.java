package HW2;

import java.util.concurrent.ThreadLocalRandom;

//import javax.*;
import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.GridLayout;
//import java.awt.*;
import java.awt.event.*;

/**
 * One-Time Password System
 * @author Gahl Goziker
 * 
 */
public class softTokenUI_Display {
	private String oneTimePass;
	private JFrame frame;
	private JPanel panel;
	private JButton button;
	private JLabel passwordLabel;
	private softTokenUI softTokenUI;		//Stores a reference to the calling client, so it can generate its OTP
	
	
//	public static void main(String[]args){
//		clientGUI s = new clientGUI("abcdefg");
//	}
	
	
	public softTokenUI_Display(softTokenUI caller){
		this.softTokenUI = caller;
		setupFrame();
	}
	
	public void setupFrame(){
		//Build window frame
		frame = new JFrame("Button");
		frame.setSize(300, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(2, 1));
		
		//Build button
		panel = new JPanel();
		frame.add(panel);
		
		
		button = new JButton("Click here for password");
		panel.add(button);
		button.setSize(100,100);
		button.setVisible(true);
		
		//
		passwordLabel = new JLabel("",JLabel.CENTER);    
		passwordLabel.setSize(350,100);
		

		frame.add(passwordLabel);
		
		
		//When button pushed, obtain OTP, display to frame
		button.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {     
	        	 oneTimePass = softTokenUI.generateOTP();
	        	 //Send updated hash to testUI
	        	 softTokenUI.sendHash();
	             passwordLabel.setText("Password: " + oneTimePass);        
	          }
	       });
		
	}
	
}
