package com.coherentlogic.fred.client.core.converters;

import com.thoughtworks.xstream.converters.basic.BigDecimalConverter;

/**
 * The observation value can be "." and hence we need to treat this conversion
 * as a special case, since the BigDecimalConverter will throw an exception if
 * that string is passed to it.
 *
 * @author <a href="support@coherentlogic.com">Support</a>
 */
public class ObservationValueConverter extends BigDecimalConverter {

    @Override
    public Object fromString(String value) {

        Object result = null;

        if (value != null && !(".".equals(value)))
            result = super.fromString(value);

        return result;
    }
}
