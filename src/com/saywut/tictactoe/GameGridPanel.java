package com.saywut.tictactoe;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class GameGridPanel extends JButton
{
	private static final long serialVersionUID = 1L;

	private Color background = Color.WHITE;

	public GameGridPanel()
	{
		setBorder(new LineBorder(Color.BLACK, 2));
		setBackground(null);
		setFocusPainted(false);
		setBackground(background);
		setForeground(Color.orange);
		setContentAreaFilled(false);
		setOpaque(true);

		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent e)
			{
				if (isEnabled())
					setBackground(Color.decode("#E2E2E2"));
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				if (isEnabled())
					setBackground(background);
			}
		});
	}
}
