package com.panjohnny.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.UUID;

public class Player {
	public Color color;
	public int x=0, y=0;
	public UUID uuid;
	public Player(UUID uuid) {
		this.uuid=uuid;
//		Random ran = new Random();
//		float r = ran.nextFloat();
//		float g= ran.nextFloat();
//		float b=ran.nextFloat();
//		color = new Color(r, g, b);
		color = Color.green;
	}
	
	public Player(UUID uuid, Color color) {
		this.uuid=uuid;
		this.color = color;
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, 10, 10);
	}
}
