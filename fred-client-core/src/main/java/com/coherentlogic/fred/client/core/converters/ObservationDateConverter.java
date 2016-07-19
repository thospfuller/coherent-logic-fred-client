package com.coherentlogic.fred.client.core.converters;

import com.thoughtworks.xstream.converters.basic.DateConverter;
import java.util.Locale;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * The observation value can be "." and hence we need to treat this conversion
 * as a special case, since the BigDecimalConverter will throw an exception if
 * that string is passed to it.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class ObservationDateConverter extends DateConverter {

    private static final DateTimeFormatter FORMATTER
            = DateTimeFormat.forPattern("yyyy-MM-dd")
                    .withLocale(Locale.ROOT)
                    .withZoneUTC();
    
    @Override
    public Object fromString(String value) {
        return FORMATTER.parseDateTime(value).toDate();
    }
}
