package impl;

import java.lang.reflect.Array;
import java.util.Iterator;

import core.Entry;
import core.Map;

/**
 * Hash Map implementation based on the notes - there were some
 * small errors in the pseudo code that I have fixed below. I have
 * also added support for rehashing once the load hits 75% (it is
 * represented using the constant value LOAD_THRESHOLD).
 * 
 * @author Rem
 *
 * @param <K>
 * @param <V>
 */
public class HashMap<K,V> implements Map<K,V> {
	protected class MapEntry implements Entry<K,V> {
		K key;
		V value;
		
		public MapEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		@Override
		public K key() {
			return key;
		}

		@Override
		public V value() {
			return value;
		}
		
		public String toString() {
			return "[ " + key + ", " + value + " ]";
		}
	}
	
	/**
	 * This object is the special token used in cases where an
	 * entry is deleted from the Map.
	 */
	protected final Entry<K, V> AVAILABLE = new Entry<K, V>() {
		@Override
		public K key() {
			return null;
		}

		@Override
		public V value() {
			return null;
		}

		public String toString() {
			return "AVAILABLE";
		}
	};
	
	protected Entry<K, V>[] array;
	protected int size;
	
	@SuppressWarnings("unchecked")
	public HashMap(int capacity) {
		// As with ListMap, this must be used to implement an array of values whose
		// type is based on generic types. This is also used in the rehash part of
		// the put algorithm.
		array = (Entry<K, V>[]) Array.newInstance(Entry.class, capacity);
		size = 0;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Default hash function used division compression map and
	 * whatever hash code map is provided by Java.
	 * 
	 * @param k
	 * @return
	 */
	protected int hashFunction(K k) {
		return k.hashCode() % array.length;
	}
	
	@Override
	public V get(K k) {
		int i = hashFunction(k);
		
		int probe = 0;
		while (probe < array.length) {
			Entry<K,V> entry = array[i];
			if (entry == null) return null;

			if (k.equals(entry.key())) return entry.value(); 
			
			i = (i+1) % array.length;
			probe++;
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V put(K k, V v) {
		capacityCheck();
		
		int i = hashFunction(k);
		int probe = 0;
		int available = -1;
		while (probe < array.length) {
			Entry<K,V> entry = array[i];
			if (entry == null) {
				if (available == -1) {
					array[i] = new MapEntry(k,v);
				} else {
					array[available] = new MapEntry(k,v);
				}
				size++;
				return null;
			}
			
			
			if (entry == AVAILABLE && available == -1) {
				available = i;
			} else if (k.equals(entry.key())) {
				V temp = entry.value();
				array[i] = new MapEntry(k,v);
				return temp;
			}

			i = (i+1) % array.length;
			probe++; 
		}
		
		return null;
	}

	/**
	 * This method determines how the map will respond to being filled up. The code
	 * below implements a capacity check and throws an error if the capacity is
	 * reached. Alternatively the RehashableHashMap class implements a rehashing
	 * strategy. You should look at both pieces of code. 
	 *
	 */
	protected void capacityCheck() {
		if (size == array.length) throw new MapFullException();
	}

	@Override
	public V remove(K k) {
		int i = hashFunction(k);
		
		int probe = 0;
		while (probe < array.length) {
			Entry<K,V> entry = array[i];
			if (entry == null) return null;

			if (k.equals(entry.key())) {
				array[i] = AVAILABLE;
				size--;
				return entry.value(); 
			}
			
			i = (i+1) % array.length;
			probe++;
		}
		
		return null;
	}

	@Override
	public Iterator<K> keys() {
		// Note that this reuses the entries() iterator
		return new Iterator<K>() {
			Iterator<Entry<K,V>> iterator = entries();
			
			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}

			@Override
			public K next() {
				return iterator.next().key();
			}
		};
	}

	@Override
	public Iterator<V> values() {
		// Note that this reuses the entries() iterator
		return new Iterator<V>() {
			Iterator<Entry<K,V>> iterator = entries();
			
			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}

			@Override
			public V next() {
				return iterator.next().value();
			}
		};
	}

	@Override
	public Iterator<Entry<K, V>> entries() {
		// This iterator uses a counter to keep track of how many
		// entries have been returned. hasNext() returns false when
		// size entries have been iterated over.
		return new Iterator<Entry<K,V>>() {
			int i = 0;
			int count = 0;
			
			@Override
			public boolean hasNext() {
				return count < size;
			}

			@Override
			public Entry<K, V> next() {
				// Scan forwards to the next entry in the array
				while (i < array.length && (array[i] == null || array[i] == AVAILABLE)) i++;
				// Indicate that we have now visited one more entry
				count++;
				// return the entry and move the index one cell forwards.
				return array[i++];
			}
			
		};
	}

	public String toString() {
		String out = "{ ";
		
		int i=0;
		while (i < array.length) {
			if (i > 0) out += ", ";
			out += i + ": " + array[i];
			i++;
		}
		return out + " }";
	}
	
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>(2);
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
