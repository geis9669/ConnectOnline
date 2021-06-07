package jframeSizeTester;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SizePanel extends JPanel{
	
	public SizePanel(int width, int height)
	{
		this.setLayout(null);
		this.setSize(width, height);
		
//		JScrollPane scrollPane = new JScrollPane();
		JTextArea scrollPane = new JTextArea();
		scrollPane.setLocation(0,0);
		scrollPane.setSize(100,400);
		scrollPane.setBackground(Color.BLUE);
		this.add(scrollPane);
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("SizeTester");
		int width = 600;
		int height = 400;
		frame.setSize(width, height+39);
		frame.setResizable(true);
		SizePanel panel = new SizePanel(width, height);
		frame.add(panel);
//		frame.addComponentListener( panel.getResizeListener());
		frame.getContentPane().addComponentListener(panel.getResizeListener());
		
//		frame.pack();
		frame.setVisible(true);
	}
	
	public ResizeListener getResizeListener()
	{
		return new ResizeListener();
	}
	
	class ResizeListener implements ComponentListener {
		public void componentMoved(ComponentEvent e) {}
		public void componentShown(ComponentEvent e) {}
		public void componentHidden(ComponentEvent e) {}
		
		public void componentResized(ComponentEvent e) {
			Dimension newSize = e.getComponent().getSize();
			System.out.println("width: " + newSize.width +" height: "+newSize.height);
			System.out.println("width: " + newSize.getWidth() +" height: "+newSize.getHeight());
			System.out.println("");
			//check if the new size is actually a new size
//			panel.newSize(newSize);
		}
	}

}
