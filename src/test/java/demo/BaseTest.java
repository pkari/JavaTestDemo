package demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BaseTest {
    protected final Logger logger;

    public BaseTest() {
        logger = LogManager.getLogger(this.getClass());
    }
}
