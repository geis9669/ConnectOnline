package botw;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;

import javax.swing.JFrame;

public class BotwFrame extends JFrame{
	private Dimension currentSize;
	public BotwFrame() {
		
		try {
			BotwController app = new BotwController();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(ERROR);
		}
		
		this.addComponentListener(new ResizeListener());
		this.setSize(400,400);
		this.setResizable(true);
		this.setTitle("BreathOfTheWildHyruleMapMarkers");
		
		currentSize = this.getSize();
		this.setVisible(true);
	}
	
	class ResizeListener implements ComponentListener {
		public void componentMoved(ComponentEvent e) {}
		public void componentShown(ComponentEvent e) {}
		public void componentHidden(ComponentEvent e) {}
		
		public void componentResized(ComponentEvent e) {
			Dimension newSize = e.getComponent().getSize();
			//check if the new size is actually a new size
		}
	}

}