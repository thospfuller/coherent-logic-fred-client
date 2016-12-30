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

import com.coherentlogic.fred.client.core.domain.Sources;
import com.coherentlogic.fred.client.db.integration.dao.SourcesRepository;

@Repository(SourcesService.BEAN_NAME)
@Transactional
public class SourcesService {

    public static final String BEAN_NAME = "sourcesService";

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SourcesRepository sourcesRepository;

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public SourcesRepository getSourcesRepository() {
        return sourcesRepository;
    }

    public void setSourcesRepository(SourcesRepository sourcesRepository) {
        this.sourcesRepository = sourcesRepository;
    }

    public long count() {
        return sourcesRepository.count();
    }

    public <S extends Sources> long count(Example<S> arg0) {
        return sourcesRepository.count(arg0);
    }

    public void delete(Long arg0) {
        sourcesRepository.delete(arg0);
    }

    public void delete(Sources arg0) {
        sourcesRepository.delete(arg0);
    }

    public void delete(Iterable<? extends Sources> arg0) {
        sourcesRepository.delete(arg0);
    }

    public void deleteAll() {
        sourcesRepository.deleteAll();
    }

    public boolean exists(Long arg0) {
        return sourcesRepository.exists(arg0);
    }

    public <S extends Sources> boolean exists(Example<S> arg0) {
        return sourcesRepository.exists(arg0);
    }

    public List<Sources> findAll() {
        return sourcesRepository.findAll();
    }

    public List<Sources> findAll(Sort sort) {
        return sourcesRepository.findAll(sort);
    }

    public List<Sources> findAll(Iterable<Long> ids) {
        return sourcesRepository.findAll(ids);
    }

    public Page<Sources> findAll(Pageable arg0) {
        return sourcesRepository.findAll(arg0);
    }

    public <S extends Sources> Page<S> findAll(Example<S> arg0, Pageable arg1) {
        return sourcesRepository.findAll(arg0, arg1);
    }

    public <S extends Sources> List<S> save(Iterable<S> entities) {
        return sourcesRepository.save(entities);
    }

    public void flush() {
        sourcesRepository.flush();
    }

    public <S extends Sources> S saveAndFlush(S entity) {
        return sourcesRepository.saveAndFlush(entity);
    }

    public void deleteInBatch(Iterable<Sources> entities) {
        sourcesRepository.deleteInBatch(entities);
    }

    public void deleteAllInBatch() {
        sourcesRepository.deleteAllInBatch();
    }

    public Sources getOne(Long id) {
        return sourcesRepository.getOne(id);
    }

    public <S extends Sources> List<S> findAll(Example<S> example) {
        return sourcesRepository.findAll(example);
    }

    public <S extends Sources> List<S> findAll(Example<S> example, Sort sort) {
        return sourcesRepository.findAll(example, sort);
    }

    public Sources findOne(Long arg0) {
        return sourcesRepository.findOne(arg0);
    }

    public <S extends Sources> S findOne(Example<S> arg0) {
        return sourcesRepository.findOne(arg0);
    }

    public <S extends Sources> S save(S arg0) {
        return sourcesRepository.save(arg0);
    }
}
