/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.core.metamodel.aspect.core.aspects.validation.rules;

import java.util.List;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.teiid.designer.core.ModelerCore;
import org.teiid.designer.core.validation.StructuralFeatureValidationRule;
import org.teiid.designer.core.validation.ValidationContext;
import org.teiid.designer.core.validation.ValidationProblem;
import org.teiid.designer.core.validation.ValidationProblemImpl;
import org.teiid.designer.core.validation.ValidationResult;
import org.teiid.designer.core.validation.ValidationResultImpl;
import org.teiid.designer.metamodels.core.ModelAnnotation;


/**
 * StringNameRule, rule that validates the string name
 *
 * @since 8.0
 */
public class NullPrimaryMetamodelUriRule implements StructuralFeatureValidationRule {
    
    // id of the feature being validated
    private int featureID;    
    
    /**
    * Construct an instance of RuntimeTypeRule.
    * @param featureID ID of the feature to validate 
    */
   public NullPrimaryMetamodelUriRule(int featureID)  {
       this.featureID = featureID;
   }    

    /* (non-Javadoc)
     * @See org.teiid.designer.core.validation.ValidationRule#validate(java.lang.Object, org.teiid.designer.core.validation.ValidationContext)
     */
    @Override
	public void validate(EStructuralFeature eStructuralFeature, EObject eObject, Object value, ValidationContext context) {
        // check if the feature matches the given feature
        if (eStructuralFeature.getFeatureID() != this.featureID) {
            return;
        }

        // Check that the EObject is an instanceof ModelAnnotation
        // otherwise we cannot apply this rule
        if (eObject == null || !(eObject instanceof ModelAnnotation)) {
            return;
        }
        
        // Per defect 10834, when a new model is created, the model is saved immediately (in the
        // ModelWorkspace framework, ModelBufferImpl#open(org.eclipse.core.runtime.IProgressMonitor) )
        // without a valid primary metamodel URI.  If the validation preferences are such that models
        // are validated upon save, then validation kicks in (again, before the primary metamodel URI is set).
        // Therefore, check to see if there are no contents or that the ModelAnnotation is the only content.
        // If so, then return with no errors.
        final Resource resource = eObject.eResource();
        if ( resource != null ) {
            final List contents = resource.getContents();
            final int numRoots = contents.size();
            if ( numRoots == 0 ) {
                return; // nothing to validate
            }
            if ( numRoots == 1 && contents.contains(eObject) ) {
                // the ModelAnnotation is the only object, so assume we're in the above case.
                // Besides, the primary metamodel URI cannot be set/changed by the user, only by code,
                // so this rule really is to ensure that the code is properly creating models
                return;
            }
        }

        ValidationResult result = new ValidationResultImpl(eObject,eObject.eResource());
        
        // The primary metamodel reference cannot be null
        if (value == null || (value instanceof String && ((String)value).trim().length() == 0) ) {
            // create validation problem and add it to the result
            final URI uri = eObject.eResource().getURI();
            Object[] params = new Object[]{URI.decode(uri.toString())};
            String msg = ModelerCore.Util.getString("NullPrimaryMetamodelUriRule.The_primary_metamodel_URI_value_may_not_be_null_1",params); //$NON-NLS-1$
            ValidationProblem problem = new ValidationProblemImpl(0, IStatus.ERROR, msg);
            result.addProblem(problem);
            context.addResult(result);
            return;
        }
    }

}
