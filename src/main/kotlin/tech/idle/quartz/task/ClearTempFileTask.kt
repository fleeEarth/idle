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

    @Autowired
    lateinit var fileDirPathUtil: FileDirPathUtil

    override fun execute(p0: JobExecutionContext?) {
        try{
            println("---------正在清理临时文件----------")
            val tempFileDir = fileDirPathUtil.getTempFileDir()
            if(!tempFileDir.exists()){
                tempFileDir.mkdirs()
            }
            val files = tempFileDir.listFiles()
            val nowTime = System.currentTimeMillis()
            if(files != null && files.isNotEmpty()){
               for(i in files.indices){
                   val file = files[i]
                   if(file.exists()){
                       //获取文件最后修改时间
                       val lastModified = file.lastModified()
                       if(nowTime - lastModified > failureTimeInterval){
                           //已经超过30天未使用该临时文件进行删除操作
                           try{
                               file.delete()
                           }catch (e:Exception){
                               System.err.println("文件删除失败！")
                               e.printStackTrace()
                               logger.error(e.printStackTrace().toString())
                               continue
                           }
                       }
                   }
               }
            }
            println("---------临时文件清理结束----------")
        }catch (e:Exception){
            System.err.println("文件清理任务异常！")
            logger.error(e.printStackTrace().toString())
            e.printStackTrace()
        }

    }
}