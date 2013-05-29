/*
 Copyright 2007 Politecnico di Milano
 Copyright 2013 Antonio García-Domínguez (UCA)
 This file is part of Dynamo.

 Dynamo is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 3 of the License, or
 (at your option) any later version.

 Dynamo is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package it.polimi.monitor.nodes;

import it.polimi.exception.WSCoLException;
import it.polimi.monitor.InputMonitor;

import java.util.logging.Logger;

public class XPath_ExpressionNode extends NodeWSCoL {
	private static final Logger LOGGER = Logger.getLogger(XPath_ExpressionNode.class.getCanonicalName());

	private static final long serialVersionUID = -9206263043804090275L;

	private String tree = null;
	private SLASHNode slashNode = null;

	@Override
	public void evaluate(InputMonitor inputMonitor, Aliases aliases,
			AliasNodes tempAliases) throws WSCoLException {
		slashNode = (SLASHNode) getFirstChild();
		slashNode.evaluate(inputMonitor, aliases, tempAliases);
	}

	@Override
	public String getMonitoringValue() throws WSCoLException {
		// Restore the value
		tree = slashNode.getMonitoringValue();
		LOGGER.info("xpath: " + tree);
		return tree;
	}

	@Override
	public String toString() {
		return "XpathExpression";
	}
}