package com.jpacourse.service.impl;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.mapper.VisitsMapper;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.AddressEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<PatientTO> getByLastName(String lastName) {
        return PatientMapper.toTO(patientDao.getByLastName(lastName));
    }

    @Override
    public List<VisitTO> getVisits(Long patientId) {
        return VisitsMapper.toTO(patientDao.getVisits(patientId));
    }

    @Override
    public List<PatientTO> getWhenMoreVisitsThan(int numberOfVisits) {
        return PatientMapper.toTO(patientDao.getWhenMoreVisitsThan(numberOfVisits));
    }

    @Override
    public List<PatientTO> getWhenHaveEatenLessCheesecakesThan(int numberOfCheesecakes) {
        return PatientMapper.toTO(patientDao.getWhenHaveEatenLessCheesecakesThan(numberOfCheesecakes));
    }
}
