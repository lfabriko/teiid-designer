/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.ui.actions;

import org.eclipse.jface.viewers.ISelectionChangedListener;

/**
 * @since 8.0
 */
public interface ClipboardActionHandler {
    public void cut();
    public void copy();
    public void paste();
    public void delete();
    public void selectAll();
    public void addSelectionChangedListener(ISelectionChangedListener listener);
}
