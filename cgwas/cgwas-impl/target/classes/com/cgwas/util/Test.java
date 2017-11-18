package com.cgwas.util;

public class Test {
	public static void main(String[] args)  {
		int[] a = new int[]{ 1, 2, 3, 4, 6, 7, 8, 9, 10 };
		a = addElement(5, 4, a);
		for (int i : a) {
			System.out.println(i);
		}
	}

	public static int[] addElement(int e, int n, int[] a) {
		int[] b=new int[a.length+1];
		for (int i = a.length; i > 0; i--) {
			if (i == n) {
				b[i] = e;
			}else{
				b[i+1] = a[i];
			}
		}
		return b;
	}
}
