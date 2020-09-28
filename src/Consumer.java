import java.util.Stack;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

public class Consumer extends Thread {
	
	Stack<Integer> stack1; 
	ReentrantLock lck;
	Semaphore semCon;
	Semaphore semProd;
	private int id;
	Consumer(Stack inputstack,ReentrantLock lckInput,int idInput,Semaphore semConInput,Semaphore semProdInput) { 
        this.stack1 = inputstack;
        this.lck=lckInput;
        this.id=idInput;
        this.semCon=semConInput;
        this.semProd=semProdInput;
    }  
    
    @Override
    public void run() 
    { 
    	for (int i = 0; i < 10; i++) {
//    		System.out.println("hello from consumer "+this.id );
//    		if(stack1.size()>0) {
	    		 try {
					semCon.acquire();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	           	 lck.lock();
	           	 int con=stack1.pop();
	           	 System.out.println(this.id +" consumed "+con);
	           	 lck.unlock();
		         semProd.release();
		         try {
						this.sleep((long) 200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

//            }
        
    } 

}
}
