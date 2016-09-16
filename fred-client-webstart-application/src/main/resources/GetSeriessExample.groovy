/* This example, which is written in Groovy script, sends a request to the
 * following URI:
 *
 * https://api.stlouisfed.org/fred/category/series
 *
 * Note that the api key has been set for you.
 *
 * SELECT COUNT(*) FROM SERIES
 *
 * SELECT * FROM SERIESS, SERIESS_SERIES, SERIES WHERE SERIESS.PRIMARYKEY = SERIESS_SERIES.SERIESS_PRIMARYKEY AND SERIES.PRIMARYKEY = SERIESS_SERIES.SERIESLIST_PRIMARYKEY; 
 */

import com.coherentlogic.fred.client.core.domain.Seriess
import static com.coherentlogic.coherent.data.model.core.util.Utils.using

Seriess seriess = queryBuilder
    .category ()
    .series ()
    .setSeriesId("GNPCA")
    .setRealtimeStart(
        using (2001, Calendar.JANUARY, 20)
    ).setRealtimeEnd(
        using (2004, Calendar.MAY, 17)
    ).doGet (Seriess.class)

seriessDAO.persist (seriess)

return seriess