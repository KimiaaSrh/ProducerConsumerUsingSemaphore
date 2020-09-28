import java.util.Stack;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer {
	
	public static void main(String args[])  
    { 
		Stack<Integer> stack = new Stack<Integer>();
		ReentrantLock lock=new ReentrantLock();
        Semaphore semCon = new Semaphore(0); 
        
        Semaphore semProd = new Semaphore(5); 
          
        int numberOfCosumers=5;
        int numberOfProducers=5;
        Thread[] threads=new Thread[numberOfCosumers+numberOfProducers];
        for (int i = 0; i < numberOfCosumers+numberOfProducers; i++) {
        	if(i<numberOfProducers) {
        		Producer p=new Producer(stack,lock,i,semCon,semProd);
        		threads[i]=p;
        	}
        	else {
        		Consumer p=new Consumer(stack,lock,i,semCon,semProd);
        		threads[i]=p;
        	}
        }
        
        for (int j = 0; j < numberOfCosumers+numberOfProducers; j++) {
			threads[j].start();
		}
        
        for (int j = 0; j < numberOfCosumers+numberOfProducers; j++) {
        	try {
				threads[j].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        	
    } 

}
