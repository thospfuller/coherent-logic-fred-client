package com.coherentlogic.fred.client.db.integration.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coherentlogic.fred.client.core.domain.Observations;
import com.coherentlogic.fred.client.db.integration.dao.ObservationsRepository;

@Repository(ObservationsService.BEAN_NAME)
@Transactional
public class ObservationsService {

    public static final String BEAN_NAME = "observationsService";

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ObservationsRepository observationsRepository;

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public ObservationsRepository getObservationsRepository() {
        return observationsRepository;
    }

    void setObservationsRepository(ObservationsRepository observationsRepository) {
        this.observationsRepository = observationsRepository;
    }

    public long count() {
        return observationsRepository.count();
    }

    public <S extends Observations> long count(Example<S> arg0) {
        return observationsRepository.count(arg0);
    }

    public void delete(Long arg0) {
        observationsRepository.delete(arg0);
    }

    public void delete(Observations arg0) {
        observationsRepository.delete(arg0);
    }

    public void delete(Iterable<? extends Observations> arg0) {
        observationsRepository.delete(arg0);
    }

    public void deleteAll() {
        observationsRepository.deleteAll();
    }

    public boolean exists(Long arg0) {
        return observationsRepository.exists(arg0);
    }

    public <S extends Observations> boolean exists(Example<S> arg0) {
        return observationsRepository.exists(arg0);
    }

    public List<Observations> findAll() {
        return observationsRepository.findAll();
    }

    public List<Observations> findAll(Sort sort) {
        return observationsRepository.findAll(sort);
    }

    public List<Observations> findAll(Iterable<Long> ids) {
        return observationsRepository.findAll(ids);
    }

    public Page<Observations> findAll(Pageable arg0) {
        return observationsRepository.findAll(arg0);
    }

    public <S extends Observations> Page<S> findAll(Example<S> arg0, Pageable arg1) {
        return observationsRepository.findAll(arg0, arg1);
    }

    public <S extends Observations> List<S> save(Iterable<S> entities) {
        return observationsRepository.save(entities);
    }

    public void flush() {
        observationsRepository.flush();
    }

    public <S extends Observations> S saveAndFlush(S entity) {
        return observationsRepository.saveAndFlush(entity);
    }

    public void deleteInBatch(Iterable<Observations> entities) {
        observationsRepository.deleteInBatch(entities);
    }

    public void deleteAllInBatch() {
        observationsRepository.deleteAllInBatch();
    }

    public Observations getOne(Long id) {
        return observationsRepository.getOne(id);
    }

    public <S extends Observations> List<S> findAll(Example<S> example) {
        return observationsRepository.findAll(example);
    }

    public <S extends Observations> List<S> findAll(Example<S> example, Sort sort) {
        return observationsRepository.findAll(example, sort);
    }

    public Observations findOne(Long arg0) {
        return observationsRepository.findOne(arg0);
    }

    public <S extends Observations> S findOne(Example<S> arg0) {
        return observationsRepository.findOne(arg0);
    }

    public <S extends Observations> S save(S arg0) {
        return observationsRepository.save(arg0);
    }
}
