/* This example, which is written in Groovy script, sends a request to the
   following URI:
 *
 * https://api.stlouisfed.org/fred/tags/series?tag_names=slovenia;food;oecd
 *
 * Note that the api key has been set for you.
 */

import com.coherentlogic.fred.client.core.domain.Tags

return queryBuilder
    .tags ()
    .doGet(Tags.class)