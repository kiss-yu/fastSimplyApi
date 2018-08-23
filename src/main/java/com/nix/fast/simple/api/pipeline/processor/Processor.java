package com.nix.fast.simple.api.pipeline.processor;

import com.nix.fast.simple.api.context.Context;

/**
 * @author Kiss
 * @date 2018/08/23 10:44
 * Pipeline流动中的处理器
 */
public interface Processor {

    /**
     * 上下文处理
     * @param context
     * @return
     * */
    boolean process(Context context);
}
