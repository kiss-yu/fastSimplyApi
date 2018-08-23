package com.nix.fast.simple.api.util.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Kiss
 * @date 2018/08/23 15:55
 */

public final class LogKit {
    private static final Log log = LogFactory.getLog("nix");
    private final static String getClassName(Class clazz){
        return clazz.getName() + " : ";
    }
    public final static void info(Class clazz,String msg){
        log.info(getClassName(clazz) + msg);
    }
    public final static void debug(Class clazz,String msg){
        log.debug(getClassName(clazz) + msg);
    }
    public final static void error(Class clazz,String msg){
        log.error(getClassName(clazz) + msg);
    }
    public static Log getLog(Class clazz) {
        return LogFactory.getLog(clazz);
    }
}