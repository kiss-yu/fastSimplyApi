package com.nix.fast.simple.api.pipeline.encode.json;

import com.nix.fast.simple.api.context.Context;
import com.nix.fast.simple.api.pipeline.encode.Decoding;

/**
 * @author Kiss
 * @date 2018/08/23 11:20
 */
public class JsonDecoding implements Decoding {
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
}
