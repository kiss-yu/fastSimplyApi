package com.nix.simple.api.http.request;

import com.nix.simple.api.http.session.Session;

/**
 * @author Kiss
 * @date 2018/08/26 20:55
 */
public interface Request {
    Session getSession(boolean isCreate);
}
