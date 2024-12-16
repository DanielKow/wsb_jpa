package com.jpacourse.service.impl;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.AddressEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientDao patientDao;

    @Autowired
    public PatientServiceImpl(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    @Override
    public PatientTO findById(Long id) {
        final PatientEntity entity = patientDao.findOne(id);
        return PatientMapper.toTO(entity);
    }

    @Override
    public void remove(Long id) {
        patientDao.delete(id);
    }
}
