package com.tecacet.life.gui;

import javax.swing.JApplet;

@SuppressWarnings("serial")
public class LifeApplet extends JApplet {

	LifePanel panel;

	public void init() {
		panel = new LifePanel();
		getContentPane().add(panel);
	}
}
