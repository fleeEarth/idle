package tech.idle.result

object Code {
    /**
     * * 调用成功
     */
    @JvmStatic
    val SUCCESS:Int = 200

    /**
     * 调用成功并显示提示信息
     */
    @JvmStatic
    val INFO:Int = 201


    /**
     * * 调用失败
     */
    @JvmStatic
    val ERROR:Int = 500

    /**
     * * 会话已失效
     */
    @JvmStatic
    val TIMEOUT:Int = 401

    /**
     * 无访问权限
     */
    @JvmStatic
    val NOACCESS:Int = 402

    /**
     * * 找不到服务
     */
    @JvmStatic
    val NOTFOUND:Int = 404


}