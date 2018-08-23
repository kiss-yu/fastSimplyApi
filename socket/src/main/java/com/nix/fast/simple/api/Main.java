package com.nix.fast.simple.api;

import com.nix.fast.simple.api.bootstrap.BootStrap;

import java.io.IOException;

/**
 * @author Kiss
 * @date 2018/08/23 18:21
 */
public class Main {
    public static void main(String[] args) throws IOException {
        new BootStrap().start();
    }
}
