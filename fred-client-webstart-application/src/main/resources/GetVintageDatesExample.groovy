/* This example, which is written in Groovy script, sends a request to the
 * following URI:
 *
 * https://api.stlouisfed.org/fred/series/vintagedates
 *
 * Note that the api key has been set for you.
 *
 * SELECT COUNT(*) FROM VINTAGE_DATE
 *
 * SELECT * FROM VINTAGE_DATES, VINTAGE_DATES_VINTAGE_DATE, VINTAGE_DATE WHERE VINTAGE_DATES.PRIMARYKEY = VINTAGE_DATES_VINTAGE_DATE.VINTAGEDATES_PRIMARYKEY AND VINTAGE_DATE.PRIMARYKEY = VINTAGE_DATES_VINTAGE_DATE.VINTAGEDATELIST_PRIMARYKEY;
 */

import com.coherentlogic.fred.client.core.domain.VintageDates
import com.coherentlogic.fred.client.core.domain.SortOrder
import static com.coherentlogic.coherent.data.model.core.util.Utils.using

VintageDates vintageDates = queryBuilder
    .series ()
    .vintageDates ()
    .setSeriesId("GNPCA")
    .setRealtimeStart(
        using (2001, Calendar.JANUARY, 20)
    ).setRealtimeEnd(
        using (2004, Calendar.MAY, 17)
    ).setLimit(100)
    .setOffset(1)
    .setSortOrder(SortOrder.desc)
    .doGet(VintageDates.class)

vintageDatesDAO.persist(vintageDates)

return vintageDates