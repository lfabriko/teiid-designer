/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.modelgenerator.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.emf.ecore.EObject;

/**
 * RelationTrackerImpl
 *
 * @since 8.0
 */
public class TransientRelationTrackerImpl implements RelationTracker {
    
    public static final int UNABLE_TO_ADD_RELATIONSHIP_CODE = 20001;

    private Map inputToOutputMap;
    
    private Map outputToInputMap;
    
    /**
     * Construct an instance of RelationTrackerImpl.
     * 
     */
    public TransientRelationTrackerImpl() {
        super();
        this.inputToOutputMap = new HashMap();
        this.outputToInputMap = new HashMap();
    }

    /**
     * @See org.teiid.designer.modelgenerator.RelationTracker#recordGeneratedFrom(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
     */
    @Override
	public void recordGeneratedFrom(final EObject input, final EObject output, final List problems) {
        doMapping(input, output);
    }

    /**
     * @See org.teiid.designer.modelgenerator.RelationTracker#recordGeneratedFrom(org.eclipse.emf.ecore.EObject, java.util.List)
     */
    @Override
	public void recordGeneratedFrom(final EObject input, final List outputs, final List problems) {
        if(outputs.size()>0){
            doMapping(input, (EObject)outputs.get(0));
        }
    }

    /**
     * @See org.teiid.designer.modelgenerator.RelationTracker#recordGeneratedFrom(java.util.List, java.util.List)
     */
    @Override
	public void recordGeneratedFrom(final List inputs, final List outputs, final List problems) {
        /*
         * the top two objects in each of the lists will be 'directly' related to one another.
         */
        if (inputs.size() > 0 && outputs.size() > 0) {
            doMapping((EObject)inputs.get(0), (EObject)outputs.get(0));
        }
    }
    
    /**
     * @See org.teiid.designer.modelgenerator.RelationTracker#getGeneratedFrom(org.eclipse.emf.ecore.EObject)
     */
    @Override
	public EObject getGeneratedFrom(EObject output) {
        return (EObject)outputToInputMap.get(output);
    }

    
    /* (non-Javadoc)
     * @See org.teiid.designer.modelgenerator.processor.RelationTracker#getGeneratedTo(org.eclipse.emf.ecore.EObject)
     */
    @Override
	public EObject getGeneratedTo(EObject input) {
        return (EObject)inputToOutputMap.get(input);
    }
    
    protected void doMapping(EObject input, EObject output){
        inputToOutputMap.put(input, output);
        outputToInputMap.put(output, input);   
    }

}
