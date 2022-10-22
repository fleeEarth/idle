package tech.idle.utils

import tech.idle.constant.CommonConstant
import java.security.MessageDigest

object MD5Util {

    fun MD5_WITH_SALT(str: String): String? {
        return MD5(str + CommonConstant.PASSWORD_SLAT)
    }

    fun MD5(str: String): String? {
        return try {
            val md5 = MessageDigest.getInstance("MD5")
            val charArray = str.toCharArray()
            val byteArray = ByteArray(charArray.size)
            for (i in charArray.indices) {
                byteArray[i] = charArray[i].code.toByte()
            }
            val md5Bytes = md5.digest(byteArray)
            val hexValue = StringBuffer()
            for (i in md5Bytes.indices) {
                val `val` = md5Bytes[i].toInt() and 0xff
                if (`val` < 16) hexValue.append("0")
                hexValue.append(Integer.toHexString(`val`))
            }
            hexValue.toString()
        } catch (e: Exception) {
            null
        }
    }
}