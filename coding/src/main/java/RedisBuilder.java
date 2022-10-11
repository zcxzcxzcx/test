public class RedisBuilder {
    private String hostname;
    private String password;
    private int port;
    private volatile static RedisBuilder redisBuilder;

    public static RedisBuilder builder(){
        return new RedisBuilder();
    }

    public RedisBuilder setHostname(String hostname){
        this.hostname=hostname;
        return this;
    }

    public RedisBuilder setPassword(String password){
        this.password=password;
        return this;
    }

    public RedisBuilder setPort(int port){
        this.port=port;
        return this;
    }

    public static RedisBuilder getInstance(String hostname,String password,int port){
        if(redisBuilder==null){
            synchronized (RedisBuilder.class){
                if(redisBuilder==null){
                    redisBuilder= RedisBuilder.builder()
                            .setHostname(hostname)
                            .setPassword(password)
                            .setPort(port);
                }
            }
        }
        return redisBuilder;
    }

}
