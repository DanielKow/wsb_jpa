package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;
import com.jpacourse.persistence.dao.PatientDao;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Transactional
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

    @Transactional
    @Test
    public void getByLastName_should_return_one_patient_when_there_is_only_one_patient_with_given_last_name() {
        // given
        // when
        Collection<PatientEntity> patients = patientDao.getByLastName("Szymański");

        // then
        assertThat(patients.size()).isEqualTo(1);
        assertThat(patients.stream().findFirst().get().getLastName()).isEqualTo("Szymański");
    }

    @Transactional
    @Test
    public void getByLastName_should_return_empty_list_when_there_is_no_patient_with_given_last_name() {
        // given
        // when
        Collection<PatientEntity> patients = patientDao.getByLastName("Wawrzyniak");

        // then
        assertThat(patients.size()).isEqualTo(0);
    }

    @Transactional
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

    @Transactional
    @Test
    public void getWhenMoreVisitsThan_should_return_empty_list_when_there_is_no_patient_with_more_visits_than_given_number() {
        // given
        // when
        Collection<PatientEntity> patients = patientDao.getWhenMoreVisitsThan(1000);

        // then
        assertThat(patients.size()).isEqualTo(0);
    }

    @Transactional
    @Test
    public void getWhenMoreVisitsThan_should_return_all_patients_when_given_number_is_minus_one() {
        // given
        // when
        Collection<PatientEntity> patients = patientDao.getWhenMoreVisitsThan(-1);

        // then
        assertThat(patients.size()).isEqualTo(patientDao.findAll().size());
    }

    @Transactional
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

    @Transactional
    @Test
    public void getWhenHaveEatenLessCheesecakesThan_should_return_empty_list_when_there_is_no_patient_with_less_eaten_cheesecakes_than_given_number() {
        // given
        // when
        Collection<PatientEntity> patients = patientDao.getWhenHaveEatenLessCheesecakesThan(0);

        // then
        assertThat(patients.size()).isEqualTo(0);
    }

    @Transactional
    @Test
    public void getWhenHaveEatenLessCheesecakesThan_should_return_all_patients_when_given_number_is_very_very_very_big() {
        // given
        // when
        // Żeby zjeść tyle serników człowiek musiałby jeść średnio 3tys na godzinę, więc nikt tyle nie zje
        Collection<PatientEntity> patients = patientDao.getWhenHaveEatenLessCheesecakesThan(Integer.MAX_VALUE);

        // then
        assertThat(patients.size()).isEqualTo(patientDao.findAll().size());
    }

    @Test
    public void when_concurrent_transactions_update_same_entity_OptimisticLockException_should_be_thrown() {
        PatientEntity patientFirstTransaction = patientDao.findOne(1L);
        PatientEntity patientSecondTransaction = patientDao.findOne(1L);

        patientFirstTransaction.setNumberOfCheesecakes(111);
        patientDao.update(patientFirstTransaction);

        var afterFirstTransaction = patientDao.findOne(1L);

        assertThat(afterFirstTransaction.getNumberOfCheesecakes()).isEqualTo(111);
        assertThat(afterFirstTransaction.getVersion()).isEqualTo(1);

        patientSecondTransaction.setNumberOfCheesecakes(222);

        assertThatThrownBy(() -> patientDao.update(patientSecondTransaction))
                .isInstanceOf(ObjectOptimisticLockingFailureException.class);
    }

}
