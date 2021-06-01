package botw.testers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import org.junit.Test;

import botw.BotwPanel;
import botw.MatchCondition;

public class BotwPanelTester {

	@Test
	public void testMoveIfContains() 
	{
		DefaultListModel<String> a = new DefaultListModel<>();
		a.add(a.getSize(),"food");
		a.add(a.getSize(),"fooed");
		a.add(a.getSize(),"tsea");
		a.add(a.getSize(),"asdfoodadfslj");
		DefaultListModel<String> b = new DefaultListModel<>();
		MatchCondition<String> condition = (list) -> list.contains("food");
		BotwPanel.moveIfContains(a,b,condition);
		String[] a_right = {"fooed","tsea"};
		String[] b_right = {"asdfoodadfslj","food"};
		assertArrayEquals("a works",a_right,a.toArray());
		assertArrayEquals("b works",b_right, b.toArray());
	}

}
