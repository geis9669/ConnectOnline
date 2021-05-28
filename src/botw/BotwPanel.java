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
	
	private JLabel acceptedLabel;
	private JScrollPane acceptedScrollPane;
	public BotwPanel(BotwController app)
	{
		super();
		this.resources = app;
		this.setLayout(null);
		this.setBackground(Color.RED);
		
		acceptedLabel = new JLabel("markers to keep");
		this.add(acceptedLabel);
		JList<String> acceptedMapMarkers = new JList<>();
		DefaultListModel<String> a_Model = new DefaultListModel<>();
		HashMap<String, String> mapMarkers = new HashMap<>();
		String[] list = resources.gettingMapMarkers();
		for(String marker : list){
			String d = "alt=\"";//divider
			int start = marker.indexOf(d)+d.length();
			mapMarkers.put(marker.substring(start, marker.indexOf("\"",start+1 )), marker);
		}
		a_Model.addAll(mapMarkers.keySet());
		acceptedMapMarkers.setModel(a_Model);
		acceptedMapMarkers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		acceptedMapMarkers.setSelectedIndex(0);
		acceptedScrollPane = createJScrollPane();
		acceptedScrollPane.setViewportView(acceptedMapMarkers);
		this.add(acceptedScrollPane);
	}
	
	private JScrollPane createJScrollPane()
	{
		JScrollPane scrollViewer = new JScrollPane();
		scrollViewer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollViewer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		return scrollViewer;
	}
	
	public void newSize(Dimension newSize){
		int inset = 10;
		acceptedLabel.setLocation(inset, inset);//x,y
		acceptedLabel.setSize((int) (newSize.getWidth()/2.5),25);
		acceptedScrollPane.setLocation(acceptedLabel.getX(), acceptedLabel.getY()+acceptedLabel.getHeight());
		acceptedScrollPane.setSize(acceptedLabel.getWidth() ,(int) (newSize.getHeight()/2));
	}

}
