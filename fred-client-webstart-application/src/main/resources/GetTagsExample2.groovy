/* This example is written in Groovy script.
 *
 * Note that the api key has been set for you.
 *
 * Example H2 Queries:
 *
 * SELECT COUNT(*) FROM TAG
 *
 * SELECT * FROM TAGS, TAGS_TAG, TAG WHERE TAGS.PRIMARYKEY = TAGS_TAG.TAGS_PRIMARYKEY AND TAG.PRIMARYKEY = TAGS_TAG.TAGLIST_PRIMARYKEY;
 */

import com.coherentlogic.fred.client.core.domain.Tags
import static com.coherentlogic.coherent.data.model.core.util.Utils.using

Date realtimeStart = using (2001, Calendar.JANUARY, 20);
Date realtimeEnd = using (2004, Calendar.MAY, 17);

Tags tags = queryBuilder
    .series()
    .search()
    .tags()
    .withRealtimeStart(realtimeStart)
    .withRealtimeEnd(realtimeEnd)
    .withSeriesSearchText("monetary service index")
    .doGet(Tags.class);

// 
// Uncomment to save to H2.
//
// tagsService.save(tags)

return tags