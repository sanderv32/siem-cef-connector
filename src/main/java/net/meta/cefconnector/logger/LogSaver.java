/*******************************************************************************
 * Copyright 2017 Akamai Technologies
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package net.meta.cefconnector.logger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Special class for logging messages to filelog and/or syslog
 *
 */
public class LogSaver {

	private static final Logger log = LogManager.getLogger(LogSaver.class);

	private LogSaver() {
	}

	/**
	 * Saves message to filelog and/or syslog (if they are configured)
	 *
	 * @param message
	 *            message to log
	 */
	static void save(String message) {
		// Writing Event to Socket Appender
		// Below line is an error, change it to uppercase SYSLOG
		//LogManager.getLogger("cefsyslog").log(Level.getLevel("syslog"), message);
		LogManager.getLogger("cefsyslog").log(Level.getLevel("SYSLOG"), message);
		if (log.isDebugEnabled()) {
			log.debug(message);
		}
	}
}
