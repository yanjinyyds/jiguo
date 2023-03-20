package com.xt.jiguo.utils;

import lombok.extern.log4j.Log4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.log4j.Logger;
import redis.clients.jedis.*;
import redis.clients.jedis.util.Pool;

import java.io.InputStream;
import java.util.Properties;

/**
 * Redis工具类：提供redis连接池
 *
 * @author 杨卫兵
 * @version V1.00 [2022/8/11 08:36]
 * @since V1.00
 */
@Log4j
public class RedisUtil {
    public static String ConfigFileName = "redis.properties";
    //    private static JedisPool pool=null;
    private static Pool<Jedis> pool = null;
//    private static Logger log = Logger.getRootLogger();

    private static void init() {
        Properties props = new Properties();
        InputStream is = null;
        String host = "127.0.0.1";
        Integer port = 6379;
        Integer database = 0;
        String password = "";
        Integer timeout = 10000;
        try {
            is = RedisUtil.class.getClassLoader().getResourceAsStream(ConfigFileName);
            props.load(is);
            log.debug("Load Redis Config File " + ConfigFileName + " OK!");
            String value = null;

            try {
                value = props.getProperty("redis.timeout");
                timeout = Integer.parseInt(value);
                log.debug("redis.timeout=" + timeout);
            } catch (Exception ex) {
                log.debug("redis.timeout is not set,using default 10000.");
            }

            try {
                value = props.getProperty("redis.database");
                database = Integer.parseInt(value);
                log.debug("redis.database=" + database);
            } catch (Exception ex) {
                log.debug("redis.database is not set,using default 0.");
            }

            value = props.getProperty("redis.password");
            if (value != null) {
                password = value;
            }
            log.debug("redis.password=" + password);

            value = props.getProperty("redis.host");
            if (value != null) {
                host = value;
            }
            log.debug("redis.host=" + host);

            try {
                value = props.getProperty("redis.port");
                port = Integer.parseInt(value);
                log.debug("redis.port=" + port);
            } catch (Exception ex) {
                log.debug("redis.port is not set,using default 6379.");
            }

            GenericObjectPoolConfig<Jedis> poolConfig = new GenericObjectPoolConfig<>();
            try {
                value = props.getProperty("pool.maxActive");
                poolConfig.setMaxTotal(Integer.parseInt(value));
                log.debug("pool.maxActive=" + value);
            } catch (Exception ex) {
                poolConfig.setMaxTotal(5);
                log.debug("pool.maxActive is not set,using default 5.");
            }
            try {
                value = props.getProperty("pool.maxIdle");
                poolConfig.setMaxIdle(Integer.parseInt(value));
                log.debug("pool.maxIdle=" + value);
            } catch (Exception ex) {
                poolConfig.setMaxIdle(2);
                log.debug("pool.maxIdle is not set,using default 2.");
            }
            try {
                value = props.getProperty("pool.minIdle");
                poolConfig.setMinIdle(Integer.parseInt(value));
                log.debug("pool.minIdle=" + value);
            } catch (Exception ex) {
                poolConfig.setMinIdle(0);
                log.debug("pool.minIdle is not set,using default 0.");
            }
            pool = new JedisPool(poolConfig, host, port, timeout, password, database);

        } catch (Exception ex) {
            log.warn(ex.getMessage());
        } finally {
            try {
                is.close();
            } catch (Exception ex) {
            }
        }
    }


    public static Jedis getJedis() {
        if (pool == null) {
            init();
        }
        return pool.getResource();
    }
}
