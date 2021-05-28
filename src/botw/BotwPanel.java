package botw;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

public class BotwPanel extends JPanel{
	private BotwController resources;
	
	public BotwPanel(BotwController app)
	{
		super();
		this.resources = app;
		this.setLayout(null);
		this.setBackground(Color.RED);
		
}
