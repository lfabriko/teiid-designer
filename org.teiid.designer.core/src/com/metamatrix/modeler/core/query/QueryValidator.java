/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package com.metamatrix.modeler.core.query;

import com.metamatrix.query.metadata.QueryMetadataInterface;


/** 
 * QueryValidator, a helper utility that validate the sql and returns a result.
 */
public interface QueryValidator {

    //================================================================================================//
    // Constants
    //================================================================================================//
    int SELECT_TRNS = 0;
    int INSERT_TRNS = 1;
    int UPDATE_TRNS = 2;
    int DELETE_TRNS = 3;
    int UNKNOWN_TRNS = -1;    

    /**
     * Validate the sqlString of the given type, valid types are 
     * {@link SELECT_TRNS}, {@link INSERT_TRNS}, {@link UPDATE_TRNS}, {@link DELETE_TRNS}
     * {@link UNKNOWN_TRNS}, if this is a unknow transfor, the query will only be parsed.
     * @param sqlString The sqlString that needs to be validate
     * @param type The type of sql being passed in
     * @param isUUIDSql Boolean to determine if this is a UUID sql string
     * @param cacheResult Boolean to determine if the result needs to be cached.
     * @return The ValidationResult.
     */
    QueryValidationResult validateSql(String sqlString, int type, boolean isUUIDSql, boolean cacheResult);
    
    /**
     * The QueryMetadataInterface used to validate the sql.
     * @return QueryMetadataInterface
     */
    QueryMetadataInterface getQueryMetadata();
    
    /**
     * Returns whether or not a query's mapping root is valid.  It may be stale. This is to prevent unnecessary validation 
     * @return
     * @since 5.0.2
     */
    boolean isValidRoot();
}
