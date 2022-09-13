package tech.idle.aop

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpInputMessage
import java.io.InputStream

class EncryptHttpInputMessage : HttpInputMessage {

    private var headers: HttpHeaders? = null
    private var body: InputStream? = null

    constructor(headers: HttpHeaders, body: InputStream) {
        this.headers = headers
        this.body = body
    }

    override fun getHeaders(): HttpHeaders {
        return this.headers!!
    }

    override fun getBody(): InputStream {
        return this.body!!
    }
}