package com.meli.interview.back.subscription_api.config;

import java.time.ZoneOffset;
import java.util.Locale;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LocaleConfig {

    public static final String DEFAULT_LOCALE_STRING = "es-AR";

    public static final String DEFAULT_TIMEZONE = "America/Argentina/Buenos_Aires";

    public static final String DEFAULT_OFFSET_ID = "-03:00";

    public static final String DEFAULT_CURRENCY = "ARS";

    public static final String DEFAULT_COUNTRY = "AR";

    public static final String DEFAULT_LANGUAGE = "es";

    public static final Locale DEFAULT_LOCALE = Locale.forLanguageTag(DEFAULT_LOCALE_STRING);

    public static final ZoneOffset DEFAULT_ZONE_OFFSET = ZoneOffset.of(DEFAULT_OFFSET_ID);

}