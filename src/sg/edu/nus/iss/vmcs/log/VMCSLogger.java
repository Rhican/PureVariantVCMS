package sg.edu.nus.iss.vmcs.log;

import org.apache.log4j.Logger;

public class VMCSLogger {

	final static String name = "vmcs";
	
	final static Logger logger = Logger.getLogger(name);

	public void debug(String message) {
		if(logger.isDebugEnabled()){
			logger.debug(message);
		}
	}
	
	public void debug(String message, Throwable t) {
		if(logger.isDebugEnabled()){
			logger.debug(message, t);
		}
	}
	
	public void info(String message) {
		if(logger.isInfoEnabled()){
			logger.info(message);
		}
	}
	
	public void info(String message, Throwable t) {
		if(logger.isInfoEnabled()){
			logger.info(message, t);
		}
	}
	
	public void warn(String message) {
		logger.warn(message);
	}
	
	public void warn(String message, Throwable t) {
		logger.warn(message, t);
	}
	
	public void error(String message) {
		logger.error(message);
	}
	
	public void error(String message, Throwable t) {
		logger.error(message, t);
	}
	
	public void fatal(String message) {
		logger.fatal(message);
	}
	
	public void fatal(String message, Throwable t) {
		logger.fatal(message, t);
	}
}
