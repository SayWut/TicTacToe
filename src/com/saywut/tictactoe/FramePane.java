package com.saywut.tictactoe;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class FramePane extends JPanel
{
	private static final long serialVersionUID = 1L;
	private GameGridPanel[][] grid = new GameGridPanel[3][3];

	public FramePane()
	{
		setLayout(new GridLayout(3, 3));

		setPreferredSize(new Dimension(500, 500));
				
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid.length; j++)
			{
				grid[i][j] = new GameGridPanel();
				add(grid[i][j]);
			}
	}

	public GameGridPanel[][] getGrid()
	{
		return grid;
	}
}
