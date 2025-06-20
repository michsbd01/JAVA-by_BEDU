package com.monitor;

public class VitalSignsEvent {
    private final int patientId;
    private final int heartRate;
    private final int systolic;
    private final int diastolic;
    private final int spo2;

    public VitalSignsEvent(int patientId, int heartRate, int systolic, int diastolic, int spo2) {
        this.patientId = patientId;
        this.heartRate = heartRate;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.spo2 = spo2;
    }

    public boolean isCritical() {
        return heartRate < 50 || heartRate > 120 ||
               systolic < 90 || systolic > 140 ||
               diastolic < 60 || diastolic > 90 ||
               spo2 < 90;
    }

    @Override
    public String toString() {
        if (heartRate < 50 || heartRate > 120)
            return "Paciente " + patientId + " - FC crítica: " + heartRate + " bpm";
        if (systolic < 90 || systolic > 140 || diastolic < 60 || diastolic > 90)
            return "Paciente " + patientId + " - PA crítica: " + systolic + "/" + diastolic + " mmHg";
        if (spo2 < 90)
            return "Paciente " + patientId + " - SpO2 baja: " + spo2 + "%";
        return "Paciente " + patientId + " - Evento no crítico";
    }
}
