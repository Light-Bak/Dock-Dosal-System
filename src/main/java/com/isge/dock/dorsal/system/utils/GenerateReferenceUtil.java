package com.isge.dock.dorsal.system.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.UUID;

public class GenerateReferenceUtil {
    private GenerateReferenceUtil() {
    }

    private static final BigDecimal MAX_VALUE = new BigDecimal("100");

    public static BigDecimal generateRandomBigDecimal() {
        SecureRandom random = new SecureRandom();
        double randomValue = random.nextDouble() * MAX_VALUE.doubleValue();
        return BigDecimal.valueOf(randomValue).setScale(MAX_VALUE.scale(), RoundingMode.HALF_UP);
    }

    public static UUID generateUUIDReference() {
        return UUID.randomUUID();
    }
}
