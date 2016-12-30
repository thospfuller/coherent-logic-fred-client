/* This example, which is written in Groovy script, sends a request to the
 * following URI:
 *
 * https://api.stlouisfed.org/fred/release/sources
 *
 * Note that the api key has been set for you.
 *
 * SELECT COUNT(*) FROM SOURCES
 *
 * SELECT * FROM SOURCES, SOURCES_SOURCE, SOURCE WHERE SOURCES.PRIMARYKEY = SOURCES_SOURCE.SOURCES_PRIMARYKEY AND SOURCE.PRIMARYKEY = SOURCES_SOURCE.SOURCELIST_PRIMARYKEY;
 */

import com.coherentlogic.fred.client.core.domain.Sources

Sources sources = queryBuilder
    .release ()
    .sources ()
    .withReleaseId(51L)
    .withRealtimeStart("2010-06-01")
    .withRealtimeEnd("2012-06-18")
    .doGet(Sources.class)

// 
// Uncomment to save to H2.
//
// sourcesService.save(sources)

return sources