package assign9;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

/**
 * Performs a timing analysis on various methods in the BinarySearchTree class
 * and compares these results to Java's TreeSet class.
 * 
 * This class uses formulas to compare and display the empirically observed
 * running time of a method/algorithm to the expected Big-O behavior.
 * 
 * @author Tim Ellenberger, ellenber
 * @author John Strasser, strasser
 * @author Erin Parker
 * @version 11/05/2015
 */
public class TimingAnalysis {

	public static void main(String[] args) {
		int timesToLoop = 20000;
		boolean rand = false;
		
		//Time a sorted BST for finding all elements
		timeBSTContains(timesToLoop, 2000, 22000, 2000, rand, "Contains", "Worst Case -- Should be O(N)");
		
		//Time a random BST for finding all elements
		rand = true;
		
		timeBSTContains(timesToLoop, 2000, 22000, 2000, rand, "Contains", "Average Case -- Should be O(logN)");
		
		/////////// Average Case: BinarySearchTree vs Java's TreeSet /////////////
		rand = true;
		
		timeBSTvsTreeSet(timesToLoop, 2000, 22000, 2000, rand, "BST add", "Average Case -- Should be O(logN)");
		timeBSTvsTreeSet(timesToLoop, 2000, 22000, 2000, rand, "BST contains", "Average Case -- Should be O(logN)");
		
		timeBSTvsTreeSet(timesToLoop, 2000, 22000, 2000, rand, "TreeSet add", "Average Case -- Should be O(logN)");
		timeBSTvsTreeSet(timesToLoop, 2000, 22000, 2000, rand, "TreeSet contains", "Average Case -- Should be O(logN)");
		
		/////////// Worst Case: BinarySearchTree vs Java's TreeSet /////////////
		rand = false;
		
		timeBSTvsTreeSet(timesToLoop, 2000, 22000, 2000, rand, "BST add", "Worst Case -- Should be O(N)");
		timeBSTvsTreeSet(timesToLoop, 2000, 22000, 2000, rand, "BST contains", "Worst Case -- Should be O(N)");
		
		timeBSTvsTreeSet(timesToLoop, 2000, 22000, 2000, rand, "TreeSet add", "Worst Case -- Should be O(logN)");
		timeBSTvsTreeSet(timesToLoop, 2000, 22000, 2000, rand, "TreeSet contains", "Worst Case -- Should be O(logN)");
	}
	
	/**
	 * Initiates a timing analysis for problem 4 in the analysis document.
	 * 
	 * @param timesToLoop The number of times to average the experiment.
	 * @param startSize	The starting problem size
	 * @param stopSize The ending problem size
	 * @param stepSize The amount to increase the problem size on each iteration
	 * @param random True will instantiate a random order tree, False will instantiate a sorted order tree
	 * @param timingMethod The method being timed.
	 * @param caseType The type of experiment (worst case or average case)
	 */
	private static void timeBSTContains(int timesToLoop, int startSize, int stopSize, int stepSize, boolean random, String timingMethod, String caseType) {
		long startTime, midptTime, stopTime;
		
		// try computing T(N)/F(N), see if it converges
		DecimalFormat formatter = new DecimalFormat("0000E0");

		System.out
				.println("------------------- GraphUtil Timing Analysis: " + timingMethod + "(" + caseType + ")" + " ----------------------");
		System.out.println("\t\t\t    timesToLoop: " + timesToLoop + " | " + (random ? " random order" : " in-order"));
		System.out.println("\nN\tT(N)\t\t|\tT(N)/logN\tT(N)/NlogN\tT(N)/N\t\tT(N)/N^2\tT(N)/N^3");
		System.out.println(
				"---------------------------------------------------------------------------------------------------------");

		for (int n = startSize; n <= stopSize; n += stepSize) {

			// Create a data set to work with
			List<Integer> setData = new LinkedList<>();
			for (int i = 0; i <n; i++) {
				setData.add(i);
			}
			// random will randomly permute the elements
			if(random) {
				Collections.shuffle(setData);
			}
			
			BinarySearchTree<Integer> myBST = new BinarySearchTree<>();
			myBST.addAll(setData);
			
			System.out.print(n + "\t");

			// Let things stabilize
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000)
				;

			// Time the routine
			Random rnd = new Random();
			startTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++) {
				myBST.contains(rnd.nextInt(n));
			}

			midptTime = System.nanoTime();
			// Time the empty loops
			for (int i = 0; i < timesToLoop; i++) {
				rnd.nextInt(n);
			}

			stopTime = System.nanoTime();

			// Compute the average time
			double avgTime = ((midptTime - startTime) - (stopTime - midptTime)) / timesToLoop;

