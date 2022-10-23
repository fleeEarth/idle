package tech.idle.entity

import java.sql.Timestamp

class UserInfo {

    /**
     * 用户ID
     */
    var uid: String? = null

    /**
     * 用户名
     */
    var username: String? = null

    /**
     * 昵称
     */
    var nickname: String? = null

    /**
     * 联系方式
     */
    var phone: String? = null

    /**
     * 用户注册时间
     */
    var regTime: Timestamp? = null

    /**
     * 加密密钥
     */
    var publicKey: String? = null

    /**
     * 是否已经登录
     */
    var hasLogin: Boolean = false

    /**
     * 是否通过验证码校验
     */
    var hasCheckCaptcha: Boolean = false


}