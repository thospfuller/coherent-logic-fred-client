package com.coherentlogic.fred.client.db.integration.dao;

import static com.coherentlogic.fred.client.db.integration.dao.DAOConstants.SOURCES_DAO;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coherentlogic.fred.client.core.domain.Sources;

/**
 * Data access pattern implementation for {@link Sources} objects.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
@Repository(SOURCES_DAO)
@Transactional
public class SourcesDAO extends SerializableDAO<Sources> {

    @Override
    public Sources find(long primaryKey) {
        return find(Sources.class, primaryKey);
    }
}
