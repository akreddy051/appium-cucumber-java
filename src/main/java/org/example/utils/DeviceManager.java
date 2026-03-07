package org.example.utils;

import io.cucumber.messages.ndjson.internal.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DeviceManager {

    private static final AtomicInteger deviceIndex = new AtomicInteger(0);
    private static final List<Device> devices;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = DeviceManager.class
                    .getClassLoader()
                    .getResourceAsStream("devices.json");

            devices = Arrays.asList(mapper.readValue(is, Device[].class));

        } catch (Exception e) {
            throw new RuntimeException("Failed to load devices.json", e);
        }
    }

    public static Device getDevice() {
        int index = deviceIndex.getAndIncrement();
        return devices.get(index % devices.size());
    }
}
