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
package net.meta.cefconnector.dataaccess;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DBUtilTest {

	@Before
	public void init() {
		try {
			DBUtil.initDatabase();
		} catch (SQLException e) {
			fail("Could not create database");
		}
	}

	@After
	public void destroy() {
		try {
			DBUtil.destroyDatabase();
		} catch (SQLException e) {
			fail("Could not create database");
		}
	}

	@Test
	public void testGetConnection() {
		try (Connection connection = DBUtil.getConnection()) {
			assertNotNull(connection);
		} catch (SQLException e) {
			fail("Could not get connection");
		}
	}

	@Test
	public void testInitDatabase() {
		try {
			String str = DBUtil.getLastOffset();
			System.out.println(str);
		} catch (SQLException e) {
			fail("Could not create database");
		}
	}

	@Test
	public void testSetLastOffset() {
		try {
			String expected = "abcd";
			DBUtil.setLastOffset(expected);
			String actual = DBUtil.getLastOffset();
			assertEquals(actual, expected);

		} catch (SQLException e) {
			fail("Could not create database");
		}
	}

}
