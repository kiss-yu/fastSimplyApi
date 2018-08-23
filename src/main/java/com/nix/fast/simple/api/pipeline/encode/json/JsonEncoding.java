package com.nix.fast.simple.api.pipeline.encode.json;

import com.nix.fast.simple.api.context.Context;
import com.nix.fast.simple.api.pipeline.encode.Encoding;

/**
 * @author Kiss
 * @date 2018/08/23 11:19
 */
public class JsonEncoding implements Encoding {
    /**
     * 上下文处理
     *
     * @param context
     * @return
     */
    @Override
    public boolean process(Context context) {
        return false;
    }

    @Override
    public Context decoding() {
        return null;
    }
}
