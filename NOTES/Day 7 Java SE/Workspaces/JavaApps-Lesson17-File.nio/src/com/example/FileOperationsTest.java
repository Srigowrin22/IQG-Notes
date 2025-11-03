package com.example;

import java.nio.file.*;
import java.io.*;
import java.util.stream.*;


public class FileOperationsTest{
	public static void main(String arg[]) throws IOException{
		Path p1 = Paths.get("C:\\Users\\srigowri.n\\OneDrive - iqgateway pvt ltd\\Desktop\\NOTES\\Day 7 Java SE\\Java Notes\\MyTestFile.txt");
		//Files.createFile(p1);

		Path p2 = Paths.get("C:\\Users\\srigowri.n\\OneDrive - iqgateway pvt ltd\\Desktop\\NOTES\\Day 7 Java SE\\MyTestFolder1");
		//Files.createDirectory(p2);
		//Files.createDirectories(Paths.get("C:\\Users\\srigowri.n\\OneDrive - iqgateway pvt ltd\\Desktop\\NOTES\\Day 7 Java SE\\MyTestFolder2\\src\\com\\example"));
		
		System.out.println("File List");
		try(Stream<Path> files = Files.list(Paths.get("C:\\Users"))) {
			files.forEach(line -> System.out.println(line));
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		System.out.println("File Walk");
		try(Stream<Path> files = Files.walk(Paths.get("C:\\Users"))) {
			files.forEach(line -> System.out.println(line));
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
}