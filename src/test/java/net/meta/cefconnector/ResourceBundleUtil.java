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

import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;


public class ResourceBundleUtil extends ResourceBundle {

	private Properties properties;
    
    public ResourceBundleUtil(Properties properties) {
                this.properties = properties;
    }

	@Override
	protected Object handleGetObject(String key) {
		// TODO Auto-generated method stub
		return properties.get(key);
	}

	@Override
	public Enumeration<String> getKeys() {
		// TODO Auto-generated method stub
		return null;
	}
}
