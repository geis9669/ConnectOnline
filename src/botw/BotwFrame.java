package botw;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class BotwFrame extends JFrame{
	private Dimension currentSize;
	private BotwPanel panel;
	public BotwFrame() {
		
		try {
			BotwController app = new BotwController();
			panel = new BotwPanel(app);
			this.add(panel);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(ERROR);
		}
		
		this.getContentPane().addComponentListener(new ResizeListener());
		this.setSize(600,400);
		this.setResizable(true);
		this.setTitle("BreathOfTheWildHyruleMapMarkers");
		this.addWindowListener(new WindowAdapter() {
        	public void windowClosing(WindowEvent event)
        	{
        		System.exit(0);
        	}
        });
		
		
		JMenuBar menuBar = new JMenuBar();
		JMenuItem saveMenu = new JMenuItem("Save as");
		saveMenu.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		saveMenu.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				panel.saveHtml();
			}
		});
		saveMenu.setEnabled(true);
		menuBar.add(saveMenu);
		this.setJMenuBar(menuBar);
		
		currentSize = this.getContentPane().getSize();
		panel.newSize(currentSize);
		this.setVisible(true);
	}
	
	class ResizeListener implements ComponentListener {
		public void componentMoved(ComponentEvent e) {}
		public void componentShown(ComponentEvent e) {}
		public void componentHidden(ComponentEvent e) {}
		
		public void componentResized(ComponentEvent e) {
			Dimension newSize = e.getComponent().getSize();
			//check if the new size is actually a new size
			panel.newSize(newSize);
		}
	}
	
}
