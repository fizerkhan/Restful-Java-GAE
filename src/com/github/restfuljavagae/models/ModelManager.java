package com.github.restfuljavagae.models;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public final class ModelManager {
	
    private static final PersistenceManagerFactory pmfInstance =
        JDOHelper.getPersistenceManagerFactory("transactions-optional");

    private ModelManager() {}

    public static PersistenceManagerFactory get() {
        return pmfInstance;
    }
}
