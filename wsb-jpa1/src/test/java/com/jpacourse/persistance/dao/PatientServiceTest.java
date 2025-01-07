package com.jpacourse.persistance.dao;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.Sex;
import com.jpacourse.persistence.enums.TreatmentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import com.jpacourse.service.PatientService;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientServiceTest {
    @Autowired
    private PatientService patientService;

    @Autowired
    private VisitDao visitDao;

    @Autowired
    private DoctorDao doctorDao;

    @Transactional
    @Test
    public void testShouldFindPatientById() {
        // Given
        // When
        PatientTO patient = patientService.findById(1L);

        // Then
        assertThat(patient).isNotNull();
        assertThat(patient.getFirstName()).isEqualTo("Jan");
        assertThat(patient.getLastName()).isEqualTo("Kowalski");
        assertThat(patient.getDateOfBirth()).isEqualTo("1990-01-01");
        assertThat(patient.getSex()).isEqualTo(Sex.MALE);
        assertThat(patient.getTelephoneNumber()).isEqualTo("+48500123456");
        assertThat(patient.getEmail()).isEqualTo("jan.kowalski@example.com");
        assertThat(patient.getPatientNumber()).isEqualTo("P1234");
        assertThat(patient.getNumberOfCheesecakes()).isEqualTo(1);
        assertThat(patient.getVisits().size()).isEqualTo(3);
        // do not base on order
        assertThat(patient.getVisits().get(0).getTime()).isEqualTo("2023-10-10T10:00:00");
        assertThat(patient.getVisits().get(0).getDoctorName()).isEqualTo("Alicja");
        assertThat(patient.getVisits().get(0).getDoctorSurname()).isEqualTo("Brązowa");
        assertThat(patient.getVisits().get(0).getTreatments().size()).isEqualTo(1);
        assertThat(patient.getVisits().get(0).getTreatments().get(0)).isEqualTo(TreatmentType.USG);
        assertThat(patient.getVisits().get(1).getTime()).isEqualTo("2023-10-20T10:00:00");
        assertThat(patient.getVisits().get(1).getDoctorName()).isEqualTo("Bartosz");
        assertThat(patient.getVisits().get(1).getDoctorSurname()).isEqualTo("Biały");
        assertThat(patient.getVisits().get(1).getTreatments().size()).isEqualTo(1);
        assertThat(patient.getVisits().get(1).getTreatments().get(0)).isEqualTo(TreatmentType.RTG);
        assertThat(patient.getVisits().get(2).getTime()).isEqualTo("2023-10-21T11:00:00");
        assertThat(patient.getVisits().get(2).getDoctorName()).isEqualTo("Iwona");
        assertThat(patient.getVisits().get(2).getDoctorSurname()).isEqualTo("Zielona");
        assertThat(patient.getVisits().get(2).getTreatments().size()).isEqualTo(2);
        assertThat(patient.getVisits().get(2).getTreatments().get(0)).isEqualTo(TreatmentType.EKG);
        assertThat(patient.getVisits().get(2).getTreatments().get(1)).isEqualTo(TreatmentType.RTG);
    }

    @Transactional
    @Test
    public void testShouldRemovePatientAndVisitsButNotDoctors()
    {
        // Given
        assertThat(visitDao.findOne(1L)).isNotNull();
        assertThat(visitDao.findOne(11L)).isNotNull();
        assertThat(visitDao.findOne(12L)).isNotNull();
        assertThat(doctorDao.findOne(1L)).isNotNull();
        assertThat(doctorDao.findOne(2L)).isNotNull();
        assertThat(doctorDao.findOne(9L)).isNotNull();

        // When
        patientService.remove(1L);

        // Then
        assertThat(visitDao.findOne(1L)).isNull();
        assertThat(visitDao.findOne(11L)).isNull();
        assertThat(visitDao.findOne(12L)).isNull();
        assertThat(doctorDao.findOne(1L)).isNotNull();
        assertThat(doctorDao.findOne(2L)).isNotNull();
        assertThat(doctorDao.findOne(9L)).isNotNull();
    }

    @Transactional
    @Test
    public void getVisits_should_return_all_visits_of_patient_with_given_id() {
        // given
        // when
        Collection<VisitTO> visits = patientService.getVisits(1L);

        // then
        assertThat(visits.size()).isEqualTo(3);
        for (VisitTO visit : visits) {
            assertThat(visit.getPatient().getId()).isEqualTo(1L);
        }
    }

    @Transactional
    @Test
    public void getVisits_should_return_only_one_visit_when_patient_has_only_one_visits() {
        // given
        // when
        Collection<VisitTO> visits = patientService.getVisits(10L);

        // then
        assertThat(visits.size()).isEqualTo(1);
        assertThat(visits.stream().findFirst().get().getPatient().getId()).isEqualTo(10L);
    }

    @Transactional
    @Test
    public void getVisits_should_return_empty_list_when_patient_with_given_id_has_no_visits() {
        // given
        // when
        Collection<VisitTO> visits = patientService.getVisits(13L);

        // then
        assertThat(visits.size()).isEqualTo(0);
    }
}