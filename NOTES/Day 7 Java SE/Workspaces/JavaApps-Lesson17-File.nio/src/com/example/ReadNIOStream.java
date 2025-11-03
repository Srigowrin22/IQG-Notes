package com.example;

import java.nio.file.*;
import java.io.*;
import java.util.stream.*;


public class ReadNIOStream{

	public static void main(String arg[]) {
		
		
		System.out.println("Read NIO Stream");
		try(Stream<String> lines = Files.lines(Paths.get("C:\\Users\\srigowri.n\\OneDrive - iqgateway pvt ltd\\Desktop\\NOTES\\Day 7 Java SE\\Java Notes\\Day-1.txt"))) {
			
			lines.forEach(line -> System.out.println(line));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}