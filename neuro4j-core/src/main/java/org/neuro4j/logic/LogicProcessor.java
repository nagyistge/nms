package org.neuro4j.logic;

import java.util.Properties;

import org.neuro4j.core.Entity;
import org.neuro4j.core.Network;
import org.neuro4j.logic.swf.SimpleWorkflowException;
import org.neuro4j.storage.NeuroStorage;

public interface LogicProcessor {

	public void init(Properties properties) throws LogicProcessorException;

	/**
	 * Network usually local - much faster but may not have all data
	 * NeuroStorage has all data but slower
	 * 
	 * @param start
	 * @param network
	 * @param neuroStorage
	 * @param logicContext
	 * @return
	 * @throws SimpleWorkflowException 
	 */
	public LogicContext action(Entity start, Network network, NeuroStorage neuroStorage, LogicContext logicContext) throws LogicProcessorException;
	
}
