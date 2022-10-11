import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;

public class Cache {
    private final JedisPool jedisPool;
    private volatile static Cache cache;

    public Cache(JedisPool jedisPool){
        this.jedisPool=jedisPool;
    }

    public static Cache getInstance(Map<String,String> config){
        if(cache==null){
            synchronized (Builder.class){
                if(cache==null){
                    cache= Cache.builder()
                            .setConfig(config)
                            .build();
                }
            }
        }
        return cache;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private JedisPool jedisPool;

        public Builder setConfig(Map<String,String> config){
            String hostname=config.get("hostname");
            String password=config.get("hostname");
            String port=config.get("port");
            if(config==null){
                return this;
            }
            this.jedisPool=new JedisPool(new JedisPoolConfig(),hostname,Integer.parseInt(port),1000,password);
            return this;
        }

        public Cache build(){
            return new Cache(this.jedisPool);
        }
    }

}
