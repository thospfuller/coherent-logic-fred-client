package com.coherentlogic.fred.client.db.integration.dao;

import static com.coherentlogic.fred.client.db.integration.dao.DAOConstants.RELEASE_DATES_DAO;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coherentlogic.fred.client.core.domain.ReleaseDates;

/**
 * Data access pattern implementation for {@link ReleaseDates} objects.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
@Repository(RELEASE_DATES_DAO)
@Transactional
public class ReleaseDatesDAO extends SerializableDAO<ReleaseDates> {

    @Override
    public ReleaseDates find(long primaryKey) {
        return find(ReleaseDates.class, primaryKey);
    }
}
