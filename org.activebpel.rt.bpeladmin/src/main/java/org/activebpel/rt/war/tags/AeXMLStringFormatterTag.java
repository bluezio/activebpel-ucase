//$Header: /Development/AEDevelopment/projects/org.activebpel.rt.war/src/org/activebpel/rt/war/tags/AeXMLStringFormatterTag.java,v 1.1 2007/04/24 17:23:1
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
package org.activebpel.rt.war.tags;

import java.text.Format;

import javax.servlet.jsp.JspException;

import org.activebpel.rt.AeException;
import org.activebpel.rt.util.AeHTMLFormatter;

/**
 * This class formats an XML string into one that can be properly rendered for display
 * in a browser.  Basically this means converting &lt; and &gt; characters into their
 * respective HTML entities.
 */
public class AeXMLStringFormatterTag extends AeAbstractPropertyFormatterTag
{
   /**
    * Overrides method to formats an XML string into one that will render
    * properly in HTML. It does this by replacing &lt; and &gt; characters with
    * their equivalent HTML entities.
    *
    * @see org.activebpel.rt.war.tags.AeAbstractPropertyFormatterTag#getFormattedText()
    */
   protected String getFormattedText() throws JspException
   {
      try
      {
         String value = (String) getPropertyFromBean();
         return AeHTMLFormatter.formatXMLString(value);
      }
      catch (ClassCastException e)
      {
         throw new JspException(e);
      }
   }

   /**
    * Overrides method to do nothing.
    * 
    * @see org.activebpel.rt.war.tags.AeAbstractPropertyFormatterTag#createFormatter(java.lang.String)
    */
   protected Format createFormatter(String aPattern) throws AeException
   {
      return null;
   }
}
