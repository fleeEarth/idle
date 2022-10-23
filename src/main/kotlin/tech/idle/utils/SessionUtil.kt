package tech.idle.utils

import com.alibaba.fastjson.JSONObject
import tech.idle.constant.CommonConstant
import tech.idle.entity.UserInfo
import java.util.*

object SessionUtil {

    /**
     * 登录校验
     */
    fun loginAuth(token: String): Boolean {
        if (isTimeout(token)) {
            return false
        }
        val userInfo = getUserInfo(token)
        if (userInfo == null || !userInfo.hasLogin) {
            return false
        }
        //刷新超时时间
        refTokenTimeout(token)
        return true
    }

    /**
     * 获取用户信息
     */
    fun getUserInfo(token: String): UserInfo? {
        return try {
            RedisUtil.getHashValue(RedisUtil.REDIS_USER_INFO_LIST, token) as UserInfo
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }


    /**
     * 刷新token超时和用户信息
     */
    fun refTimeAndUserInfo(token: String, userInfo: UserInfo) {
        refTokenTimeout(token)
        refUserInfo(token, userInfo)
    }

    /**
     * 校验token是否超时
     */
    fun isTimeout(token: String): Boolean {
        if (getTokenTimeout(token) > System.currentTimeMillis()) {
            return false
        }
        return true
    }

    /**
     * 生产token
     */
    fun getTokenId(): String {
        return UUID.randomUUID().toString().replace("-".toRegex(), "")
    }


    /**
     * 刷新token超时时间
     */
    private fun refTokenTimeout(token: String) {
        RedisUtil.putHashValue(
            RedisUtil.REDIS_USER_TIMEOUT_LIST, token,
            System.currentTimeMillis() + CommonConstant.TOKEN_TIME_OUT
        )
    }

    /**
     * 刷新用户信息
     */
    private fun refUserInfo(token: String, userInfo: UserInfo) {
        RedisUtil.putHashValue(
            RedisUtil.REDIS_USER_INFO_LIST, token,
            userInfo
        )
    }


    /**
     * 获取超时时间
     */
    private fun getTokenTimeout(token: String): Long {
        return try {
            RedisUtil.getHashValue(RedisUtil.REDIS_USER_TIMEOUT_LIST, token) as Long
        } catch (e: Exception) {
            0L
        }
    }


}