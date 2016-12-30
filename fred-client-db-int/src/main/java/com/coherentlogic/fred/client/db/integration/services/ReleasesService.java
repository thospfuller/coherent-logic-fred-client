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

import com.coherentlogic.fred.client.core.domain.Releases;
import com.coherentlogic.fred.client.db.integration.dao.ReleasesRepository;

@Repository(ReleasesService.BEAN_NAME)
@Transactional
public class ReleasesService {

    public static final String BEAN_NAME = "releasesService";

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ReleasesRepository releasesRepository;

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public ReleasesRepository getReleasesRepository() {
        return releasesRepository;
    }

    void setReleasesRepository(ReleasesRepository releasesRepository) {
        this.releasesRepository = releasesRepository;
    }

    public long count() {
        return releasesRepository.count();
    }

    public <S extends Releases> long count(Example<S> arg0) {
        return releasesRepository.count(arg0);
    }

    public void delete(Long arg0) {
        releasesRepository.delete(arg0);
    }

    public void delete(Releases arg0) {
        releasesRepository.delete(arg0);
    }

    public void delete(Iterable<? extends Releases> arg0) {
        releasesRepository.delete(arg0);
    }

    public void deleteAll() {
        releasesRepository.deleteAll();
    }

    public boolean exists(Long arg0) {
        return releasesRepository.exists(arg0);
    }

    public <S extends Releases> boolean exists(Example<S> arg0) {
        return releasesRepository.exists(arg0);
    }

    public List<Releases> findAll() {
        return releasesRepository.findAll();
    }

    public List<Releases> findAll(Sort sort) {
        return releasesRepository.findAll(sort);
    }

    public List<Releases> findAll(Iterable<Long> ids) {
        return releasesRepository.findAll(ids);
    }

    public Page<Releases> findAll(Pageable arg0) {
        return releasesRepository.findAll(arg0);
    }

    public <S extends Releases> Page<S> findAll(Example<S> arg0, Pageable arg1) {
        return releasesRepository.findAll(arg0, arg1);
    }

    public <S extends Releases> List<S> save(Iterable<S> entities) {
        return releasesRepository.save(entities);
    }

    public void flush() {
        releasesRepository.flush();
    }

    public <S extends Releases> S saveAndFlush(S entity) {
        return releasesRepository.saveAndFlush(entity);
    }

    public void deleteInBatch(Iterable<Releases> entities) {
        releasesRepository.deleteInBatch(entities);
    }

    public void deleteAllInBatch() {
        releasesRepository.deleteAllInBatch();
    }

    public Releases getOne(Long id) {
        return releasesRepository.getOne(id);
    }

    public <S extends Releases> List<S> findAll(Example<S> example) {
        return releasesRepository.findAll(example);
    }

    public <S extends Releases> List<S> findAll(Example<S> example, Sort sort) {
        return releasesRepository.findAll(example, sort);
    }

    public Releases findOne(Long arg0) {
        return releasesRepository.findOne(arg0);
    }

    public <S extends Releases> S findOne(Example<S> arg0) {
        return releasesRepository.findOne(arg0);
    }

    public <S extends Releases> S save(S arg0) {
        return releasesRepository.save(arg0);
    }
}
