package task;

public class WorkWithArray implements Runnable {
	
	private int[] arr;
	int sumOfPortionElements;
	private int firstElementIndex;
	private int lastElementIndex;
			
	public WorkWithArray(int[] arr, int firstElementIndex, int lastElementIndex) {
		super();
		this.arr = arr;
		this.firstElementIndex = firstElementIndex;
		this.lastElementIndex = lastElementIndex;
	}
	
	public int getSumOfPortionElements() {
		return sumOfPortionElements;
	}

	public int CalculateSumOfElements(int[] arr, int firstElementIndex, int lastElementIndex) {
		
		int sumOfElements = 0;
		
		for (int i = firstElementIndex; i <= lastElementIndex; i++) {
			sumOfElements += arr[i];
		}
				
		return sumOfElements;
				
	}

	@Override
	public void run() {
	
		Thread thread = Thread.currentThread();
		sumOfPortionElements = CalculateSumOfElements(arr, firstElementIndex, lastElementIndex);
		System.out.println(thread.getName() + " " + sumOfPortionElements);
				
	}
	
}
