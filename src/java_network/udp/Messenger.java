package java_network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

public class Messenger extends Thread {
	private DatagramSocket socket;
	private DatagramPacket packet;
	
	private JTextArea textArea;
	private InetAddress address;
	private int otherPort;
	
	public Messenger(int myPort, int otherPort, String address){
	
		try {
			socket = new DatagramSocket(myPort);
			this.address = InetAddress.getByName(address);
			this.otherPort = otherPort;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
	
	@Override
	public void run() {
		while(true){
			byte[] buf = new byte[255];
			packet = new DatagramPacket(buf, buf.length);
			try {
				socket.receive(packet);// 소켓에다 데이터를 담는다.
				textArea.append("RECEIVED : " + new String(buf) + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void sendMessage(String msg){
		byte[] buf = msg.getBytes();
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address, otherPort);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
