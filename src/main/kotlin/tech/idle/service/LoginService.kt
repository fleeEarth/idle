package tech.idle.service

import com.alibaba.fastjson.JSONObject
import tech.idle.result.Result

interface LoginService {
    fun login(jsonData: JSONObject): Result
}