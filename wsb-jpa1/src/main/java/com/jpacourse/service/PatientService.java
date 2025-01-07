package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;

import java.util.List;


public interface PatientService {
    PatientTO findById(final Long id);
    void remove(final Long id);
    List<PatientTO> getByLastName (final String lastName);
    List<VisitTO> getVisits(Long patientId);
    List<PatientTO> getWhenMoreVisitsThan(int numberOfVisits);
    List<PatientTO> getWhenHaveEatenLessCheesecakesThan(int numberOfCheesecakes);

}
