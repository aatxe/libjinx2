/**
 * libjinx2: a library for loading the NX file format
 * Copyright (C) 2012 Aaron Weiss <aaronweiss74@gmail.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package us.aaronweiss.libjinx2.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import us.aaronweiss.libjinx2.INXNode;
import us.aaronweiss.libjinx2.NXException;
import us.aaronweiss.libjinx2.NXFile;


/**
 * A simple tool for benchmarking libjinx2.
 * @author Aaron Weiss
 * @version 1.0
 */
public class Benchmark {
	/**
	 * @param args None
	 */
	public static void main(String[] args) {
		System.out.print("Press return to begin.");
		NXFile nx = null;
		Scanner in = new Scanner(System.in);
		in.nextLine();
		{
			System.out.println("libjinx2: load bench: start");
			long startNano = System.nanoTime();
			long start = System.currentTimeMillis();
			try {
				nx = new NXFile("Data.nx");
				nx.parse();
			} catch (NXException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			long end = System.currentTimeMillis();
			long endNano = System.nanoTime();
			System.out.println("libjinx2: load bench: end");
			System.out.println("libjinx2: load bench: " + (end - start) + " ms " + (endNano - startNano) / 1000 + " µs");
		}
		{
			System.out.println("libjinx2: access bench: start");
			long startNano = System.nanoTime();
			long start = System.currentTimeMillis();
			for (int i = 0; i < 1000000; i++) {
				nx.resolvePath("Effect/BasicEff.img/LevelUp/5/origin");
			}
			long end = System.currentTimeMillis();
			long endNano = System.nanoTime();
			System.out.println("libjinx2: access bench: end");
			System.out.println("libjinx2: access bench: " + (end - start) + " ms " + (endNano - startNano) / 1000 + " µs");
		}
		{
			System.out.println("libjinx2: recursion bench: start");
			long startNano = System.nanoTime();
			long start = System.currentTimeMillis();
			Benchmark.recurse(nx.getBaseNode());
			long end = System.currentTimeMillis();
			long endNano = System.nanoTime();
			System.out.println("libjinx2: recursion bench: end");
			System.out.println("libjinx2: recursion bench: " + (end - start) + " ms " + (endNano - startNano) / 1000 + " µs");
		}
		in.nextLine();
		try {
			nx.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void recurse(INXNode target) {
		for (INXNode n : target) {
			recurse (n);
		}
	}
}
