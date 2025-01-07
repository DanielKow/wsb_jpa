package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao
{
    private final DoctorDao doctorDao;

    public PatientDaoImpl(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    @Override
    public void addNewVisit(Long patientId, Long doctorId, LocalDateTime visitDate, String description) {

        PatientEntity patient = findOne(patientId);
        DoctorEntity doctor = doctorDao.findOne(doctorId);
        VisitEntity visit = new VisitEntity();
        visit.setPatient(patient);
        visit.setDoctor(doctor);
        visit.setTime(visitDate);
        visit.setDescription(description);

        patient.getVisits().add(visit);

        update(patient);
    }

    @Override
    public List<PatientEntity> getByLastName(String lastName) {
        return entityManager.createQuery("SELECT patients FROM PatientEntity patients WHERE patients.lastName = :lastName", PatientEntity.class)
                .setParameter("lastName", lastName)
                .getResultList();
    }

    @Override
    public List<VisitEntity> getVisits(Long patientId) {
        return entityManager.createQuery("SELECT visits FROM VisitEntity visits WHERE visits.patient.id = :patientId", VisitEntity.class)
                .setParameter("patientId", patientId)
                .getResultList();
    }

    @Override
    public List<PatientEntity> getWhenMoreVisitsThan(int numberOfVisits) {
        return entityManager.createQuery("SELECT patients FROM PatientEntity patients WHERE patients.visits.size > :numberOfVisits", PatientEntity.class)
                .setParameter("numberOfVisits", numberOfVisits)
                .getResultList();
    }

    @Override
    public List<PatientEntity> getWhenHaveEatenLessCheesecakesThan(int numberOfCheesecakes) {
        return entityManager.createQuery("SELECT patients FROM PatientEntity patients WHERE patients.numberOfCheesecakes < :numberOfCheesecakes", PatientEntity.class)
                .setParameter("numberOfCheesecakes", numberOfCheesecakes)
                .getResultList();
    }
}
