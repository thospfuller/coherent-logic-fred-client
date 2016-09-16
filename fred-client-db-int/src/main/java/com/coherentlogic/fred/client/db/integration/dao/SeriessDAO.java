package com.coherentlogic.fred.client.db.integration.dao;

import static com.coherentlogic.fred.client.db.integration.dao.DAOConstants.SERIESS_DAO;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coherentlogic.fred.client.core.domain.Seriess;

/**
 * Data access pattern implementation for {@link Seriess} objects.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
@Repository(SERIESS_DAO)
@Transactional
public class SeriessDAO extends SerializableDAO<Seriess> {

    @Override
    public Seriess find (long primaryKey) {
        return find(Seriess.class, primaryKey);
    }
}
