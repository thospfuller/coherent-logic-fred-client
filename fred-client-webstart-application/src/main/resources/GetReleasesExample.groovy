/* This example, which is written in Groovy script, sends a request to the
 * following URI:
 *
 * https://api.stlouisfed.org/fred/series/release
 *
 * Note that the api key has been set for you.
 *
 * SELECT COUNT(*) FROM RELEASE_TABLE
 *
 * SELECT * FROM RELEASES, RELEASES_RELEASE_TABLE, RELEASE_TABLE WHERE RELEASES.PRIMARYKEY = RELEASES_RELEASE_TABLE.RELEASES_PRIMARYKEY AND RELEASE_TABLE.PRIMARYKEY = RELEASES_RELEASE_TABLE.RELEASELIST_PRIMARYKEY;
 */

import com.coherentlogic.fred.client.core.domain.Releases
import static com.coherentlogic.coherent.data.model.core.util.Utils.using

Releases releases = queryBuilder
    .series ()
    .release ()
    .withSeriesId("IRA")
    .withRealtimeStart(
        using (2001, Calendar.JANUARY, 20)
    ).withRealtimeEnd(
        using (2004, Calendar.MAY, 17)
    ).doGet(Releases.class)

// 
// Uncomment to save to H2.
//
// releasesDAO.persist (releases)

return releases