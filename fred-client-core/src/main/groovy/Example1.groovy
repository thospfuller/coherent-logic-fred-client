@GrabConfig(systemClassLoader=true)

@Grab(group='com.coherentlogic.coherent', module='data-model', version='1.0.8-RELEASE')
@Grab(group='com.coherentlogic.fred.client', module='fred-client-core', version='0.9.14')
@Grab(group='com.coherentlogic.fred.client', module='fred-client-db-int', version='0.9.14')
@Grab(group='com.coherentlogic.fred.client', module='fred-client-misc', version='0.9.14')

@Grab(group='org.springframework', module='spring-core', version='3.2.4.RELEASE')
@Grab(group='org.springframework', module='spring-beans', version='3.2.4.RELEASE')
@Grab(group='org.springframework', module='spring-context', version='3.2.4.RELEASE')
@Grab(group='org.springframework', module='spring-web', version='3.2.4.RELEASE')
@Grab(group='org.springframework', module='spring-orm', version='3.2.4.RELEASE')
@Grab(group='org.springframework.ws', module='spring-ws-core', version='2.1.3.RELEASE')

@Grab(group='cglib', module='cglib', version='2.2.2')
@Grab(group='org.hibernate', module='hibernate-core', version='4.1.7.Final')
@Grab(group='org.hibernate', module='hibernate-entitymanager', version='4.1.7.Final')

@Grab(group='org.eclipse.persistence', module='javax.persistence', version='2.0.0')
@Grab(group='com.thoughtworks.xstream', module='xstream', version='1.4.4')

@Grab(group='org.glassfish.jersey.core', module='jersey-common', version='2.0-m13-3')
@Grab(group='javax.ws.rs', module='javax.ws.rs-api', version='2.0-m16')
@Grab(group='commons-lang', module='commons-lang', version='2.6')

@Grab(group='commons-beanutils', module='commons-beanutils', version='1.8.3')
@Grab(group='log4j', module='log4j', version='1.2.16')
@Grab(group='org.slf4j', module='slf4j-api', version='1.6.4')
@Grab(group='org.slf4j', module='slf4j-log4j12', version='1.6.4')

import com.coherentlogic.fred.client.core.builders.QueryBuilder
import com.coherentlogic.fred.client.core.factories.QueryBuilderFactory
import com.coherentlogic.fred.client.core.domain.Seriess

import static com.coherentlogic.coherent.data.model.core.util.Utils.using

import org.springframework.context.ApplicationContext
import org.springframework.context.support.GenericApplicationContext
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader
import org.springframework.core.io.ByteArrayResource

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeEvent

import org.springframework.web.client.RestClientException
import org.springframework.web.client.HttpClientErrorException

