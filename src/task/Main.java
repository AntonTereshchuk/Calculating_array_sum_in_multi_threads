package task;

public class Main {

	public static void main(String[] args) {
		
		long startTime;
		long endTime;
		long timeElapsed;
							
		int[] array = new int[10000000];
		int numberOfElementsInPortion = array.length / 4;
		int elementsInPortionCounter = 0;
		
		Thread[] threads = new Thread[4];
		int threadCounter = 0;
		
		WorkWithArray[] worksWithArrays = new WorkWithArray[4];
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
		
		for (int i = 0; i < array.length; i++) {
									
			elementsInPortionCounter += 1;
			
			if (elementsInPortionCounter == numberOfElementsInPortion) {
				worksWithArrays[threadCounter] = new WorkWithArray(array, i - numberOfElementsInPortion + 1, i);
				threads[threadCounter] = new Thread(worksWithArrays[threadCounter]);
				threads[threadCounter].start();
				threadCounter += 1;
				elementsInPortionCounter = 0;
			}
			
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
