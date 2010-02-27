/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portalweb.portlet.wikidisplay.wikipage.removeredirectwdfrontpagechildpage;

import com.liferay.portalweb.portal.BaseTestCase;
import com.liferay.portalweb.portal.util.RuntimeVariables;

/**
 * <a href="RemoveRedirectWDFrontPageChildPageTest.java.html"><b><i>View Source
 * </i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class RemoveRedirectWDFrontPageChildPageTest extends BaseTestCase {
	public void testRemoveRedirectWDFrontPageChildPage()
		throws Exception {
		selenium.open("/web/guest/home/");

		for (int second = 0;; second++) {
			if (second >= 60) {
				fail("timeout");
			}

			try {
				if (selenium.isElementPresent("link=Wiki Display Test Page")) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}

		selenium.clickAt("link=Wiki Display Test Page",
			RuntimeVariables.replace(""));
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isPartialText("//h1[@class='page-title']",
				"Front Page Child Page Test Edited"));
		assertEquals(RuntimeVariables.replace(
				"(Redirected from Front Page Child Page Test)"),
			selenium.getText("//div[@class='page-redirect']"));
		selenium.clickAt("//div[@class='page-redirect']",
			RuntimeVariables.replace(""));
		selenium.waitForPageToLoad("30000");
		selenium.clickAt("//input[@value='Remove Redirect']",
			RuntimeVariables.replace(""));
		selenium.waitForPageToLoad("30000");
		selenium.type("//textarea",
			RuntimeVariables.replace("This is a remove redirect test."));
		selenium.clickAt("//input[@value='Save']", RuntimeVariables.replace(""));
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent(
				"Your request processed successfully."));
		assertTrue(selenium.isPartialText("//h1[@class='page-title']",
				"Front Page Child Page Test"));
		assertEquals(RuntimeVariables.replace("This is a remove redirect test."),
			selenium.getText("//div[@class='wiki-body']"));
		assertFalse(selenium.isPartialText("//h1[@class='page-title']",
				"Front Page Child Page Test Edited"));
		assertFalse(selenium.isTextPresent(
				"(Redirected from Front Page Child Page Test)"));
	}
}