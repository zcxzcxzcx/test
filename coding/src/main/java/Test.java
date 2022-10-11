import java.util.HashMap;
import java.util.Map;

public class Test {
    private Cache instance;
    public  void test() {
        Map<String,String> config=new HashMap<String, String>();
        config.put("hostname","a");
        config.put("password","b");
        config.put("port","6379");
        instance = Cache.getInstance(config);
    }
}
