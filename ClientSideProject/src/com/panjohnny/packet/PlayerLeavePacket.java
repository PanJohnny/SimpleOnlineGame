package com.panjohnny.packet;

import java.util.UUID;

public class PlayerLeavePacket extends Packet{
	public UUID uuid;
	public PlayerLeavePacket(UUID uuid) {
		this.uuid=uuid;
	}
	@Override
	public String u() {
		// TODO Auto-generated method stub
		return "PlayerLeavePacket-"+uuid.toString();
	}

}
