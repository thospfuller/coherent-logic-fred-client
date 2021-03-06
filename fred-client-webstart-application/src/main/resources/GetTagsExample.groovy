/* This example, which is written in Groovy script, sends a request to the
   following URI:
 *
 * https://api.stlouisfed.org/fred/tags/series?tag_names=slovenia;food;oecd
 *
 * Note that the api key has been set for you.
 *
 * Example H2 Queries:
 *
 * SELECT COUNT(*) FROM TAG
 *
 * SELECT * FROM TAGS, TAGS_TAG, TAG WHERE TAGS.PRIMARYKEY = TAGS_TAG.TAGS_PRIMARYKEY AND TAG.PRIMARYKEY = TAGS_TAG.TAGLIST_PRIMARYKEY;
 */

def tags = queryBuilder
    .tags ()
    .doGetAsTags() // New method

// 
// Uncomment to save to H2.
//
// tagsService.save(tags)

return tags