package com.saywut.tictactoe;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GameGrid extends Observable implements ActionListener
{
	private GameGridPanel[][] grid = new GameGridPanel[3][3];
	private GameGridPanel[] winnerStartEndPanels = new GameGridPanel[2];
	
	private char playerTurn = 'X';
	
	public GameGrid(JPanel p)
	{
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid.length; j++)
			{
				grid[i][j] = new GameGridPanel();
				grid[i][j].addActionListener(this);
				p.add(grid[i][j]);
			}
	}
	
	@Override
	public void notifyObservers(Object arg)
	{
		setChanged();
		super.notifyObservers(arg);
		clearChanged();
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton source = (JButton)e.getSource();
	
		if (source.isEnabled())
		{
			source.setFont(new Font("", Font.PLAIN, source.getSize().width));
			source.setText(playerTurn + "");
			playerTurn ^= 'X' ^ 'O';

			// Changing the window title to player turn
			((JFrame) SwingUtilities.getWindowAncestor(source)).setTitle(playerTurn + " turn");
		}

		source.setEnabled(false);
		checkIsWin();
	}
	
	public void checkIsWin()
	{
		for (int i = 0; i < grid.length; i++)
		{
			boolean checkRows = true;
			boolean checkColumns = true;
			boolean checkLeftDiag = true;
			boolean checkRightDiag = true;
			
			for (int j = 0; j < grid.length - 1; j++)
			{
				checkRows = grid[i][j].getText().equals(grid[i][j + 1].getText()) && checkRows;
				checkColumns = grid[j][i].getText().equals(grid[j + 1][i].getText()) && checkColumns;
				checkLeftDiag = grid[j][j].getText().equals(grid[j + 1][j + 1].getText()) && checkLeftDiag;
				checkRightDiag = grid[j][grid.length - 1 - j].getText()
						.equals(grid[j + 1][grid.length - 2 - j].getText()) && checkRightDiag;
			}
			
			if (checkRows && !grid[i][0].getText().equals(""))
			{
				finishGame(grid[i][0], grid[i][2]);
				return;
			}
			else if (checkColumns && !grid[0][i].getText().equals(""))
			{
				finishGame(grid[0][i], grid[2][i]);
				return;
			}
			else if (checkLeftDiag && !grid[0][0].getText().equals(""))
			{
				finishGame(grid[0][0], grid[2][2]);
				return;
			}
			else if (checkRightDiag && !grid[0][2].getText().equals(""))
			{
				finishGame(grid[0][2], grid[2][0]);
				return;
			}
		}
	}
	
	private void finishGame(GameGridPanel start, GameGridPanel end)
	{
		// Disable all grid panels
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid.length; j++)
				grid[i][j].setEnabled(false);
		
		winnerStartEndPanels[0] = start;
		winnerStartEndPanels[1] = end;
		notifyObservers(winnerStartEndPanels);
	}
}
