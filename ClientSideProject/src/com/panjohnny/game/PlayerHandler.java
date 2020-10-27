package com.panjohnny.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import com.panjohnny.client.Client;

public class PlayerHandler extends Canvas implements Runnable{
	private static final long serialVersionUID = -92605406662111996L;
	boolean running=true;
	public List<Player> otherPlayers = new ArrayList<Player>();
	public Player localPlayer;
	public Client client;
	public Thread thr;
	public InputManager inMa;
	public PlayerHandler(Client c) {
		client=c;
	}
	public void start() {
		System.out.println("starting player handler");
		thr=new Thread(this);
		Window w = new Window("client test", this);
		inMa=new InputManager(this);
		this.addKeyListener(inMa);
		thr.start();
	}
	@Override
	public void run() {
		localPlayer=new Player(client.sen.uuid);
		while(running) {
			render();
		}
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs==null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, 500, 500);
		otherPlayers.forEach(p -> {
			p.render(g);
		});
		localPlayer.render(g);
		g.dispose();
		bs.show();
	}

}
