import java.util.*;

import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

public class Udp {
	
	static String Received = null;

	public static void startServer() throws SecurityException, IOException {
		try {
			Runnable task = () -> {
				receive();
			};
			Thread thread = new Thread(task);
			thread.start();
		}catch(Exception e) {
			System.out.println("Exception in start server: " + e);
		}
	}
	
	 private synchronized static void receive() {
		 MulticastSocket  aSocket = null;
		 try {
				boolean flag=false;
				String	rep = null;
				aSocket = new MulticastSocket (8888);
				aSocket.joinGroup(InetAddress.getByName("230.1.1.5"));
				System.out.println("Server 8888 Started............");
				while (true) {
					byte[] buffer = new byte[10000];
					DatagramPacket request = new DatagramPacket(buffer, buffer.length);
					aSocket.receive(request);
					Received = new String(request.getData());
					System.out.println("Output received is : " + Received);
					rep = "Hello 2";
					buffer = rep.getBytes();
					DatagramPacket reply = new DatagramPacket(buffer, buffer.length, request.getAddress(),
							request.getPort());
					aSocket.send(reply);
				}
			} catch (SocketException e) {
				System.out.println("Socket: " + e.getMessage());
			} catch (IOException e) {
				System.out.println("IO: " + e.getMessage());
			} finally {
				if (aSocket != null)
					aSocket.close();
			}
		 
		 sendMessage(7777,"Hello");
		 
	 }
	 
	   private static void sendMessage(int serverPort, String Sem) {
			DatagramSocket aSocket = null;
			try {
				aSocket = new DatagramSocket();
				byte[] message = Sem.getBytes();
				InetAddress aHost = InetAddress.getByName("230.1.1.5");
				System.out.println("sem length is:" + Sem.length());
				DatagramPacket request = new DatagramPacket(message, Sem.length(), aHost, serverPort);
				aSocket.send(request);
				System.out.println("Request message sent from the client to server with port number " + serverPort + " is: "
						+ new String(request.getData()));
				byte[] buffer = new byte[1000];
				DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
				aSocket.receive(reply);
				Received = null;
				Received = new String(reply.getData()).trim();
					System.out.println("Reply received from the server with port number " + serverPort + " to COMP server is: "
								+ Received);
			} catch (SocketException e) {
				System.out.println("Socket: " + e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("IO: " + e.getMessage());
			} finally {
				if (aSocket != null)
					aSocket.close();
			}
		}
	
	 public static void main(String args[]) throws InvalidName, AdapterInactive, ServantNotActive, WrongPolicy, org.omg.CosNaming.NamingContextPackage.InvalidName, NotFound, CannotProceed, SecurityException, IOException {
		 		startServer();
		 		
			}
	
	
	
	
	
}
