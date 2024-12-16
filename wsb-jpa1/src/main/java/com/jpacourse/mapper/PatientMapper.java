package com.jpacourse.mapper;


import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.entity.PatientEntity;

public final class PatientMapper {
    public PatientTO ToTO(PatientEntity patientEntity) {
        PatientTO patientTO = new PatientTO();
        patientTO.setId(patientEntity.getId());
        patientTO.setFirstName(patientEntity.getFirstName());
        patientTO.setLastName(patientEntity.getLastName());
        patientTO.setTelephoneNumber(patientEntity.getTelephoneNumber());
        patientTO.setAddress(patientEntity.getAddress());
    }
}
