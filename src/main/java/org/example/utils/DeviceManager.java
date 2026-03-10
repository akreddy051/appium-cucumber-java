package org.example.utils;

import io.cucumber.messages.ndjson.internal.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class DeviceManager {

    private static final BlockingQueue<Device> availableDevices = new LinkedBlockingQueue<>();
    private static final ThreadLocal<Device> threadDevice = new ThreadLocal<>();

    static {
        loadDevicesFromJson();
    }

    private static void loadDevicesFromJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = DeviceManager.class
                    .getClassLoader()
                    .getResourceAsStream("devices.json");
            if (is == null) {
                throw new RuntimeException("devices.json not found in resources");
            }
            List<Device> devices = Arrays.asList(mapper.readValue(is, Device[].class));
            availableDevices.addAll(devices);
            System.out.println("Loaded devices: " + devices.size());
        } catch (Exception e) {
            throw new RuntimeException("Failed to load devices.json", e);
        }
    }

    public static Device getDevice() {
        Device device = threadDevice.get();
        if (device == null) {
            try {
                device = availableDevices.take(); // waits if no device available
                threadDevice.set(device);
            } catch (InterruptedException e) {
                throw new RuntimeException("Failed to acquire device", e);
            }
        }
        return device;
    }

    public static void releaseDevice() {
        Device device = threadDevice.get();
        if (device != null) {
            availableDevices.offer(device);
            threadDevice.remove();
        }
    }
}
