package com.coherentlogic.fred.client.db.integration.dao;

import static com.coherentlogic.fred.client.db.integration.dao.DAOConstants.RELEASES_DAO;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coherentlogic.fred.client.core.domain.Releases;

/**
 * Data access pattern implementation for {@link Releases} objects.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
@Repository(RELEASES_DAO)
@Transactional
public class ReleasesDAO extends SerializableDAO<Releases> {

    @Override
    public Releases find(long primaryKey) {
        return find(Releases.class, primaryKey);
    }
}
