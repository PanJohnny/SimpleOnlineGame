package com.panjohnny.client;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.UUID;

import com.panjohnny.packet.Packet;
import com.panjohnny.packet.PlayerJoinPacket;
import com.panjohnny.packet.TextMessagePacket;

public class PacketSender implements Runnable {
	public ObjectOutputStream out;
	public Thread thr;
	public boolean sending = true;
	private Client client;
	public UUID uuid;
	public PacketSender(ObjectOutputStream out, Client client) {
		this.out=out;
		this.client=client;
		thr=new Thread(this);
		thr.start();
	}
	public boolean sendPacket(Packet p) {
		try {
			out.writeObject(p);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Scanner sc = new Scanner(System.in);
			uuid = UUID.randomUUID();
			System.out.println("CREATED UUID: "+uuid.toString());
			out.writeObject(new PlayerJoinPacket(uuid, Color.red));
			boolean chatting = true;
			while(chatting) {
				String message = sc.nextLine();
				if(message.equalsIgnoreCase("exit")) {
					chatting=false;
				} else {
					out.writeObject(new TextMessagePacket(message));
				}
			}
		}
		catch(Exception e) {
			
		}
		try {
			sending=false;
			System.out.println("stopped sending packets");			
			System.out.println("terminating process...");
			System.exit(0);
			thr.join(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
