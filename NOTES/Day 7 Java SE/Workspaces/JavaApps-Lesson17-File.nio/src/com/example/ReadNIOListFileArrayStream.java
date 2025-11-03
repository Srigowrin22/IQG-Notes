package com.example;

import java.nio.file.*;
import java.io.*;
import java.util.stream.*;
import java.util.*;


public class ReadNIOListFileArrayStream{

	public static void main(String arg[]) {
		
		
		System.out.println("Read NIO Stream List File Array: ");

		Path file = Paths.get("C:\\Users\\srigowri.n\\OneDrive - iqgateway pvt ltd\\Desktop\\NOTES\\Day 7 Java SE\\Java Notes\\Day-1.txt");
		List<String> fileArray;

		try {
			fileArray = Files.readAllLines(file);
			
			fileArray.stream()
				.filter(line -> line.contains("1."))
				.forEach(line -> System.out.println(line));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}