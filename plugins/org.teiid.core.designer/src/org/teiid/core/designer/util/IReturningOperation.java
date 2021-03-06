/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.core.designer.util;

/**
 * @since 8.0
 */
public interface IReturningOperation {

    // ===========================================================================================================================
    // Methods

    /**
     * @throws Exception
     * @since 5.0.2
     */
    Object execute() throws Exception;
}
