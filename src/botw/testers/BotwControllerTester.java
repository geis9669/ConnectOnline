package botw.testers;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;
import botw.BotwController;
import botw.MatchCondition;

public class BotwControllerTester {

//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}
	
	@Test
	public void moveIfContainsTest()
	{
		List<String> a = new ArrayList<>();
		a.add("food");
		a.add("fooed");
		a.add("tsea");
		a.add("asdfoodadfslj");
		List<String> b = new ArrayList<>();
		BotwController.moveIfContains(a,b,"food");
		String[] a_right = {"fooed","tsea"};
		String[] b_right = {"asdfoodadfslj","food"};
		assertArrayEquals("a works",a_right,a.toArray());
		assertArrayEquals("b works",b_right, b.toArray());
	}

	@Test
	public void moveIfContainsGenericTest()
	{
		List<String> a = new ArrayList<>();
		a.add("food");
		a.add("fooed");
		a.add("tsea");
		a.add("asdfoodadfslj");
		List<String> b = new ArrayList<>();
		MatchCondition<String> condition = (list) -> list.contains("food");
		BotwController.moveIfContains(a,b,condition);
		String[] a_right = {"fooed","tsea"};
		String[] b_right = {"asdfoodadfslj","food"};
		assertArrayEquals("a works",a_right,a.toArray());
		assertArrayEquals("b works",b_right, b.toArray());
	}
}
