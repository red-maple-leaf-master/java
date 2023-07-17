package com.example.demo.utils;


import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.neo4j.transaction.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class TransactionUtils {
	
	private static final Logger log = LoggerFactory.getLogger(TransactionUtils.class);
	
	private TransactionUtils() {
    }
	
	public static Session getSession(SessionFactory sessionFactory) {
		SessionHolder sessionHolder = (SessionHolder) TransactionSynchronizationManager.getResource(sessionFactory);
		if (sessionHolder == null) {
			log.error("Can not get seesion from spring neo4j sessionHolder, the previously created transaction will be invalidated.");
			return sessionFactory.openSession();
		}
		return sessionHolder.getSession();
    }
}

