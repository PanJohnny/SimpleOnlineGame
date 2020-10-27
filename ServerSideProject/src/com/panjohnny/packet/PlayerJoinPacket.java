package com.panjohnny.packet;

import java.awt.Color;
import java.util.UUID;

public class PlayerJoinPacket extends Packet {
	public UUID uuid;
	public Color color;
	public PlayerJoinPacket(UUID uuid, Color color) {
		this.uuid=uuid;
		this.color=color;
	}
	@Override
	public String u() {
		// TODO Auto-generated method stub
		return "PjoinPckt+"+uuid.toString();
	}



}
