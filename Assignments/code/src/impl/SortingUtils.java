package impl;

import java.lang.reflect.Array;
import java.util.Random;

import core.List;

public class SortingUtils {
	@SuppressWarnings("unchecked")
	public static void singleDigitBucketSort(List<Integer> list) {
		List<Integer>[] buckets = (List<Integer>[]) Array.newInstance(List.class, 10);
		
		// Step 1: Copy the values from the list into the buckets.
		while (!list.isEmpty()) {
			int value = list.remove(list.first());
			if (value < 0 || value > 9) throw new UnsortableException("The list contains an invalid value: " + value);
			if (buckets[value] == null) {
				buckets[value] = new LinkedList<Integer>();
			}
			buckets[value].insertLast(value);
		}
		
		// Step 2: Copy the values from the buckets to the list.
		for (int i=0; i < buckets.length; i++) {
			if (buckets[i] != null) {
				while (!buckets[i].isEmpty()) {
					list.insertLast(buckets[i].remove(buckets[i].first()));
				}
			}
		}
	}

	public static void integerBucketSort(List<Integer> list, int lower, int upper) {
	}
	
	public static void integerRadixSort(List<Integer> list, int digits) {
	}
	
	public static void stringRadixSort(List<String> list, int digits) {
	}
}
