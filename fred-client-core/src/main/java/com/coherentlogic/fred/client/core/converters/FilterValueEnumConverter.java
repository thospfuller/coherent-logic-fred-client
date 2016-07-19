package com.coherentlogic.fred.client.core.converters;

import com.coherentlogic.fred.client.core.domain.FilterValue;
import com.thoughtworks.xstream.converters.enums.EnumSingleValueConverter;

/**
 * It turns out only SingleValueConverter(s) work for attributes.
 * 
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 *
 */
public class FilterValueEnumConverter extends EnumSingleValueConverter {

    public FilterValueEnumConverter(Class<? extends Enum<?>> type) {
        super(type);
    }

    @Override
    public Object fromString(String value) {

        return FilterValue.valueOf(value);
    }
}
