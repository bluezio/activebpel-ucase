//$Header: /Development/AEDevelopment/projects/org.activebpel.rt.bpeladmin.war/src/org/activebpel/rt/bpeladmin/war/graph/bpel/figure/AeBpelSimpleActivityFigure.java,v 1.1 2005/04/18 18:31:4
/*
 * Copyright (c) 2004-2006 Active Endpoints, Inc.
 *
 * This program is licensed under the terms of the GNU General Public License
 * Version 2 (the "License") as published by the Free Software Foundation, and 
 * the ActiveBPEL Licensing Policies (the "Policies").  A copy of the License 
 * and the Policies were distributed with this program.  
 *
 * The License is available at:
 * http: *www.gnu.org/copyleft/gpl.html
 *
 * The Policies are available at:
 * http: *www.activebpel.org/licensing/index.html
 *
 * Unless required by applicable law or agreed to in writing, this program is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied.  See the License and the Policies
 * for specific language governing the use of this program.
 */
package org.activebpel.rt.bpeladmin.war.graph.bpel.figure;

import java.awt.Image;

/**
 * Figure for displaying simple acitvities.
 */
public class AeBpelSimpleActivityFigure extends AeBpelFigureBase
{

   /**
    * Constructs the figure with the given name.
    * @param aBpelName
    */
   public AeBpelSimpleActivityFigure(String aBpelName)
   {
      super(aBpelName);
   }
   
   /**
    * Constructs the figure with the given name and icon image.
    * @param aBpelName
    * @param aIconImage activity icon image.
    */   
   public AeBpelSimpleActivityFigure(String aBpelName, Image aIconImage)
   {   
      super(aBpelName, aIconImage);     
   }

}
