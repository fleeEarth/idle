package tech.idle.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tech.idle.service.CommonService
import tech.idle.utils.FileDirPathUtil
import javax.servlet.http.HttpServletResponse

@Service
class CommonServiceImpl : CommonService {

    @Autowired
    lateinit var fileDirPathUtil: FileDirPathUtil

    @Autowired
    lateinit var response: HttpServletResponse

}