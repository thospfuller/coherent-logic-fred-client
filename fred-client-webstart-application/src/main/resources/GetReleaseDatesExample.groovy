/* This example, which is written in Groovy script, sends a request to the
 * following URI:
 *
 * https://api.stlouisfed.org/fred/releases/dates
 *
 * Note that the api key has been set for you.
 */

import com.coherentlogic.fred.client.core.domain.ReleaseDates

return queryBuilder
    .releases ()
    .dates ()
    .setRealtimeStart("2012-06-18")
    .setRealtimeEnd("2012-06-18")
    .doGet(ReleaseDates.class)