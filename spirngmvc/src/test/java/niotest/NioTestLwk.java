package niotest;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

public class NioTestLwk {
	public static void main(String[] args) throws Exception {
		FileOutputStream fos=new FileOutputStream(new File("c://lwktestNio.txt")); 
		FileChannel fchannel= fos.getChannel();
		Scanner scanner=new Scanner(System.in);
		String msg=scanner.nextLine();
		while(!msg.equals("quit")){
			msg=scanner.nextLine();
			fchannel.write(ByteBuffer.wrap(msg.getBytes()));
		}
		System.out.println("ÍêÊÂ¶ù");
	}
}
