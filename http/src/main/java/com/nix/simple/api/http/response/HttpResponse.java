package com.nix.simple.api.http.response;

import com.nix.simple.api.http.session.Session;

/**
 * @author Kiss
 * @date 2018/08/26 20:56
 */
public class HttpResponse implements Response{

    @Override
    public byte[] httpContentBytes() {
        return new byte[0];
    }

    @Override
    public void setSession(Session session) {

    }
}
