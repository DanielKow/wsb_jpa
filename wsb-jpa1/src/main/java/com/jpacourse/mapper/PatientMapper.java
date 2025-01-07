package com.jpacourse.mapper;


import com.jpacourse.dto.AddressTO;
import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.MedicalTreatmentEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.TreatmentType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class PatientMapper {

    public static PatientTO toTO(PatientEntity patientEntity) {
        if (patientEntity == null) {
            return null;
        }

        PatientTO patientTO = new PatientTO();
        patientTO.setId(patientEntity.getId());
        patientTO.setFirstName(patientEntity.getFirstName());
        patientTO.setLastName(patientEntity.getLastName());
        patientTO.setTelephoneNumber(patientEntity.getTelephoneNumber());
        AddressTO addressTO = AddressMapper.mapToTO(patientEntity.getAddress());
        patientTO.setAddress(addressTO);
        patientTO.setDateOfBirth(patientEntity.getDateOfBirth());
        patientTO.setEmail(patientEntity.getEmail());
        patientTO.setPatientNumber(patientEntity.getPatientNumber());
        patientTO.setSex(patientEntity.getSex());
        patientTO.setNumberOfCheesecakes(patientEntity.getNumberOfCheesecakes());
        List<VisitTO> visits = new ArrayList<>();

        for (VisitEntity visitEntity : patientEntity.getVisits()) {
            VisitTO visitTO = new VisitTO();
            visitTO.setTime(visitEntity.getTime());
            visitTO.setDoctorName(visitEntity.getDoctor().getFirstName());
            visitTO.setDoctorSurname(visitEntity.getDoctor().getLastName());
            List<TreatmentType> treatments = new ArrayList<>();
            for (MedicalTreatmentEntity treatmentEntity : visitEntity.getTreatments()) {
                treatments.add(treatmentEntity.getType());
            }
            visitTO.setTreatments(treatments);
            visits.add(visitTO);
        }

        patientTO.setVisits(visits);

        return patientTO;
    }

    public static Collection<PatientTO> toTO(Collection<PatientEntity> patientEntities) {
        if (patientEntities == null) {
            return new ArrayList<>();
        }

        List<PatientTO> patientTOs = new ArrayList<>();
        for (PatientEntity patientEntity : patientEntities) {
            patientTOs.add(toTO(patientEntity));
        }

        return patientTOs;
    }

    public static PatientEntity toEntity(PatientTO patientTO) {
        if (patientTO == null) {
            return null;
        }

        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(patientTO.getId());
        patientEntity.setFirstName(patientTO.getFirstName());
        patientEntity.setLastName(patientTO.getLastName());
        patientEntity.setTelephoneNumber(patientTO.getTelephoneNumber());
        patientEntity.setAddress(AddressMapper.mapToEntity(patientTO.getAddress()));
        patientEntity.setDateOfBirth(patientTO.getDateOfBirth());
        patientEntity.setEmail(patientTO.getEmail());
        patientEntity.setPatientNumber(patientTO.getPatientNumber());
        return patientEntity;
    }
}
