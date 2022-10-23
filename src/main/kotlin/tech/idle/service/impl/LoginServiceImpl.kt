package tech.idle.service.impl

import com.alibaba.fastjson.JSONObject
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import tech.idle.dao.LoginDao
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
        if (!cacheUserInfo.hasCheckCaptcha) {
            return Result.error("请先完成校验！")
        }
        val password = jsonData.getString("password")
        val username = jsonData.getString("username")
        if(StringUtils.isBlank(password) || StringUtils.isBlank(username)){
            return Result.error("用户名和密码不能为空！")
        }
        jsonData["password"] = MD5Util.MD5_WITH_SALT(password)
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

    @Transactional
    override fun register(jsonData: JSONObject): Result {
        val username = jsonData.getString("username")
        val password = jsonData.getString("password")
        val phone = jsonData.getString("phone")
        val code = jsonData.getString("code")
        if ("PosZ9x!02d235*2$@%" != code) {
            return Result.error("邀请码错误，请联系管理员！")
        }
        //因为没有短信注册机，采用邀请码的方式
        if (StringUtils.isBlank(username)) {
            return Result.error("用户名不能为空！")
        }
        val hasUsername = loginDao.hasUsername(username)
        if (hasUsername > 0) {
            return Result.error("用户名已存在！")
        }
        if (StringUtils.isBlank(password)) {
            return Result.error("密码不能为空！")
        }
        if (!CommonUtil.validatePasswd(password)) {
            return Result.error("密码强度不符合规则！")
        }
        if (StringUtils.isBlank(phone)) {
            return Result.error("手机号码不能为空！")
        }
        if (!CommonUtil.validatePhoneNumber(phone)) {
            return Result.error("手机号码不符合规则！")
        }
        jsonData["password"] = MD5Util.MD5_WITH_SALT(password)
        jsonData["uid"] = SessionUtil.getTokenId()
        val i = loginDao.register(jsonData)
        if(i>0){
            return Result.info("注册成功！")
        }
        return Result.error("注册失败！")
    }
}