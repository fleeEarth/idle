package tech.idle.utils

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit


/**
 * redis工具类
 */

@Component
object RedisUtil {

    /**
     * redis中存储用户信息的hash
     */
    const val REDIS_USER_INFO_LIST = "IDLE_USER_INFO_LIST"

    /**
     * redis中存储的用户信息对应的超时hash
     */
    const val REDIS_USER_TIMEOUT_LIST = "IDLE_USER_TIMEOUT"


    private var redisTemplate: RedisTemplate<String, Any>? = null

    @Autowired
    fun setRedisTemplate(redisTemplate: RedisTemplate<String, Any>) {
        this.redisTemplate = redisTemplate
    }


    /**
     * 获取hash中的long对象
     * @param key
     * @param timeout
     * @return
     */
    fun getHashValueForLong(key: String, timeout: Any): Long {
        return redisTemplate!!.opsForHash<Any, Any>()[key, timeout!!] as Long
    }

    /**
     * 获取hash的所有对象
     * @param key
     * @return
     */
    fun getHashMap(key: String): kotlin.collections.Map<Any, Any> {
        return redisTemplate!!.opsForHash<Any, Any>().entries(key)
    }


    /**
     * 获取hash中的对象
     * @param key
     * @param hashKey
     * @return
     */
    fun getHashValue(key: String, hashKey: Any?): Any? {
        return redisTemplate!!.opsForHash<Any, Any>()[key, hashKey!!]
    }


    /**
     * 判断hash中是否存在该key
     * @param key
     * @param hashKey
     * @return
     */
    fun hashHasKey(key: String, hashKey: Any?): Boolean {
        return redisTemplate!!.opsForHash<Any, Any>().hasKey(key, hashKey!!)
    }

    /**
     * 向指定hash中添加key,value
     * @param key
     * @param hashKey
     * @param value
     */
    fun putHashValue(key: String, hashKey: Any, value: Any) {
        redisTemplate!!.opsForHash<Any, Any>().put(key, hashKey, value)
    }


    /**
     * 向指定hash中存入整个map
     * @param key
     * @param map
     */
    fun putHashMap(key: String, map: kotlin.collections.Map<*, *>?) {
        redisTemplate!!.opsForHash<Any, Any>().putAll(key, map!!)
    }

    /**
     * 删除整个hash
     * @param key
     */
    fun delHash(key: String) {
        redisTemplate!!.delete(key)
    }

    /**
     * 删除指定hash中的key
     * @param key
     * @param hashKey
     */
    fun delHashKey(key: String, hashKey: Any?) {
        redisTemplate!!.opsForHash<Any, Any>().delete(key, hashKey)
    }

    /**
     * 获取key的到期时间
     * @param key
     * @return
     */
    fun getExpire(key: String): Long {
        return redisTemplate!!.getExpire(key, TimeUnit.SECONDS)
    }

    /**
     * 设置key的到期时间
     * @param key
     * @param time
     * @return
     */
    fun expire(key: String, time: Long?): Boolean {
        return redisTemplate!!.expire(key, time!!, TimeUnit.SECONDS)
    }


}
