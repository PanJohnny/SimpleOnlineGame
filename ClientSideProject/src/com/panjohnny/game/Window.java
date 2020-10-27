package com.panjohnny.game;


import javax.swing.JFrame;

public class Window extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6334947977499663289L;
	public Window(String name, PlayerHandler handler) {
		System.out.println("---------------Window---------------");
		System.out.println("setting size to 500x500");
		this.setSize(500, 500);
		System.out.println("setting default close operation to JFrame.EXIT_ON_CLOSE");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		System.out.println("setting window resizable?"+false);
		this.setResizable(false);
		System.out.println("setting window always on top");
		this.setAlwaysOnTop(true);
		System.out.println("adding handler as component");
		this.add(handler);
		System.out.println("setting window to visible");
		this.setVisible(true);
		System.out.println("is window visible?"+isVisible());
		System.out.println("-----------------------------------");
	}
}
