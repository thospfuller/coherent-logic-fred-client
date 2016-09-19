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
import joinery.DataFrame
import joinery.DataFrame.PlotType

Observations observations = queryBuilder
    .series ()
    .observations ()
    .setSeriesId("EXJPUS")
    .doGet(Observations.class)

log.info "queryBuilder.escapedURI: ${queryBuilder.escapedURI}"

observationsDAO.persist (observations)

def dataFrame = new DataFrame<Object> ()

def observationDates = [] as List
def observationValues = [] as List<BigDecimal>

observations.observationList.forEach {
    observationDates << it.date
    observationValues << it.value
}

dataFrame.add((Object) "Observation Date", (List<Object>) observationDates)
dataFrame.add((Object) "Observation Values", (List<Object>) observationValues)

dataFrame.plot(PlotType.LINE)
dataFrame.show()

return observations