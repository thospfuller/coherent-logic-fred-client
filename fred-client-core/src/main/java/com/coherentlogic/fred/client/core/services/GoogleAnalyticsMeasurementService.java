package com.coherentlogic.fred.client.core.services;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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
public class GoogleAnalyticsMeasurementService {

    private static final Logger log = LoggerFactory.getLogger(GoogleAnalyticsMeasurementService.class);

    static final String GOOGLE_ANALYTICS_TRACKING_KEY = "GOOGLE_ANALYTICS_TRACKING";

    public boolean shouldTrack () {

        String gatValue = System.getProperty(GOOGLE_ANALYTICS_TRACKING_KEY);

        return (gatValue == null || Boolean.parseBoolean(gatValue));
    }

    /**
     *
     */
    public void fireGAFrameworkUsageEvent () {

        log.debug("fireGAFrameworkUsageEvent: method begins.");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://www.google-analytics.com/collect")
            .queryParam("v", "1")
            .queryParam("tid", "UA-1434183-1")
            .queryParam("cid", UUID.randomUUID().toString())
            .queryParam("t", "event")
            .queryParam("ec", "FRED Client Framework Usage") // event category
            .queryParam("an", "FRED Client") // application name
            .queryParam("ea", "Framework Started") // event action
            .queryParam("av", "Version 1.0.10-RELEASE") // Application version.
            .queryParam("el", "Version 1.0.10-RELEASE");

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<?> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate ();

        HttpEntity<String> response = restTemplate.exchange(
            builder.build().encode().toUri(),
            HttpMethod.POST,
            entity,
            String.class
        );

        log.debug("fireGAFrameworkUsageEvent: method ends; response: " + response);
    }
}


//String gatValue = System.getProperty(GOOGLE_ANALYTICS_TRACKING_KEY);
//
//if (gatValue == null || Boolean.getBoolean(gatValue)) {
//    try {
//        fireGAFrameworkUsageEvent ();
//    } catch (Throwable thrown) {
//        log.warn("fireGAFrameworkUsageEvent: method call failed.", thrown);
//    }
//}