package springframework.services.map;

import springframework.model.BaseEntity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract  class AbstractMapService <T extends BaseEntity,ID extends Long>{

    protected Map<Long,T> map = new HashMap<>();

    protected Set<T> findAll(){
        return new HashSet<>(map.values());
    }

    protected T findById(ID id){
        return map.get(id);
    }

    protected T save(T t){
        if(t != null && t.getId() == null){
            t.setId(getId());
            map.put(t.getId(),t);
        } else if(t == null){
            throw new RuntimeException("object cannot be null");
        }
        map.put(t.getId(),t);
        return t;
    }

    protected void deleteById(ID id){
         map.remove(id);
    }

    protected void delete(T t){
        map.entrySet().removeIf(entry ->entry.getValue().equals(t));
    }

    private Long getId(){
        return map.size()+1l;
    }





}
