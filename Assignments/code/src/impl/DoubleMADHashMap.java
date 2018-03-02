package impl;

import core.Entry;
import core.Map;

public class DoubleMADHashMap<K, V> extends MADHashMap<K, V> {
	protected int doubleHash(K k) {
		return 11 - (k.hashCode() % 11);
	}

	@Override
	public V get(K k) {
		int i = hashFunction(k);
		int d = doubleHash(k);
		
		int probe = 0;
		while (probe < array.length) {
			Entry<K,V> entry = array[i];
			if (entry == null) return null;

			if (k.equals(entry.key())) return entry.value(); 
			
			i = (i+d) % array.length;
			probe++;
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V put(K k, V v) {
		capacityCheck();
		
		int i = hashFunction(k);
		int d = doubleHash(k);

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

			i = (i+d) % array.length;
			probe++; 
		}
		
		return null;
	}

	@Override
	public V remove(K k) {
		int i = hashFunction(k);
		int d = doubleHash(k);
		
		int probe = 0;
		while (probe < array.length) {
			Entry<K,V> entry = array[i];
			if (entry == null) return null;

			if (k.equals(entry.key())) {
				array[i] = AVAILABLE;
				size--;
				return entry.value(); 
			}
			
			i = (i+d) % array.length;
			probe++;
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		Map<Integer, String> map = new DoubleMADHashMap<Integer, String>();
		map.put(15, "");
		System.out.println(map);
		map.put(7, "");
		System.out.println(map);
		map.put(26, "");
		System.out.println(map);
		map.put(39, "");
		System.out.println(map);
		map.put(11, "");
		System.out.println(map);
		map.put(9, "");
		System.out.println(map);
		map.put(27, "");
		System.out.println(map);
		map.put(5, "");
		System.out.println(map);
		map.put(18, "");
		System.out.println(map);
		map.put(2, "");
		System.out.println(map);
		map.put(54, "");
		System.out.println(map);
		map.put(22, "");
		System.out.println(map);
		map.put(4, "");
		System.out.println(map);
	}
}
