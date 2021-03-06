/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.modelgenerator.processor;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.teiid.designer.core.ModelerCore;
import org.teiid.designer.core.types.DatatypeManager;

/**
 * BuiltInDatatypeFinder
 *
 * @since 8.0
 */
public class BuiltInDatatypeFinder implements DatatypeFinder {
    
    public static final BuiltInDatatypeFinder INSTANCE = new BuiltInDatatypeFinder();
    
    /**
     * Construct an instance of BuiltInDatatypeFinder.
     * 
     */
    public BuiltInDatatypeFinder() {
        super();
    }

    /**
     * @see DatatypeFinder#findDatatype(java.lang.String)
     */
    @Override
	public EObject findDatatype(final String name) throws CoreException {
        final DatatypeManager dtMgr = ModelerCore.getWorkspaceDatatypeManager();    // only care about built-ins!
        if ( name == null ) {
            return dtMgr.getAnySimpleType();
        }
        final EObject datatype = dtMgr.getBuiltInDatatype(name);
        return datatype;
    }

    /**
     * @see DatatypeFinder#findDatatype(org.eclipse.emf.common.util.URI)
     */
    @Override
	public EObject findDatatype(final URI uri) throws CoreException {
        final DatatypeManager dtMgr = ModelerCore.getWorkspaceDatatypeManager();    // only care about built-ins!
        if ( uri == null ) {
            return dtMgr.getAnySimpleType();
        }
        final EObject datatype = dtMgr.findDatatype(uri.toString());
        return datatype;
    }

    /**
     * @see DatatypeFinder#findAllDatatypes(java.lang.String)
     */
    @Override
	public List findAllDatatypes(final String name) throws CoreException {
        final EObject datatype = findDatatype(name);
        if ( datatype != null ) {
            final List result = new LinkedList();
            result.add(datatype);
            return result;
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @see DatatypeFinder#findAllDatatypes(org.eclipse.emf.common.util.URI)
     */
    @Override
	public List findAllDatatypes(final URI uri) throws CoreException {
        final EObject datatype = findDatatype(uri);
        if ( datatype != null ) {
            final List result = new LinkedList();
            result.add(datatype);
            return result;
        }
        return Collections.EMPTY_LIST;
    }

}
