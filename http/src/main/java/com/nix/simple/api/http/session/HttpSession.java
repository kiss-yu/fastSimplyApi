package com.nix.simple.api.http.session;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Kiss
 * @date 2018/08/26 21:08
 */
public class HttpSession implements Session{
    private ConcurrentHashMap<String,Object> sessionMap = new ConcurrentHashMap<>(2);
    private final String id;

    public HttpSession(String id) {
        this.id = id;
    }

    @Override
    public void put(String key, Object object) {
        sessionMap.put(key,object);
    }

    @Override
    public Object get(String key) {
        return sessionMap.get(key);
    }

    @Override
    public String getId() {
        return id;
    }
}
