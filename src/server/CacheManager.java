package server;

import java.io.IOException;
import java.util.ArrayList;

public interface CacheManager {
    public void save(Integer key, ArrayList<String> solution);
    public ArrayList<String> load(Integer key) ;
}
