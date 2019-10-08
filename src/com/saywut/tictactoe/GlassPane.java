package com.saywut.tictactoe;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;

public class GlassPane extends JComponent implements Observer
{
	private static final long serialVersionUID = 1L;
	private GameGridPanel[] winnerStartEndPanels;
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		
		if (winnerStartEndPanels != null)
		{
			GameGridPanel start = winnerStartEndPanels[0];
			GameGridPanel end = winnerStartEndPanels[1];
			
			int xStart = start.getBounds().x + start.getBounds().width / 2;
			int yStart = start.getBounds().y + start.getBounds().height / 2;
			int xEnd = end.getBounds().x + start.getBounds().width / 2;
			int yEnd = end.getBounds().y + start.getBounds().height / 2;

			String winner = start.getText() + " Winner";
			Font f = new Font("", Font.BOLD, getWidth() / 6);
			
			g2.setStroke(new BasicStroke(15));
			g2.setColor(Color.RED);
			g2.drawLine(xStart, yStart, xEnd, yEnd);
			g2.setFont(f);
			g2.setColor(Color.GREEN);
			
			int winnerTextWidth = (int) g2.getFontMetrics().getStringBounds(winner, g2).getWidth();
			int winnerTextHeight = (int) g2.getFontMetrics().getStringBounds(winner, g2).getHeight();
			int winnerCenterX = getWidth() / 2 - winnerTextWidth / 2;
			int winnerCenterY = getHeight() / 2 + winnerTextHeight / 2;
			
			g2.drawString(winner, winnerCenterX, winnerCenterY);
		}
	}
	
	@Override
	public void update(Observable o, Object arg)
	{
		winnerStartEndPanels = (GameGridPanel[])arg;
		repaint();
	}
}
