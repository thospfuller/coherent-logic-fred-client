package com.coherentlogic.fred.client.core.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coherentlogic.coherent.data.adapter.core.services.AbstractGoogleAnalyticsMeasurementService;
import com.coherentlogic.gama.client.core.builders.QueryBuilder;

/**
 * Class is used to send events to Google Analytics via the Measurement API.
 *
 * @see <a href="https://developers.google.com/analytics/devguides/collection/protocol/v1/devguide">Working with the
 *  Measurement Protocol</a>
 *
 * @see <a href="https://ga-dev-tools.appspot.com/hit-builder/">Hit Builder</a>
 *
 * @author <a href="https://www.linkedin.com/in/thomasfuller">Thomas P. Fuller</a>
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class GoogleAnalyticsMeasurementService extends AbstractGoogleAnalyticsMeasurementService {

    private static final Logger log = LoggerFactory.getLogger(GoogleAnalyticsMeasurementService.class);

    private final String applicationName;

    public GoogleAnalyticsMeasurementService(String applicationName) {
        this.applicationName = applicationName;
    }

    @Override
    public void fireGAFrameworkUsageEvent () {

        log.info("fireGAFrameworkUsageEvent: method begins.");

        // WARNING: The QueryBuilder must be used once before this will be called so if you see nothing in GA when
        //          testing this, that is the reason why.
        String response = new QueryBuilder ()
            .withV1()
            .withTid("[Add your UA id here]")
            .withCIDAsRandomUUID()
            .withTAsEvent()
            .withEc("Framework Usage") // event category
            .withAn(applicationName) // application name
            .withEa("Framework Started") // event action
            .withAv("Version 2.0.0-RELEASE") // Application version.
            .withEl("Version 2.0.0-RELEASE")
            .doPost();

        log.info("fireGAFrameworkUsageEvent: method ends; response: " + response);
    }
}
