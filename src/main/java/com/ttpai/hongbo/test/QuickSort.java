package com.ttpai.hongbo.test;

import java.util.Arrays;

/**
 * 快速排序法
 * 
 * @author Andy.Chen
 * 
 * @param <T>
 */
public class QuickSort<T extends Comparable<T>> {
	
	public static void main(String[] args) {
		QuickSort<Integer> qs = new QuickSort<Integer>();
		Integer[] array = new Integer[]{1,6,3,9,2,6,4,8};
		qs.sort(array, 0, array.length);
		System.out.println(Arrays.toString(array));
	}

	public void sort(T[] array, int from, int len) {
		quick_sort(array, from, from + len - 1);
	}

	private void quick_sort(T[] array, int from, int to) {
		if (to - from < 1)
			return;
		int pivot = selectPivot(array, from, to);
		pivot = partition(array, from, to, pivot);

		quick_sort(array, from, pivot - 1);
		quick_sort(array, pivot + 1, to);
	}

	/**
	 * 选择基准元素
	 * 
	 * @param array
	 * @param from
	 * @param to
	 * @return
	 */
	private int selectPivot(T[] array, int from, int to) {
		return (from + to) / 2;
	}

	/**
	 * 分区操作
	 * 
	 * @param array
	 * @param from
	 * @param to
	 * @param pivot
	 * @return
	 */
	private int partition(T[] array, int from, int to, int pivot) {
		T tmp = array[pivot];
		array[pivot] = array[to];
		while (from != to) {
			while (from < to && array[from].compareTo(tmp) <= 0)
				from++;
			if (from < to) {
				array[to] = array[from];
				to--;
			}

			while (from < to && array[to].compareTo(tmp) >= 0)
				to--;
			if (from < to) {
				array[from] = array[to];
				from++;
			}
		}
		array[from] = tmp;
		return from;
	}

}