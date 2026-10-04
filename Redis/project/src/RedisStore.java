import java.util.*;
public class RedisStore {
    private HashMap<String, String> store = new HashMap<>();
    private HashMap<String, Long> expiry = new HashMap<>();
    public void set(String key, String value) {
        store.put(key, value);
    }
    public String get(String key) {
        return store.get(key);
    }
    public void delete(String key) {
        store.remove(key);
    }
    public boolean exists(String key) {
        if (isExpired(key)) {
            removeExpired(key);
            return false;
        }
        return store.containsKey(key);
    }
    public void expire(String key, long seconds) {
        long expiryTime = System.currentTimeMillis() + (seconds * 1000L);//convert seconds to msecs and The L means the number is treated as a long, which matches currentTimeMillis()
        //Take the current time and add the requested number of seconds. The result is the exact timestamp when this key should expire
        expiry.put(key, expiryTime);
    }
    public boolean isExpired(String key) {
        Long expiryTime = expiry.get(key);
        if (expiryTime == null) {
            return false;
        }
        return System.currentTimeMillis() >= expiryTime;
    }
    public void removeExpired(String key) {
        store.remove(key);
        expiry.remove(key);
    }
    public long getTTL(String key) {
        if (!store.containsKey(key)) {
            return -2;//key doesn't exist
        }
        Long expiryTime = expiry.get(key);
        if (expiryTime == null) {
            return -1;//key exists but has no expiration
        }
        if (System.currentTimeMillis() >= expiryTime) {
            removeExpired(key);
            return -2;
        }
        return (expiryTime - System.currentTimeMillis()) / 1000;
    }
    public HashMap<String, String> getAllData() {
        return store;//if you need to save my data, here's the HashMap containing it.
    }
    public void loadData(String key, String value) {
        store.put(key, value);
    }
}
