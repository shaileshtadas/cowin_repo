package ExpMain;

import org.testng.TestNG;

import cowin.SlotFinder;

public class AppTest {

	static TestNG testng;

	public static void main(String[] args) {
		testng = new TestNG();
		testng.setTestClasses(new Class[] { SlotFinder.class });
		testng.run();
	}

}
