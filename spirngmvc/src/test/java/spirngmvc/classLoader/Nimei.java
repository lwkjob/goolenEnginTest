package spirngmvc.classLoader;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Nimei {
	
	
	public static void main(String[] args) {
		int[] n={1,2,3};
		int[] m=null;
		m=Arrays.copyOf(n, 3); 
//		System.arraycopy(src, srcPos, dest, destPos, length);
		for (int i = 0; i < m.length; i++) {
			System.out.println(m[i]);
		}
		
		final Queue<String> mm=new ConcurrentLinkedQueue<String>();
		
		
		for (int i = 0; i < 100000; i++) {
			mm.add("nimei"+i);
		}
		final AtomicInteger nn=new AtomicInteger();
		ExecutorService executorService=Executors.newFixedThreadPool(10);
		for (int i = 0; i <10; i++) {
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					while(true){
						String qq=mm.poll();
						if(qq!=null){
							nn.addAndGet(1);
							System.out.println(Thread.currentThread().getName()+" "+qq);
						}else{
							System.out.println(Thread.currentThread().getName()+" д╬спак"+nn.get());
							break;
						}
					}
				}
			});
		}
	}
	
}
