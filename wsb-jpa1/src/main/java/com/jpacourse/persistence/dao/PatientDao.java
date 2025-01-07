package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long> {
    void addNewVisit(Long patientId, Long doctorId, LocalDateTime visitDate, String description);
    List<PatientEntity> getByLastName(String lastName);
    List<VisitEntity> getVisits(Long patientId);
    List<PatientEntity> getWhenMoreVisitsThan(int numberOfVisits);
    List<PatientEntity> getWhenHaveEatenLessCheesecakesThan(int numberOfCheesecakes);
}
