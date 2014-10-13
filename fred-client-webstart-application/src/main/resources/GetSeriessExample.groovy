/* This example, which is written in Groovy script, sends a request to the
 * following URI:
 *
 * http://api.stlouisfed.org/fred/category/series
 *
 * Note that the api key has been set for you.
 */

import com.coherentlogic.fred.client.core.domain.Seriess
import static com.coherentlogic.coherent.data.model.core.util.Utils.using

return queryBuilder
    .category ()
    .series ()
    .setSeriesId("GNPCA")
    .setRealtimeStart(
        using (2001, Calendar.JANUARY, 20)
    ).setRealtimeEnd(
        using (2004, Calendar.MAY, 17)
    ).doGet (Seriess.class)