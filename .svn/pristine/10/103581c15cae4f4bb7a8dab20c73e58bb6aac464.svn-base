package cn.proem.dagl.web.scheduled.task.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import cn.proem.dagl.web.scheduled.task.HttpTask;

public class HttpTaskImpl implements HttpTask {
    
    /**
     * 访问地址
     */
    private String url;
    
    /**
     * @Description: 设置任务地址
     * @param uri
     */
    public HttpTaskImpl(String url){
        this.url = url;
    }
    
    @Override
    public String execute() throws Exception {
        // 地址为空报错处理
        if(url == null){
            throw new Exception("空地址");
        }
        URI uri = new URI(this.url);
        SimpleClientHttpRequestFactory schr = new SimpleClientHttpRequestFactory();
        ClientHttpRequest chr = schr.createRequest(uri, HttpMethod.POST);
        //chr.getBody().write(param.getBytes());//body中设置请求参数
        ClientHttpResponse res = chr.execute();
        InputStreamReader isr = new InputStreamReader(res.getBody());
        BufferedReader br = new BufferedReader(isr);
        StringBuilder response = new StringBuilder();
        String str = "";
        while((str = br.readLine())!=null){
            response.append(str);
        }
        isr.close();
        return response.toString();
    }

}
