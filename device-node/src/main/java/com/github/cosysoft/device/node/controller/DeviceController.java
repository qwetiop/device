package com.github.cosysoft.device.node.controller;

import com.github.cosysoft.device.node.domain.Result;
import com.github.cosysoft.device.node.service.DeviceService;
import com.github.cosysoft.device.node.domain.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by 兰天 on 2015/4/8.
 */
@RestController
@RequestMapping("/api/device")
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    @RequestMapping
    public Collection<Device> devices() {
        return deviceService.getDevices();
    }

    @RequestMapping(value = "/{serialId}/avatar", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] avatar(@PathVariable String serialId) {
        return deviceService.getAvatar(serialId);
    }

    @RequestMapping(value = "/{serialId}/screenshot", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] screenshot(@PathVariable String serialId) {
        return deviceService.takeScreenShot(serialId);
    }

    @RequestMapping(value = "/{serialId}/adb/{cmd}")
    public Result<String> executeADB(@PathVariable String serialId, @PathVariable String cmd) {
        return deviceService.runAdbCommand(serialId, cmd);
    }
}
