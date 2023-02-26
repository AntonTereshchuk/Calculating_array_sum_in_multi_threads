package task;

public class WorkWithArray implements Runnable {
	
	private int[] arr;
	private int sumOfPortionElements;
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
	
	@Override
	public void run() {
					
		for (int i = firstElementIndex; i <= lastElementIndex; i++) {
			sumOfPortionElements += arr[i];
		}
		
		Thread thread = Thread.currentThread();
		System.out.println(thread.getName() + " " + sumOfPortionElements);
				
	}
	
}
