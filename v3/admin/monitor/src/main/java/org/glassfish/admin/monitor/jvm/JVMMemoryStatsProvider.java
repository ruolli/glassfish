/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
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

package org.glassfish.admin.monitor.jvm;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import org.glassfish.gmbal.AMXMetadata;
import org.glassfish.gmbal.Description;
import org.glassfish.gmbal.ManagedAttribute;
import org.glassfish.gmbal.ManagedObject;

/* jvm.memory */
//@AMXMetadata(type="memory-mon", group="monitoring", isSingleton=true)
@ManagedObject
@Description( "JVM Memory Statistics" )
public class JVMMemoryStatsProvider {
    
    private MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();
    private MemoryUsage memUsageHeap = memBean.getHeapMemoryUsage();
    private MemoryUsage memUsageNonHeap = memBean.getNonHeapMemoryUsage();

    @ManagedAttribute(id="committedheapsize-count")
    @Description( "amount of memory in bytes that is committed for the Java virtual machine to use" )
    public long getCommittedHeap() {
        return memUsageHeap.getCommitted();
    }

    @ManagedAttribute(id="initheapsize-count")
    @Description( "amount of memory in bytes that the Java virtual machine initially requests from the operating system for memory management" )
    public long getInitHeap() {
        return memUsageHeap.getInit();
    }

    @ManagedAttribute(id="maxheapsize-count")
    @Description( "maximum amount of memory in bytes that can be used for memory management" )
    public long getMaxHeap() {
        return memUsageHeap.getMax();
    }

    @ManagedAttribute(id="usedheapsize-count")
    @Description( "amount of used memory in bytes" )
    public long getUsedHeap() {
        return memUsageHeap.getUsed();
    }

    @ManagedAttribute(id="committednonheapsize-count")
    @Description( "amount of memory in bytes that is committed for the Java virtual machine to use" )
    public long getCommittedNonHeap() {
        return memUsageNonHeap.getCommitted();
    }

    @ManagedAttribute(id="initnonheapsize-count")
    @Description( "amount of memory in bytes that the Java virtual machine initially requests from the operating system for memory management" )
    public long getInitNonHeap() {
        return memUsageNonHeap.getInit();
    }

    @ManagedAttribute(id="maxnonheapsize-count")
    @Description( "maximum amount of memory in bytes that can be used for memory management" )
    public long getMaxNonHeap() {
        return memUsageNonHeap.getMax();
    }

    @ManagedAttribute(id="usednonheapsize-count")
    @Description( "amount of used memory in bytes" )
    public long getUsedNonHeap() {
        return memUsageNonHeap.getUsed();
    }

    @ManagedAttribute(id="objectpendingfinalizationcount-count")
    @Description( "approximate number of objects for which finalization is pending" )
    public int getObjectPendingFinalizationCount() {
        return memBean.getObjectPendingFinalizationCount();
    }
}