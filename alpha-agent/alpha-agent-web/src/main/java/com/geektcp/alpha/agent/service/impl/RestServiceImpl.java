package com.geektcp.alpha.agent.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geektcp.alpha.agent.service.RestService;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author tanghaiyang on 2018/5/16.
 */
@Component
public class RestServiceImpl implements RestService {

    private final RestTemplate restTemplate;

    @Autowired
    public RestServiceImpl() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public <Req, Resp> Resp doGet(String url, Req request, Class<Resp> responseType) throws Exception {
        URIBuilder builder = new URIBuilder(url);
        Map<String, Object> params = this.getParameters(request);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            builder.addParameter(entry.getKey(), Objects.toString(entry.getValue(), ""));
        }
        return restTemplate.getForObject(builder.build(), responseType);
    }

    @Override
    public <Resp> Resp doPost(String url, Class<Resp> responseType) throws Exception {
        return doPost(url, null, responseType, null);
    }

    @Override
    public <Req, Resp> Resp doPost(String url, Req request, Class<Resp> responseType) throws Exception {
        return doPost(url, request, responseType, null);
    }

    @Override
    public <Req, Resp> Resp doPost(String url, Req request, Class<Resp> responseType, Map<String, String>
            requestHeaders) throws Exception {
        // headers
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.setAcceptCharset(Collections.singletonList(Charset.forName("UTF-8")));
        if (!MapUtils.isEmpty(requestHeaders)) {
            for (Map.Entry<String, String> entry : requestHeaders.entrySet()) {
                httpHeaders.add(entry.getKey(), entry.getValue());
            }
        }
        // body
        String jsonBody;
        if (request instanceof String) {
            jsonBody = (String) request;
        } else {
            if (Objects.isNull(request)) {
                jsonBody = new ObjectMapper().writeValueAsString(new JSONObject());
            } else {
                jsonBody = new ObjectMapper().writeValueAsString(request);
            }
        }
        HttpEntity<String> httpEntity = new HttpEntity<>(jsonBody, httpHeaders);
        return restTemplate.postForObject(url, httpEntity, responseType);
    }

    @Override
    public <Req, Resp> Resp doPost(String url, Req request,ParameterizedTypeReference<Resp> respType) throws Exception {
        if (request == null) {
            return restTemplate.exchange(url, HttpMethod.POST, null, respType).getBody();
        }
        // headers
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.setAcceptCharset(Collections.singletonList(Charset.forName("UTF-8")));
        // body
        String jsonBody = new ObjectMapper().writeValueAsString(request);
        HttpEntity<String> httpEntity = new HttpEntity<>(jsonBody, httpHeaders);
        return restTemplate.exchange(url, HttpMethod.POST, httpEntity, respType).getBody();
    }

    @Override
    public <Req, Resp> Resp doPost(String url, Req request, ParameterizedTypeReference<Resp> respType, Map<String,String> headerParams) throws Exception {
        if (request == null) {
            return restTemplate.exchange(url, HttpMethod.POST, null, respType).getBody();
        }
        // headers
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.setAcceptCharset(Collections.singletonList(Charset.forName("UTF-8")));
        if (Objects.nonNull(headerParams) && (headerParams.size() > 0)){
            headerParams.forEach((param,value) -> httpHeaders.add(param,value));
        }

        // body
        String jsonBody = new ObjectMapper().writeValueAsString(request);
        HttpEntity<String> httpEntity = new HttpEntity<>(jsonBody, httpHeaders);
        return restTemplate.exchange(url, HttpMethod.POST, httpEntity, respType).getBody();
    }

    @Override
    public <Req, Resp> Resp doDelete(String url, Req request, Class<Resp> responseType) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        Map<String, Object> params = this.getParameters(request);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            headers.add(entry.getKey(), Objects.toString(entry.getValue(), ""));
        }
        ResponseEntity<Resp> exchange = restTemplate.exchange(url, HttpMethod.DELETE,
                new HttpEntity<>(headers), responseType);
        return exchange.getBody();
    }

    ///////////////////////
    // private functions
    ///////////////////////
    private <IN> Map<String, Object> getParameters(IN requestQo) {
        Map<String, Object> params;
        if (requestQo instanceof Map) {
            params = new HashMap<>((Map) requestQo);
        } else {
            params = toMap(requestQo);
        }
        return params;
    }

    /**
     *
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> Map<String, Object> toMap(T bean) {
        String text = JSON.toJSONString(bean);
        return JSON.parseObject(text, new TypeReference<Map<String, Object>>() {
        });
    }
}
