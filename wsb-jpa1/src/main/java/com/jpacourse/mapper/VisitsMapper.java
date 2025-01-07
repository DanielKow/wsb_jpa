package com.jpacourse.mapper;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.MedicalTreatmentEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.TreatmentType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VisitsMapper {

    public static VisitTO toTo(VisitEntity entity) {
        VisitTO to = new VisitTO();
        to.setTime(entity.getTime());
        to.setDoctorName(entity.getDoctor().getFirstName());
        to.setDoctorSurname(entity.getDoctor().getLastName());
        to.setPatient(PatientMapper.toTO(entity.getPatient()));

        ArrayList<TreatmentType> treatments = new ArrayList<>();

        for (MedicalTreatmentEntity treatmentEntity : entity.getTreatments()) {
            treatments.add(treatmentEntity.getType());
        }

        to.setTreatments(treatments);

        return to;
    }

    public static Collection<VisitTO> toTO(Collection<VisitEntity> entities) {
        ArrayList<VisitTO> visits = new ArrayList<>();

        for (VisitEntity entity : entities) {
            visits.add(toTo(entity));
        }

        return visits;
    }
}