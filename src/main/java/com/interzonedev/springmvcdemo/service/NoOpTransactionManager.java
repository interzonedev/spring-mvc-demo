package com.interzonedev.springmvcdemo.service;

import javax.inject.Named;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.SimpleTransactionStatus;

@Named("noOpTransactionManager")
public class NoOpTransactionManager implements PlatformTransactionManager {

	@Override
	public TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException {
		return new SimpleTransactionStatus();
	}

	@Override
	public void commit(TransactionStatus status) throws TransactionException {
	}

	@Override
	public void rollback(TransactionStatus status) throws TransactionException {
	}
}
