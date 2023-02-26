package task;

public class Main {

	public static void main(String[] args) {
		
		long startTime;
		long endTime;
		long timeElapsed;
							
		int[] array = new int[100000000];
		int numberOfThreads = 4;
		int numberOfElementsInPortion = array.length / numberOfThreads;
		int firstElementIndex = 0;
				
		Thread[] threads = new Thread[numberOfThreads];
				
		WorkWithArray[] worksWithArrays = new WorkWithArray[numberOfThreads];
		int sumOfArrayElements = 0;
				
		for (int i = 0; i < array.length; i++) {
			array[i] = (int)(Math.random() * 10);
		}
					
		// Simple algorithm
		startTime = System.nanoTime();
		
		for (int i = 0; i < array.length; i++) {
			sumOfArrayElements += array[i];
		}
		
		System.out.println("Sum of array elements: " + sumOfArrayElements);
		
		endTime = System.nanoTime();
		timeElapsed = endTime - startTime;
		System.out.println("Execution time of simple algorithm in milliseconds: " + timeElapsed / 1000000);
		System.out.println();
		
		sumOfArrayElements = 0;
				
		// Multi threads algorithm
		startTime = System.nanoTime();
		
		for (int i = 0; i < numberOfThreads; i++) {
			
			WorkWithArray wwa = new WorkWithArray(array, firstElementIndex, firstElementIndex + numberOfElementsInPortion - 1);
			Thread thr = new Thread(wwa);
			thr.start();
			
			worksWithArrays[i] = wwa;
			threads[i] = thr;
			
			firstElementIndex += numberOfElementsInPortion; 
			
		}
		
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for (int i = 0; i < worksWithArrays.length; i++) {
			sumOfArrayElements += worksWithArrays[i].getSumOfPortionElements();
		}
				
		System.out.println();
		System.out.println("Sum of array elements: " + sumOfArrayElements);

		endTime = System.nanoTime();
		timeElapsed = endTime - startTime;
		System.out.println("Execution time of multi threads algorithm in milliseconds: " + timeElapsed / 1000000);
 				
	}

}
