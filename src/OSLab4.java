import java.util.concurrent.locks.ReentrantLock;

class TT extends Thread {
    private int id;
    private ReentrantLock lck;
  
    public TT(int idInput,ReentrantLock lckInput) {
    	this.id=idInput;
    	this.lck=lckInput;
    }

    @Override
    public void run() {
        for (int i = 0; i <500000; i++) { 
        	lck.lock();
        	OSLab4.rvarible=OSLab4.rvarible+1;
        	lck.unlock();
        	/*try {
				Thread.sleep((long) 0.2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} */      	
        }
    	

    }
}

public class OSLab4 {
	public static boolean visited(int[] arrInput,int input) {
		for (int i = 0; i < arrInput.length; i++) {
			if(input==arrInput[i])
				return true;
		}
		return false;
	}
	public static void countFreq(int arr[]) 
	{ 
		int[] visited=new int[arr.length];
		int vCounter=0;
		int count=0;
		for (int i = 0; i < arr.length; i++) {
			if(!visited(visited,arr[i])) {
				for (int j = 0; j < arr.length; j++) {
					if(arr[i]==arr[j])
						count++;
				}
				visited[vCounter]=arr[i];
				System.out.println(arr[i]+"		"+count);	
				vCounter++;
				count=0;
			}
		}
	} 
	static int rvarible=0;
	static int[]varibles=new int[10];
	static Thread[] threads=new Thread[2];
	static int counter=0;
	static long[] times=new long[10];
	
	public static void main(String[] args) {
		ReentrantLock lock=new ReentrantLock();
		for (int counter = 0; counter < 10; counter++) {
			long startTime = System.nanoTime();
			for (int i = 0; i < threads.length; i++) {
				TT p = new TT(i,lock);
				 p.start();
				threads[i]=p;
			   
			}
			
			for (int i = 0; i < threads.length; i++) {
				try {
			        threads[i].join();
			    } catch (InterruptedException e) {
			        e.printStackTrace();
			    }

			}
			long endTime = System.nanoTime();
	        long totalTime = endTime - startTime;
			varibles[counter]=rvarible;
			times[counter]=totalTime/1000;
			rvarible=0;
		}
		long sumag=0;
		for (int i = 0; i < times.length; i++) {
			sumag+=times[i];
		}
		System.out.println(sumag/10);
		countFreq(varibles);
		
	}
	
	
}

