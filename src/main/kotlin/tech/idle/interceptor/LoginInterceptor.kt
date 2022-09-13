package tech.idle.interceptor

import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import tech.idle.result.Code
import tech.idle.result.Result
import tech.idle.utils.HttpClientUtil
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class LoginInterceptor : HandlerInterceptor {


    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

//      如果不是映射到方法直接拒绝
        if (handler !is HandlerMethod) {
            response.contentType = HttpClientUtil.JSON
            response.writer.println(
                Result(
                    Code.NOTFOUND,
                    "Interface not found,please check your name."
                ).toString()
            )
            return false
        }
        return true

    }


}