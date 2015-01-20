package com.lwk.nio.test;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

public class NioReadFile {
	public static void main(String[] args) throws Exception {
		
		IntBuffer buffer2=IntBuffer.allocate(10);
		for(int i=0;i<buffer2.capacity();i++){
			buffer2.put((byte)i);
		}
		buffer2.flip();
		
		while(buffer2.hasRemaining()){
			System.out.print(buffer2.get()+" ");
		}
		
		System.out.println("------------------");
		
		FileInputStream fin=new FileInputStream(new File("c:\\test.txt"));
		ByteBuffer buffer=ByteBuffer.allocate(1024);
		FileChannel channel= fin.getChannel();
		channel.read(buffer);
		buffer.flip();
		while(buffer.hasRemaining()){
			System.out.print(buffer.get()+" ");
		}
		
		fin.close();
		System.out.println();
		System.out.println(new String(buffer.array()));
	}
}
