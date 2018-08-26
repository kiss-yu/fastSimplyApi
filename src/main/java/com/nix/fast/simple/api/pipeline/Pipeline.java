package com.nix.fast.simple.api.pipeline;

import com.nix.fast.simple.api.context.Context;
import com.nix.fast.simple.api.pipeline.processor.Processor;
import com.nix.fast.simple.api.util.ContextUtil;
import com.nix.simple.api.http.request.Request;
import com.nix.simple.api.http.response.Response;

/**
 * @author Kiss
 * @date 2018/08/23 10:45
 */
public class Pipeline {
    class ProcessorLink{
        public ProcessorLink(Processor processor) {
            this.processor = processor;
        }
        final Processor processor;
        ProcessorLink prev = null;
        ProcessorLink next = null;
    }
    /**
     * Processor 链条头部
     * */
    private ProcessorLink head = null;
    /**
     * 尾部
     * */
    private ProcessorLink tail = null;
    /**
     * 头部开始执行
     * */
    public Response inOpened(Request request) {
        Context context = ContextUtil.request2Context(request);
        outOpened(context);
        return ContextUtil.cotext2Response(context);
    }
    /**
     * 尾部开始执行
     * */
    public Context outOpened(Context context) {
        return null;
    }


    /**
     * 注册Processor
     * */
    public synchronized Pipeline registerProcessor(Processor processor) {
        if (processor == null) {
            return this;
        }
        ProcessorLink note = new ProcessorLink(processor);
        if (head == null) {
            head = note;
            tail = note;
            return this;
        }
        note.prev = tail;
        tail.next = note;
        tail = note;
        return this;
    }
}
