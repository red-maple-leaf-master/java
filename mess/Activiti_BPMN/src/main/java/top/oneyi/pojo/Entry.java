package top.oneyi.pojo;

import lombok.Data;

@Data
public class Entry<K,V> {
    public K key;
    public V value;
}
