package tech.idle.result

import com.alibaba.fastjson.JSONObject
import java.io.Serializable

class Result constructor() : Serializable {
    var data: Any? = null
    var code: Int = Code.SUCCESS
    var msg: String = "success"

    constructor(code: Int) : this() {
        this.code = code
    }

    constructor(code: Int, data: Any?) : this() {
        this.code = code
        this.data = data
    }

    constructor(code: Int, msg: String) : this() {
        this.code = code
        this.msg = msg
    }

    constructor(code: Int, msg: String, data: Any?) : this(code, msg) {
        this.data = data
    }


    override fun toString(): String {
        return JSONObject.toJSONString(this)
    }

    companion object {

        fun page(data: Any?, total: Int?):Result{
            val result = JSONObject()
            result["items"] = data
            result["total"] = total
            return Result(Code.SUCCESS,result)
        }

        fun success(): Result {
            return Result(Code.SUCCESS)
        }

        fun info(msg: String): Result {
            return Result(Code.INFO, msg)
        }

        fun success(msg: String, data: Any): Result {
            return Result(Code.SUCCESS, msg, data)
        }

        fun success(msg: String): Result {
            return Result(Code.SUCCESS, msg)
        }

        fun success(data: Any?): Result {
            return Result(Code.SUCCESS, data)
        }

        fun error(): Result {
            return Result(Code.ERROR,"error")
        }

        fun error(msg: String, data: Any?): Result {
            return Result(Code.ERROR, msg, data)
        }

        fun error(msg: String): Result {
            return Result(Code.ERROR, msg)
        }

        fun error(data: Any): Result {
            return Result(Code.ERROR, data)
        }
    }


}