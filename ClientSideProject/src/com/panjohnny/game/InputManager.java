package com.panjohnny.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.panjohnny.packet.PlayerLocationPacket;

public class InputManager implements KeyListener{
	PlayerHandler h;
	public InputManager(PlayerHandler h) {
		this.h=h;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public int speed = 5;
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			h.localPlayer.y+=speed;
			h.client.sen.sendPacket(new PlayerLocationPacket(h.localPlayer.x, h.localPlayer.y,h.localPlayer.uuid));
		} else if(e.getKeyCode()==KeyEvent.VK_UP) {
			h.localPlayer.y-=speed;
			h.client.sen.sendPacket(new PlayerLocationPacket(h.localPlayer.x, h.localPlayer.y,h.localPlayer.uuid));
		}else if(e.getKeyCode() ==KeyEvent.VK_RIGHT) {
			h.localPlayer.x+=speed;
			h.client.sen.sendPacket(new PlayerLocationPacket(h.localPlayer.x, h.localPlayer.y,h.localPlayer.uuid));
		}
		if(e.getKeyChar()==KeyEvent.VK_LEFT) {
			h.localPlayer.x-=speed;
			h.client.sen.sendPacket(new PlayerLocationPacket(h.localPlayer.x, h.localPlayer.y,h.localPlayer.uuid));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
