/* This example is written in Groovy script.
 *
 * Note that the api key has been set for you.
 */

import com.coherentlogic.fred.client.core.domain.Tags
import static com.coherentlogic.coherent.data.model.core.util.Utils.using

Date realtimeStart = using (2001, Calendar.JANUARY, 20);
Date realtimeEnd = using (2004, Calendar.MAY, 17);

return queryBuilder
    .series()
    .search()
    .tags()
    .setRealtimeStart(realtimeStart)
    .setRealtimeEnd(realtimeEnd)
    .setSeriesSearchText("monetary service index")
    .doGet(Tags.class);