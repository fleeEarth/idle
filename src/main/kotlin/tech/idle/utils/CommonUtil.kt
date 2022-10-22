package tech.idle.utils

import tech.idle.constant.CommonConstant
import java.net.URLEncoder
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 公共工具类
 */
object CommonUtil {
    /**
     * 向请求头添加信息
     */
    fun addInfoToResponseHeader(response: HttpServletResponse, status: String, message: String) {
        response.setHeader(
            "status",
            status
        )
        response.setHeader("message", URLEncoder.encode(message, "UTF-8"))
    }


    fun getTokenFromRequest(request: HttpServletRequest): String? {
        return try {
            request.getHeader(CommonConstant.TOKEN_NAME)
        } catch (e: Exception) {
            null
        }

    }

}