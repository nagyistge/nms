package org.neuro4j.logic;

import org.neuro4j.core.Connected;
import org.neuro4j.logic.swf.FlowExecutionException;
import org.neuro4j.logic.swf.FlowInitializationException;

public interface ExecutableEntity {

	public void load(Connected entity) throws FlowInitializationException;

	public int execute(LogicContext ctx) throws FlowExecutionException;

}
