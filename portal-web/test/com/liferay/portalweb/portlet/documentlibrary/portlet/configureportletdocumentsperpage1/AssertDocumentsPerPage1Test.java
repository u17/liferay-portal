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

package com.liferay.portalweb.portlet.documentlibrary.portlet.configureportletdocumentsperpage1;

import com.liferay.portalweb.portal.BaseTestCase;
import com.liferay.portalweb.portal.util.RuntimeVariables;

/**
 * <a href="AssertDocumentsPerPage1Test.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class AssertDocumentsPerPage1Test extends BaseTestCase {
	public void testAssertDocumentsPerPage1() throws Exception {
		selenium.open("/web/guest/home/");

		for (int second = 0;; second++) {
			if (second >= 60) {
				fail("timeout");
			}

			try {
				if (selenium.isElementPresent("link=Document Library Test Page")) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}

		selenium.clickAt("link=Document Library Test Page",
			RuntimeVariables.replace(""));
		selenium.waitForPageToLoad("30000");
		selenium.clickAt("//a/strong", RuntimeVariables.replace(""));
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent(
				"Test1 Document1\nThis is test1 document1."));
		assertFalse(selenium.isTextPresent(
				"Test2 Document2\nThis is test2 document2."));
		assertFalse(selenium.isTextPresent(
				"Test3 Document3\nThis is test3 document3."));
		selenium.clickAt("link=Next", RuntimeVariables.replace(""));
		selenium.waitForPageToLoad("30000");
		assertFalse(selenium.isTextPresent(
				"Test1 Document1\nThis is test1 document1."));
		assertTrue(selenium.isTextPresent(
				"Test2 Document2\nThis is test2 document2."));
		assertFalse(selenium.isTextPresent(
				"Test3 Document3\nThis is test3 document3."));
		selenium.clickAt("link=Next", RuntimeVariables.replace(""));
		selenium.waitForPageToLoad("30000");
		assertFalse(selenium.isTextPresent(
				"Test1 Document1\nThis is test1 document1."));
		assertFalse(selenium.isTextPresent(
				"Test2 Document2\nThis is test2 document2."));
		assertTrue(selenium.isTextPresent(
				"Test3 Document3\nThis is test3 document3."));
	}
}