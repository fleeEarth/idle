package tech.idle.utils

import java.net.URLEncoder
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
}