package org.neuro4j.logic.def.node;

import org.neuro4j.logic.LogicContext;
import org.neuro4j.logic.def.LogicBlock;
import org.neuro4j.logic.swf.FlowExecutionException;
import org.neuro4j.logic.swf.SWFConstants;

public class StartBlock extends LogicBlock {

	public StartBlock() {
		super();
	}
		
	public StartBlock(String name) {
		super();
		lba.setName(name);
		lba.setProperty(SWFConstants.N4J_CONSOLE_NODE_TYPE, "triangle"); 
	}


	public int execute(LogicContext ctx) throws FlowExecutionException {
		return NEXT;
	}

}
