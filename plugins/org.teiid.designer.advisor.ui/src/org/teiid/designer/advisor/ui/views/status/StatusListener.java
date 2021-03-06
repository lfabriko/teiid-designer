/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.advisor.ui.views.status;

/**
 * 
 */
public interface StatusListener {
    /**
     * @param status the <code>WorkspaceStatus</code>
     */
    void notifyStatusChanged( ModelProjectStatus status );
}
