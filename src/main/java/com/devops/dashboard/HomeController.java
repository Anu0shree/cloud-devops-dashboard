package com.devops.dashboard;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Value("${grafana.url}")
    private String grafanaUrl;

    @GetMapping("/")
    public String dashboard(Model model) {

        Runtime runtime = Runtime.getRuntime();

        model.addAttribute("status", "Running");
        model.addAttribute("version", "1.0.0");
        model.addAttribute("environment", "Development");
        model.addAttribute("port", "8080");

        model.addAttribute("os", System.getProperty("os.name"));
        model.addAttribute("javaVersion", System.getProperty("java.version"));
        model.addAttribute("processors", runtime.availableProcessors());

        model.addAttribute("freeMemory",
                runtime.freeMemory() / 1024 / 1024 + " MB");

        model.addAttribute("totalMemory",
                runtime.totalMemory() / 1024 / 1024 + " MB");

        model.addAttribute("uptime",
                ManagementFactory.getRuntimeMXBean().getUptime() / 1000 + " sec");

        try {
            String hostname = InetAddress.getLocalHost().getHostName();
            model.addAttribute("hostname", hostname);
        } catch (Exception e) {
            model.addAttribute("hostname", "Unknown");
        }

        model.addAttribute("grafanaUrl", grafanaUrl);

        return "dashboard";
    }
}