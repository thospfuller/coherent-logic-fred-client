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

import com.coherentlogic.fred.client.core.domain.Categories;
import com.coherentlogic.fred.client.db.integration.dao.CategoriesRepository;

@Repository(CategoriesService.BEAN_NAME)
@Transactional
public class CategoriesService {

    public static final String BEAN_NAME = "categoriesService";

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CategoriesRepository categoriesRepository;

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public CategoriesRepository getCategoriesRepository() {
        return categoriesRepository;
    }

    void setCategoriesRepository(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public long count() {
        return categoriesRepository.count();
    }

    public <S extends Categories> long count(Example<S> arg0) {
        return categoriesRepository.count(arg0);
    }

    public void delete(Long arg0) {
        categoriesRepository.delete(arg0);
    }

    public void delete(Categories arg0) {
        categoriesRepository.delete(arg0);
    }

    public void delete(Iterable<? extends Categories> arg0) {
        categoriesRepository.delete(arg0);
    }

    public void deleteAll() {
        categoriesRepository.deleteAll();
    }

    public boolean exists(Long arg0) {
        return categoriesRepository.exists(arg0);
    }

    public <S extends Categories> boolean exists(Example<S> arg0) {
        return categoriesRepository.exists(arg0);
    }

    public List<Categories> findAll() {
        return categoriesRepository.findAll();
    }

    public List<Categories> findAll(Sort sort) {
        return categoriesRepository.findAll(sort);
    }

    public List<Categories> findAll(Iterable<Long> ids) {
        return categoriesRepository.findAll(ids);
    }

    public Page<Categories> findAll(Pageable arg0) {
        return categoriesRepository.findAll(arg0);
    }

    public <S extends Categories> Page<S> findAll(Example<S> arg0, Pageable arg1) {
        return categoriesRepository.findAll(arg0, arg1);
    }

    public <S extends Categories> List<S> save(Iterable<S> entities) {
        return categoriesRepository.save(entities);
    }

    public void flush() {
        categoriesRepository.flush();
    }

    public <S extends Categories> S saveAndFlush(S entity) {
        return categoriesRepository.saveAndFlush(entity);
    }

    public void deleteInBatch(Iterable<Categories> entities) {
        categoriesRepository.deleteInBatch(entities);
    }

    public void deleteAllInBatch() {
        categoriesRepository.deleteAllInBatch();
    }

    public Categories getOne(Long id) {
        return categoriesRepository.getOne(id);
    }

    public <S extends Categories> List<S> findAll(Example<S> example) {
        return categoriesRepository.findAll(example);
    }

    public <S extends Categories> List<S> findAll(Example<S> example, Sort sort) {
        return categoriesRepository.findAll(example, sort);
    }

    public Categories findOne(Long arg0) {
        return categoriesRepository.findOne(arg0);
    }

    public <S extends Categories> S findOne(Example<S> arg0) {
        return categoriesRepository.findOne(arg0);
    }

    public <S extends Categories> S save(S arg0) {
        return categoriesRepository.save(arg0);
    }
}
