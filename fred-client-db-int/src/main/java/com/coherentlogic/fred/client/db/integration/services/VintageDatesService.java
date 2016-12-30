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

import com.coherentlogic.fred.client.core.domain.VintageDates;
import com.coherentlogic.fred.client.db.integration.dao.VintageDatesRepository;

@Repository(VintageDatesService.BEAN_NAME)
@Transactional
public class VintageDatesService {

    public static final String BEAN_NAME = "vintageDatesService";

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private VintageDatesRepository vintageDatesRepository;

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected VintageDatesRepository getVintageDatesRepository() {
        return vintageDatesRepository;
    }

    protected void setVintageDatesRepository(VintageDatesRepository vintageDatesRepository) {
        this.vintageDatesRepository = vintageDatesRepository;
    }

    public long count() {
        return vintageDatesRepository.count();
    }

    public <S extends VintageDates> long count(Example<S> arg0) {
        return vintageDatesRepository.count(arg0);
    }

    public void delete(Long arg0) {
        vintageDatesRepository.delete(arg0);
    }

    public void delete(VintageDates arg0) {
        vintageDatesRepository.delete(arg0);
    }

    public void delete(Iterable<? extends VintageDates> arg0) {
        vintageDatesRepository.delete(arg0);
    }

    public void deleteAll() {
        vintageDatesRepository.deleteAll();
    }

    public boolean exists(Long arg0) {
        return vintageDatesRepository.exists(arg0);
    }

    public <S extends VintageDates> boolean exists(Example<S> arg0) {
        return vintageDatesRepository.exists(arg0);
    }

    public List<VintageDates> findAll() {
        return vintageDatesRepository.findAll();
    }

    public List<VintageDates> findAll(Sort sort) {
        return vintageDatesRepository.findAll(sort);
    }

    public List<VintageDates> findAll(Iterable<Long> ids) {
        return vintageDatesRepository.findAll(ids);
    }

    public Page<VintageDates> findAll(Pageable arg0) {
        return vintageDatesRepository.findAll(arg0);
    }

    public <S extends VintageDates> Page<S> findAll(Example<S> arg0, Pageable arg1) {
        return vintageDatesRepository.findAll(arg0, arg1);
    }

    public <S extends VintageDates> List<S> save(Iterable<S> entities) {
        return vintageDatesRepository.save(entities);
    }

    public void flush() {
        vintageDatesRepository.flush();
    }

    public <S extends VintageDates> S saveAndFlush(S entity) {
        return vintageDatesRepository.saveAndFlush(entity);
    }

    public void deleteInBatch(Iterable<VintageDates> entities) {
        vintageDatesRepository.deleteInBatch(entities);
    }

    public void deleteAllInBatch() {
        vintageDatesRepository.deleteAllInBatch();
    }

    public VintageDates getOne(Long id) {
        return vintageDatesRepository.getOne(id);
    }

    public <S extends VintageDates> List<S> findAll(Example<S> example) {
        return vintageDatesRepository.findAll(example);
    }

    public <S extends VintageDates> List<S> findAll(Example<S> example, Sort sort) {
        return vintageDatesRepository.findAll(example, sort);
    }

    public VintageDates findOne(Long arg0) {
        return vintageDatesRepository.findOne(arg0);
    }

    public <S extends VintageDates> S findOne(Example<S> arg0) {
        return vintageDatesRepository.findOne(arg0);
    }

    public <S extends VintageDates> S save(S arg0) {
        return vintageDatesRepository.save(arg0);
    }
}
