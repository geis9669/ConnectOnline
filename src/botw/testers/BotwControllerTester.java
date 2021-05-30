package botw.testers;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;
import botw.BotwController;

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

}
