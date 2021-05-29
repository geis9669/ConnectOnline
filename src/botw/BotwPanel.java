package botw;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
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
	private JLabel removeLabel;
	private JScrollPane removeScrollPane;
	private JButton removeButton;
	private JButton addButton;
	public BotwPanel(BotwController app)
	{
		super();
		this.resources = app;
		this.setLayout(null);
		this.setBackground(Color.RED);
		
		JScrollPane scrollViewer = new JScrollPane();
		scrollViewer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollViewer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		JTextArea textArea = new JTextArea();
		scrollViewer.add(textArea);
		this.add(scrollViewer);
		
		JScrollPane htmlview = createJScrollPane();
		JScrollPane rejected = createJScrollPane();
		
		acceptedLabel = new JLabel("markers to keep");
		this.add(acceptedLabel);
		JList<String> acceptedMapMarkers = new JList<>();
		DefaultListModel<String> a_Model = new DefaultListModel<>();
		HashMap<String, String> mapMarkers = new HashMap<>();
		String[] list = resources.gettingMapMarkers();
		for(String marker : list)
		{
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
		
		removeLabel = new JLabel("markers to leave out");
		this.add(removeLabel);
		JList<String> removeMapMarkers = new JList<>();
		DefaultListModel<String> r_Model = new DefaultListModel<>();
		removeMapMarkers.setModel(r_Model);
		removeMapMarkers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		removeMapMarkers.setSelectedIndex(0);
		removeScrollPane = createJScrollPane();
		removeScrollPane.setViewportView(removeMapMarkers);
		this.add(removeScrollPane);
		
		removeButton = new JButton("remove");
		removeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent click)
			{
				int index = acceptedMapMarkers.getSelectedIndex();
				if(index<0) {return;}
				String value = a_Model.remove(index);
				//could make a check for null
				r_Model.addElement(value);
				removeMapMarkers.setSelectedIndex(r_Model.getSize()-1);
			}
		});
		this.add(removeButton);
		
		addButton = new JButton("add");
		addButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent click)
			{
				int index = removeMapMarkers.getSelectedIndex();
				if(index<0) {return;}
				String value = r_Model.remove(index);
				//could make a check for null
				a_Model.addElement(value);
				acceptedMapMarkers.setSelectedIndex(a_Model.getSize()-1);
			}
		});
		this.add(addButton);
	}
	
	private JScrollPane createJScrollPane()
	{
		JScrollPane scrollViewer = new JScrollPane();
		scrollViewer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollViewer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		return scrollViewer;
	}
	
	public void newSize(Dimension newSize){
		int inset = 5;
		int buttonHeight = 25;
		acceptedLabel.setLocation(inset, inset);//x,y
		acceptedLabel.setSize((int) (newSize.getWidth()/2.5),buttonHeight);
		acceptedScrollPane.setLocation(acceptedLabel.getX(), acceptedLabel.getY()+acceptedLabel.getHeight());
		acceptedScrollPane.setSize(acceptedLabel.getWidth() ,(int) (newSize.getHeight()/2));
		
		removeButton.setLocation(acceptedLabel.getX()+acceptedLabel.getWidth()+10 ,acceptedScrollPane.getY());
		removeButton.setSize((removeLabel.getX()-inset) - removeButton.getX(),buttonHeight);
		addButton.setLocation(removeButton.getX(), removeButton.getY()+inset+buttonHeight);
		addButton.setSize(removeButton.getSize());
		
		removeLabel.setSize(acceptedLabel.getSize());
		removeLabel.setLocation((int) (newSize.getWidth()-(removeLabel.getWidth()+inset+17)),acceptedLabel.getY());
		removeScrollPane.setLocation(removeLabel.getX(), removeLabel.getY()+removeLabel.getHeight());
		removeScrollPane.setSize(acceptedScrollPane.getSize());
		
	}
	/*
	 * need to make a way of removing Items from the list
	 * need to make a way to save the modified file.
	 */

}
