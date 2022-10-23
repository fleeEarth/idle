package tech.idle.utils

import tech.idle.constant.CommonConstant
import java.net.URLEncoder
import java.util.regex.Pattern
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

    /**
     * 密码复杂度校验
     */
    fun validatePasswd(newPassword: String?): Boolean {
        if (newPassword == null || newPassword.length < 8 || newPassword.length > 18) {
            return false
        }
        val p = Pattern.compile("^(?![a-z\\d\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z\\d\\W]+$)[a-zA-Z\\d\\W]{6,18}$")
        val m = p.matcher(newPassword)
        return m.find()
    }

    /**
     * 手机号码校验
     */
    fun validatePhoneNumber(phoneNumber: String?): Boolean {
        return if (phoneNumber != null && phoneNumber.isNotEmpty()) {
            Pattern.matches("^1[3-9]\\d{9}$", phoneNumber)
        } else false
    }


    fun getTokenFromRequest(request: HttpServletRequest): String? {
        return try {
            request.getHeader(CommonConstant.TOKEN_NAME)
        } catch (e: Exception) {
            null
        }

    }

}