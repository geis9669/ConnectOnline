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
	private JScrollPane r_matchScrollPane;
	private JButton removeMatchingButton;
	private JScrollPane a_matchScrollPane;
	private JButton addMatchingButton;
	
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
		addActionMoveItemToOther(removeButton, acceptedMapMarkers, a_Model, removeMapMarkers, r_Model);
		this.add(removeButton);
		
		addButton = new JButton("add");
		addActionMoveItemToOther(addButton, removeMapMarkers, r_Model, acceptedMapMarkers, a_Model);
		this.add(addButton);
		
		JTextArea removeMatching = new JTextArea();
		removeMatching.setEditable(true);
		removeMatching.setEnabled(true);
		r_matchScrollPane = createJScrollPane();
		r_matchScrollPane.setViewportView(removeMatching);
		this.add(r_matchScrollPane);
		
		removeMatchingButton = new JButton("remove if contains");
		addActionRemoveMatching(removeMatchingButton, a_Model, r_Model, removeMatching);
		this.add(removeMatchingButton);
		
		JTextArea addMatching = new JTextArea();
		addMatching.setEditable(true);
		addMatching.setEnabled(true);
		a_matchScrollPane = createJScrollPane();
		a_matchScrollPane.setViewportView(addMatching);
		this.add(a_matchScrollPane);
		
		addMatchingButton = new JButton("add if contains");
		addActionRemoveMatching(addMatchingButton, r_Model, a_Model, addMatching);
		this.add(addMatchingButton);
	}

	/**
	 * This will add the behavior of removing strings from one listModel to another
	 * based off the strings from the JTextArea separated by new lines.
	 * 
	 * get the strings in the text area, then separate it into a list, then pass
	 * that into moveifContains method.
	 * 
	 * @param from
	 * @param to
	 * @param text
	 */
	private void addActionRemoveMatching(JButton button, 
			DefaultListModel<String> from, 
			DefaultListModel<String> to, 
			JTextArea text ) 
	{
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				/*
				 * get string
				 * make list from string
				 * make condition for moveifcontains
				 * pass the two lists and the condition to moveifcontains
				 */
				String newLine = "\n";
				if(text.getText().length() <= 0) return;
				String[] list = text.getText().split(newLine);
				MatchCondition<String> condition = (a) -> {
					boolean result = false;
					int index = 0;
					while(!result && index < list.length)
					{
						result = a.contains(list[index]);
						index++;
					}
					return result;
				};
				moveIfContains(from, to, condition);
			}
		});
	}

	/**
	 * Adds the action of removing the selected item from one JList and its model to
	 * a different JList and its model when the button is pressed. It adds the item
	 * to the end of the list.
	 * 
	 * @param button The button to add this action to.
	 * @param fromList  
	 * @param fromModel
	 * @param toList
	 * @param toModel
	 */
	private void addActionMoveItemToOther(JButton button, 
			JList<String> fromList, DefaultListModel<String> fromModel, 
			JList<String> toList, DefaultListModel<String> toModel) 
	{
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent click)
			{
				int index = fromList.getSelectedIndex();
				if(index<0) {return;}
				String value = fromModel.remove(index);
				//could make a check for null
				toModel.addElement(value);
				toList.setSelectedIndex(toModel.getSize()-1);
			}
		});
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
		acceptedLabel.setLocation(inset, 0);//x,y
		acceptedLabel.setSize((int) (newSize.getWidth()/2.5),buttonHeight);
		acceptedScrollPane.setLocation(acceptedLabel.getX(), acceptedLabel.getY()+acceptedLabel.getHeight());
		acceptedScrollPane.setSize(acceptedLabel.getWidth() ,(int) (newSize.getHeight()/2));
		
		removeLabel.setSize(acceptedLabel.getSize());
		removeLabel.setLocation((int) (newSize.getWidth()-(removeLabel.getWidth()+inset)),acceptedLabel.getY());
		removeScrollPane.setLocation(removeLabel.getX(), removeLabel.getY()+removeLabel.getHeight());
		removeScrollPane.setSize(acceptedScrollPane.getSize());
		
		removeButton.setLocation(acceptedLabel.getX()+acceptedLabel.getWidth()+inset ,acceptedScrollPane.getY());
		removeButton.setSize(removeLabel.getX() - (removeButton.getX()+inset) ,buttonHeight);
		addButton.setLocation(removeButton.getX(), removeButton.getY()+inset+buttonHeight);
		addButton.setSize(removeButton.getSize());
		
		removeMatchingButton.setLocation(acceptedLabel.getX(), acceptedScrollPane.getY()+acceptedScrollPane.getHeight()+inset);
		removeMatchingButton.setSize(acceptedScrollPane.getWidth(), buttonHeight);
		r_matchScrollPane.setLocation(acceptedLabel.getX(), removeMatchingButton.getY()+removeMatchingButton.getHeight()+inset);
		r_matchScrollPane.setSize(acceptedScrollPane.getWidth(), (newSize.height - (r_matchScrollPane.getY()+inset)));
		
		addMatchingButton.setLocation(removeLabel.getX(), removeMatchingButton.getY());
		addMatchingButton.setSize(removeScrollPane.getWidth(), buttonHeight);
		a_matchScrollPane.setLocation(addMatchingButton.getX(), addMatchingButton.getY()+addMatchingButton.getHeight()+inset);
		a_matchScrollPane.setSize(addMatchingButton.getWidth(), (newSize.height - (a_matchScrollPane.getY()+inset)));
	}
	
	/**
	 * Move items from one list to the other list if the condition returns true.
	 * @param <T>
	 * @param from the list to remove items from
	 * @param to the list to add the items to
	 * @param condition used to determine which items to move, if true it move it to the other list,
	 * false leave it alone.
	 */
	public static <T> void moveIfContains(DefaultListModel<T> from, DefaultListModel<T> to, MatchCondition<T> condition)
	{
		for(int i = from.size()-1; i>-1; i--)
		{
			if(from.get(i) != null && condition.matchCondition(from.get(i)))
			{
				T move = from.remove(i);
				to.add(to.getSize(), move);
			}
		}
	}
	/*
	 * need to make a way of removing Items from the list
	 * need to make a way to save the modified file.
	 * 
	 * add a box to be able to add qualifiers like the string has to contain "korok" to stay, 
	 * and for it to remove the item if it has the string init. 
	 */

}
