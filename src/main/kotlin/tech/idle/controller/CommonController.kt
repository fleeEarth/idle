package tech.idle.controller

import com.alibaba.fastjson.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import tech.idle.annotation.PassLogin
import tech.idle.result.Result
import tech.idle.service.CommonService

@RequestMapping("/commonService")
@RestController
class CommonController {

    @Autowired
    lateinit var commonService: CommonService


}