def applicationContextXML = '''
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xmlns:util="http://www.springframework.org/schema/util"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/util
        http://www.springframework.org/schema/util/spring-util.xsd">

    <bean id="fredRestTemplate" class="org.springframework.web.client.RestTemplate">
        <property name="messageConverters">
            <list>
                <bean id="xstreamMessageConverter"
                    class="org.springframework.http.converter.xml.MarshallingHttpMessageConverter">
                    <property name="marshaller" ref="xstreamMarshaller" />
                    <property name="unmarshaller" ref="xstreamMarshaller" />
                </bean>
                <bean id="applicationZipMessageConverter"
                    class="com.coherentlogic.fred.client.core.converters.ApplicationZipMessageConverter"/>
            </list>
        </property>
    </bean>

    <bean id="fredDateConverter" class="com.thoughtworks.xstream.converters.basic.DateConverter">
        <constructor-arg name="defaultFormat" value="yyyy-MM-dd"/>
        <constructor-arg name="acceptableFormats">
            <list>
                <value>yyyy-MM-dd</value>
            </list>
        </constructor-arg>
    </bean>

    <bean id="fredOutputTypeConverter" class="com.coherentlogic.fred.client.core.converters.OutputTypeEnumConverter">
        <constructor-arg name="type" value="com.coherentlogic.fred.client.core.domain.OutputType"/>
    </bean>

    <bean id="fredSortOrderConverter" class="com.coherentlogic.fred.client.core.converters.SortOrderEnumConverter">
        <constructor-arg name="type" value="com.coherentlogic.fred.client.core.domain.SortOrder"/>
    </bean>

    <bean id="fredOrderByEnumConverter" class="com.coherentlogic.fred.client.core.converters.OrderByEnumConverter">
        <constructor-arg name="type" value="com.coherentlogic.fred.client.core.domain.OrderBy"/>
    </bean>

    <bean id="fredFilterValueEnumConverter" class="com.coherentlogic.fred.client.core.converters.FilterValueEnumConverter">
        <constructor-arg name="type" value="com.coherentlogic.fred.client.core.domain.FilterValue"/>
    </bean>

    <bean id="xstreamMarshaller" class="com.coherentlogic.coherent.data.model.core.xstream.CustomXStreamMarshaller">
        <!-- Underscores in attributes cause problems with XStream and the stream driver below
             addresses this issue - in particular see the XmlFriendlyReplacer.
          -->
        <property name="streamDriver">
            <bean class="com.thoughtworks.xstream.io.xml.XppDriver">
                <constructor-arg>
                    <bean class="com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer">
                        <constructor-arg index="0" value="_-"/>
                        <constructor-arg index="1" value="_"/>
                    </bean>
                </constructor-arg>
            </bean>
        </property>
        <property name="autodetectAnnotations" value="true" />
        <property name="annotatedClasses">
            <array>
                <value>com.coherentlogic.fred.client.core.domain.Sources</value>
                <value>com.coherentlogic.fred.client.core.domain.Source</value>
                <value>com.coherentlogic.fred.client.core.domain.Seriess</value>
                <value>com.coherentlogic.fred.client.core.domain.Series</value>
                <value>com.coherentlogic.fred.client.core.domain.Categories</value>
                <value>com.coherentlogic.fred.client.core.domain.Category</value>
                <value>com.coherentlogic.fred.client.core.domain.Observations</value>
                <value>com.coherentlogic.fred.client.core.domain.Observation</value>
                <value>com.coherentlogic.fred.client.core.domain.Releases</value>
                <value>com.coherentlogic.fred.client.core.domain.Release</value>
                <value>com.coherentlogic.fred.client.core.domain.VintageDates</value>
                <value>com.coherentlogic.fred.client.core.domain.VintageDate</value>
                <value>com.coherentlogic.fred.client.core.domain.ReleaseDates</value>
                <value>com.coherentlogic.fred.client.core.domain.ReleaseDate</value>
                <value>com.coherentlogic.fred.client.core.domain.Tags</value>
                <value>com.coherentlogic.fred.client.core.domain.Tag</value>
            </array>
        </property>
        <!-- TODO: Consider using annotations exclusively and remove the
                   converters from here.
          -->
        <property name="converters">
            <array>
                <ref bean="fredDateConverter"/>
                <ref bean="fredOutputTypeConverter"/>
                <ref bean="fredSortOrderConverter"/>
                <ref bean="fredOrderByEnumConverter"/>
                <ref bean="fredFilterValueEnumConverter"/>
            </array>
        </property>
    </bean>
    
    <bean id="cacheServiceProvider" class="com.coherentlogic.coherent.data.model.core.cache.MapCompliantCacheServiceProvider">
        <constructor-arg name="cache"><util:map/></constructor-arg>
    </bean>

    <bean id="queryBuilderFactory" class="com.coherentlogic.fred.client.core.factories.QueryBuilderFactory">
        <constructor-arg name="restTemplate" ref="fredRestTemplate"/>
        <constructor-arg name="uri" value="http://api.stlouisfed.org/fred/"/>
        <constructor-arg name="cacheServiceProvider" ref="cacheServiceProvider"/>
        <!-- NOTE: You must supply your own API key to run this example.
          -->
        <constructor-arg name="apiKey" value="YOU NEED TO PROVIDE YOUR OWN API KEY!!!"/>
    </bean>
</beans>
'''

def xmlBytes = applicationContextXML.getBytes()

def resource = new ByteArrayResource(xmlBytes)

def applicationContext = new GenericApplicationContext()
def xmlBeanDefinitionReader = new XmlBeanDefinitionReader (applicationContext)

xmlBeanDefinitionReader.loadBeanDefinitions (resource)

//applicationContext.refresh ()

def QUERY_BUILDER_FACTORY = "queryBuilderFactory"

def queryBuilderFactory = applicationContext.getBean (QUERY_BUILDER_FACTORY)

def queryBuilder = queryBuilderFactory.getInstance ()

def seriess = null

try {
    seriess = queryBuilder
        .category ()
        .series ()
        .setSeriesId("GNPCA")
        .setRealtimeStart(
            using (2001, Calendar.JANUARY, 20)
        ).setRealtimeEnd(
            using (2004, Calendar.MAY, 17)
        ).doGet (Seriess.class)
} catch (HttpClientErrorException exception) {
    // If you see this, then you need to set the value for the constructor-arg in the
    // Spring application context above -- specifically:
    // <constructor-arg name="apiKey" value="YOU NEED TO PROVIDE YOUR OWN API KEY!!!"/>
    throw new RestClientException (
'''\n*********************************************************************************
*** Did you forget to set the apiKey value?                                   ***
***                                                                           ***
*** This is required in order to run this script and you need to provide this ***
*** yourself.                                                                 ***
***                                                                           ***
*** See here: http://api.stlouisfed.org/api_key.html                          ***
*********************************************************************************''', exception)
}

seriess.addPropertyChangeListener (
    new PropertyChangeListener () {
        @Override
        public void propertyChange(PropertyChangeEvent event) {
            // NOTE THAT WHEN THE OFFSET IS SET TO 1234 THIS METHOD WILL
            // BE INVOKED.
            println "******************************************************"
            println "******************************************************"
            println "event: ${event}, source: ${event.source.realtimeStart}"
            println "******************************************************"
            println "******************************************************"
        }
    }
)

seriess.offset = 1234