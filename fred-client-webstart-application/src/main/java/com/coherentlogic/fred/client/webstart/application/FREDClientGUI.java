package com.coherentlogic.fred.client.webstart.application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.coherentlogic.fred.client.core.builders.QueryBuilder;
import com.coherentlogic.fred.client.core.exceptions.IORuntimeException;
import com.coherentlogic.fred.client.core.exceptions.InvalidURIException;
import com.coherentlogic.fred.client.core.factories.QueryBuilderFactory;
import com.coherentlogic.fred.client.db.integration.dao.CategoriesRepository;
import com.coherentlogic.fred.client.db.integration.dao.ObservationsRepository;
import com.coherentlogic.fred.client.db.integration.dao.ReleaseDatesRepository;
import com.coherentlogic.fred.client.db.integration.dao.ReleasesRepository;
import com.coherentlogic.fred.client.db.integration.dao.SeriessRepository;
import com.coherentlogic.fred.client.db.integration.dao.SourcesRepository;
import com.coherentlogic.fred.client.db.integration.dao.TagsRepository;
import com.coherentlogic.fred.client.db.integration.dao.VintageDatesRepository;
import com.jamonapi.MonKey;
import com.jamonapi.MonKeyImp;
import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;

/**
 * The front-end for the FRED Client that allows users to directly work with the
 * {@link com.coherentlogic.fred.client.core.builders.QueryBuilder}. 
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages="com.coherentlogic.fred.client")
public class FREDClientGUI extends JFrame implements CommandLineRunner {

    private static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(FREDClientGUI.class);

    private URI uri = null;

    private static final String
        SERIESS = "Seriess",
        CATEGORIES = "Categories",
        OBSERVATIONS = "Observations",
        RELEASES = "Releases",
        VINTAGE_DATES = "VintageDates",
        RELEASE_DATES = "ReleaseDates",
        SOURCES = "Sources",
        TAGS = "Tags",
        TAGS2 = "Tags2",
        FRED_CLIENT_GUI = "fredClientGUI";

    private static final String
        CATEGORIES_SERVICE = "categoriesService",
        SERIESS_SERVICE = "seriessService",
        OBSERVATIONS_SERVICE = "observationsService",
        RELEASEDATES_SERVICE = "releaseDatesService",
        RELEASES_SERVICE = "releasesService",
        SOURCES_SERVICE = "sourcesService",
        TAGS_SERVICE = "tagsService",
        VINTAGEDATES_SERVICE = "vintageDatesService";

    private static final String
        SERIESS_QUERY_BUILDER_FACTORY = "seriessQueryBuilderFactory",
        CATEGORIES_QUERY_BUILDER_FACTORY = "categoriesQueryBuilderFactory",
        OBSERVATIONS_QUERY_BUILDER_FACTORY =
            "observationsQueryBuilderFactory",
        RELEASES_QUERY_BUILDER_FACTORY = "releasesQueryBuilderFactory",
        VINTAGE_DATES_QUERY_BUILDER_FACTORY =
            "vintageDatesQueryBuilderFactory",
        RELEASE_DATES_QUERY_BUILDER_FACTORY =
            "releaseDatesQueryBuilderFactory",
        SOURCES_QUERY_BUILDER_FACTORY = "sourcesQueryBuilderFactory",
        TAGS_QUERY_BUILDER_FACTORY = "tagsQueryBuilderFactory";

    private static final Map<String, String> beanIdMap =
        new HashMap<String, String> ();

    static {
        beanIdMap.put(SERIESS, SERIESS_QUERY_BUILDER_FACTORY);
        beanIdMap.put(CATEGORIES, CATEGORIES_QUERY_BUILDER_FACTORY);
        beanIdMap.put(OBSERVATIONS, OBSERVATIONS_QUERY_BUILDER_FACTORY);
        beanIdMap.put(RELEASES, RELEASES_QUERY_BUILDER_FACTORY);
        beanIdMap.put(VINTAGE_DATES, VINTAGE_DATES_QUERY_BUILDER_FACTORY);
        beanIdMap.put(RELEASE_DATES, RELEASE_DATES_QUERY_BUILDER_FACTORY);
        beanIdMap.put(SOURCES, SOURCES_QUERY_BUILDER_FACTORY);
        beanIdMap.put(TAGS, TAGS_QUERY_BUILDER_FACTORY);
        beanIdMap.put(TAGS2, TAGS_QUERY_BUILDER_FACTORY);
    }

    private static final String
        QUERY_BUILDER = "queryBuilder",
        LOG = "log";

    private final JTextArea outputTextArea = new JTextArea();

    private final JButton runScriptButton = new JButton("Run script");

    private final ButtonGroup requestMenuItemsGroup = new ButtonGroup();

    private final Map<ButtonModel, JRadioButtonMenuItem> radioButtonMap =
        new HashMap<ButtonModel, JRadioButtonMenuItem> ();

    private GroovyEngine groovyEngine;

    private Map<String, QueryBuilderFactory> queryBuilderFactoryMap;

    private Map<String, String> exampleMap;

    @Autowired
    private ApplicationContext applicationContext;

    private final ObjectStringifier objectStringifier =
        new ObjectStringifier ();

    private final MonKey monKey = new MonKeyImp(
        "Call FRED web services and return an instance of a domain class.",
        TimeUnit.MILLISECONDS.toString());

    /**
     * @see #initialize()
     */
    void initializeMenu (JTextArea inputTextArea) {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

//        JMenu mnFile = new JMenu("File");
//        menuBar.add(mnFile);
//
//        JMenuItem mntmExit = new JMenuItem("Exit");
//        mnFile.add(mntmExit);

        JMenu mnRequest = new JMenu("Examples");
        menuBar.add(mnRequest);

        JRadioButtonMenuItem seriess = new JRadioButtonMenuItem("Seriess");
        mnRequest.add(seriess);

        seriess.addActionListener(
            new MenuItemSelectedActionListener (exampleMap, inputTextArea));

        JRadioButtonMenuItem categories =
            new JRadioButtonMenuItem("Categories");
        mnRequest.add(categories);

        categories.addActionListener(
            new MenuItemSelectedActionListener (exampleMap, inputTextArea));

        JRadioButtonMenuItem observations =
            new JRadioButtonMenuItem("Observations");
        observations.setSelected(true);
        mnRequest.add(observations);

        observations.addActionListener(
            new MenuItemSelectedActionListener (exampleMap, inputTextArea));

        JRadioButtonMenuItem releases = new JRadioButtonMenuItem("Releases");
        mnRequest.add(releases);

        releases.addActionListener(
            new MenuItemSelectedActionListener (exampleMap, inputTextArea));

        JRadioButtonMenuItem vintageDates =
            new JRadioButtonMenuItem("VintageDates");
        mnRequest.add(vintageDates);

        vintageDates.addActionListener(
            new MenuItemSelectedActionListener (exampleMap, inputTextArea));

        JRadioButtonMenuItem releaseDates =
            new JRadioButtonMenuItem("ReleaseDates");
        mnRequest.add(releaseDates);

        releaseDates.addActionListener(
            new MenuItemSelectedActionListener (exampleMap, inputTextArea));

        JRadioButtonMenuItem sources = new JRadioButtonMenuItem("Sources");
        mnRequest.add(sources);

        sources.addActionListener(
            new MenuItemSelectedActionListener (exampleMap, inputTextArea));

        JRadioButtonMenuItem tags = new JRadioButtonMenuItem(TAGS);
        mnRequest.add(tags);

        tags.addActionListener(
            new MenuItemSelectedActionListener (exampleMap, inputTextArea));

        JRadioButtonMenuItem tags2 = new JRadioButtonMenuItem(TAGS2);
        mnRequest.add(tags2);

        tags2.addActionListener(
            new MenuItemSelectedActionListener (exampleMap, inputTextArea));

        requestMenuItemsGroup.add(seriess);
        requestMenuItemsGroup.add(categories);
        requestMenuItemsGroup.add(observations);
        requestMenuItemsGroup.add(releases);
        requestMenuItemsGroup.add(vintageDates);
        requestMenuItemsGroup.add(releaseDates);
        requestMenuItemsGroup.add(sources);
        requestMenuItemsGroup.add(tags);
        requestMenuItemsGroup.add(tags2);

        radioButtonMap.put(seriess.getModel(), seriess);
        radioButtonMap.put(categories.getModel(), categories);
        radioButtonMap.put(observations.getModel(), observations);
        radioButtonMap.put(releases.getModel(), releases);
        radioButtonMap.put(vintageDates.getModel(), vintageDates);
        radioButtonMap.put(releaseDates.getModel(), releaseDates);
        radioButtonMap.put(sources.getModel(), sources);
        radioButtonMap.put(tags.getModel(), tags);
        radioButtonMap.put(tags2.getModel(), tags2);

        addHelpAbout (menuBar);
    }

    private void addHelpAbout (JMenuBar menuBar) {
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

        addAPIMenuItem (helpMenu);

        JMenuItem mntmAbout = new JMenuItem("About");
        helpMenu.add(mntmAbout);

        mntmAbout.addActionListener(
            new ActionListener () {

                @Override
                public void actionPerformed(ActionEvent actionEvent) {

                    AboutDialog dialog;

                    try {
                        dialog = new AboutDialog ();
                    } catch (IOException ioException) {
                        throw new IORuntimeException(
                            "Unable to create the AboutDialog.", ioException);
                    }
                    dialog.setVisible(true);
                }
            }
        );
    }

    private void addAPIMenuItem (JMenu helpMenu) {

        final String destination = "http://bit.ly/1G3dwJG";

        JMenuItem apiJavadocs = new JMenuItem("API Javadocs");

        apiJavadocs.setForeground(Color.blue);

        helpMenu.add(apiJavadocs);

        apiJavadocs.addActionListener(
            new ActionListener () {

                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    // This is for tracking purposes and will direct the user
                    // to http://coherentlogic.com/fredJavaDoc/
                    try {
                        AboutDialog.open(destination);
                    } catch (URISyntaxException uriSyntaxException) {
                        throw new InvalidURIException(
                            "Unable to open the destination: " + destination,
                            uriSyntaxException
                        );
                    }
                }
            }
        );
    }

    /**
     * Method configures the Swing components that are added to this object's
     * JFrame.
     */
    @PostConstruct
    public void initialize () {

        groovyEngine = applicationContext.getBean(GroovyEngine.class);

        queryBuilderFactoryMap = (Map<String, QueryBuilderFactory>)
            applicationContext.getBean("queryBuilderFactoryMap");

        exampleMap = (Map<String, String>) applicationContext.getBean("exampleMap");

        try {
            uri = new URI("https://twitter.com/CoherentMktData");
        } catch (URISyntaxException uriSyntaxException) {
            throw new RuntimeException (uriSyntaxException);
        }

        setTitle("FRED Client GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel parent = new JPanel();
        parent.setLayout(new BorderLayout()); 

        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        parent.add(panel);

        getContentPane().add(parent, BorderLayout.CENTER);
        setExtendedState(Frame.MAXIMIZED_BOTH); 

        JLabel enterYourQueryLabel = new JLabel(
            "Enter your query here (context contains references to: " +
            "queryBuilder):");

        panel.add(enterYourQueryLabel);

        final JTextArea inputTextArea = new JTextArea();

        JScrollPane inputTextAreaScrollPane = new JScrollPane(inputTextArea);

        inputTextAreaScrollPane.
            setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        inputTextAreaScrollPane.
        setHorizontalScrollBarPolicy(
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        initializeMenu(inputTextArea);

        String exampleText = exampleMap.get (OBSERVATIONS);

        inputTextArea.setText(exampleText);

        inputTextArea.setColumns(80);
        inputTextArea.setRows(40);
        panel.add(inputTextAreaScrollPane);

        panel.add(runScriptButton);

        JLabel outputAppearsBelowLabel = new JLabel("The output appears below:");

        panel.add(outputAppearsBelowLabel);

        outputTextArea.setColumns(80);
        outputTextArea.setRows(40);

        JScrollPane outputTextAreaScrollPane = new JScrollPane(outputTextArea);

        outputTextAreaScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panel.add(outputTextAreaScrollPane);

        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();

        Rectangle bounds = env.getMaximumWindowBounds();

        setBounds(bounds);

        runScriptButton.addActionListener(
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent actionEvent) {

                    String scriptText = inputTextArea.getText();

                    log.info("scriptText:\n\n" + scriptText);

                    ButtonModel buttonModel =
                        requestMenuItemsGroup.getSelection();

                    JRadioButtonMenuItem selectedMenuItem =
                        radioButtonMap.get(buttonModel);

                    String selectedText = selectedMenuItem.getText();

                    QueryBuilderFactory queryBuilderFactory =
                        (QueryBuilderFactory)
                            queryBuilderFactoryMap.get(selectedText);

                    QueryBuilder requestBuilder =
                        queryBuilderFactory.getInstance();

                    CategoriesRepository categoriesDAO = applicationContext.getBean(CategoriesRepository.class);
                    SeriessRepository seriessDAO = applicationContext.getBean(SeriessRepository.class);
                    ObservationsRepository observationsDAO = applicationContext.getBean(ObservationsRepository.class);
                    ReleaseDatesRepository releaseDatesDAO = applicationContext.getBean(ReleaseDatesRepository.class);
                    ReleasesRepository releasesDAO = applicationContext.getBean(ReleasesRepository.class);
                    SourcesRepository sourcesDAO = applicationContext.getBean(SourcesRepository.class);
                    TagsRepository tagsDAO = applicationContext.getBean(TagsRepository.class);
                    VintageDatesRepository vintageDatesDAO = applicationContext.getBean(VintageDatesRepository.class);

                    groovyEngine.setVariable(QUERY_BUILDER, requestBuilder);
                    groovyEngine.setVariable(LOG, log);
                    groovyEngine.setVariable(CATEGORIES_SERVICE, categoriesDAO);
                    groovyEngine.setVariable(SERIESS_SERVICE, seriessDAO);
                    groovyEngine.setVariable(OBSERVATIONS_SERVICE, observationsDAO);
                    groovyEngine.setVariable(RELEASEDATES_SERVICE, releaseDatesDAO);
                    groovyEngine.setVariable(RELEASES_SERVICE, releasesDAO);
                    groovyEngine.setVariable(SOURCES_SERVICE, sourcesDAO);
                    groovyEngine.setVariable(TAGS_SERVICE, tagsDAO);
                    groovyEngine.setVariable(VINTAGEDATES_SERVICE, vintageDatesDAO);

                    Object result = null;

                    Monitor monitor = MonitorFactory.start(monKey);

                    try {
                        result = groovyEngine.evaluate(scriptText);
                    } catch (Throwable throwable) {

                        log.error("Evaluation failed for the script:\n\n" +
                            scriptText, throwable);

                        JOptionPane.showMessageDialog(
                            panel,
                            throwable.getMessage(),
                            "Evaluation failed!",
                            JOptionPane.ERROR_MESSAGE);

                        return;
                    } finally {
                        monitor.stop();
                        log.info ("JAMon report: " + monitor);
                    }

                    log.info("result: " + result);

                    if (result != null) {
                        String stringifiedResult =
                            objectStringifier.toString(result);

                        String fullResult =
                            "// Note that null values are not indicative of a problem, per se, for \n" +
                            "// example the PrimaryKey is only ever assigned when the object has been \n" +
                            "// saved to a database and since this does not happen in this example.\n" +
                            "//\n" +
                            "// -----\n" +
                            "//\n" +
                            "// JAMON Performance Metrics:\n" +
                            "//\n" +
                            "// " + monitor + "\n" +
                            "//\n" +
                            "// -----\n" +
                            "//\n\n\n" +
                            stringifiedResult;

                        outputTextArea.setText(fullResult);
                    }
                }
            }
        );
    }

    /**
     * The main method uses the Spring application context to get an instance of
     * {@link FREDClientGUI} and then displays this object.
     */
    @Override
    public void run(String... args) throws Exception {

        // Server server = Server.createTcpServer("-tcpAllowOthers","-webAllowOthers").start();
//
//        String url = server.getURL();
//
//        log.info("The h2 URL is: " + url);

//        SplashComponent splash;
//
//        try {
//            splash = new SplashComponent ();
//            splash.show();
//        } catch (NullPointerException npe) {
//            log.error("No splash will be displayed.", npe);
//        }

//        FREDClientGUI applet = (FREDClientGUI)
//            applicationContext.getBean(FRED_CLIENT_GUI);

        setVisible(true);

        java.awt.EventQueue.invokeLater(
            () -> {
                toFront();
                repaint();
            }
        );
    }

    public static void main (String[] unused) throws InterruptedException {

        try {

            SpringApplicationBuilder builder =
                new SpringApplicationBuilder (FREDClientGUI.class);

            builder
                .web(false)
                .headless(false)
                .registerShutdownHook(true)
                .run(unused);

        } catch (Throwable thrown) {
            log.error("ExampleApplication.main caught an exception.", thrown);
        }

        Thread.sleep(Long.MAX_VALUE);

        System.exit(-9999);
    }
}

/**
 * An {@link java.awt.event ActionListener} implementation that adds a given
 * example to the inputTextArea when the user selects a given
 * {@link javax.swing.JMenuItem}.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
class MenuItemSelectedActionListener implements ActionListener {

    private final Map<String, String> exampleMap;

    private final JTextArea inputTextArea;

    public MenuItemSelectedActionListener(
        Map<String, String> exampleMap,
        JTextArea inputTextArea
    ) {
        super();
        this.exampleMap = exampleMap;
        this.inputTextArea = inputTextArea;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        JMenuItem menuItem = (JMenuItem) actionEvent.getSource();

        String selectedMenu = menuItem.getText();

        String example = exampleMap.get(selectedMenu);

        inputTextArea.setText(example);
    }
}
