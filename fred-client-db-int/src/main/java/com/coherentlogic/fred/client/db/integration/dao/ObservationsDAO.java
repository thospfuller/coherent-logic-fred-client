package com.coherentlogic.fred.client.db.integration.dao;

import static com.coherentlogic.fred.client.db.integration.dao.DAOConstants.OBSERVATIONS_DAO;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coherentlogic.fred.client.core.domain.Observations;

/**
 * Data access pattern implementation for {@link Observations} objects.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
@Repository(OBSERVATIONS_DAO)
@Transactional
public class ObservationsDAO extends SerializableDAO<Observations> {

    @Override
    public Observations find (long primaryKey) {
        return find(Observations.class, primaryKey);
    }
}
