/* This example, which is written in Groovy script, sends a request to the
 * following URI:
 *
 * http://api.stlouisfed.org/fred/release/sources
 *
 * Note that the api key has been set for you.
 */

import com.coherentlogic.fred.client.core.domain.Sources

return queryBuilder
    .release ()
    .sources ()
    .setReleaseId(51L)
    .setRealtimeStart("2010-06-01")
    .setRealtimeEnd("2012-06-18")
    .doGet(Sources.class)