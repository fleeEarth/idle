package tech.idle.quartz

import org.quartz.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import tech.idle.quartz.task.ClearTempFileTask
import java.util.*

@Configuration
class ScheduledConfiguration {

    /**
     * 临时文件定时清理任务
     */
    @Bean
    fun clearTempFile(): JobDetail {
        return JobBuilder.newJob(ClearTempFileTask::class.java) //业务类
            .withIdentity("clearTempFile") //可以给该JobDetail起一个id
            //每个JobDetail内都有一个Map，包含了关联到这个Job的数据，在Job类中可以通过context获取
            .usingJobData("msg", "clearTempFile-task") //关联键值对
            .storeDurably() //即使没有Trigger关联时，也不需要删除该JobDetail
            .build()
    }

    fun clearTempFileTrigger(): Trigger {
        //设置时间 每24小时清理一次临时文件
        val simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().repeatForever().withIntervalInHours(24)
        return TriggerBuilder.newTrigger()
            .forJob(clearTempFile()) //关联上述的JobDetail
            .withIdentity("clearTempFileTrigger") //给Trigger起个名字
            .withSchedule(simpleScheduleBuilder) //启动后延迟30分钟执行按需调整
            .startAt(Date(System.currentTimeMillis() + 1800000))
            .build()
    }

}