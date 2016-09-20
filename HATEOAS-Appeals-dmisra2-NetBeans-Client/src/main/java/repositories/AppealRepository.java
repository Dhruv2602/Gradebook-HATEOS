package repositories;

import java.util.HashMap;
import java.util.Map;
import model.Appeal;
import model.Identifier;

public class AppealRepository
{
    private static final AppealRepository theRepository = new AppealRepository();
    private HashMap<String, Appeal> backingStore = new HashMap<>(); 
    
    public static AppealRepository current() {
        return theRepository;
    }
    
    public Appeal get(Identifier identifier) {
        return backingStore.get(identifier.toString());
     }

    public Identifier store(Appeal appeal) {
        Identifier id = new Identifier();
                
        backingStore.put(id.toString(), appeal);
        return id;
    }
    
    public void store(Identifier identifier, Appeal appeal) {
        backingStore.put(identifier.toString(), appeal);
    }

    public boolean has(Identifier identifier) {
        boolean result =  backingStore.containsKey(identifier.toString());
        
        return result;
    }

    public void remove(Identifier identifier) {
        backingStore.remove(identifier.toString());
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Appeal> entry : backingStore.entrySet()) {
            sb.append(entry.getKey());
            sb.append("\t:\t");
            sb.append(entry.getValue());
            sb.append("\n");
        }
        return sb.toString();
    }

    public synchronized void clear() {
        backingStore = new HashMap<>();
    }

    public int size() {
        return backingStore.size();
    }
}
