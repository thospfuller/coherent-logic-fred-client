package com.coherentlogic.fred.client.db.integration.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.coherentlogic.fred.client.core.domain.Categories;
import com.coherentlogic.fred.client.core.domain.Sources;

/**
 * Data access pattern implementation for {@link Sources} objects.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
@Transactional
public interface SourcesRepository extends JpaRepository<Sources, Long> {

}
