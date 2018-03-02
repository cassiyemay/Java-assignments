package impl;
import java.lang.reflect.Array;
import java.util.Random;

public class SortingUtils {
	
	                  //------Part 1-----Bucket Sort------------//
	//the simpler version for only one digit number
	public static void singleDigitBucketSort(Sequence<Integer> unsorted){
		//initialize buckets
		@SuppressWarnings("unchecked")
		Sequence<Integer>[] buckets =  (Sequence<Integer>[]) Array.newInstance(Sequence.class, 10);
		//phase1: copy values from  the sequence into the bucket
		while (! unsorted.isEmpty()) {
			int value = unsorted.remove(unsorted.first());
			if(value <0 || value >9) throw 
			new UnsortableException("The sequence constains an invalid value" + value);
			if(buckets[value]==null)
				buckets[value] = new ArraySequence<Integer>();
			buckets[value].insertLast(value);
		}
		//phase2: copy the values from bucket into the sequence
		for(int i=0; i< buckets.length;i++) {
			if(buckets[i] !=null) {
				while (! buckets[i].isEmpty()) 
					unsorted.insertLast(buckets[i].remove(buckets[i].first()));
			}
		}
				
	}
	
	//implement integerBurketSort method
	public static void integerBucketSort(Sequence<Integer> unsorted, int lower, int upper) {
		//sort integers between the lower and upper 
		//initialize buckets
		@SuppressWarnings("unchecked")
		Sequence<Integer>[] buckets = (Sequence<Integer>[]) Array.newInstance(Sequence.class, 1000);
		//phase 1: copy each value from sequence into buckets
		while(! unsorted.isEmpty()) {
			//current value
			int value = unsorted.remove(unsorted.first());
			if(value <lower || value >upper) throw 
			new UnsortableException("The sequence constains an invalid value" + value);
			if(buckets[value] ==null)
				buckets[value] = new ArraySequence<Integer>(1000);
			buckets[value].insertLast(value);
			
		}
		
		//phase 2: copy buckets values into unsorted sequence
		for (int i =0; i< buckets.length; i++) {
			if (buckets[i] !=null) {
				while (!buckets[i].isEmpty())
					unsorted.insertLast(buckets[i].remove(buckets[i].first()));}
		}
	}
	
	//test integer bucket sort method
	public static void ibsTest() {
		Sequence<Integer> list = new ArraySequence<Integer>(1000);
		
		//add 500 random integers in the range [50,250]
		Random random = new Random();
		for(int i=0;i< 500; i++)
			list.insertFirst(random.nextInt((250-50)+1)+50);
		//print list before sorted
		System.out.println("Unsorted:\n" + list);
		//sort list by bucket sort
		integerBucketSort(list,25,250);
		//print the list after bucket sort
		System.out.println("Sorted:\n" + list);
	
	}
	                    //------Part 2-----Integer Radix Sort------------//
	//use radix sort algorithm to sort values with a specified maximum digits
	public static void integerRadixSort(Sequence<Integer> unsorted, int digits) {
		//initialize buckets
		@SuppressWarnings("unchecked")
		Sequence<Integer>[] buckets =  (Sequence<Integer>[]) Array.newInstance(Sequence.class, 1000);
		//iterate numbers of digits
		for(int i=0; i< digits; i++) {
			while (!unsorted.isEmpty()) {
				int value = unsorted.remove(unsorted.first());
				int number = (int) (value/(Math.pow(10, i))) % 10;
				
				//Throw exception of number exceeds specified numbers of digits
				if ((int)(value/(Math.pow(10, digits)))%10 > 0) throw
				new UnsortableException("The sequence constains an invalid value" + value);
				
				//phase1: add value to the bucket
				if(buckets[number] ==null) 
					buckets[number] = new ArraySequence<Integer>(1000);
				buckets[number].insertLast(value);
			}
			
			//phase2: copy value from buckets into sequence
			for(int j =0; j<buckets.length;j++) {
				if(buckets[j] !=null) {
					while (!buckets[j].isEmpty())
						unsorted.insertLast(buckets[j].remove(buckets[j].first()));
				}
			}
		}
	}
	
	//test integer radix sort method
	public static void irsTest() {
		Sequence<Integer> list = new ArraySequence<Integer>(1000);
		//add 500 random integers 
		Random random = new Random();
		for (int i =0 ;i < 500; i++)
			list.insertFirst(random.nextInt((999-0) +1));
		//print the list before sorted
		System.out.println("Unsorted:\n" + list);
		//sorted list by radix sort
		integerRadixSort(list,3);
		//print the list after radix sorted
		System.out.println("Sorted:\n" + list);
	}
	
	                  //------Part 3-----String Radix Sort------------//
	public static void stringRadixSort(Sequence<String> unsorted, int digits) {
		//initialized buckets
		@SuppressWarnings("unchecked")
		Sequence<String>[] buckets =  (Sequence<String>[]) Array.newInstance(Sequence.class, 1000);
		// track current letter
		int letter = digits;
		//iterate  the number of digits
		for(int i =0; i< digits; i++) {
			while (! unsorted.isEmpty()) {
				//current word
				String word = unsorted.remove(unsorted.first());
				if(word.length() > digits) throw
				new UnsortableException("the sequence contains an invalid word:" + word);
				
				//phase1: add words into buckets
				int index;
				if(word.length() >= letter) {
					char c = word.charAt(letter-1);
					index = c-'a' +1;
				}
				else
					index =0;
				if (buckets[index] ==null) 
					buckets[index] = new ArraySequence<String>(1000);
				buckets[index].insertLast(word);
			}
			
			//phase 2: copy from buckets into sequence unsorted
			  for(int j =0; j< buckets.length;j++) {
				  if(buckets[j] != null) {
					  while ( ! buckets[j].isEmpty()) 
						  unsorted.insertLast(buckets[j].remove(buckets[j].first()));
				  }
			  }
			  letter--;
		}
	}
	
	//test string radix sort method
	public static void srsTest() {
		Sequence<String> list = new ArraySequence<String>();
		//add the words into list
		list.insertLast("the");
		list.insertLast("big");
		list.insertLast("black");
		list.insertLast("cat");
		list.insertLast("sat");
		list.insertLast("on");
		list.insertLast("beautiful");
		list.insertLast("brown");
		list.insertLast("mat");
		//find the max length word in the sentence
		int max =0;
		for(String word: list) {
			if(word.length() > max)
				max = word.length();
		}
		
		//print the list before sorted
				System.out.println("Unsorted:\n" + list);
				//sorted list by string radix sort
				stringRadixSort(list,max);
				//print the list after radix sorted
				System.out.println("Sorted:\n" + list);
	}
	//main method to print all the test methods
	public static void main(String[] args) {
		//print the method integer bucket sort
		System.out.println(" ---------------------------Integer Bucket Sort-------------------------");
		ibsTest();
		//print the method for integer radix sort
		System.out.println("\n--------------------------Integer Radix Sort--------------------------");
		irsTest();
		//print the method for string radix sort
		System.out.println("\n--------------------------String Radix Sort---------------------------");
		srsTest();
		
	}
}
