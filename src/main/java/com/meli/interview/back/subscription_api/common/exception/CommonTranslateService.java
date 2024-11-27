package com.meli.interview.back.subscription_api.common.exception;

import com.meli.interview.back.subscription_api.config.LocaleConfig;

import java.util.List;
import java.util.ResourceBundle;

import lombok.extern.jbosslog.JBossLog;

@JBossLog
public class CommonTranslateService {

    private static final List<ResourceBundle> translatedMessages = List.of(
            ResourceBundle.getBundle("message", LocaleConfig.DEFAULT_LOCALE)
    );

    private CommonTranslateService() {
    }

    public static String translate(String key) {
        for (ResourceBundle next : translatedMessages) {
            if (next.containsKey(key)) {
                return next.getString(key);
            }
        }
        log.warnv("Translate cannot be applied on this key {0}", key);
        return key;
    }
}