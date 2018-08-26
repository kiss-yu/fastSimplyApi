package com.nix.simple.api.http.request;

import com.nix.simple.api.http.RequestHeader;
import com.nix.simple.api.http.RequestParams;
import com.nix.simple.api.http.session.Session;
import com.nix.simple.api.http.session.SessionStory;

/**
 * @author Kiss
 * @date 2018/08/26 20:56
 */
public class HttpRequest implements Request {
    private RequestParams params = null;
    private RequestHeader header = null;
    private Session session = null;

    @Override
    public Session getSession(boolean isCreate) {
        if (isCreate) {
            if (session == null) {
                return SessionStory.createSession();
            }
        }
        return session;
    }
    public static Request createRequest(byte[] httpContent) {
        return new HttpRequest();
    }
}
