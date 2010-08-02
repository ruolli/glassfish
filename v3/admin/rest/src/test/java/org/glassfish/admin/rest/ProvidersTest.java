/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2010 Sun Microsystems, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License. You can obtain
 * a copy of the License at https://glassfish.dev.java.net/public/CDDL+GPL.html
 * or glassfish/bootstrap/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at glassfish/bootstrap/legal/LICENSE.txt.
 * Sun designates this particular file as subject to the "Classpath" exception
 * as provided by Sun in the GPL Version 2 section of the License file that
 * accompanied this code.  If applicable, add the following below the License
 * Header, with the fields enclosed by brackets [] replaced by your own
 * identifying information: "Portions Copyrighted [year]
 * [name of copyright owner]"
 *
 * Contributor(s):
 *
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package org.glassfish.admin.rest;

import org.junit.Ignore;
import org.glassfish.api.ActionReport.MessagePart;
import org.glassfish.api.ActionReport;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Properties;
import com.sun.enterprise.v3.common.JsonActionReporter;
import org.glassfish.admin.rest.provider.ActionReportResultJsonProvider;
import org.glassfish.admin.rest.results.ActionReportResult;
import org.glassfish.admin.rest.results.OptionsResult;
import org.glassfish.api.ActionReport.ExitCode;
import com.sun.jersey.api.client.ClientResponse;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jasonlee
 */
public class ProvidersTest extends RestTestBase {
    private static final String URL_ACTION_REPORT_RESULT = "/domain/uptime";
    private static final String URL_COMMAND_RESOURCE_GET_RESULT = "/domain/stop";
    private static final String URL_GET_RESULT = "/domain";
    private static final String URL_GET_RESULT_LIST = "/domain/servers/server";
    private static final String URL_OPTIONS_RESULT = "/domain";
    private static final String URL_STRING_LIST_RESULT = "/domain/configs/config/server-config/java-config/jvm-options";
    private static String URL_TREE_NODE;

    public ProvidersTest() {
        URL_TREE_NODE = "http://localhost:" + getParameter("admin.port", "4848") + "/monitoring/domain";
    }

    @Test
    public void testActionReportResultHtmlProvider() {
        ClientResponse response = get(URL_ACTION_REPORT_RESULT + ".html");
        assertTrue(isSuccess(response));
    }

    @Test
    public void testActionReportResultXmlProvider() {
        ClientResponse response = get(URL_ACTION_REPORT_RESULT + ".xml");
        assertTrue(isSuccess(response));
    }

    @Test
    public void testActionReportResultJsonProvider() {
        ClientResponse response = get(URL_ACTION_REPORT_RESULT + ".json");
        assertTrue(isSuccess(response));
    }

    @Test
    public void testCommandResourceGetResultHtmlProvider() {
        ClientResponse response = get(URL_COMMAND_RESOURCE_GET_RESULT + ".html");
        assertTrue(isSuccess(response));
    }

    @Test
    public void testCommandResourceGetResultXmlProvider() {
        ClientResponse response = get(URL_COMMAND_RESOURCE_GET_RESULT + ".xml");
        assertTrue(isSuccess(response));
    }

    @Test
    public void testCommandResourceGetResultJsonProvider() {
        ClientResponse response = get(URL_COMMAND_RESOURCE_GET_RESULT + ".json");
        assertTrue(isSuccess(response));
    }

    @Test
    public void testGetResultHtmlProvider() {
        ClientResponse response = get(URL_GET_RESULT + ".html");
        assertTrue(isSuccess(response));
    }

    @Test
    public void testGetResultXmlProvider() {
        ClientResponse response = get(URL_GET_RESULT + ".xml");
        assertTrue(isSuccess(response));
    }

    @Test
    public void testGetResultJsonProvider() {
        ClientResponse response = get(URL_GET_RESULT + ".json");
        assertTrue(isSuccess(response));
    }

    @Test
    public void testGetResultListHtmlProvider() {
        ClientResponse response = get(URL_GET_RESULT_LIST + ".html");
        assertTrue(isSuccess(response));
    }

    @Test
    public void testGetResultListXmlProvider() {
        ClientResponse response = get(URL_GET_RESULT_LIST + ".xml");
        assertTrue(isSuccess(response));
    }

    @Test
    public void testGetResultListJsonProvider() {
        ClientResponse response = get(URL_GET_RESULT_LIST + ".json");
        assertTrue(isSuccess(response));
    }

    @Test
    public void testOptionsResultXmlProvider() {
        ClientResponse response = options(URL_OPTIONS_RESULT + ".xml");
        assertTrue(isSuccess(response));
    }

    @Test
    public void testOptionsResultJsonProvider() {
        ClientResponse response = options(URL_OPTIONS_RESULT + ".json");
        assertTrue(isSuccess(response));
    }

    @Test
    public void testStringListResultHtmlProvider() {
        ClientResponse response = get(URL_STRING_LIST_RESULT + ".html");
        assertTrue(isSuccess(response));
    }

    @Test
    public void testStringListResultXmlProvider() {
        ClientResponse response = get(URL_STRING_LIST_RESULT + ".xml");
        assertTrue(isSuccess(response));
    }

    @Test
    public void testStringListResultJsonProvider() {
        ClientResponse response = get(URL_STRING_LIST_RESULT + ".json");
        assertTrue(isSuccess(response));
    }

    @Test
    public void testTreeNodeHtmlProvider() {
        ClientResponse response = get(URL_TREE_NODE + ".html");
        assertTrue(isSuccess(response));
    }

    @Test
    public void testTreeNodeXmlProvider() {
        ClientResponse response = get(URL_TREE_NODE + ".xml");
        assertTrue(isSuccess(response));
    }

    @Test
    public void testTreeNodeJsonProvider() {
        ClientResponse response = get(URL_TREE_NODE + ".json");
        assertTrue(isSuccess(response));
    }
}