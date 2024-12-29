package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao
{
    @Override
    public void addNewVisit(Long patientId, Long doctorId, LocalDateTime visitDate, String description) {
        PatientEntity patient = entityManager.find(PatientEntity.class, patientId);
        DoctorEntity doctor = entityManager.find(DoctorEntity.class, doctorId);
        VisitEntity visit = new VisitEntity();
        visit.setPatient(patient);
        visit.setDoctor(doctor);
        visit.setTime(visitDate);
        visit.setDescription(description);

        patient.getVisits().add(visit);

        entityManager.merge(patient);
    }
}
