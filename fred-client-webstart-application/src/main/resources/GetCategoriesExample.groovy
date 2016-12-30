/* This example, which is written in Groovy script, sends a request to the
   following URI:
 *
 * https://api.stlouisfed.org/fred/series/categories
 *
 * Note that the api key has been set for you.
 *
 * See also: http://stackoverflow.com/questions/17643456/spring3-jpa-hibernate4-not-persisting
 *
 * SELECT COUNT(*) FROM CATEGORY
 *
 * SELECT * FROM CATEGORIES, CATEGORIES_CATEGORY, CATEGORY WHERE CATEGORIES.PRIMARYKEY = CATEGORIES_CATEGORY.CATEGORIES_PRIMARYKEY AND CATEGORY.PRIMARYKEY = CATEGORIES_CATEGORY.CATEGORYLIST_PRIMARYKEY;
 */

import static com.coherentlogic.coherent.data.model.core.util.Utils.using

def categories = queryBuilder
    .series ()
    .categories ()
    .withSeriesId("EXJPUS")
    .withRealtimeStart(
        using (2001, Calendar.JANUARY, 20)
    ).withRealtimeEnd(
        using (2004, Calendar.MAY, 17)
    ).doGetAsCategories() // New method

// 
// Uncomment to save to H2.
//
// categoriesService.save (categories)

return categories