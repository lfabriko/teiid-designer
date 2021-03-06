/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.runtime.ui.wizards.webservices.util;

import java.util.Properties;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

/**
 * This class defines the interfaces for building a WAR file and validating the input properties.
 *
 * @since 8.0
 */
public interface WebArchiveBuilder {

    /**
     * Validate that a context name is valid.
     * 
     * @param contextName
     * @return
     */
    public IStatus validateContextName( String contextName );

    /**
     * Creates a new WAR file using the input inputproperties.
     * 
     * @param properties
     * @return
     */
    public IStatus createWebArchive( Properties properties,
                                     IProgressMonitor monitor );

    /**
     * Checks the target WAR fileName (constructed from properties) to see if it already exists on the file system
     * 
     * @param properties
     * @return 'true' if the file already exists, 'false' if not.
     */
    public boolean targetWarFileExists( Properties properties );

}
