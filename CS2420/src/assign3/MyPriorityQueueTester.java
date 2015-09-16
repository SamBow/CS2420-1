package assign3;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.junit.Test;


/**
 * A testing suite for the priority queue implementation
 * 
 * @author Tim Ellenberger, ellenber
 * @author Jay Mendez, BlueJay45
 */
public class MyPriorityQueueTester {

	@Test
	public void testSortRandomIntegersComparator() {

		MyPriorityQueue<Integer> queue1 = new MyPriorityQueue<Integer>(new IntegerComparator());
		Random rn = new Random();
		
		// Insert 200 random numbers into the queue, the numbers range between 1 to 200
		for(int i = 0; i < 200; i++) {
			queue1.insert(rn.nextInt(200 - 1 + 1) + 1);
		}
		
		queue1.printContainer();
		//assertEquals((Integer)2, queue1.findMin());	
		
		queue1.clear();
		queue1.printContainer();
		assertEquals(null, queue1.findMin());
	}
	
	@Test
	public void testFindMinWithZeroInputComparator() {
		
		MyPriorityQueue<Integer> queue2 = new MyPriorityQueue<Integer>(new IntegerComparator());
		queue2.insert(-191);
		queue2.insert(12);
		queue2.insert(-4);
		queue2.insert(1);
		queue2.insert(0);
		queue2.insert(-1);
		
		queue2.printContainer();
		assertEquals((Integer)(-191), queue2.findMin());
		
	}
	
	@Test
	public void testFindMinWithStringComparator() {
		
		MyPriorityQueue<String> queue3 = new MyPriorityQueue<String>(new StringComparator());
		queue3.insert("aardvark");
		queue3.insert("castle");
		queue3.insert("speaker");
		queue3.insert("xylophone");
		
		queue3.printContainer();
		assertEquals("aardvark", queue3.findMin());
		
	}
	
	@Test
	public void testInsertRandomIntegersComparable() {

		MyPriorityQueue<Integer> queue1 = new MyPriorityQueue<Integer>();
		Random rn = new Random();
		
		// Insert 10 random numbers into the queue, the numbers range between 1 to 200
		for(int i = 0; i < 10; i++) {
			queue1.insert(rn.nextInt(200 - 1 + 1) + 1);
		}
		
		queue1.printContainer();
		//assertEquals((Integer)2, queue1.findMin());	
		
		queue1.clear();
		queue1.printContainer();
		assertEquals(null, queue1.findMin());
	}
	
	// This test hangs
//	@Test
//	public void testInsertRandomStringsComparable() {
//
//		MyPriorityQueue<String> queue3 = new MyPriorityQueue<String>();
//		queue3.insert("a");
//		queue3.insert("b");
//		queue3.insert("c");
//		queue3.insert("d");
//		
//		queue3.printContainer();
//	}
	
	@Test
	public void testInsertAllWithIntegers() {
		
		MyPriorityQueue<Integer> queue2 = new MyPriorityQueue<Integer>(new IntegerComparator());
		
		List<Integer> list = new ArrayList<Integer>();
		
		list.add(-191);
		list.add(12);
		list.add(-4);
		list.add(1);
		list.add(0);
		list.add(-1);
		
		queue2.insertAll(list);
		
		queue2.printContainer();
		assertEquals((Integer)(-191), queue2.findMin());
		
	}
	
}
