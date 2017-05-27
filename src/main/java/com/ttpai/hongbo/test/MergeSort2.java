package com.ttpai.hongbo.test;

import java.util.Arrays;

public class MergeSort2 {

	// 　private　static　long　sum　=　0;

	/**
	 * 　*　
	 * 
	 * <pre>
	 * 　*　二路归并
	 * 　*　原理：将两个有序表合并和一个有序表
	 * 　*　
	 * </pre>
	 * 
	 * 　* 　*　@param　a 　*　@param　s 　*　第一个有序表的起始下标 　*　@param　m 　*　第二个有序表的起始下标
	 * 　*　@param　t 　*　第二个有序表的结束小标 　*
	 */
	private static void merge(int[] a, int s, int m, int t) {
		int[] tmp = new int[t - s + 1];
		int i = s, j = m, k = 0;
		while (i < m && j <= t) {
			if (a[i] <= a[j]) {
				tmp[k] = a[i];
				k++;
				i++;
			} else {
				tmp[k] = a[j];
				j++;
				k++;
			}
		}
		while (i < m) {
			tmp[k] = a[i];
			i++;
			k++;
		}
		while (j <= t) {
			tmp[k] = a[j];
			j++;
			k++;
		}
		System.arraycopy(tmp, 0, a, s, tmp.length);
	}

	/**
	 * 　* 　*　@param　a 　*　@param　s 　*　@param　len 　*　每次归并的有序集合的长度
	 */
	public static void mergeSort(int[] a, int s, int len) {
		int size = a.length;
		int mid = size / (len << 1);
		int c = size & ((len << 1) - 1);
		// 　-------归并到只剩一个有序集合的时候结束算法-------//
		if (mid == 0)
			return;
		// 　------进行一趟归并排序-------//
		for (int i = 0; i < mid; ++i) {
			s = i * 2 * len;
			merge(a, s, s + len, (len << 1) + s - 1);
		}
		// 　-------将剩下的数和倒数一个有序集合归并-------//
		if (c != 0)
			merge(a, size - c - 2 * len, size - c, size - 1);
		// 　-------递归执行下一趟归并排序------//
		mergeSort(a, 0, 2 * len);
	}

	public static void main(String[] args) {
		test3();
		
	}
	static void test1(){
		 int[] a = new int[] { 4, 3, 6, 1, 2, 5 };
		 mergeSort(a, 0, 1);
		 for (int i = 0; i < a.length; ++i) {
		 System.out.print(a[i] + "　");
		 }
	}
	static void test2(){
		int[] a = new int[] { 1, 2, 3, 4 };
		int[] b = new int[] { 1, 2, 3, 4 };
		int[] c = MemeryArray(a, b);
		System.out.println(Arrays.toString(c));
	}
	static void test3(){
		int[] a = new int[] { 4, 3, 6, 1, 2, 5 };
		int[] p = new int[a.length];
		mergesort(a, 0, a.length - 1, p);
		System.out.println(Arrays.toString(p));
	}

	// 将有序数组a[]和b[]合并到c[]中
	static int[] MemeryArray(int a[], int b[]) {
		int i, j, k;
		int n = a.length, m = b.length;
		int c[] = new int[n + m];
		i = j = k = 0;
		int count = 0;
		while (i < n && j < m) {
			count++;
			if (a[i] < b[j])
				c[k++] = a[i++];
			else
				c[k++] = b[j++];
		}

		while (i < n)
			c[k++] = a[i++];

		while (j < m)
			c[k++] = b[j++];
		System.out.println(count);
		return c;
	}

	static void mergearray(int a[], int first, int mid, int last, int temp[]) {
		int i = first, j = mid + 1;
		int m = mid, n = last;
		int k = 0;

		while (i <= m && j <= n) {
			if (a[i] <= a[j])
				temp[k++] = a[i++];
			else
				temp[k++] = a[j++];
		}

		while (i <= m)
			temp[k++] = a[i++];

		while (j <= n)
			temp[k++] = a[j++];

		for (i = 0; i < k; i++)
			a[first + i] = temp[i];
	}

	static void mergesort(int a[], int first, int last, int temp[]) {
		if (first < last) {
			int mid = (first + last) / 2;
			mergesort(a, first, mid, temp); // 左边有序
			mergesort(a, mid + 1, last, temp); // 右边有序
			mergearray(a, first, mid, last, temp); // 再将二个有序数列合并
		}
	}

}
