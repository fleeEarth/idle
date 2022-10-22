package tech.idle.constant

object CommonConstant {
    /**
     * 请求token名称
     */
    @JvmStatic
    val TOKEN_NAME: String = "token"

    /**
     * 密钥名称
     */
    @JvmStatic
    val PUBLIC_KEY: String = "publicKey"

    /**
     * 默认token失效时间
     */
    @JvmStatic
    val TOKEN_TIME_OUT: Long = 1000 * 60 * 30L

    @JvmStatic
    val PASSWORD_SLAT: String = "$2x@0uZ^dfsj*8521Z"


    /**
     * 是否开启数据加密
     */
    const val OPEN_ENCRYPT: Boolean = false
}