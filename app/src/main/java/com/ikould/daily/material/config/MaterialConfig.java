package com.ikould.daily.material.config;


public class MaterialConfig {

    private static volatile MaterialConfig singleton;

    private MaterialConfig() {
    }

    public static MaterialConfig getInstance() {
        if (singleton == null) {
            synchronized (MaterialConfig.class) {
                if (singleton == null) {
                    singleton = new MaterialConfig();
                }
            }
        }
        return singleton;
    }
}