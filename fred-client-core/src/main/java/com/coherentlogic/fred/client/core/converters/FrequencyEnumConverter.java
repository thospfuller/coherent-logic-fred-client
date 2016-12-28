package com.coherentlogic.fred.client.core.converters;

import com.coherentlogic.fred.client.domain.Frequency;
import com.thoughtworks.xstream.converters.enums.EnumSingleValueConverter;

/**
 * It turns out only SingleValueConverter(s) work for attributes.
 * 
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 *
 */
public class FrequencyEnumConverter extends EnumSingleValueConverter {

    public FrequencyEnumConverter(Class<? extends Enum<?>> type) {
        super(type);
    }

    @Override
    public Object fromString(String value) {
        value = value.toLowerCase();
        try {
            return Frequency.valueOf(value);
        } catch (IllegalArgumentException ex) {
            return Frequency.valueOf("_"+value);
        }
    }
}
