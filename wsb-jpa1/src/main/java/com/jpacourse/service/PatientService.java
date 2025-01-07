package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;

import java.util.Collection;
import java.util.List;


public interface PatientService {
    PatientTO findById(final Long id);
    void remove(final Long id);
    Collection<PatientTO> getByLastName (final String lastName);
    Collection<VisitTO> getVisits(Long patientId);
    Collection<PatientTO> getWhenMoreVisitsThan(int numberOfVisits);
    Collection<PatientTO> getWhenHaveEatenLessCheesecakesThan(int numberOfCheesecakes);

}
