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

import static com.coherentlogic.coherent.data.model.core.util.Utils.using

def releases = queryBuilder
    .series ()
    .release ()
    .withSeriesId("IRA")
    .withRealtimeStart(
        using (2001, Calendar.JANUARY, 20)
    ).withRealtimeEnd(
        using (2004, Calendar.MAY, 17)
    ).doGetAsReleases() // New method

// 
// Uncomment to save to H2.
//
// releasesService.save (releases)

return releases