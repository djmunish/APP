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
	public static Arena arena;
	public static Player human;
	
	
	

	public static void startServer(int port) throws SecurityException, IOException {
		try {
			Runnable task = () -> {
				receive(port);
			};
			Thread thread = new Thread(task);
			thread.start();
		}catch(Exception e) {
			System.out.println("Exception in start server: " + e);
		}
	}
	
	 private synchronized static void receive(int port) {
		 MulticastSocket  aSocket = null;
		 try {
				boolean flag=false;
				String	rep = null;
				aSocket = new MulticastSocket (port);
				aSocket.joinGroup(InetAddress.getByName("230.1.1.5"));
				System.out.println("Server " + port + " Started............");
				while (true) {
					byte[] buffer = new byte[10000];
					DatagramPacket request = new DatagramPacket(buffer, buffer.length);
					aSocket.receive(request);
					Received = new String(request.getData());
					System.out.println("Output received is : " + Received);
					String Receive = Received.substring(0, 1);
					if(Receive.equals("H")) {
						System.out.println("Hit received is " + Receive.substring(1));
						flag = Ships.checkhit(Receive.substring(1), human);
						if(flag) {
							rep = "R,Y";
						}else {
							rep = "R,N";
						}
						//arena.postHit();
					}else if(Receive.equals("S")) {
						System.out.println("Salva Hit received is " + Receive.substring(1));
					}else if(Receive.equals("R")) {
						System.out.println("Response received is " + Receive.substring(1));
					}
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
	 	}
	 
	   public static void sendMessage(int serverPort, String Sem) {
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

	
	
	
	
	
}
