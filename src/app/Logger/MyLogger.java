package app.Logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Program <code>MyWindow</code>
 * Klasa <code>MyLogger</code> definiujaca i obslugujaca
 * dziennik zdarzen tzw. log. Wykorzystuje biblioteke log4j2 a konfiguracja
 * zawarta jest w pliku config/log4j2.xml
 * @author
 * @version 1.0	05/06/2023
 */
public class MyLogger {
    static final Logger logger =  LogManager.getLogger(MyLogger.class.getName());
    /**
     * Konstruktor bezparametrowy klasy MyLogger
     */
    public MyLogger() {

    }
    public static void writeLog(String level, String message) {
        if(level.equals("DEBUG")) logger.debug(message);
        else if(level.equals("INFO")) logger.info(message);
        else if(level.equals("WARN")) logger.warn(message);
        else if(level.equals("ERROR")) logger.error(message);
        else if(level.equals("FATAL")) logger.fatal(message);
    }
}
