/* This example, which is written in Groovy script, sends a request to the
 * following URI:
 *
 * https://api.stlouisfed.org/fred/series/observations
 *
 * Note that the api key has been set for you.
 */
import com.coherentlogic.fred.client.core.domain.Observations

return queryBuilder
    .series ()
    .observations ()
    .setSeriesId("EXJPUS")
    .doGet(Observations.class)