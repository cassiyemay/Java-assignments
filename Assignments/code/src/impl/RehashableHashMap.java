package impl;

import java.lang.reflect.Array;
import java.util.Iterator;

import core.Entry;
import core.Map;

public class RehashableHashMap<K,V> extends HashMap<K,V> {
	private static final double LOAD_THRESHOLD = 0.75;
	private static final int DEFAULT_CAPACITY = 13;
	
	public RehashableHashMap() {
		super(DEFAULT_CAPACITY);
	}
	
	public RehashableHashMap(int capacity) {
		super(capacity);
	}
	
	@SuppressWarnings("unchecked")
	protected void capacityCheck() {
		// If load factor is > LOAD_THRESHOLD rehash - if we didn't do this, then
		// we would have to do a capacity check (i.e. size == array.length => FULL
		// MAP).
		if (size > LOAD_THRESHOLD*array.length) {
			// Keep a reference to the old array.
			Entry<K,V>[] old = array;
			
			// Create a new array that is twice as large
			array = (Entry<K, V>[]) Array.newInstance(Entry.class, array.length*2);
			
			// Reset size and insert all entries from the old array
			// into the new array (using the put operation to avail of
			// the revised hash function.
			size = 0;
			for (int i=0; i < old.length; i++) {
				if (old[i] != null && old[i] != AVAILABLE) {
					put(old[i].key(), old[i].value());
				}
			}
			// Now continue on and do the original put operation...
		}
	}
	
	public static void main(String[] args) {
		Map<String, String> map = new RehashableHashMap<String, String>(2);
		System.out.println("put(ie, Iceland) - " + map.put("ie", "Iceland"));
		System.out.println(map);
		System.out.println("put(uk, United Kingdom) - " + map.put("uk", "United Kingdom"));
		System.out.println(map);
		System.out.println("put(de, Germany) - " + map.put("de", "Germany"));
		System.out.println(map);
		System.out.println("put(nl, Netherlands) - " + map.put("nl", "Netherlands"));
		System.out.println(map);
		System.out.println("get(ie) - " + map.get("ie"));
		System.out.println(map);
		System.out.println("put(ie, Ireland) - " + map.put("ie", "Ireland"));
		System.out.println(map);
		System.out.println("put(sw, Santa's Workshop) - " + map.put("sw", "Santa's Workshop"));
		System.out.println(map);
		System.out.println("put(fr, France) - " + map.put("fr", "France"));
		System.out.println(map);
		System.out.println("put(cn, China) - " + map.put("cn", "China"));
		System.out.println(map);
		System.out.println("put(us, United States of America) - " + map.put("us", "United States of America"));
		System.out.println(map);
		System.out.println("remove(sw) - " + map.remove("sw"));
		System.out.println(map);
		
		Iterator<String> it = map.keys();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
}
