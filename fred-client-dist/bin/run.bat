@echo off
echo **************************************************************************
echo *** Note that the log4j.log file is written to the C:/temp directory.  ***
echo *** Since Log4J is configured using a FileAppender, the log file will  ***
echo *** be overwritten every time this application is run.                 ***
echo ***                                                                    ***
echo *** EXAMPLE: "...\dist\bin>run ../config/requestResponseParadigm.xml"  ***
echo ***                                                                    ***
echo **************************************************************************

java -jar ../lib/assembly-0.8.0-SNAPSHOT-shaded.jar -a %1