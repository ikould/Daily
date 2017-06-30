package com.ikould.daily.main.config;

import com.ikould.frame.config.BaseAppConfig;
import com.ikould.frame.utils.FileUtil;

import java.io.File;

public class MainConfig {

    public static final String TEST_DIR = BaseAppConfig.TEMP_DIR + File.separator + "test";
    private static volatile MainConfig singleton;

    private MainConfig() {
        init();
    }

    public static MainConfig getInstance() {
        if (singleton == null) {
            synchronized (MainConfig.class) {
                if (singleton == null) {
                    singleton = new MainConfig();
                }
            }
        }
        return singleton;
    }

    private void init() {
        FileUtil.initDirectory(TEST_DIR);
    }
}