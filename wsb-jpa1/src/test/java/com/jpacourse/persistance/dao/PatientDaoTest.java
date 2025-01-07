package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.entity.PatientEntity;
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

    @Test
    public void getByLastName_should_return_all_patients_with_given_last_name() {
        // given
        // when
        Collection<PatientEntity> kowalscy = patientDao.getByLastName("Kowalski");

        // then
        assertThat(kowalscy.size()).isEqualTo(3);
        for (PatientEntity kowalski : kowalscy) {
            assertThat(kowalski.getLastName()).isEqualTo("Kowalski");
        }
    }

    @Test
    public void getByLastName_should_return_one_patient_when_there_is_only_one_patient_with_given_last_name() {
        // given
        // when
        Collection<PatientEntity> patients = patientDao.getByLastName("Szymański");

        // then
        assertThat(patients.size()).isEqualTo(1);
        assertThat(patients.stream().findFirst().get().getLastName()).isEqualTo("Szymański");
    }

    @Test
    public void getByLastName_should_return_empty_list_when_there_is_no_patient_with_given_last_name() {
        // given
        // when
        Collection<PatientEntity> patients = patientDao.getByLastName("Wawrzyniak");

        // then
        assertThat(patients.size()).isEqualTo(0);
    }

    @Test
    public void getWhenMoreVisitsThan_should_return_patients_with_more_visits_than_given_number() {
        // given
        // when
        Collection<PatientEntity> patients = patientDao.getWhenMoreVisitsThan(2);

        // then
        assertThat(patients.size()).isEqualTo(2);
        for (PatientEntity patient : patients) {
            assertThat(patient.getVisits().size()).isGreaterThan(2);
        }
    }

    @Test
    public void getWhenMoreVisitsThan_should_return_empty_list_when_there_is_no_patient_with_more_visits_than_given_number() {
        // given
        // when
        Collection<PatientEntity> patients = patientDao.getWhenMoreVisitsThan(1000);

        // then
        assertThat(patients.size()).isEqualTo(0);
    }

    @Test
    public void getWhenMoreVisitsThan_should_return_all_patients_when_given_number_is_minus_one() {
        // given
        // when
        Collection<PatientEntity> patients = patientDao.getWhenMoreVisitsThan(-1);

        // then
        assertThat(patients.size()).isEqualTo(patientDao.findAll().size());
    }

    @Test
    public void getWhenHaveEatenLessCheesecakesThan_should_return_patients_with_less_eaten_cheesecakes_than_given_number() {
        // given
        // when
        Collection<PatientEntity> patients = patientDao.getWhenHaveEatenLessCheesecakesThan(2);

        // then
        assertThat(patients.size()).isEqualTo(2);
        for (PatientEntity patient : patients) {
            assertThat(patient.getNumberOfCheesecakes()).isLessThan(2);
        }
    }

    @Test
    public void getWhenHaveEatenLessCheesecakesThan_should_return_empty_list_when_there_is_no_patient_with_less_eaten_cheesecakes_than_given_number() {
        // given
        // when
        Collection<PatientEntity> patients = patientDao.getWhenHaveEatenLessCheesecakesThan(0);

        // then
        assertThat(patients.size()).isEqualTo(0);
    }

    @Test
    public void getWhenHaveEatenLessCheesecakesThan_should_return_all_patients_when_given_number_is_very_very_very_big() {
        // given
        // when
        // Żeby zjeść tyle serników człowiek musiałby jeść średnio 3tys na godzinę, więc nikt tyle nie zje
        Collection<PatientEntity> patients = patientDao.getWhenHaveEatenLessCheesecakesThan(Integer.MAX_VALUE);

        // then
        assertThat(patients.size()).isEqualTo(patientDao.findAll().size());
    }

}
