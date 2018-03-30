package org.afc.challenge.sort;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FubarSortTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRandom() {
		for (int k = 0; k < 100; k++) {
			int[] array = new int[100];
			for(int i = 0; i < array.length; i++) {
				array[i] = (int)(Math.random() * 100);
			}
			
			FubarSort sort = new FubarSort(array);
			sort.sort();
			
			assertInOrder(array);
		}
	}

	@Test
	public void testPerf() {
		long t0 = 0;
		long t1 = 0;
		for (int k = 0; k < 10; k++) {
			int[] array = new int[20000];
			for(int i = 0; i < array.length; i++) {
				array[i] = (int)(Math.random() * 10000);
			}
			
			long s0 = System.nanoTime();
			FubarSort sort = new FubarSort(array);
			sort.sort();
			assertInOrder(array);
			long s1 = System.nanoTime();
			t0+=(s1-s0);
					
			long s2 = System.nanoTime();
			Arrays.sort(array);
			assertInOrder(array);
			long s3 = System.nanoTime();
			t1+=(s3-s2);
		}
		System.out.println(t0);
		System.out.println(t1);
		
	}
	
	@Test
	public void testSmallestLast() {
		
		int[] array = new int[] { 2, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 20, 1 };
		
		FubarSort sort = new FubarSort(array);
		sort.sort();
		
		assertInOrder(array);
	}

	@Test
	public void testBiggerFirst() {
		int[] array = new int[] { 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 22, 1 };
		
		FubarSort sort = new FubarSort(array);
		sort.sort();
		
		assertInOrder(array);
	}

	@Test
	public void testRepeatValue() {
		int[] array = new int[] { 20, 19, 8, 17, 17, 17, 17, 13, 12, 11, 17, 8, 8, 7, 17, 20, 4, 1, 22, 1 };
		
		FubarSort sort = new FubarSort(array);
		sort.sort();
		
		assertInOrder(array);
	}

	private void assertInOrder(int[] array) {
		for(int i = 0; i < array.length; i++) {
			if (i + 1 < array.length && array[i] > array[i+1]) {
				System.err.println("array[i] > array[i+1] " + array[i] + " " + array[i+1]);
				throw new RuntimeException("array[i] > array[i+1] " + array[i] + " " + array[i+1]);
			}
		}
	}
}
