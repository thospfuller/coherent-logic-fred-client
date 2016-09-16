/* This example, which is written in Groovy script, sends a request to the
 * following URI:
 *
 * https://api.stlouisfed.org/fred/series/observations
 *
 * Note that the api key has been set for you.
 *
 * SELECT COUNT(*) FROM OBSERVATION
 *
 * SELECT * FROM OBSERVATIONS, OBSERVATIONS_OBSERVATION, OBSERVATION WHERE OBSERVATIONS.PRIMARYKEY = OBSERVATIONS_OBSERVATION.OBSERVATIONS_PRIMARYKEY AND OBSERVATION.PRIMARYKEY = OBSERVATIONS_OBSERVATION.OBSERVATIONLIST_PRIMARYKEY;
 */
import com.coherentlogic.fred.client.core.domain.Observations

Observations observations = queryBuilder
    .series ()
    .observations ()
    .setSeriesId("EXJPUS")
    .doGet(Observations.class)

observationsDAO.persist (observations)

return observations