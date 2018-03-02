package impl;

import core.Map;

public class MADHashMap<K, V> extends HashMap<K, V> {
	public MADHashMap() {
		super(19);
	}
	
	protected int hashFunction(K k) {
		return (3*k.hashCode() + 7) % 19;
	}
	
	public static void main(String[] args) {
		Map<Integer, String> map = new MADHashMap<Integer, String>();
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
