package com.nix.fast.simple.api.util;

import com.nix.fast.simple.api.context.Context;
import com.nix.simple.api.http.request.Request;
import com.nix.simple.api.http.response.HttpResponse;
import com.nix.simple.api.http.response.Response;

/**
 * @author Kiss
 * @date 2018/08/26 21:36
 */
public final class ContextUtil {
    public static Context request2Context(Request request) {
        return new Context();
    }
    public static Response cotext2Response(Context context) {
        return new HttpResponse();
    }
}
