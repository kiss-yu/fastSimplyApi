package com.nix.simple.api.http.session;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Kiss
 * @date 2018/08/26 21:08
 */
public final class SessionStory {
    private final static ConcurrentHashMap<String, Session> SESSION_STORY = new ConcurrentHashMap<>(4);

    /**
     * 根据sessionId获取session
     * */
    public static Session getSession(String key) {
        return SESSION_STORY.get(key);
    }

    /**
     * 创建session
     * */
    public static Session createSession() {
        Session session = new HttpSession(generateSessionId());
        SESSION_STORY.put(session.getId(), session);
        return session;
    }

    private static String generateSessionId() {
        Thread current = Thread.currentThread();
        String threadName = current.getName();
        long threadId = current.getId();
        long currentTime = System.nanoTime();
        return md5(threadName + threadName + currentTime);
    }

    private static String md5(String data) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] inputByteArray = data.getBytes();
            messageDigest.update(inputByteArray);
            byte[] resultByteArray = messageDigest.digest();
            return byteArrayToHex(resultByteArray);
        } catch (Exception e) {
            return null;
        }
    }

    private static String byteArrayToHex(byte[] byteArray) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] resultCharArray = new char[byteArray.length * 2];
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }
        return new String(resultCharArray);
    }
}
