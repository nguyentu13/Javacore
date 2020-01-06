package edu.fa;

import java.util.Arrays;

public class SortArraySample {
	private static final int[] NUMBERS = { 49, 38, 65, 97, 76, 13, 27, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18,
			23, 34, 15, 35, 25, 53, 51 };

	public static void quickSorf(int[] array, int low, int high) {

		int i = low, j = high;
		int pivot = array[low + (high - low) / 2];
		while (i < j) {
			while (array[i] < pivot)
				++i;
			while (array[j] > pivot)
				--j;
			if (i <= j) {
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				++i;
				--j;
			}
		}
		if (low < j) {
			quickSorf(array, low, j);
		}
		if (i < high) {
			quickSorf(array, i, high);
		}

//		System.out.println(Arrays.toString(array));

	}

	public static void selectSort(int[] array) {
		int position = 0;
		for (int i = 0; i < array.length; i++) {
			int j = i + 1;
			position = i;
			int temp = array[i];
			for (; j < array.length; j++) {
				if (array[j] < temp) {
					temp = array[j];
					position = j;
				}
			}
			array[position] = array[i];
			array[i] = temp;
		}
		System.out.println(Arrays.toString(array) + " selectSort");
	}

	public static void bubbleSort(int[] array) {
		int temp = 0;
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length - 1 - i; j++) {
				if (array[j] > array[j + 1]) {
					temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
		System.out.println(Arrays.toString(array) + " bubbleSort");
	}

	public static void insertSort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			int temp = array[i];
			int j = i - 1;
			for (; j >= 0 && array[j] > temp; j--) {

				array[j + 1] = array[j];
			}
			array[j + 1] = temp;
		}
		System.out.println(Arrays.toString(array) + " insertSort");
	}

	public static void main(String[] args) {
//		bubbleSort(NUMBERS);
//		insertSort(NUMBERS);
//		selectSort(NUMBERS);
		quickSorf(NUMBERS, 0, NUMBERS.length - 1);
		System.out.println(Arrays.toString(NUMBERS));
	}
}
