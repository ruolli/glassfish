/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2010 Sun Microsystems, Inc. All rights reserved.
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
package com.sun.enterprise.admin.cli;

import com.sun.enterprise.util.io.DomainDirs;
import com.sun.enterprise.util.io.ServerDirs;
import java.io.*;
import java.util.*;
import java.net.Socket;
import java.security.KeyStore;

import org.glassfish.api.Param;
import org.glassfish.api.admin.*;

import com.sun.enterprise.admin.cli.remote.RemoteCommand;
import com.sun.enterprise.util.SystemPropertyConstants;
import com.sun.enterprise.universal.io.SmartFile;
import com.sun.enterprise.universal.xml.MiniXmlParser;
import com.sun.enterprise.universal.xml.MiniXmlParserException;
import com.sun.enterprise.security.store.PasswordAdapter;
import com.sun.enterprise.universal.StringUtils;

/**
 * A class that's supposed to capture all the behavior common to operation
 * on a "local" domain.  It's supposed to act as the abstract base class that
 * provides more functionality to the commands that operate on a local domain.
 * @author &#2325;&#2375;&#2342;&#2366;&#2352 (km@dev.java.net)
 * @author Byron Nevins  (bnevins@dev.java.net)
 */
public abstract class LocalDomainCommand extends LocalServerCommand {
    @Param(name = "domaindir", optional = true)
    private String domainDirParam = null;
    // subclasses decide whether it's optional, required, or not allowed
    //@Param(name = "domain_name", primary = true, optional = true)
    private String userArgDomainName;
    // the key for the Domain Root in the main attributes of the
    // manifest returned by the __locations command
    private static final String DOMAIN_ROOT_KEY = "Domain-Root_value";
    private DomainDirs dd = null;

    @Override
    /*
     * The prepare method must ensure that the superclass' implementation of
     * the method is called.  
     * The reason we override here is that we can get into trouble with layers 
     * of NPE possibilities.  So here the ServerDirs object is initialized
     * right away.  It will return null for all non-boolean method calls.  But we
     * never have to do a null-check on the ServerDirs object itself.
     * ServerDirs is 100% immutable.  A new one will be made later if needed.
     */
    protected void prepare()
            throws CommandException, CommandValidationException {
        super.prepare();
        setServerDirs(new ServerDirs()); // do-nothing ServerDirs object...
        String pw = getServerDirs().getLocalPassword();

        if(StringUtils.ok(pw)) {
            programOpts.setPassword(pw, ProgramOptions.PasswordLocation.LOCAL_PASSWORD);
            logger.printDebugMessage("Using local password");
        }
        else
            logger.printDebugMessage("Not using local password");
    }

    @Override
    protected void validate()
            throws CommandException, CommandValidationException {

        initDomain();
    }

    protected final File getDomainsDir() {
        return dd.getDomainsDir();
    }

    protected final File getDomainRootDir() {
        return dd.getDomainDir();
    }

    protected final String getDomainName() {
        // can't just use "dd" since it may be half-baked right now!

        if(dd != null && dd.isValid())
            return dd.getDomainName();
        else // too early!
            return userArgDomainName;  // might be and is ok to be null
    }

    /**
     * We need this so that @Param values for domainname can be remembered later
     * when the ServerDirs object is made.
     * @param name the user-specified domain name.
     */
    protected final void setDomainName(String name) {
        userArgDomainName = name;
    }

    protected void initDomain() throws CommandException {
        try {
            File domainsDirFile = null;

            if(ok(domainDirParam))
                domainsDirFile = new File(domainDirParam);

            dd = new DomainDirs(domainsDirFile, getDomainName());
            setServerDirs(dd.getServerDirs());
        }
        catch (Exception e) {
            throw new CommandException(e);
        }
    }

    protected boolean isThisDAS(File ourDir) {
        return isThisServer(ourDir, DOMAIN_ROOT_KEY);
    }

}
