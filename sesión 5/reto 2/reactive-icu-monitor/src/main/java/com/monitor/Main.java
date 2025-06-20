package com.monitor;

import java.time.Duration;

import reactor.core.publisher.Flux;

public class Main {

    public static void main(String[] args) {

        Flux.merge(
                PatientDataGenerator.generatePatientStream(1),
                PatientDataGenerator.generatePatientStream(2),
                PatientDataGenerator.generatePatientStream(3)
        )
        .filter(data -> data.isCritical())
        .delayElements(Duration.ofSeconds(1))
        .subscribe(data -> System.out.println("⚠️ " + data));

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
           System.err.println("Error: " + e.getMessage()); 
        }
    }
}
