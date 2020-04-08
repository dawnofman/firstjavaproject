
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MainTest extends Model {

//Editor usability
	@Test
	void test1() {
		fail("The editor is not opening");
	}
//Draw shapes
	void test2() {
		Model m1 = new Model();
		Controller c1 = new Controller(m1);
		fail("draw is working");
	}
//Link shapes and comment check
	void test3() {
		Model m1 = new Model();
		Controller c1 = new Controller(m1);
		View v1 = new View(c1);
		m1.addObserver(v1);
	}
	
//Performance check
	void test4() {
		for (int i=0;i<=8;i++) {
			loadData();
		}
	}
}
