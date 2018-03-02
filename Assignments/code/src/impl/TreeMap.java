package impl;

import java.util.Iterator;

import core.Entry;
import core.Map;

public class TreeMap<K extends Comparable<K>, V> implements Map<K, V> {
	private class TreeEntry implements Entry<K,V>,Comparable<TreeEntry> {
		K key;
		V value;
		
		public TreeEntry(K key, V value) {
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
			return "{" + key + "," + value +"}";
		}

		@Override
		public int compareTo(TreeEntry entry) {
			return key.compareTo(entry.key);
		}
	}

	private BinarySearchTree<TreeEntry> tree = new BinarySearchTree<TreeEntry>();
	
	@Override
	public int size() {
		return tree.size();
	}

	@Override
	public boolean isEmpty() {
		return tree.isEmpty();
	}

	@Override
	public V get(K k) {
		TreeEntry entry = tree.find(new TreeEntry(k,null));
		if (entry == null) return null;
		return entry.value();
	}

	@Override
	public V put(K k, V v) {
		TreeEntry entry = tree.insert(new TreeEntry(k,v));
		if (entry == null) return null;
		return entry.value();
	}

	@Override
	public V remove(K k) {
		TreeEntry entry = tree.remove(new TreeEntry(k,null));
		if (entry == null) return null;
		return entry.value();
	}

	@Override
	public Iterator<K> keys() {
		return new Iterator<K>() {
			Iterator<TreeEntry> iterator = tree.iterator();

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
			Iterator<TreeEntry> iterator = tree.iterator();

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
		return new Iterator<Entry<K,V>>() {
			Iterator<TreeEntry> iterator = tree.iterator();

			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}

			@Override
			public Entry<K, V> next() {
				return iterator.next();
			}
		};
	}

	public String toString() {
		return tree.toString();
	}
	
	public static void main(String[] args) {
		TreeMap<Integer, String> map = new TreeMap<Integer,String>();
		map.put(12,"AA");
		System.out.println(map);
		map.put(5,"ZZ");
		System.out.println(map);
		map.put(15,"XX");
		System.out.println(map);
		map.put(7,"YY");
		System.out.println(map);
		map.remove(12);
		System.out.println(map);
		map.remove(5);
		System.out.println(map);
		
		System.out.println("Values: ");
		Iterator<String> it = map.values();
		while (it.hasNext()) {
			System.out.println(it.next()+",");
		}
	}
}
