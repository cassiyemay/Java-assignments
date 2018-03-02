package impl;

import java.util.Iterator;

import core.Entry;
import core.List;
import core.Map;
import core.Position;

public class ListMap<K, V> implements Map<K, V> {
	private class ListMapEntry implements Entry<K,V> {
		K key;
		V value;
		
		public ListMapEntry(K key, V value) {
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
			return "(" + key + ", " + value + ")";
		}
	}
	
	private List<Entry<K,V>> list = new LinkedList<Entry<K,V>>();
	
	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public V get(K k) {
		Position<Entry<K,V>> current = find(k);
		if (current == null) return null;
		return current.element().value();
	}

	@Override
	public V put(K k, V v) {
		Position<Entry<K,V>> current = find(k);
		if (current == null) {
			// we have a new entry so insert at the end
			list.insertLast(new ListMapEntry(k,v));
			return null;
		} else {
			ListMapEntry entry = (ListMapEntry) current.element();
			V temp = entry.value;
			entry.value = v;
			return temp;
		}
	}

	@Override
	public V remove(K k) {
		Position<Entry<K,V>> current = find(k);
		if (current == null) return null;
		list.remove(current);
		return current.element().value();
	}
	
	private Position<Entry<K,V>> find(K key) {
		if (list.isEmpty()) return null;
		Position<Entry<K,V>> current = list.first();
		while (current != list.last()) {
			if (current.element().key().equals(key)) return current;
			current = list.next(current);
		}
		
		// below is a nice shortcut for implementing an if statement that returns 
		// one of two values. It is equivalent to:
		// if (current.element().key().equals(key)) return current;
		// else return null;
		return current.element().key().equals(key) ? current:null;
	}

	@Override
	public Iterator<K> keys() {
		return new Iterator<K>() {
			Iterator<Entry<K,V>> iterator = list.iterator();
			
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
		return new Iterator<V>() {
			Iterator<Entry<K,V>> iterator = list.iterator();
			
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
		return list.iterator();
	}

	public String toString() {
		return list.toString();
	}
	
	public static void main(String[] args) {
		Map<String, String> map = new ListMap<String, String>();
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
	}
}
