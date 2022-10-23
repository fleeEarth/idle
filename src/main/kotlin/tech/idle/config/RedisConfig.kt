package tech.idle.config

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.*
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
@EnableCaching
class RedisConfig : CachingConfigurerSupport() {
    /**
     * retemplate相关配置
     * @param factory
     * @return
     */
    @Bean
    fun redisTemplate(factory: RedisConnectionFactory?): RedisTemplate<String, Any>? {
        val template = RedisTemplate<String, Any>()
        // 配置连接工厂
        template.setConnectionFactory(factory!!)

        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        val jacksonSeial: Jackson2JsonRedisSerializer<*> = Jackson2JsonRedisSerializer(Any::class.java)
        val om = ObjectMapper()
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL)
        jacksonSeial.setObjectMapper(om)

        // 值采用json序列化
        template.valueSerializer = jacksonSeial
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.keySerializer = StringRedisSerializer()

        // 设置hash key 和value序列化模式
        template.hashKeySerializer = StringRedisSerializer()
        template.hashValueSerializer = jacksonSeial
        template.afterPropertiesSet()
        return template
    }

    /**
     * 对hash类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    fun hashOperations(redisTemplate: RedisTemplate<String?, Any?>): HashOperations<String?, String?, Any?>? {
        return redisTemplate.opsForHash()
    }

    /**
     * 对redis字符串类型数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    fun valueOperations(redisTemplate: RedisTemplate<String?, Any?>): ValueOperations<String?, Any?>? {
        return redisTemplate.opsForValue()
    }

    /**
     * 对链表类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    fun listOperations(redisTemplate: RedisTemplate<String?, Any?>): ListOperations<String?, Any?>? {
        return redisTemplate.opsForList()
    }

    /**
     * 对无序集合类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    fun setOperations(redisTemplate: RedisTemplate<String?, Any?>): SetOperations<String?, Any?>? {
        return redisTemplate.opsForSet()
    }

    /**
     * 对有序集合类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    fun zSetOperations(redisTemplate: RedisTemplate<String?, Any?>): ZSetOperations<String?, Any?>? {
        return redisTemplate.opsForZSet()
    }
}