package com.panjohnny.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.UUID;

import com.panjohnny.packet.Packet;
import com.panjohnny.packet.PlayerJoinPacket;
import com.panjohnny.packet.PlayerLeavePacket;
import com.panjohnny.packet.PlayerLocationPacket;
import com.panjohnny.packet.PlayerRequestPacket;
import com.panjohnny.packet.TextMessagePacket;
import com.panjohnny.server.Server;

public class ServerClient implements Runnable{
	public boolean reading =true;
	public Socket socket;
	private Thread thr;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Server server;	
	public UUID uuid;
	public PlayerJoinPacket myPJP=null;
	public ServerClient(Socket socket, Server server) {
		System.out.println("created instance of ServerClient");
		this.socket=socket;
		thr=new Thread(this);
		thr.start();
		this.server=server;
	}

	public void sendPacket(Packet p) {
		try {
			out.writeObject(p);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Packet readPacket() {
		try {
			return (Packet)in.readObject();
		} catch (Exception e ) {
			// TODO Auto-generated catch block
			System.out.println("client disconnected");
			server.clients.remove(this);
			server.clients.forEach(c-> {
				c.sendPacket(new PlayerLeavePacket(uuid));
			});
			reading=false;
			try {
				thr.join(0);

			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		}
	}
	public int packetCount=0;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			sendPacket(new TextMessagePacket("hello you succesfully connected"));
			String name="USER-"+new Random().nextInt(99999);
			while(reading) {
				Packet p = readPacket();
				packetCount++;
				if(p instanceof PlayerJoinPacket) {
					PlayerJoinPacket pjp=(PlayerJoinPacket) p;
					//setting uuid from local player looooool
					System.out.println("setting uuid for player "+name+" to "+pjp.uuid);
					uuid=pjp.uuid;
					//oh message
					myPJP = pjp;
					String message = name+" joined the game";
					//for each of the clients
					server.clients.forEach(client -> {
						if(client!=this) {
							client.sendPacket(new TextMessagePacket(message));
							client.sendPacket(pjp);
							
						}
					});
				}
				if(p instanceof TextMessagePacket) {
					String message = name+": "+((TextMessagePacket)p).u();
					System.out.println(message);
					server.clients.forEach(client -> {
						if(client!=this) {
							client.sendPacket(new TextMessagePacket(message));

						}
					});
				}
				if(p instanceof PlayerLocationPacket) {
					
					server.clients.forEach(client->{
						if(client!=this) {
							client.sendPacket((PlayerLocationPacket)p);
						}
					});
				}
				if(p instanceof PlayerRequestPacket) {
					System.out.println("getting PlayerRequestPacket for uuid: "+((PlayerRequestPacket)p).uuid);
					System.out.println("listing possible uuids: ");
					System.out.print("[");
					PlayerRequestPacket prp = (PlayerRequestPacket) p;
					server.clients.forEach(c -> {
						System.out.print(c.uuid.toString()+",");
						if(prp.uuid.equals(c.uuid)) {
							System.out.println("getting this pjp: ["+c.myPJP.uuid+"," +c.myPJP.color+"]");
							sendPacket(c.myPJP);
						}
					});
					System.out.print("]");
					System.out.println();
					System.out.println("listing finished...");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block

		}

	}
}
