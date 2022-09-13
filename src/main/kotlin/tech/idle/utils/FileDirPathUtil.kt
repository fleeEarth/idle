package tech.idle.utils

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.File

/**
 * 文件夹路径获取
 */
@Component
class FileDirPathUtil {
    /**
     * 文件夹顶级目录
     */
    @Value("\${props.project.rootFilePath}")
    var rootFilePath: String = ""

    /**
     * 临时文件夹目录
     */
    @Value("\${props.project.temp}")
    var tempFileDir: String = ""

    /**
     * 获取临时文件夹
     */
    fun getTempFileDir(): File {
        return File(rootFilePath, tempFileDir)
    }

    fun makeDirs(filePath: String) {
        File(rootFilePath, filePath).mkdirs()
    }

}