/*
 Copyright 2007 Politecnico di Milano
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

package it.polimi.monitor.nodes.complex;

import java.util.logging.Logger;

import it.polimi.exception.WSCoLCastException;
import it.polimi.exception.WSCoLException;
import it.polimi.monitor.InputMonitor;
import it.polimi.monitor.nodes.AliasNode;
import it.polimi.monitor.nodes.AliasNodes;
import it.polimi.monitor.nodes.Aliases;
import it.polimi.monitor.nodes.NodeWSCoL;


public class ForAllNode extends ComplexQuantificationNode {
	
	private static final Logger LOGGER = Logger.getLogger(ExistsNode.class.getCanonicalName());
	private static final long serialVersionUID = -1444472461556224669L;

	public ForAllNode() {
		serializeTag="forall";
	}

	@Override
	public void evaluate(InputMonitor inputMonitor, Aliases aliases , AliasNodes tempAliases ) throws WSCoLException {
		LOGGER.info("Start evaluate "+serializeTag);
		aliasNode=(AliasNode)this.getFirstChild();
		aliasNode.setTypeOfExtraction(AliasNode.EXTRACTSTEPBYSTEP);
		aliasNode.evaluate(inputMonitor, aliases, tempAliases);
		condition= (NodeWSCoL) aliasNode.getNextSibling();
		for (int i=0; i < aliasNode.getNumberOfChildren();i++){
			condition.evaluate(inputMonitor, aliases, tempAliases);
			Object res=condition.getMonitoringValue();
			if (!( res instanceof Boolean) ) {
				if(res instanceof String &&(res.equals("false")||res.equals("true")))
						value=Boolean.parseBoolean((String)res);
				else
					throw new WSCoLCastException("Can't make quantification of "+ res.getClass()); 
			} else {
				if ((Boolean)res == false) {
					break;
				} else 
					value=(Boolean)res;
			}
			aliasNode.nextChild();
		}
		tempAliases.removeAliasNode(aliasNode.getIdentifier());	
		LOGGER.info("Finish evaluate "+serializeTag);
	}
	
	public String toString(){
		return "Forall";
	}
}