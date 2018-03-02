package impl;

import java.util.Iterator;

import core.Entry;
import core.Map;

public class BinaryTreeMap<K extends Comparable<K>, V> implements Map<K, V> {
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
		return null;
	}

	@Override
	public V put(K k, V v) {
		return null;
	}

	@Override
	public V remove(K k) {
		return null;
	}

	@Override
	public Iterator<K> keys() {
		return null;
	}

	@Override
	public Iterator<V> values() {
		return null;
	}

	@Override
	public Iterator<Entry<K, V>> entries() {
		return null;
	}

	public String toString() {
		return tree.toString();
	}
	
	public static void main(String[] args) {
		BinaryTreeMap<Integer, String> map = new BinaryTreeMap<Integer,String>();
		map.put(12,"");
		System.out.println(map);
		map.put(5,"");
		System.out.println(map);
		map.put(15,"");
		System.out.println(map);
		map.put(7,"");
		System.out.println(map);
		map.remove(12);
		System.out.println(map);
		map.remove(5);
		System.out.println(map);
		
	}
}
