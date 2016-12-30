package com.coherentlogic.fred.client.db.integration.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.coherentlogic.fred.client.core.domain.Seriess;

/**
 * Data access pattern implementation for {@link Seriess} objects.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
@Transactional
public interface SeriessRepository extends JpaRepository<Seriess, Long> {

}
