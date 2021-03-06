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
package org.activebpel.rt.bpel.def.activity.support;

import java.util.Iterator;

import org.activebpel.rt.bpel.def.AeBaseContainer;
import org.activebpel.rt.bpel.def.visitors.IAeDefVisitor;

/**
 * Models the BPEL 2.0 'toParts' construct.
 */
public class AeToPartsDef extends AeBaseContainer
{
   /**
    * Default c'tor.
    */
   public AeToPartsDef()
   {
      super();
   }

   /**
    * Adds a toPart def to the container.
    * 
    * @param aDef
    */
   public void addToPartDef(AeToPartDef aDef)
   {
      add(aDef);
   }
   
   /**
    * Gets an iterator over all of the toPart defs.
    */
   public Iterator getToPartDefs()
   {
      return getValues();
   }

   /**
    * @see org.activebpel.rt.bpel.def.AeBaseDef#accept(org.activebpel.rt.bpel.def.visitors.IAeDefVisitor)
    */
   public void accept(IAeDefVisitor aVisitor)
   {
      aVisitor.visit(this);
   }
}
