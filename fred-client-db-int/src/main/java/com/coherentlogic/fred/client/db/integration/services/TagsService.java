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

import com.coherentlogic.fred.client.core.domain.Tags;
import com.coherentlogic.fred.client.db.integration.dao.TagsRepository;

@Repository(TagsService.BEAN_NAME)
@Transactional
public class TagsService {

    public static final String BEAN_NAME = "tagsService";

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TagsRepository tagsRepository;

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

	public TagsRepository getTagsRepository() {
		return tagsRepository;
	}

	void setTagsRepository(TagsRepository tagsRepository) {
		this.tagsRepository = tagsRepository;
	}

	public long count() {
		return tagsRepository.count();
	}

	public <S extends Tags> long count(Example<S> arg0) {
		return tagsRepository.count(arg0);
	}

	public void delete(Long arg0) {
		tagsRepository.delete(arg0);
	}

	public void delete(Tags arg0) {
		tagsRepository.delete(arg0);
	}

	public void delete(Iterable<? extends Tags> arg0) {
		tagsRepository.delete(arg0);
	}

	public void deleteAll() {
		tagsRepository.deleteAll();
	}

	public boolean exists(Long arg0) {
		return tagsRepository.exists(arg0);
	}

	public <S extends Tags> boolean exists(Example<S> arg0) {
		return tagsRepository.exists(arg0);
	}

	public List<Tags> findAll() {
		return tagsRepository.findAll();
	}

	public List<Tags> findAll(Sort sort) {
		return tagsRepository.findAll(sort);
	}

	public List<Tags> findAll(Iterable<Long> ids) {
		return tagsRepository.findAll(ids);
	}

	public Page<Tags> findAll(Pageable arg0) {
		return tagsRepository.findAll(arg0);
	}

	public <S extends Tags> Page<S> findAll(Example<S> arg0, Pageable arg1) {
		return tagsRepository.findAll(arg0, arg1);
	}

	public <S extends Tags> List<S> save(Iterable<S> entities) {
		return tagsRepository.save(entities);
	}

	public void flush() {
		tagsRepository.flush();
	}

	public <S extends Tags> S saveAndFlush(S entity) {
		return tagsRepository.saveAndFlush(entity);
	}

	public void deleteInBatch(Iterable<Tags> entities) {
		tagsRepository.deleteInBatch(entities);
	}

	public void deleteAllInBatch() {
		tagsRepository.deleteAllInBatch();
	}

	public Tags getOne(Long id) {
		return tagsRepository.getOne(id);
	}

	public <S extends Tags> List<S> findAll(Example<S> example) {
		return tagsRepository.findAll(example);
	}

	public <S extends Tags> List<S> findAll(Example<S> example, Sort sort) {
		return tagsRepository.findAll(example, sort);
	}

	public Tags findOne(Long arg0) {
		return tagsRepository.findOne(arg0);
	}

	public <S extends Tags> S findOne(Example<S> arg0) {
		return tagsRepository.findOne(arg0);
	}

	public <S extends Tags> S save(S arg0) {
		return tagsRepository.save(arg0);
	}

	
}
