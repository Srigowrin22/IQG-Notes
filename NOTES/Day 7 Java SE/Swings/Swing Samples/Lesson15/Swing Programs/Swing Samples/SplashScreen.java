/*
 * Created on Aug 23, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.gui.jdbc;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JWindow;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SplashScreen extends JWindow {
	Image img = null;
	
	SplashScreen()
	{
	Toolkit tk = Toolkit.getDefaultToolkit();
	img = tk.getImage("SolarEclipse.jpg");
	MediaTracker md = new MediaTracker(this);
	md.addImage(img,1);
	try {
		md.waitForAll();
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	setSize(img.getWidth(null),img.getHeight(null));
	Dimension d = tk.getScreenSize();
	int w = (d.width-img.getWidth(null))/2;
	int h = (d.height-img.getHeight(null))/2;
	setLocation(w,h);
	}
	public void paint(Graphics g)
	{
		g.drawImage(img,0,0,null);
	}
}
