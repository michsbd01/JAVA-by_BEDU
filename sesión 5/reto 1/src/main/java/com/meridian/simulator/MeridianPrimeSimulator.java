package com.meridian.simulator;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class MeridianPrimeSimulator {

    public static void main(String[] args) {

        Random random = new Random();
        AtomicInteger redCount = new AtomicInteger(0);
        Sinks.Many<String> globalAlerts = Sinks.many().multicast().onBackpressureBuffer();

        Flux.interval(Duration.ofMillis(500))
                .map(i -> random.nextInt(101))
                .filter(level -> level > 70)
                .doOnNext(level -> {
                    System.out.println("\uD83D\uDE97 Alerta: Congestión del " + level + "% en Avenida Solar");
                    globalAlerts.tryEmitNext("Traffic");
                })
                .subscribe();

        Flux.interval(Duration.ofMillis(600))
                .map(i -> random.nextInt(100))
                .filter(pm -> pm > 50)
                .doOnNext(pm -> {
                    System.out.println("\uD83C\uDF2B️ Alerta: Contaminación alta (PM2.5: " + pm + " ug/m3)");
                    globalAlerts.tryEmitNext("Pollution");
                })
                .subscribe();

        Flux.interval(Duration.ofMillis(800))
                .map(i -> {
                    String[] levels = {"Baja", "Media", "Alta"};
                    return levels[random.nextInt(3)];
                })
                .filter(priority -> priority.equals("Alta"))
                .doOnNext(p -> {
                    System.out.println("\uD83D\uDE91 Emergencia vial: Accidente con prioridad " + p);
                    globalAlerts.tryEmitNext("Accident");
                })
                .subscribe();

        Flux.interval(Duration.ofMillis(700))
                .map(i -> random.nextInt(11))
                .onBackpressureBuffer()
                .filter(delay -> delay > 5)
                .doOnNext(delay -> {
                    System.out.println("\uD83D\uDE89 Tren maglev con retraso crítico: " + delay + " minutos");
                    globalAlerts.tryEmitNext("Train");
                })
                .subscribe();

        Flux.interval(Duration.ofMillis(400))
                .map(i -> {
                    String[] states = {"Verde", "Amarillo", "Rojo"};
                    return states[random.nextInt(3)];
                })
                .doOnNext(state -> {
                    if ("Rojo".equals(state)) {
                        int count = redCount.incrementAndGet();
                        if (count >= 3) {
                            System.out.println("\uD83D\uDEA6 Semáforo en Rojo detectado 3 veces seguidas en cruce Norte");
                            globalAlerts.tryEmitNext("TrafficLight");
                            redCount.set(0);
                        }
                    } else {
                        redCount.set(0);
                    }
                })
                .subscribe();

        globalAlerts.asFlux()
                .buffer(Duration.ofSeconds(1))
                .filter(list -> list.size() >= 3)
                .doOnNext(list -> System.out.println("\uD83D\uDEA8 Alerta global: Múltiples eventos críticos detectados en Meridian Prime"))
                .subscribe();

        try {
            Thread.sleep(20000); // Run simulation for 20 seconds
        } catch (InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}