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

public class NumMaxResultsNode extends NodeWSCoL {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7718066629837722997L;
	private String value=null;
	@Override
	public void evaluate(InputMonitor inputMonitor, Aliases aliases,
			AliasNodes aliasNodes) throws WSCoLException {
		value=(String)((NodeWSCoL)this.getFirstChild()).getMonitoringValue();

	}

	@Override
	public Integer getMonitoringValue() throws WSCoLException {
		Integer assType=null;
		try {
			assType=Integer.parseInt(value);
		} catch (NumberFormatException e) {
			assType=10;
			return assType;
			}
		if (assType==-1)
			assType=10;
		return assType;
		
	}
	@Override
	public String toString() {
		return "NumOfResults";
	}

}