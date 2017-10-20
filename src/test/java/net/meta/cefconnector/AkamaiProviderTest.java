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
package net.meta.cefconnector;

import net.meta.cefconnector.akamai.AkamaiProvider;
import net.meta.cefconnector.config.CEFContext;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class AkamaiProviderTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testURLByOffset(){

		CEFContext ctx = new CEFContext();
		ctx.setOffsetMode(true);
		ctx.setDataLimit(Long.valueOf("200000"));
		ctx.setRequestUrlHost("https://cloudsecurity.akamaiapis.net");
		ctx.setConfigIds("7003");

		Assert.assertEquals(AkamaiProvider.getURLByOffset(ctx), "https://cloudsecurity.akamaiapis.net/siem/v1/configs/7003?offset=null&limit=200000");
	}


	@Test
	public void testURLByTimeEmptyFrom(){

		CEFContext ctx = new CEFContext();
		ctx.setOffsetMode(false);
		ctx.setDataLimit(Long.valueOf("200000"));
		ctx.setRequestUrlHost("https://cloudsecurity.akamaiapis.net");
		ctx.setConfigIds("7003");

		thrown.expect(NullPointerException.class);
		AkamaiProvider.getURLByTime(ctx);
	}

	@Test
	public void testURLByTimeValidFromEmptyTo(){

		CEFContext ctx = new CEFContext();
		ctx.setOffsetMode(false);
		ctx.setDataLimit(Long.valueOf("200000"));
		ctx.setRequestUrlHost("https://cloudsecurity.akamaiapis.net");
		ctx.setConfigIds("7003");

		long fromTime = System.currentTimeMillis() / 1000L;
		ctx.setDateTimeFrom(Long.toString(fromTime));
		ctx.setDateTimeTo("");

		Assert.assertEquals("https://cloudsecurity.akamaiapis.net/siem/v1/configs/7003?from=" + fromTime + "&limit=200000", AkamaiProvider.getURLByTime(ctx));
	}

	@Test
	public void testURLByTimeValidFromAndTo(){

		CEFContext ctx = new CEFContext();
		ctx.setOffsetMode(false);
		ctx.setDataLimit(Long.valueOf("200000"));
		ctx.setRequestUrlHost("https://cloudsecurity.akamaiapis.net");
		ctx.setConfigIds("7003");

		long fromTime = (System.currentTimeMillis() - (4 * 60 * 60 * 1000))/ 1000L;
		ctx.setDateTimeFrom(Long.toString(fromTime));

		long toTime = System.currentTimeMillis()/1000;
		ctx.setDateTimeTo(Long.toString(toTime));

		Assert.assertEquals("https://cloudsecurity.akamaiapis.net/siem/v1/configs/7003?from=" + fromTime + "&to=" + toTime + "&limit=200000", AkamaiProvider.getURLByTime(ctx));
	}



}
