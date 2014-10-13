/* This example, which is written in Groovy script, sends a request to the
   following URI:
 *
 * http://api.stlouisfed.org/fred/series/categories
 *
 * Note that the api key has been set for you.
 */

import com.coherentlogic.fred.client.core.domain.Categories
import static com.coherentlogic.coherent.data.model.core.util.Utils.using

return queryBuilder
    .series ()
    .categories ()
    .setSeriesId("EXJPUS")
    .setRealtimeStart(
        using (2001, Calendar.JANUARY, 20)
    ).setRealtimeEnd(
        using (2004, Calendar.MAY, 17)
    ).doGet(Categories.class)