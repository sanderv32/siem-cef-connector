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
package net.meta.cefconnector.akamai;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EventProducer {

	private BlockingQueue<Message> queue = null;
	private AtomicInteger counter = new AtomicInteger(0);
	private static final Logger log = LogManager.getLogger(EventConsumer.class);

	public EventProducer(BlockingQueue<Message> q) {
		this.queue = q;
	}

	public void produce(Message message) {
		try {
			queue.put(message);
			counter.getAndIncrement();
		} catch (InterruptedException e) {
			log.error("Producer thread interrupted, error processing " + message.getEvent(), e);
		}
	}

	public int getProcessedRecord() {
		return counter.get();
	}
}
