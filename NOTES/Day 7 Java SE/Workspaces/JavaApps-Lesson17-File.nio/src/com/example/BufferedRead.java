package com.example;

import java.nio.file.*;
import java.io.*;
import java.util.stream.*;


public class BufferedRead{

	public static void main(String arg[]) {
		
		
		System.out.println("Buffered Read");
		try(BufferedReader bReader = new BufferedReader(new FileReader ("C:\\Users\\srigowri.n\\OneDrive - iqgateway pvt ltd\\Desktop\\NOTES\\Day 7 Java SE\\Java Notes\\Day-1.txt"))) {
			bReader.lines()
			.forEach(line -> System.out.println(line));
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		
	}
}