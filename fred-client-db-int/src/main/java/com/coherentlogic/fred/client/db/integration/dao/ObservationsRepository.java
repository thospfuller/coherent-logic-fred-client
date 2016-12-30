package com.coherentlogic.fred.client.db.integration.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.coherentlogic.fred.client.core.domain.Observations;

/**
 * Data access pattern implementation for {@link Observations} objects.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
@Transactional
public interface ObservationsRepository extends JpaRepository<Observations, Long> {

}
