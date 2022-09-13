package tech.idle.utils

import com.alibaba.fastjson.JSONObject
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import tech.idle.result.Code
import java.io.IOException
import java.util.concurrent.TimeUnit

object HttpClientUtil {

    /**
     * 连接超时时间秒
     */
    @JvmStatic
    private val CONNECTION_TIMEOUT:Long = 90L

    @JvmStatic
    private var client:OkHttpClient = OkHttpClient()

    @JvmStatic
    val JSON:String = "application/json;charset=utf-8"

    init {
        client.newBuilder().connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
    }


    fun post(params: String, url: String, mediaType: String): JSONObject {
        return try {
            println("params:$params")
            val requestBody = params.toRequestBody(mediaType.toMediaType())
            val request:Request =  Request.Builder().url(url).post(requestBody).build()
            val response = client.newCall(request).execute()
            JSONObject.parseObject(response.body!!.string())
        } catch (e: IOException) {
            e.printStackTrace()
            val result = JSONObject()
            result["code"] = Code.ERROR
            result["msg"] = "Call interface failed"
            return result
        }
    }

    fun postJson(params: String, url: String): JSONObject {
        return try {
            println("params:$params")
            val requestBody = params.toRequestBody(JSON.toMediaType())
            val request: Request = Request.Builder().url(url).post(requestBody).build()
            val response = client.newCall(request).execute()
            JSONObject.parseObject(response.body!!.string())
        } catch (e: IOException) {
            e.printStackTrace()
            val result = JSONObject()
            result["code"] = Code.ERROR
            result["msg"] = "Call interface failed"
            result
        }
    }




    fun post(params: String, url: String, mediaType: String, headers: Map<String, String>): ResponseBody? {
        return try {
            val requestBody = params.toRequestBody(mediaType.toMediaType())
            val builder = Request.Builder()
            if (headers.isNotEmpty()) {
                val keys = headers.keys
                for (key in keys) {
                    builder.addHeader(key, headers.getValue(key))
                }
            }
            val request: Request = builder.post(requestBody).build()
            val response = client.newCall(request).execute()
            response.body
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }






}
