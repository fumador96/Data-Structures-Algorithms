package PracticeDSA;

public class HashMap<K, V> {

	private class HTPair {
		K key;
		V value;

		public HTPair(K key, V value) {
			this.key = key;
			this.value = value;
		}
		@Override
		public boolean equals(Object other) {
			HTPair pair = (HTPair) other;
			return this.key.equals(pair.key);
		}
		@Override
		public String toString() {
			return this.key + "=" + this.value;
		}
	}

	private GenericLinkedList<HTPair>[] bucketArray;
	private int size;

	private static final int Default_Capacity = 10;

	public HashMap() {
		this(Default_Capacity);
	}

	public HashMap(int size) {
		this.bucketArray = (GenericLinkedList<HTPair>[]) new GenericLinkedList[size];
	}
	
	private int hashfunction(K key) {
		int hashcode = key.hashCode();
		hashcode = Math.abs(hashcode);
		int bestindex = hashcode % bucketArray.length;
		return bestindex;
	}

	public void put(K key, V value) throws Exception {
		int bestindex = this.hashfunction(key);
		HTPair pair = new HTPair(key, value);
		GenericLinkedList<HTPair> bucket = bucketArray[bestindex];
		if (bucket == null) {
			bucket = new GenericLinkedList<>();
			bucket.addLast(pair);
			this.size++;
			this.bucketArray[bestindex] = bucket;
		} else {
			int findAt = bucket.findIndexWithSameData(pair);
			if (findAt == -1) {
				bucket.addLast(pair);
				this.size++;
			} else {
				bucket.getAt(findAt).value = value;
			}

		}
		if(this.size/bucketArray.length>0.75) {
			this.rehash();
		}
	}
	
	private void rehash() throws Exception{
		GenericLinkedList<HTPair>[] oldbucketarray = this.bucketArray;
		int arrsize = oldbucketarray.length;
		System.out.println(arrsize);
		this.bucketArray = (GenericLinkedList<HTPair>[]) new GenericLinkedList[2*arrsize];
		this.size=0;
		for(int i=0;i<arrsize;i++) {
			GenericLinkedList<HTPair> bucket = oldbucketarray[i];
			if(bucket!=null && !bucket.isEmpty()) {
				for(int index=0;index<bucket.size();index++) {
					HTPair pair = bucket.getAt(index);
					this.put(pair.key,pair.value);
				}
			}
		}
	}
	
	public V get(K key) throws Exception{
		int bestindex = this.hashfunction(key);
		HTPair pair = new HTPair(key, null);
		GenericLinkedList<HTPair> bucket = bucketArray[bestindex];
		if (bucket == null) {
			return null;
		}
		else if(bucket.size()==0) {
			return null;
		}
		else {
			for(int i=0;i<bucket.size();i++) {
				if(bucket.getAt(i).equals(pair)) {
					return bucket.getAt(i).value;
				}
			}
		}
		return null;
	}
	
	public V remove(K key) throws Exception{
		int bestindex = this.hashfunction(key);
		HTPair pair = new HTPair(key, null);
		GenericLinkedList<HTPair> bucket = bucketArray[bestindex];
		if (bucket == null) {
			return null;
		}
		else if(bucket.size()==0) {
			return null;
		}
		else {
			for(int i=0;i<bucket.size();i++) {
				if(bucket.getAt(i).equals(pair)) {
					V val = bucket.getAt(i).value;
					bucket.removeAt(i);
					this.size--;
					return val;
				}
			}
		}
		return null;
	}
	
	
	public void display() throws Exception {
		for (int i = 0; i < bucketArray.length; i++) {
			if (bucketArray[i] != null && !bucketArray[i].isEmpty()) {
				bucketArray[i].display();
			} else
				System.out.println("NULL");
			System.out.println("~~~~~~~~~~~~~~~~");
		}
	}
	
	

	public static void main(String[] args) throws Exception {
		HashMap<String,Integer> map = new HashMap<String,Integer>(4);
		map.put("India", 300);
		map.put("China", 350);
		map.put("Ukraine", 100);
		map.display();
		map.put("Aus", 250);
		System.out.println("After Rehashing: ");
		map.display();
	}

	public boolean containsKey(char c) {
		// TODO Auto-generated method stub
		return false;
	}

}
