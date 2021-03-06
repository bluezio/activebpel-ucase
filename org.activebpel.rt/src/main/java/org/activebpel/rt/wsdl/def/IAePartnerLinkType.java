// $Header: /Development/AEDevelopment/projects/org.activebpel.rt/src/org/activebpel/rt/wsdl/def/IAePartnerLinkType.java,v 1.3 2004/07/08 13:09:4
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
package org.activebpel.rt.wsdl.def;

import java.util.Iterator;


/**
 * This interface represents a Partner Link Type element.  It contains
 * information about operations associated with this Partner Link Type.
 */
public interface IAePartnerLinkType
{
   /**
    * Add a Role element to this Partner Link Type.
    * @param aRole
    */
   public void addRole(IAeRole aRole);

   /**
    * Find a specific Role element given its Role name.
    * @param aName
    * @return IAeRole
    */
   public IAeRole findRole(String aName);

   /**
    * Get all the Roles defined for this Partner Link Type.
    * @return Iterator
    */
   public Iterator getRoleList();
   
   /**
    * Remove a Role that is associated with this Partner Link Type. 
    * @param aName
    * @return IAeRole
    */
   public IAeRole removeRole(String aName);

   /**
    * Get the name of this Partner Link Type.
    * @return String
    */
   public String getName();

   /**
    * Set the name for Partner Link Type.
    * @param aName
    */
   public void setName(String aName);

}
