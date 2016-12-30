package com.coherentlogic.fred.client.db.integration.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.coherentlogic.fred.client.core.domain.Tags;

/**
 * Data access pattern implementation for {@link Tags} objects.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
@Transactional
public interface TagsRepository extends JpaRepository<Tags, Long> {

}
