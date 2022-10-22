package tech.idle.service.impl

import com.alibaba.fastjson.JSONObject
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tech.idle.dao.LoginDao
import tech.idle.entity.UserInfo
import tech.idle.result.Code
import tech.idle.result.Result
import tech.idle.service.LoginService
import tech.idle.utils.CommonUtil
import tech.idle.utils.MD5Util
import tech.idle.utils.SessionUtil
import javax.servlet.http.HttpServletRequest

@Service
class LoginServiceImpl : LoginService {

    @Autowired
    lateinit var loginDao: LoginDao

    @Autowired
    lateinit var request: HttpServletRequest

    override fun login(jsonData: JSONObject): Result {
        val token = CommonUtil.getTokenFromRequest(request)
        if (StringUtils.isBlank(token) || SessionUtil.isTimeout(token!!)) {
            return Result(Code.TIMEOUT, "当前会话已失效，请刷新页面后重试！")
        }
        val cacheUserInfo = SessionUtil.getUserInfo(token)!!
        if(!cacheUserInfo.hasCheckCaptcha){
            return Result.error("请先完成校验！")
        }
        jsonData["password"] = MD5Util.MD5_WITH_SALT(jsonData.getString("password"))
        val userInfo = loginDao.login(jsonData)
        if (userInfo != null) {
            cacheUserInfo.hasLogin = true
            cacheUserInfo.hasCheckCaptcha = false
            cacheUserInfo.uid = jsonData.getString("uid")
            cacheUserInfo.username = jsonData.getString("username")
            cacheUserInfo.nickname = jsonData.getString("nickname")
            cacheUserInfo.phone = jsonData.getString("phone")
            cacheUserInfo.regTime = jsonData.getTimestamp("register_time")
            SessionUtil.refTimeAndUserInfo(token, cacheUserInfo)
            return Result.success("登录成功", userInfo)
        }
        return Result.error("用户名或密码错误！")
    }
}