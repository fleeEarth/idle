package tech.idle.controller

import com.alibaba.fastjson.JSONObject
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import tech.idle.annotation.PassEncrypt
import tech.idle.annotation.PassLogin
import tech.idle.constant.CommonConstant
import tech.idle.entity.UserInfo
import tech.idle.result.Result
import tech.idle.service.LoginService
import tech.idle.utils.BinaryConversionUtil
import tech.idle.utils.CommonUtil
import tech.idle.utils.SessionUtil
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/loginService")
class LoginController {


    @Autowired
    lateinit var loginService: LoginService

    @PassLogin
    @RequestMapping(value = ["/login"], method = [RequestMethod.POST])
    fun login(@RequestBody jsonData: JSONObject): Result {
        return loginService.login(jsonData)
    }

    @PassLogin
    @PassEncrypt
    @RequestMapping(value = ["/getIndexInfo"], method = [RequestMethod.POST])
    fun getIndexInfo(request: HttpServletRequest): Result {
        var token = CommonUtil.getTokenFromRequest(request)
        if(StringUtils.isBlank(token)){
            token = SessionUtil.getTokenId()
        }
        val userInfo = UserInfo()
        userInfo.publicKey = BinaryConversionUtil.getSixteenBitString()
        SessionUtil.refTimeAndUserInfo(token!!,userInfo)
        val info = JSONObject()
        info[CommonConstant.TOKEN_NAME] = token
        info[CommonConstant.PUBLIC_KEY] = userInfo.publicKey
        return Result.success(info)
    }

    /**
     * 用户注册
     */
    @PassLogin
    @RequestMapping(value = ["/register"], method = [RequestMethod.POST])
    fun register(@RequestBody jsonData: JSONObject): Result {
        return loginService.register(jsonData)
    }



}