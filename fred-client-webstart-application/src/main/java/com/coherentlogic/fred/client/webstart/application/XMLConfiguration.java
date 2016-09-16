package com.coherentlogic.fred.client.webstart.application;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author <a href="https://www.linkedin.com/in/thomasfuller">Thomas P. Fuller</a>
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
@Configuration
@ImportResource({"classpath*:jpa-beans.xml", "classpath*:api-key-beans.xml", "classpath*:application-context.xml"})
public class XMLConfiguration {

}