			System.out.println(formatter.format(avgTime) + "\t\t|\t"
					+ formatter.format(avgTime / (Math.log10(n) / Math.log10(2))) + "\t\t"
					+ formatter.format(avgTime / (n * (Math.log10(n) / Math.log10(2)))) + "\t\t"
					+ formatter.format(avgTime / n) + "\t\t"
					+ formatter.format(avgTime / (n * n)) + "\t\t"
					+ formatter.format(avgTime / (n * n * n)));
		}
		System.out.println(
				"---------------------------------------------------------------------------------------------------------");	
	}
	
	/**
	 * Initiates a timing analysis for problem 5 in the analysis document.
	 * 
	 * @param timesToLoop The number of times to average the experiment.
	 * @param startSize	The starting problem size
	 * @param stopSize The ending problem size
	 * @param stepSize The amount to increase the problem size on each iteration
	 * @param random True will instantiate a random order tree, False will instantiate a sorted order tree
	 * @param timingMethod The method being timed.
	 * @param caseType The type of experiment (worst case or average case)
	 */
	private static void timeBSTvsTreeSet(int timesToLoop, int startSize, int stopSize, int stepSize, boolean random, String timingMethod, String caseType) {
		long startTime, midptTime, stopTime;
		
		// try computing T(N)/F(N), see if it converges
		DecimalFormat formatter = new DecimalFormat("0000E0");

		System.out
				.println("------------------- GraphUtil Timing Analysis: " + timingMethod + "(" + caseType + ")" + " ----------------------");
		System.out.println("\t\t\t    timesToLoop: " + timesToLoop + " | " + (random ? " random order" : " in-order"));
		System.out.println("\nN\tT(N)\t\t|\tT(N)/logN\tT(N)/NlogN\tT(N)/N\t\tT(N)/N^2\tT(N)/N^3");
		System.out.println(
				"---------------------------------------------------------------------------------------------------------");

		
		for (int n = startSize; n <= stopSize; n += stepSize) {			
			// Create a data set to work with
			List<Integer> setData = new LinkedList<>();
			for (int i = 0; i <n; i++) {
				setData.add(i);
			}
			
			// If random is set to true, randomly permute the elements
			if(random) {
				Collections.shuffle(setData);
			}
			
			BinarySearchTree<Integer> myBST = new BinarySearchTree<>();
			TreeSet<Integer> myTreeSet = new TreeSet<>();
			
			ArrayList<BinarySearchTree<Integer>> BSTlist = new ArrayList<>();
			ArrayList<TreeSet<Integer>> TreeSetlist = new ArrayList<>();
			
			// Assign the setData to the correct tree type
			if(timingMethod.equals("BST contains") || timingMethod.equals("BST add")) {
				myBST.addAll(setData);
			} else if(timingMethod.equals("TreeSet contains") || timingMethod.equals("TreeSet add")) {
				myTreeSet.addAll(setData);
			}
			
			// If testing the add method, create a new data set for each timesToLoop for accurate results
			if(timingMethod.equals("BST add")) {
				BSTlist = new ArrayList<>();
				
				for(int i = 0; i< timesToLoop; i++) {
					BSTlist.add(i, myBST);
				}
			} else if (timingMethod.equals("TreeSet add")) {
				TreeSetlist = new ArrayList<>();
				
				for(int i = 0; i< timesToLoop; i++) {
					TreeSetlist.add(i, myTreeSet);
				}
			}
			
			System.out.print(n + "\t");

			// Let things stabilize
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000)
				;

			// Time the routine
			Random rnd = new Random();
			startTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++) {
				if(timingMethod.equals("BST add")) {
					BSTlist.get(i).add(rnd.nextInt(n));
				}
				if(timingMethod.equals("BST contains")) {
					myBST.contains(rnd.nextInt(n));
				}
				if(timingMethod.equals("TreeSet add")) {
					TreeSetlist.get(i).add(rnd.nextInt(n));
				}
				if(timingMethod.equals("TreeSet contains")) {
					myTreeSet.contains(rnd.nextInt(n));
				}
					
			}

			midptTime = System.nanoTime();
			
			// Time the empty loops
			for (int i = 0; i < timesToLoop; i++) {
				if(timingMethod.equals("BST add")) {
					BSTlist.get(i);
					rnd.nextInt(n);
				}
				if(timingMethod.equals("BST contains")) {
					rnd.nextInt(n);
				}
				if(timingMethod.equals("TreeSet add")) {
					TreeSetlist.get(i);
					rnd.nextInt(n);
				}
				if(timingMethod.equals("TreeSet contains")) {
					rnd.nextInt(n);
				}
			}

			stopTime = System.nanoTime();

			// Compute the average time
			double avgTime = ((midptTime - startTime) - (stopTime - midptTime)) / timesToLoop;

			System.out.println(formatter.format(avgTime) + "\t\t|\t"
					+ formatter.format(avgTime / (Math.log10(n) / Math.log10(2))) + "\t\t"
					+ formatter.format(avgTime / (n * (Math.log10(n) / Math.log10(2)))) + "\t\t"
					+ formatter.format(avgTime / n) + "\t\t"
					+ formatter.format(avgTime / (n * n)) + "\t\t"
					+ formatter.format(avgTime / (n * n * n)));
		}
		System.out.println(
				"---------------------------------------------------------------------------------------------------------");	
	}
}