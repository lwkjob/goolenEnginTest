package com.lwk.nio.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * ֻ�����������ӻ�������������������������
 * 
 * @author lwkjob
 *
 */
public class TestNio {
	public static void main(String[] args) throws Exception {
		ByteBuffer buffer=ByteBuffer.allocate(10);
		for(int i=0;i<buffer.capacity();i++){
			buffer.put((byte)i);
		}
		
		 // ����ֻ��������  
		ByteBuffer readOnlyBufer=buffer.asReadOnlyBuffer();
		
		
		// �����ӻ�����  
		buffer.position(3);//λ��
		buffer.limit(7);//����
		ByteBuffer slice= buffer.slice();
		
		 // �ı��ӻ����������� 
		for (int i = 0; i < slice.capacity(); i++) {
			byte b=slice.get(i);
			b*=10;
			slice.put(i,b);
		}
		
		buffer.position(0);
		buffer.limit(buffer.capacity());
		
		//remaining ʣ���
		while(buffer.remaining()>0){
			System.out.print(buffer.get()+" ");
		}
		
		System.out.println("------------------");
		
		 readOnlyBufer.flip();
		//�ı�ֻ������java.nio.ReadOnlyBufferException
		// readOnlyBufer.put((byte)3);
		 
		while(readOnlyBufer.remaining()>0){
			System.out.print(readOnlyBufer.get()+" ");
		}
		
		
		
		FileInputStream fin=new FileInputStream(new File("c:\\test.txt"));
		FileChannel fichannel= fin.getChannel();
		
		FileOutputStream fot=new FileOutputStream(new File("c:\\outtest.txt"));
		FileChannel fochannel=fot.getChannel();
		
		//ֱ���ڴ� �����������ļ�  ���ó����� �������Ƹ��� 
		ByteBuffer directBuffer=ByteBuffer.allocateDirect(1024);
		
		while(true){
			directBuffer.clear();
			int r=fichannel.read(directBuffer);
			if(r==-1) break;
			directBuffer.flip();
			fochannel.write(directBuffer);
		}
		
		
		//�ڴ�ӳ���ļ�I/O �����������ļ�
		RandomAccessFile raccessFile=new RandomAccessFile("c:\\test.txt", "rw");
		
		FileChannel foutchannal=raccessFile.getChannel();
		
		MappedByteBuffer mpp= foutchannal.map(FileChannel.MapMode.READ_WRITE, 0, 1024);
		
		mpp.put(0,(byte)97);
		mpp.put(1, (byte)122);
		mpp.put(2, (byte)122);
		mpp.put(3, (byte)122);
		mpp.put(1023, (byte)122);
		
		raccessFile.close();
		
	
	}
}
