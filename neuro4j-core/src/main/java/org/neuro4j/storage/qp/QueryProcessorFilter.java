package org.neuro4j.storage.qp;

import org.neuro4j.core.Connected;
import org.neuro4j.storage.Storage;

// Query Processor Filter
public interface QueryProcessorFilter {

	public void filter(Connected er, Storage storage);
	
}
