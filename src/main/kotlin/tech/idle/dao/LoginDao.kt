package tech.idle.dao

import com.alibaba.fastjson.JSONObject

interface LoginDao {

    fun login(jsonData: JSONObject): JSONObject?

}