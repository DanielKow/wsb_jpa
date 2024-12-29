package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.VisitEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.jpacourse.persistence.dao.PatientDao;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientDaoTest {

    @Autowired
    private PatientDao patientDao;

    @Transactional
    @Test
    public void addNewVisitToPatientShouldPersistVisit() {
        // given
        ArrayList<VisitEntity> visitsBefore = new ArrayList<>(patientDao.findOne(1L).getVisits());

        // when
        patientDao.addNewVisit(1L, 1L, LocalDateTime.parse("2023-10-21T11:00:00"), "Przykładowy opis wizyty");

        // then
        Collection<VisitEntity> visitsAfter = patientDao.findOne(1L).getVisits();
        assertThat(visitsAfter.size()).isEqualTo(visitsBefore.size() + 1);
        VisitEntity newVisit = visitsAfter.stream().filter(v -> !visitsBefore.contains(v)).findFirst().get();
        assertThat(newVisit.getTime()).isEqualTo(LocalDateTime.parse("2023-10-21T11:00:00"));
        assertThat(newVisit.getDescription()).isEqualTo("Przykładowy opis wizyty");
        assertThat(newVisit.getDoctor().getId()).isEqualTo(1L);
    }

}
