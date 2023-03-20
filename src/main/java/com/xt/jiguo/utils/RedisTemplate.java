package com.xt.jiguo.utils;

import lombok.extern.log4j.Log4j;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;

/**
 * Redis工具类：提供redis操作
 *
 * @author 杨卫兵
 * @version V1.00 [2022/8/11 09:31]
 * @since V1.00
 */
@Log4j
@Component
public class RedisTemplate {
//    private static Logger log = Logger.getRootLogger();

    public Set<String> keys(String pattern) {
        Jedis jedis = RedisUtil.getJedis();

        try {
            return jedis.keys(pattern);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return null;
        } finally {
            jedis.close();
        }

    }

    public String get(String key) {
        Jedis jedis = RedisUtil.getJedis();

        try {
            return jedis.get(key);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return null;
        } finally {
            jedis.close();
        }

    }


    public String set(String key, String value) {
        Jedis jedis = RedisUtil.getJedis();

        try {
            return jedis.set(key, value);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return null;
        } finally {
            jedis.close();
        }

    }


    public boolean exists(String key) {
        Jedis jedis = RedisUtil.getJedis();

        try {
            return jedis.exists(key);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return false;
        } finally {
            jedis.close();
        }

    }

    public Long del(String... keys) {
        Jedis jedis = RedisUtil.getJedis();

        try {
            return jedis.del(keys);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return null;
        } finally {
            jedis.close();
        }

    }

    public String set(String key, Object value) {
        Jedis jedis = RedisUtil.getJedis();

        try {
            return jedis.set(key.getBytes(), serialize(value));
        } catch (Exception e) {
            log.warn(e.getMessage());
            return null;
        } finally {
            jedis.close();
        }

    }

    public Object get(byte[] key) {
        Jedis jedis = RedisUtil.getJedis();

        try {
            return deserialize(jedis.get(key));
        } catch (Exception e) {
            log.warn(e.getMessage());
            return null;
        } finally {
            jedis.close();
        }

    }

    private byte[] serialize(Object value) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(value);
            return baos.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
            log.warn(ex.getMessage());
        } finally {
            try {
                oos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                baos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private Object deserialize(byte[] bts) {
        ObjectInputStream ois = null;
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(bts);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception ex) {
            log.warn(ex.getMessage());
        } finally {
            try {
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                bais.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
