package com.coherentlogic.fred.client.core.domain;

import static com.coherentlogic.fred.client.core.util.Constants.ANNUALLY;
import static com.coherentlogic.fred.client.core.util.Constants.BI_WEEKLY;
import static com.coherentlogic.fred.client.core.util.Constants.BI_WEEKLY_ENDING_MONDAY;
import static com.coherentlogic.fred.client.core.util.Constants.BI_WEEKLY_ENDING_WEDNESDAY;
import static com.coherentlogic.fred.client.core.util.Constants.DAILY;
import static com.coherentlogic.fred.client.core.util.Constants.MONTHLY;
import static com.coherentlogic.fred.client.core.util.Constants.QUARTERLY;
import static com.coherentlogic.fred.client.core.util.Constants.SEMI_ANNUALLY;
import static com.coherentlogic.fred.client.core.util.Constants.WEEKLY;
import static com.coherentlogic.fred.client.core.util.Constants.WEEKLY_ENDING_FRIDAY;
import static com.coherentlogic.fred.client.core.util.Constants.WEEKLY_ENDING_MONDAY;
import static com.coherentlogic.fred.client.core.util.Constants.WEEKLY_ENDING_THURSDAY;
import static com.coherentlogic.fred.client.core.util.Constants.WEEKLY_ENDING_TUESDAY;
import static com.coherentlogic.fred.client.core.util.Constants.WEEKLY_ENDING_WEDNESDAY;
import java.util.HashMap;

/**
 * An optional parameter that indicates a lower frequency to aggregate values
 * to.
 * <p>
 * Read the 'Frequency Aggregation' section of the FRED FAQs for implementation
 * details.
 * <p>
 * @see <a href="https://api.stlouisfed.org/docs/fred/series_observations.html#frequency">frequency</a>
 *
 * @author <a href="support@coherentlogic.com">Support</a>
 */
public enum Frequency {

    daily (DAILY),
    weekly (WEEKLY),
    biWeekly (BI_WEEKLY),
    monthly (MONTHLY),
    quarterly (QUARTERLY),
    semiAnnually (SEMI_ANNUALLY),
    annually (ANNUALLY),
    weeklyEndingFriday (WEEKLY_ENDING_FRIDAY),
    weeklyEndingThursday (WEEKLY_ENDING_THURSDAY),
    weeklyEndingWednesday (WEEKLY_ENDING_WEDNESDAY),
    weeklyEndingTuesday (WEEKLY_ENDING_TUESDAY),
    weeklyEndingMonday (WEEKLY_ENDING_MONDAY),
    biWeeklyEndingWednesday (BI_WEEKLY_ENDING_WEDNESDAY),
    biWeeklyEndingMonday (BI_WEEKLY_ENDING_MONDAY);

    private final String value;

    // inverse lookup table
    private final static HashMap<String, Frequency> byString
            = new HashMap<>();
    
    static {
        for (Frequency frequency : Frequency.values()) {
            byString.put(frequency.toString().toLowerCase(), frequency);
        }
    }
    
    Frequency (String value) {
        this.value = value;
    }

    public static Frequency fromString(String frequency) {
        return byString.get(frequency.toLowerCase());
    }
    
    @Override
    public String toString () {
        return value;
    }
}
