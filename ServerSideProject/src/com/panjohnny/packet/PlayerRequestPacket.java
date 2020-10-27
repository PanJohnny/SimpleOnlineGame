package com.panjohnny.packet;

import java.util.UUID;

public class PlayerRequestPacket extends Packet{
	public UUID uuid;
	public PlayerRequestPacket(UUID uuidRequest) {
		// TODO Auto-generated constructor stub
		this.uuid=uuidRequest;
	}
	@Override
	public String u() {
		// TODO Auto-generated method stub
		return "Reguest of player: "+uuid.toString();
	}
}
