package com.coherentlogic.fred.client.db.integration.dao;

import static com.coherentlogic.fred.client.db.integration.dao.DAOConstants.CATEGORIES_DAO;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coherentlogic.fred.client.core.domain.Categories;

/**
 * Data access pattern implementation for {@link Categories} objects.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
@Repository(CATEGORIES_DAO)
@Transactional
public class CategoriesDAO extends SerializableDAO<Categories> {

    @Override
    public Categories find (long primaryKey) {
        return find(Categories.class, primaryKey);
    }
}
