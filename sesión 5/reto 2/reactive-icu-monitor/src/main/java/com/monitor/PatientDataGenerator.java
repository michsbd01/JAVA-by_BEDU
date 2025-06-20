package com.monitor;

import reactor.core.publisher.Flux;
import java.time.Duration;
import java.util.Random;

public class PatientDataGenerator {

    private static final Random random = new Random();

    public static Flux<VitalSignsEvent> generatePatientStream(int patientId) {
        return Flux.interval(Duration.ofMillis(300))
                .map(tick -> generateRandomEvent(patientId));
    }

    private static VitalSignsEvent generateRandomEvent(int patientId) {
        int heartRate = 40 + random.nextInt(100);
        int systolic = 80 + random.nextInt(80);
        int diastolic = 50 + random.nextInt(50);
        int spo2 = 85 + random.nextInt(15);

        return new VitalSignsEvent(patientId, heartRate, systolic, diastolic, spo2);
    }
}
