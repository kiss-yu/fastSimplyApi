package com.nix.fast.simple.api.pipeline.encode;

import com.nix.fast.simple.api.context.Context;
import com.nix.fast.simple.api.pipeline.processor.Processor;

/**
 * @author Kiss
 * @date 2018/08/23 11:13
 * 编码器
 */
public interface Encoding extends Processor {
    Context decoding();

}
