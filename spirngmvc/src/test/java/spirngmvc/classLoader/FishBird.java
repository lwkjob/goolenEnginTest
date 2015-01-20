package spirngmvc.classLoader;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class FishBird {  
  
    long time ;  
    long birdNum ;  
    long fishNum ;  
    Object lock = new Object() ;  
    CyclicBarrier barrier  ;  
      
    public FishBird(long birdNum , long fishNum){  
        this.birdNum = birdNum ;  
        this.fishNum = fishNum ;  
    }  
  
    public static void main(String[] args) {  
  
        FishBird bf = new FishBird(5 , 20) ;  
        bf.start();   
  
    }  
  
    public void start(){  
  
        FishThread ft = new FishThread() ;  
        BirdThread bt = new BirdThread() ;  
        TimeLine tl = new TimeLine() ;  
  
        //��ʼ���������ϣ���barrier�����await��������������֮�󣬽���ִ��tl�߳�  
        barrier = new CyclicBarrier(2, tl) ;  
  
        ft.start();  
        bt.start();  
  
    }  
  
    public void printInfo(){  
        System.out.printf("time[%d]:birdNum[%d] ,fishNum[%d]\n" ,time , birdNum , fishNum);  
    }  
  
    private class TimeLine implements Runnable {  
        @Override  
        public void run() { //���������񶼵�����await�����󣬽���ִ�и÷����� Ȼ���������̼߳���ִ��  
            if(fishNum <= 0){  
                System.exit(-1);     
            }  
            time += 10 ;  
        }  
    }  
  
    private class FishThread extends Thread {  
        @Override  
        public void run() {  
            while(true){  
                try {  
                    barrier.await() ;   //����˯�ߣ� �ȴ����������񶼽���˯��  Ȼ���ټ���  
                } catch (InterruptedException | BrokenBarrierException e) {  
                    e.printStackTrace();  
                }  
                synchronized (lock) {  
                    if(time % 30 == 0){  
                        fishNum += fishNum * 2;  
                        printInfo();  
                    }  
                }  
            }  
        }  
    }  
  
    private class BirdThread extends Thread{  
  
        @Override  
        public void run() {  
  
            while(true){  
                try {  
                    barrier.await() ;  //����˯�ߣ� �ȴ����������񶼽���˯��  Ȼ���ټ���  
                } catch (InterruptedException | BrokenBarrierException e) {  
                    e.printStackTrace();  
                }    
                synchronized (lock) {  
                    if(time % 10 == 0){  
                        fishNum = fishNum >= birdNum ? fishNum - birdNum : 0 ;    
                        if(time % 60 == 0){  
                            birdNum += birdNum ;  
                        }  
                        printInfo();  
                    }  
                }  
  
            }  
  
        }  
  
    }  
  
} 