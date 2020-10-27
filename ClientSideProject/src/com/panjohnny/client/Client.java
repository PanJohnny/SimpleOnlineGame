package com.panjohnny.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.panjohnny.game.PlayerHandler;
import com.panjohnny.packet.TextMessagePacket;

public class Client implements Runnable{
	public PacketSender sen;
	public PacketReciever rec;
	public PlayerHandler pha;
	public Thread thread;
	public Client() {
		thread=new Thread(this);
		thread.start();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client c= new Client();
	}
	@Override
	public void run() {
		try {
			Socket socket = new Socket("localhost", 8756);
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("recieved message from server: "+((TextMessagePacket)in.readObject()).u());
			System.out.println("creating packet sender");
			sen = new PacketSender(out, this);
			System.out.println("creating player handler instance only not starting");
			pha=new PlayerHandler(this);
			System.out.println("creating packet reciever");
			rec = new PacketReciever(in, pha);
			boolean checking=true;
			while(checking) {
				if(sen.uuid!=null) {
					System.out.println("notnull");
					pha.start();
					checking=false;
				}
				System.out.println("null");
			}
			System.out.println("stopped checking");
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("server stopped");
			System.out.println("terminating process...");
			System.exit(0);
		}
	}
}
