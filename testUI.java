package HW2;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;


/**
 * testGUI.java
 * 
 * A class representing a password tester. Passwords are generated originally
 * by softTokenUI.java, obtained by this class when the thread is run. Display
 * GUI through testUI_Display.java
 * 
 * @author Gahl Goziker
 */
public class testUI extends Thread {
	
	/** Most recently created hash */
	private String mostRecentHash = "";
	/** Socket connected to the associated softTokenUI */
	private ServerSocket serverSocket;
	
	

	/**
	 * Runs testUI program, spawns GUI
	 * @param args
	 */
	public static void main(String[] args){
		Thread testUI_Thread = new testUI();
		//Display frame
		testUI_Display passwordTest = new testUI_Display((testUI)testUI_Thread);
		//Listens for message from softTokenUI
		testUI_Thread.start();
	}
	
	
	
	/**
	 * Creates a new testUI object
	 * @param args
	 */
	public testUI(){
		try {
			//Hardcoded port number
			serverSocket = new ServerSocket(3736);
			serverSocket.setSoTimeout(100000);
		} catch (IOException e) {
			try {
				serverSocket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}	
	}
	
	
	
	/*
	 * Takes in a password which a user entered
	 * Returns whether or not it matches the actual password
	 */
	public boolean passwordMatch(String enteredPassword){
		boolean retVal;
		String OTP = mostRecentHash.substring(0, 6);
		if(OTP.equals(enteredPassword))
			return true;
		else
			return false;
	}
	
	
	
	/*
	 * Process of the OTP_Client thread
	 */
	public void run(){
		while(true){
			synchronize();
		}
	}
	
	
	
	/*
	 * Synchronizes hash with softTokenUI's hash
	 */
	private void synchronize(){
		//wait for softTokenUI to make contact
		try {
			Socket server = serverSocket.accept();
			DataInputStream in = new DataInputStream(server.getInputStream());
			
			//Read in hash sent from server, containing server's mostRecentHash
			String hash = in.readUTF();
			//synchronize testUI's hash with softTokenUI's
			this.mostRecentHash = hash;
			
	        server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
