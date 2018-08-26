package com.nix.simple.api.http.response;

import com.nix.simple.api.http.session.Session;

/**
 * @author Kiss
 * @date 2018/08/26 20:55
 */
public interface Response {
    byte[] httpContentBytes();
    void setSession(Session session);
}
