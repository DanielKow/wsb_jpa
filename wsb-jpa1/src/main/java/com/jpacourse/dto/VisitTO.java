package com.jpacourse.dto;

import com.jpacourse.persistence.enums.TreatmentType;

import java.time.LocalDateTime;
import java.util.List;

public class VisitTO {
    private LocalDateTime time;
    private String doctorName;
    private String doctorSurname;
    private List<TreatmentType> treatments;
    private PatientTO patient;

    public PatientTO getPatient() {
        return patient;
    }

    public void setPatient(PatientTO patient) {
        this.patient = patient;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorSurname() {
        return doctorSurname;
    }

    public void setDoctorSurname(String doctorSurname) {
        this.doctorSurname = doctorSurname;
    }

    public List<TreatmentType> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<TreatmentType> treatments) {
        this.treatments = treatments;
    }
}
