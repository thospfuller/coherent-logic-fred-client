/* This example, which is written in Groovy script, sends a request to the
 * following URI:
 *
 * https://api.stlouisfed.org/fred/releases/dates
 *
 * Note that the api key has been set for you.
 *
 * SELECT COUNT(*) FROM RELEASE_DATE
 *
 * SELECT * FROM RELEASE_DATES, RELEASE_DATES_RELEASE_DATE, RELEASE_DATE WHERE RELEASE_DATES.PRIMARYKEY = RELEASE_DATES_RELEASE_DATE.RELEASEDATES_PRIMARYKEY AND RELEASE_DATE.PRIMARYKEY = RELEASE_DATES_RELEASE_DATE.RELEASEDATELIST_PRIMARYKEY;
 */

def releaseDates = queryBuilder
    .releases ()
    .dates ()
    .withRealtimeStart("2012-06-18")
    .withRealtimeEnd("2012-06-18")
    .doGetAsReleaseDates () // New method

// 
// Uncomment to save to H2.
//
// releaseDatesService.save (releaseDates)

return releaseDates