package com.lwk.nio.test;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioWriter {
	public static void main(String[] args) throws Exception {
		byte[] byteArray="ÄãºÃ°¡ÃÃ×Ó".getBytes();
		ByteBuffer buffer=ByteBuffer.allocate(1024);
		FileOutputStream fileOutputStream=new FileOutputStream(new File("c:\\test.txt"));
		FileChannel fileChannel= fileOutputStream.getChannel();
		//buffer.position(byteArray.length);
		buffer.put(byteArray, 0, byteArray.length);
		buffer.put("sfsf".getBytes());
		buffer.flip();
		fileChannel.write(buffer);
		
	}
}
