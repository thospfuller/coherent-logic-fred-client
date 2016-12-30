package com.coherentlogic.fred.client.db.integration.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.coherentlogic.fred.client.core.domain.VintageDates;

/**
 * Repository specification for {@link VintageDates} objects.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
@Transactional
public interface VintageDatesRepository extends JpaRepository<VintageDates, Long> {

}
