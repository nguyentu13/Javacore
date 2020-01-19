package edu.fa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.plaf.SliderUI;

public class UDPClient {

	public static void main(String[] args) throws Exception {
	// Create input stream
	BufferedReader inFromUser= new BufferedReader(new InputStreamReader(System.in));
	// Create client socket
	DatagramSocket clientSocket = new DatagramSocket();
	// Translate hostname to TP adress using DNS
	InetAddress IPAdress = InetAddress.getByName("hostname");
	
	byte[] sendData = new byte[1024];
	byte[] receiveData = new byte[1024];
	
	String sentence= inFromUser.readLine();
	sendData = sentence.getBytes();
	// Create datagram with data-to-send, length, IPAddress, port
	DatagramPacket sendPacket= new DatagramPacket(sendData, sendData.length, IPAdress, 9876);
	// Send datagram to server
	clientSocket.send(sendPacket);
	
	DatagramPacket receivePacket= new DatagramPacket(receiveData,receiveData.length);
	// Read datagaram from server
	clientSocket.receive(receivePacket);
	
	String modifiedSentence = new String (receivePacket.getData());
	
	System.out.println("FROM SERVER: "+ modifiedSentence);
	clientSocket.close();
	}

}
