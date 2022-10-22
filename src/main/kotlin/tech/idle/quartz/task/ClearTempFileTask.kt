package tech.idle.quartz.task

import org.quartz.Job
import org.quartz.JobExecutionContext
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import tech.idle.utils.FileDirPathUtil

/**
 * 清理临时文件目录
 */
@Component
class ClearTempFileTask:Job {

    private val logger = LoggerFactory.getLogger(ClearTempFileTask::class.java)

    /**
     * 清理文件30天内未被修改文件
     */
    val failureTimeInterval:Long = 2592000000L

    override fun execute(p0: JobExecutionContext?) {
        try{

        }catch (e:Exception){
            System.err.println("文件清理任务异常！")
            logger.error(e.printStackTrace().toString())
            e.printStackTrace()
        }

    }
}