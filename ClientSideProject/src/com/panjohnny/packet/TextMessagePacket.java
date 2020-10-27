package com.panjohnny.packet;


public class TextMessagePacket extends Packet {
	private String msg;
	public TextMessagePacket(String text) {
		msg=text;
	}
	@Override
	public String u() {
		// TODO Auto-generated method stub
		return msg;
	}
	
	

}
