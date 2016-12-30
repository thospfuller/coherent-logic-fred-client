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
    .withSeriesId("GNPCA")
    .withRealtimeStart(
        using (2001, Calendar.JANUARY, 20)
    ).withRealtimeEnd(
        using (2004, Calendar.MAY, 17)
    ).withLimit(100)
    .withOffset(1)
    .withSortOrder(SortOrder.desc)
    .doGet(VintageDates.class)

// 
// Uncomment to save to H2.
//
// vintageDatesService.save(vintageDates)

return vintageDates