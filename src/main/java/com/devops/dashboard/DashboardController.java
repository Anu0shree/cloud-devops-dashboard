package com.devops.dashboard;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.management.ManagementFactory;
import java.time.Duration;

@Controller
public class DashboardController {

    @Value("${app.version:1.0.0}")
    private String appVersion;

    @Value("${app.environment:Local Development}")
    private String environment;

    @Value("${server.port:8080}")
    private String serverPort;

    @Value("${app.grafana-url}")
    private String grafanaUrl;

    /*
     * EC2 hostname is supplied through the APP_HOSTNAME
     * environment variable by GitHub Actions.
     *
     * We do NOT use InetAddress.getLocalHost() here because
     * that returns the Docker container hostname.
     */
    @Value("${app.hostname:Unavailable}")
    private String hostname;

    @GetMapping("/")
    public String dashboard(Model model) {

        Runtime runtime = Runtime.getRuntime();

        /*
         * -----------------------------------------
         * Application
         * -----------------------------------------
         */

        model.addAttribute("status", "Running");
        model.addAttribute("version", appVersion);
        model.addAttribute("environment", environment);
        model.addAttribute("port", serverPort);

        /*
         * -----------------------------------------
         * EC2 Infrastructure
         * -----------------------------------------
         */

        model.addAttribute("hostname", hostname);

        model.addAttribute(
                "os",
                System.getProperty("os.name")
        );

        model.addAttribute(
                "processors",
                runtime.availableProcessors()
        );

        /*
         * -----------------------------------------
         * JVM Information
         * -----------------------------------------
         */

        model.addAttribute(
                "javaVersion",
                System.getProperty("java.version")
        );

        model.addAttribute(
                "jvmFreeMemory",
                formatBytes(runtime.freeMemory())
        );

        model.addAttribute(
                "jvmTotalMemory",
                formatBytes(runtime.totalMemory())
        );

        model.addAttribute(
                "jvmMaxMemory",
                formatBytes(runtime.maxMemory())
        );

        model.addAttribute(
                "jvmUptime",
                formatUptime(
                        ManagementFactory
                                .getRuntimeMXBean()
                                .getUptime()
                )
        );

        /*
         * -----------------------------------------
         * Monitoring
         * -----------------------------------------
         *
         * These are relative URLs so they automatically
         * use the same EC2 host and port as the dashboard.
         *
         * Actuator Health:
         * /actuator/health
         *
         * Prometheus Metrics:
         * /actuator/prometheus
         */

        model.addAttribute(
                "actuatorHealthUrl",
                "/actuator/health"
        );

        model.addAttribute(
                "prometheusUrl",
                "/actuator/prometheus"
        );

        model.addAttribute(
                "grafanaUrl",
                grafanaUrl
        );

        return "dashboard";
    }

    /*
     * Convert bytes into MB/GB.
     */
    private String formatBytes(long bytes) {

        double mb = bytes / (1024.0 * 1024.0);

        if (mb < 1024) {

            return String.format(
                    "%.0f MB",
                    mb
            );
        }

        double gb = mb / 1024.0;

        return String.format(
                "%.2f GB",
                gb
        );
    }

    /*
     * Format JVM uptime.
     */
    private String formatUptime(long milliseconds) {

        Duration duration =
                Duration.ofMillis(milliseconds);

        long days = duration.toDays();

        long hours =
                duration.toHours() % 24;

        long minutes =
                duration.toMinutes() % 60;

        long seconds =
                duration.getSeconds() % 60;

        if (days > 0) {

            return String.format(
                    "%dd %02dh %02dm %02ds",
                    days,
                    hours,
                    minutes,
                    seconds
            );
        }

        if (hours > 0) {

            return String.format(
                    "%02dh %02dm %02ds",
                    hours,
                    minutes,
                    seconds
            );
        }

        if (minutes > 0) {

            return String.format(
                    "%02dm %02ds",
                    minutes,
                    seconds
            );
        }

        return String.format(
                "%02ds",
                seconds
        );
    }
}