package tech.idle.dao

import com.alibaba.fastjson.JSONObject
import tech.idle.annotation.PassPageAop

interface LoginDao {

    fun login(jsonData: JSONObject): JSONObject?

    @PassPageAop
    fun hasUsername(username: String): Int

    fun register(jsonData: JSONObject): Int
}