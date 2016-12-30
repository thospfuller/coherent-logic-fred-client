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

import com.coherentlogic.fred.client.core.domain.ReleaseDates;
import com.coherentlogic.fred.client.db.integration.dao.ReleaseDatesRepository;

@Repository(ReleaseDatesService.BEAN_NAME)
@Transactional
public class ReleaseDatesService {

    public static final String BEAN_NAME = "releaseDatesService";

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ReleaseDatesRepository releaseDatesRepository;

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public ReleaseDatesRepository getReleaseDatesRepository() {
        return releaseDatesRepository;
    }

    void setReleaseDatesRepository(ReleaseDatesRepository releaseDatesRepository) {
        this.releaseDatesRepository = releaseDatesRepository;
    }

    public long count() {
        return releaseDatesRepository.count();
    }

    public <S extends ReleaseDates> long count(Example<S> arg0) {
        return releaseDatesRepository.count(arg0);
    }

    public void delete(Long arg0) {
        releaseDatesRepository.delete(arg0);
    }

    public void delete(ReleaseDates arg0) {
        releaseDatesRepository.delete(arg0);
    }

    public void delete(Iterable<? extends ReleaseDates> arg0) {
        releaseDatesRepository.delete(arg0);
    }

    public void deleteAll() {
        releaseDatesRepository.deleteAll();
    }

    public boolean exists(Long arg0) {
        return releaseDatesRepository.exists(arg0);
    }

    public <S extends ReleaseDates> boolean exists(Example<S> arg0) {
        return releaseDatesRepository.exists(arg0);
    }

    public List<ReleaseDates> findAll() {
        return releaseDatesRepository.findAll();
    }

    public List<ReleaseDates> findAll(Sort sort) {
        return releaseDatesRepository.findAll(sort);
    }

    public List<ReleaseDates> findAll(Iterable<Long> ids) {
        return releaseDatesRepository.findAll(ids);
    }

    public Page<ReleaseDates> findAll(Pageable arg0) {
        return releaseDatesRepository.findAll(arg0);
    }

    public <S extends ReleaseDates> Page<S> findAll(Example<S> arg0, Pageable arg1) {
        return releaseDatesRepository.findAll(arg0, arg1);
    }

    public <S extends ReleaseDates> List<S> save(Iterable<S> entities) {
        return releaseDatesRepository.save(entities);
    }

    public void flush() {
        releaseDatesRepository.flush();
    }

    public <S extends ReleaseDates> S saveAndFlush(S entity) {
        return releaseDatesRepository.saveAndFlush(entity);
    }

    public void deleteInBatch(Iterable<ReleaseDates> entities) {
        releaseDatesRepository.deleteInBatch(entities);
    }

    public void deleteAllInBatch() {
        releaseDatesRepository.deleteAllInBatch();
    }

    public ReleaseDates getOne(Long id) {
        return releaseDatesRepository.getOne(id);
    }

    public <S extends ReleaseDates> List<S> findAll(Example<S> example) {
        return releaseDatesRepository.findAll(example);
    }

    public <S extends ReleaseDates> List<S> findAll(Example<S> example, Sort sort) {
        return releaseDatesRepository.findAll(example, sort);
    }

    public ReleaseDates findOne(Long arg0) {
        return releaseDatesRepository.findOne(arg0);
    }

    public <S extends ReleaseDates> S findOne(Example<S> arg0) {
        return releaseDatesRepository.findOne(arg0);
    }

    public <S extends ReleaseDates> S save(S arg0) {
        return releaseDatesRepository.save(arg0);
    }
}
