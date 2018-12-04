package HW2;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class softTokenUI {
	
	/** Secret key used to generate passwords */
	private String key = "808670FF00FF08812";
	/** Stores most recently created hash */
	private String mostRecentHash = key;
	/** Socket connected to the associated testUI */
	private Socket clientSocket;
	
	
	
	/**
	 * Runs softTokenUI program, spawns the GUI
	 * @param args
	 */
	public static void main(String[] args){
		
		softTokenUI testClient = new softTokenUI();
		softTokenUI_Display UI = new softTokenUI_Display(testClient);
//		collisionResistance();
	}
	
	
	
	/**
	 * Measure the probability of generating the same OTP over a period of time
	 */
	public static void collisionResistance(){
		
		//Run through # of OTP's to generate
		for(int numToGenerate = 10; numToGenerate <= 1000000; numToGenerate += 100000){
			
			int collisionCount = 0;
			softTokenUI CR = new softTokenUI();
			Set<String> set = new HashSet<String>();
			
			//Generate OTP's
			for(int i = 0; i < numToGenerate; i++){
				String hash = CR.generateOTP();
				if(set.contains(hash)){
					collisionCount++;
				}else{
					set.add(hash);
				}
			}
			System.out.println(collisionCount + " collisions occured when generating " + numToGenerate + " OTP's");
		}
	}
	
	
	
	/**
	 * Display GUI
	 */
	public void displaySoftTokenUI(){
		softTokenUI_Display button = new softTokenUI_Display(this);
	}
	
	
	
	/**
	 * Generate new password and display it to GUI
	 * @return	New 6-digit onetime password
	 */
	public String generateOTP(){
		String newHash = hash(this.mostRecentHash);
		this.mostRecentHash = newHash;
		// onetime password = first 6 digits of hash
		String oneTimePass = this.mostRecentHash.substring(0, 6);
		return oneTimePass;
	}
	
	
	
	/*
	 * Returns an SHA 256 hash of the passed string
	 */
	public static String hash(String input){
		
		//Use built-in SHA 256 hash
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] encodedhash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
		
		//convert hashed bytes to a string
		StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < encodedhash.length; i++) {
	    String hex = Integer.toHexString(0xff & encodedhash[i]);
	    if(hex.length() == 1) hexString.append('0');
	        hexString.append(hex);
	    }
	    
	    String retVal = hexString.toString();
	    return retVal;
	}
	
	
	
//	/*
//	 * Constructor; initializes clientSocket
//	 */
//	public softTokenUI(){
//		try {
//			this.clientSocket = new Socket("localhost", 3736);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	
	
	/*
	 * Sends most recent hash to testUI
	 */
	public void sendHash(){
		String send = mostRecentHash;
		
		try {
			//Connect socket to testUI
			this.clientSocket = new Socket("localhost", 3736);
			//Writes to testUI through Socket
			DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
			out.writeUTF(send);
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
