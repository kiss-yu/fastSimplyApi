package com.nix.fast.simple.api.context;

import java.util.Map;

/**
 * @author Kiss
 * @date 2018/08/23 10:44
 * socket to api context
 */
public class Context {
    //请求客户端ip
    private int ip;
    //请求host
    private String host;
    //请求路径
    private String path;
    //http header
    private Header header;
    //请求内容
    private Map<String,Object> content;
}
