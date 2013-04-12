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

package it.polimi.monitor.nodes;

import it.polimi.exception.WSCoLException;
import it.polimi.monitor.InputMonitor;

public class UserIdNode extends NodeWSCoL {
	
	private static final long serialVersionUID = 86059267440286888L;
	private String value=null;
	@Override
	public void evaluate(InputMonitor inputMonitor, Aliases aliases,
			AliasNodes aliasNodes) throws WSCoLException {
		NodeWSCoL node=(NodeWSCoL)this.getFirstChild();
		if (node!=null)
			value=(String)node.getMonitoringValue();

	}

	@Override
	public String getMonitoringValue() throws WSCoLException {
		return value;
	}
	@Override
	public String toString() {
		return "UserID";
	}

}
