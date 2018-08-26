package com.nix.simple.api.http.session;

import java.io.Serializable;

/**
 * @author Kiss
 * @date 2018/08/26 20:55
 */
public interface Session {
    /**
     * session put
     * @param key
     * @param object
     * @return true | false
     * */
    <O>void put(String key,O object);
    /**
     * session get
     * @param key
     * @return
     * */
    <O> O get(String key);

    String getId();
}
