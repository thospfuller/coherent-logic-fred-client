package com.coherentlogic.fred.client.core.domain;

import static com.coherentlogic.fred.client.core.domain.PropertyNames.DATE_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.PropertyNames.VALUE_PROPERTY;
import static com.coherentlogic.fred.client.core.util.Constants.DATE;
import static com.coherentlogic.fred.client.core.util.Constants.OBSERVATION;
import static com.coherentlogic.fred.client.core.util.Constants.OBSERVATION_DATE;
import static com.coherentlogic.fred.client.core.util.Constants.OBSERVATION_VALUE;
import static com.coherentlogic.fred.client.core.util.Constants.VALUE;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.coherentlogic.coherent.data.model.core.domain.SerializableBean;
import com.coherentlogic.fred.client.core.converters.ObservationDateConverter;
import com.coherentlogic.fred.client.core.converters.ObservationValueConverter;
import com.coherentlogic.fred.client.core.util.Constants;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;

/**
 * A class which represents an observation or data value for an economic data
 * series.
 *
 * @see <a href="https://api.stlouisfed.org/docs/fred/series_observations.html">
 * series_observations</a>
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
@Entity
@Table(name=OBSERVATION)
@XStreamAlias(OBSERVATION)
public class Observation extends SerializableBean
    implements RealtimeBoundSpecification {

    private static final long serialVersionUID = -625129582205856675L;

    @XStreamAlias(Constants.REALTIME_START)
    @XStreamAsAttribute
    private Date realtimeStart = null;

    @XStreamAlias(Constants.REALTIME_END)
    @XStreamAsAttribute
    private Date realtimeEnd = null;

    @XStreamAlias(DATE)
    @XStreamAsAttribute
    @XStreamConverter(ObservationDateConverter.class)
    private Date date = null;

    @XStreamAlias(VALUE)
    @XStreamAsAttribute
    @XStreamConverter(ObservationValueConverter.class)
    private BigDecimal value = null;

    /**
     * @see com.coherentlogic.fred.client.core.domain.RealtimeBoundSpecification#setRealtimeStart(Date)
     */
    @Override
    public Date getRealtimeStart() {
        return clone (realtimeStart);
    }

    /**
     * @see com.coherentlogic.fred.client.core.domain.RealtimeBoundSpecification#getRealtimeStart()
     */
    @Override
    public void setRealtimeStart(Date realtimeStart) {

        Date oldValue = this.realtimeStart;

        this.realtimeStart = clone (realtimeStart);

        firePropertyChange(REALTIME_START_PROPERTY, oldValue, realtimeStart);
    }

    /**
     * @see com.coherentlogic.fred.client.core.domain.RealtimeBoundSpecification#getRealtimeEnd()
     */
    @Override
    public Date getRealtimeEnd() {
        return clone (realtimeEnd);
    }

    /**
     * @see com.coherentlogic.fred.client.core.domain.RealtimeBoundSpecification#setRealtimeEnd
     */
    @Override
    public void setRealtimeEnd(Date realtimeEnd) {

        Date oldValue = this.realtimeEnd;

        this.realtimeEnd = clone (realtimeEnd);

        firePropertyChange(REALTIME_END_PROPERTY, oldValue, realtimeEnd);
    }

    /**
     * Getter method for the observation date property.
     */
    @Column(name=OBSERVATION_DATE)
    public Date getDate() {
        return clone (date);
    }

    /**
     * Setter method for the observation date property.
     */
    public void setDate(Date date) {

        Date oldValue = this.date;

        this.date = clone (date);

        firePropertyChange(DATE_PROPERTY, oldValue, date);
    }

    /**
     * Getter method for the observation value property.
     */
    @Column(name=OBSERVATION_VALUE)
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Setter method for the observation value property.
     */
    public void setValue(BigDecimal value) {

        BigDecimal oldValue = this.value;

        this.value = value;

        firePropertyChange(VALUE_PROPERTY, oldValue, value);
    }
}
