package tech.idle.interceptor

import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import tech.idle.annotation.PassLogin
import tech.idle.constant.CommonConstant
import tech.idle.result.Code
import tech.idle.result.Result
import tech.idle.utils.SessionUtil
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class LoginInterceptor : HandlerInterceptor {


    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

//      如果不是映射到方法直接拒绝
        if (handler !is HandlerMethod) {
            response.contentType = "application/json;charset=UTF-8"
            response.writer.println(
                Result(
                    Code.NOTFOUND,
                    "Interface not found,please check your name."
                ).toString()
            )
            return false
        }
        val method = handler.method
        if (method.isAnnotationPresent(PassLogin::class.java)) {
            return true
        }
        val token = request.getHeader(CommonConstant.TOKEN_NAME)
        if(StringUtils.isBlank(token) || !SessionUtil.loginAuth(token)){
            response.contentType = "application/json;charset=UTF-8"
            response.writer.println(Result(Code.TIMEOUT,"会话超时，请重新登录！"))
            return false
        }
        return true

    }


}