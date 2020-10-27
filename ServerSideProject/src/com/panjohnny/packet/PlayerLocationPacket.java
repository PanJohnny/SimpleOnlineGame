package com.panjohnny.packet;

import java.util.UUID;

public class PlayerLocationPacket extends Packet{
	private int x;
	private int y;
	private UUID uuid;
	public PlayerLocationPacket(int x, int y, UUID uuid) {
		this.x=x;
		this.y=y;
		this.uuid=uuid;
	}
	@Override
	public String u() {
		return "coords";
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public UUID getUUID() {
		return uuid;
	}
}
