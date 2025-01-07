package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long> {
    void addNewVisit(Long patientId, Long doctorId, LocalDateTime visitDate, String description);
    Collection<PatientEntity> getByLastName(String lastName);
    Collection<VisitEntity> getVisits(Long patientId);
    Collection<PatientEntity> getWhenMoreVisitsThan(int numberOfVisits);
    Collection<PatientEntity> getWhenHaveEatenLessCheesecakesThan(int numberOfCheesecakes);
}
