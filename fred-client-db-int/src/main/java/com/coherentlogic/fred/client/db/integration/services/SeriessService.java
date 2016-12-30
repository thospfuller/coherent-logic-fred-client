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

import com.coherentlogic.fred.client.core.domain.Seriess;
import com.coherentlogic.fred.client.db.integration.dao.SeriessRepository;

@Repository(SeriessService.BEAN_NAME)
@Transactional
public class SeriessService {

    public static final String BEAN_NAME = "seriessService";

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SeriessRepository seriessRepository;

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public SeriessRepository getSeriessRepository() {
        return seriessRepository;
    }

    void setSeriessRepository(SeriessRepository seriessRepository) {
        this.seriessRepository = seriessRepository;
    }

    public long count() {
        return seriessRepository.count();
    }

    public <S extends Seriess> long count(Example<S> arg0) {
        return seriessRepository.count(arg0);
    }

    public void delete(Long arg0) {
        seriessRepository.delete(arg0);
    }

    public void delete(Seriess arg0) {
        seriessRepository.delete(arg0);
    }

    public void delete(Iterable<? extends Seriess> arg0) {
        seriessRepository.delete(arg0);
    }

    public void deleteAll() {
        seriessRepository.deleteAll();
    }

    public boolean exists(Long arg0) {
        return seriessRepository.exists(arg0);
    }

    public <S extends Seriess> boolean exists(Example<S> arg0) {
        return seriessRepository.exists(arg0);
    }

    public List<Seriess> findAll() {
        return seriessRepository.findAll();
    }

    public List<Seriess> findAll(Sort sort) {
        return seriessRepository.findAll(sort);
    }

    public List<Seriess> findAll(Iterable<Long> ids) {
        return seriessRepository.findAll(ids);
    }

    public Page<Seriess> findAll(Pageable arg0) {
        return seriessRepository.findAll(arg0);
    }

    public <S extends Seriess> Page<S> findAll(Example<S> arg0, Pageable arg1) {
        return seriessRepository.findAll(arg0, arg1);
    }

    public <S extends Seriess> List<S> save(Iterable<S> entities) {
        return seriessRepository.save(entities);
    }

    public void flush() {
        seriessRepository.flush();
    }

    public <S extends Seriess> S saveAndFlush(S entity) {
        return seriessRepository.saveAndFlush(entity);
    }

    public void deleteInBatch(Iterable<Seriess> entities) {
        seriessRepository.deleteInBatch(entities);
    }

    public void deleteAllInBatch() {
        seriessRepository.deleteAllInBatch();
    }

    public Seriess getOne(Long id) {
        return seriessRepository.getOne(id);
    }

    public <S extends Seriess> List<S> findAll(Example<S> example) {
        return seriessRepository.findAll(example);
    }

    public <S extends Seriess> List<S> findAll(Example<S> example, Sort sort) {
        return seriessRepository.findAll(example, sort);
    }

    public Seriess findOne(Long arg0) {
        return seriessRepository.findOne(arg0);
    }

    public <S extends Seriess> S findOne(Example<S> arg0) {
        return seriessRepository.findOne(arg0);
    }

    public <S extends Seriess> S save(S arg0) {
        return seriessRepository.save(arg0);
    }
}
