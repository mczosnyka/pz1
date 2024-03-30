package pz1.michalczosnyka;
import java.util.ArrayList;
import java.util.List;
public class MyMap<K,V> implements Map<K,V> {

    private List<K> keyList;
    private List<V> valueList;

    public MyMap() {
        this.keyList = new ArrayList<>();
        this.valueList = new ArrayList<>();
    }

    @Override
    public boolean put(K key, V value){
        if(key==null){
            return false;
        }
        if(contains(key)){
            valueList.set(keyList.indexOf(key), value);
        }
        else{
            keyList.add(key);
            valueList.add(value);
        }
        return true;
    };
    @Override
    public boolean remove(K key){
        if(!contains(key)){
            return false;
        }
        else{
            int index = keyList.indexOf(key);
            valueList.remove(index);
            keyList.remove(index);
            return true;
        }
    };
    @Override
    public V get(K key){
        if(contains(key)){
            int index = keyList.indexOf(key);
            return valueList.get(index);
        }
        else{
            return null;
        }
    };
    @Override
    public List<K> keys(){
        return keyList;
    };
    @Override
    public List<V> values(){
        return valueList;
    };
    @Override
    public boolean contains(K key) {
        return(keyList.contains(key));
    }
}
