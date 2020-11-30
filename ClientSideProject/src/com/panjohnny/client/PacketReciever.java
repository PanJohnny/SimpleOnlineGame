package com.panjohnny.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.panjohnny.game.Player;
import com.panjohnny.game.PlayerHandler;
import com.panjohnny.packet.Packet;
import com.panjohnny.packet.PlayerJoinPacket;
import com.panjohnny.packet.PlayerLeavePacket;
import com.panjohnny.packet.PlayerLocationPacket;
import com.panjohnny.packet.PlayerRequestPacket;
import com.panjohnny.packet.TextMessagePacket;

public class PacketReciever implements Runnable {
	public ObjectInputStream in;
	public Thread thr;
	public boolean recieving=true;
	public PlayerHandler ha;
	public PacketReciever(ObjectInputStream in, PlayerHandler ha) {
		this.in=in;
		this.ha=ha;
		thr=new Thread(this);
		thr.start();
	}
	private boolean playerNotFound=true;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean chatting=true;
		while(chatting) {
			try {
				Packet p = (Packet)in.readObject();
				if(p instanceof TextMessagePacket) {
					System.out.println(((TextMessagePacket)p).u());
				}
				if(p instanceof PlayerJoinPacket) {
					System.out.println("adding player with uuid: "+((PlayerJoinPacket)p).uuid.toString());
					ha.otherPlayers.add(new Player(((PlayerJoinPacket)p).uuid,((PlayerJoinPacket)p).color));
				}
				if(p instanceof PlayerLeavePacket) {
					PlayerLeavePacket packet = (PlayerLeavePacket) p;
					if(!ha.otherPlayers.isEmpty()) {
						ha.otherPlayers.forEach(player -> {
							if(player.uuid==packet.uuid) {
								System.out.println("removing player with uuid: "+player.uuid.toString());
								ha.otherPlayers.remove(player);
							}
						});}
				}
				if(p instanceof PlayerLocationPacket) {
					UUID missingUUID=null;
					playerNotFound=true;
					for(int i=0; i<ha.otherPlayers.size();i++) {
						Player player = ha.otherPlayers.get(i);
						PlayerLocationPacket plp = (PlayerLocationPacket) p;
						System.out.println("recieved PlayerLocationPacket with uuid "+plp.getUUID());
						missingUUID=plp.getUUID();
						if(player.uuid==plp.getUUID()) {
							player.x=plp.getX();
							player.y=plp.getY();
							playerNotFound(false);
						}

					}
					if(ha.otherPlayers.size()<1) {
						PlayerLocationPacket plp = (PlayerLocationPacket) p;
						missingUUID=plp.getUUID();
					}
					if(playerNotFound) {
						System.out.println("sent player request packet for "+missingUUID);
						ha.client.sen.sendPacket(new PlayerRequestPacket(missingUUID));
					}
				}
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				chatting=false;
			}
		}
		recieving=false;
		System.out.println("Stopped recieving");
	}

	public void playerNotFound(boolean found) {
		playerNotFound=found;
	}

}
