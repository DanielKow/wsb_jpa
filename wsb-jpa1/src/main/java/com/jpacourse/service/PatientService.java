package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import java.util.List;


public interface PatientService {
    public PatientTO findById(final Long id);
    public void remove(final Long id);

    List<PatientTO> getByLastName (String lastName);

}
