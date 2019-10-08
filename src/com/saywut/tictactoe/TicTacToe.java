package com.saywut.tictactoe;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TicTacToe
{
	public TicTacToe()
	{
		JFrame frame = new JFrame("Tic Tac Toe");
		JPanel panel = new JPanel(new GridLayout(3, 3));
		GameGrid grid = new GameGrid(panel);
		GlassPane glassPane = new GlassPane();

		grid.addObserver(glassPane);
		panel.setPreferredSize(new Dimension(500, 500));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(panel);
		frame.setGlassPane(glassPane);
		glassPane.setVisible(true);

		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
	}

	public static void main(String[] args) throws IOException
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(() -> new TicTacToe());
	}

}
