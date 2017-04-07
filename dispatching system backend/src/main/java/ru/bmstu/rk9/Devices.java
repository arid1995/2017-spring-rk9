package ru.bmstu.rk9;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by farid on 4/7/17.
 */
public class Devices {
    private static final ConcurrentHashMap<String, String> devices;

    static {
        devices = new ConcurrentHashMap<>();
        devices.put("default", "d000-e000-v000-i000-c000-e001");
    }

    public static String getDeviceId(String deviceName) {
        return devices.get(deviceName);
    }
}
