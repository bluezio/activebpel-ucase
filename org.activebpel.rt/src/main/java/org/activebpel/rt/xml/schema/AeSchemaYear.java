//$Header: /Development/AEDevelopment/projects/org.activebpel.rt/src/org/activebpel/rt/xml/schema/AeSchemaYear.java,v 1.2 2006/09/07 14:41:1
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
package org.activebpel.rt.xml.schema; 

import java.util.SimpleTimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.activebpel.rt.util.AeUtil;


/**
 * Schema calendar field: gYear 
 */
public class AeSchemaYear extends AeAbstractTZBasedSchemaType
{
   /** A regular expression for matching schema year strings. */
   private static Pattern INPUT_PATTERN = Pattern.compile("(-?)([0-9]{4})(Z|(([+-])([0-9]{2}):([0-9]{2})))?"); //$NON-NLS-1$
   /** The output pattern. */
   private static String OUTPUT_PATTERN = "{0,number,0000}{1}"; //$NON-NLS-1$

   /** The year. */
   private int mYear;
   
   /**
    * Creates the schema year object.
    * 
    * @param aYear
    * @param aTimezoneOffset The timezone offset (in minutes from UTC)
    */
   public AeSchemaYear(int aYear, int aTimezoneOffset)
   {
      setYear(aYear);
      setTimeZone(new SimpleTimeZone(aTimezoneOffset * 60000, "")); //$NON-NLS-1$
   }
   
   /**
    * Ctor takes string for the four digit year (not Year 10,000 compliant!)
    * @param aValue
    */
   public AeSchemaYear(String aValue)
   {
      super(aValue);
   }
   
   /**
    * @see org.activebpel.rt.xml.schema.AeAbstractPatternBasedSchemaType#getInputPattern()
    */
   protected Pattern getInputPattern()
   {
      return INPUT_PATTERN;
   }

   /**
    * @see org.activebpel.rt.xml.schema.AeAbstractPatternBasedSchemaType#processMatcher(java.util.regex.Matcher)
    */
   protected void processMatcher(Matcher aMatcher)
   {
      String dirStr = aMatcher.group(1);
      String yearStr = aMatcher.group(2);
      setYear(Integer.parseInt(yearStr));
      if ("-".equals(dirStr)) //$NON-NLS-1$
         setYear(getYear() * -1);

      boolean isUTC = (AeUtil.isNullOrEmpty(aMatcher.group(3))) || ("Z".equals(aMatcher.group(3))); //$NON-NLS-1$
      if (!isUTC)
      {
         char tzDir = aMatcher.group(5).charAt(0);
         String tzHr = aMatcher.group(6);
         String tzMin = aMatcher.group(7);
         setTimeZone(createTimeZone(tzHr, tzMin, tzDir));
      }
      else
      {
         setTimeZone(sUTCTimeZone);
      }
   }

   /**
    * @see org.activebpel.rt.xml.schema.AeAbstractPatternBasedSchemaType#getSchemaTypeName()
    */
   protected String getSchemaTypeName()
   {
      return "xsd:gYear"; //$NON-NLS-1$
   }
   
   /**
    * @see org.activebpel.rt.xml.schema.AeAbstractPatternBasedSchemaType#getOutputPatternArguments()
    */
   protected Object[] getOutputPatternArguments()
   {
      return new Object[] { new Integer(getYear()), formatTimeZone() };
   }

   /**
    * @see org.activebpel.rt.xml.schema.AeAbstractPatternBasedSchemaType#getOutputPattern()
    */
   protected String getOutputPattern()
   {
      return OUTPUT_PATTERN;
   }

   /**
    * @return Returns the year.
    */
   public int getYear()
   {
      return mYear;
   }

   /**
    * @param aYear The year to set.
    */
   protected void setYear(int aYear)
   {
      mYear = aYear;
   }

   /**
    * @see org.activebpel.rt.xml.schema.IAeSchemaType#accept(org.activebpel.rt.xml.schema.IAeSchemaTypeVisitor)
    */
   public void accept(IAeSchemaTypeVisitor aVisitor)
   {
      aVisitor.visit(this);
   }
}
