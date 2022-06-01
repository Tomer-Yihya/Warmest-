import java.util.HashMap;


public class Warmest<K,V> {

	// pointers to the hot key and hot key value
	private K hotKey;
	private V hotKeyVal;
	private HashMap<K,V> keysMap = new HashMap<K,V>();
	private WarmestHistoryList<K,V> hotKeysHistoryList = new WarmestHistoryList<K,V>();
	
	
 	public Warmest() {
		this.hotKey = null;
		this.hotKeyVal = null;
	}
		
	public void put(K key, V value) {
		if(key == null || value == null)
			return;
		
		// delete the key if it exists
		keysMap.remove(key);
		hotKeysHistoryList.remove(key);
		
		// insert the key
		this.keysMap.put(key, value);
		this.hotKeysHistoryList.insert(key,value);
		
		updateHotKey();	
	}
	
	public V get(K k) {
		if(k == null)
			return null;
		
		V val = this.keysMap.get(k);
		if(val == null)
			return null;
		this.hotKeysHistoryList.remove(k);
		this.hotKeysHistoryList.insert(k, val);
		updateHotKey();
		return this.hotKeyVal;
	}
		
	public V remove(K k) {
		if(k == null)
			return null;
		
		V val = this.keysMap.get(k);
		if(val == null)
			return null;
		
		this.get(k);
		
		// if the removing key is the hot key, update hot key
		if(k.equals(this.hotKey)) {
			updateHotKey();
			this.keysMap.remove(k);
			this.hotKeysHistoryList.remove(k);
			if(this.keysMap.size() != 0) {
				setHotKey(this.hotKeysHistoryList.getLast().getKey());
				setHotKeyVal(this.hotKeysHistoryList.getLast().getVal());
			}
			else {
				setHotKey(null);
				setHotKeyVal(null);
			}
		}
		updateHotKey();
		return val;
	}
	
	public V getWarmest() {
		if(this.keysMap.size() == 0)
			return null;
		K key = this.hotKey;
		V val = this.hotKeyVal;
		if(key == null || val == null)
			return null;
		remove(key);
		put(key, val);

		return this.get(hotKey);
	}
		
	private void updateHotKey() {
		if(this.keysMap.size() == 0) {
			this.hotKey = null;
			this.hotKeyVal = null;
			return;
		}
		
		this.hotKey = this.hotKeysHistoryList.getLast().getKey();
		this.hotKeyVal = this.hotKeysHistoryList.getLast().getVal();
	}
		
	public K getHotKey() {
		return this.hotKey;
	}
	
	public void setHotKey(K key) {
		this.hotKey = key;
	}
	
	public V getHotKeyVal() {
		return this.hotKeyVal;
	}
	
	public void setHotKeyVal(V val) {
		this.hotKeyVal = val;
	}
	
}
