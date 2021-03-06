// $Header: /Development/AEDevelopment/projects/org.activebpel.rt/src/org/activebpel/rt/wsdl/def/castor/AeURILocation.java,v 1.6 2005/02/08 15:27:1
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
package org.activebpel.rt.wsdl.def.castor;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.wsdl.xml.WSDLLocator;

import org.activebpel.rt.AeMessages;
import org.activebpel.rt.util.AeUtil;
import org.exolab.castor.net.URILocation;
import org.xml.sax.InputSource;

/**
 * Implements the Castor interface for handling URIs.
 */
public class AeURILocation extends URILocation
{
   /** Separator for project-relative paths and URL paths. */
   private static final String PATH_SEPARATOR = "/"; //$NON-NLS-1$

   /** The WSDL locator to use for locating schema resources. */
   private final WSDLLocator mWSDLLocator;
   /** The document location. */
   private final String mLocation;
   /** The document base for the document location. */
   private final String mDocumentBase;
   /** The absolute URI for the document. */
   private String mAbsoluteURI;
   /** The URI for the document base. */
   private String mBaseURI;
   /** The document input source provided by the WSDL locator. */
   private InputSource mInputSource;
   /** The <code>Reader</code> to return for the document. */
   private Reader mReader;

   /**
    * Constructs a URI location for the document at the specified location and
    * base using the specified WSDL locator to locate the document.
    */
   public AeURILocation(WSDLLocator aWSDLLocator, String aLocation, String aDocumentBase)
   {
      mWSDLLocator = aWSDLLocator;
      mLocation = aLocation;
      mDocumentBase = aDocumentBase;
   }
   
   /**
    * @see org.exolab.castor.net.URILocation#getAbsoluteURI()
    */
   public String getAbsoluteURI()
   {
      if (mAbsoluteURI == null)
      {
         mAbsoluteURI = AeUtil.resolveImport(getBaseURI(), mLocation);
      }

      return mAbsoluteURI;
   }

   /**
    * @see org.exolab.castor.net.URILocation#getBaseURI()
    */
   public String getBaseURI()
   {
      if (mBaseURI == null)
      {
         mBaseURI = mDocumentBase;

         // If the document base is null, then use the base of the original
         // WSDL.
         if (AeUtil.isNullOrEmpty(mBaseURI))
         {
            mBaseURI = getWSDLLocator().getBaseURI();

            // The Castor-compatible document base is the portion up to and
            // including the last path separator.
            int i = mBaseURI.lastIndexOf(PATH_SEPARATOR);
            if (i >= 0)
            {
               mBaseURI = mBaseURI.substring(0, i + 1);
            }
         }
      }

      return mBaseURI;
   }

   /**
    * Returns the input source for the absolute URI.
    */
   protected InputSource getInputSource() throws IOException
   {
      if (mInputSource == null)
      {
         String parentLocation = getWSDLLocator().getBaseURI(); // the location doing the importing
         String importLocation = getAbsoluteURI();              // the location being imported
         mInputSource = getWSDLLocator().getImportInputSource(parentLocation, importLocation);
         if(mInputSource == null)
            throw new IOException(AeMessages.getString("AeURILocation.ERROR_1") + importLocation); //$NON-NLS-1$
      }

      return mInputSource;
   }

   /**
    * @see org.exolab.castor.net.URILocation#getReader()
    */
   public Reader getReader() throws IOException
   {
      if (mReader == null)
      {
         // Check for a character stream from the input source.
         mReader = getInputSource().getCharacterStream();
      }

      if (mReader == null)
      {
         // Check for a byte stream from the input source.
         if(getInputSource().getEncoding() == null)
            mReader = new InputStreamReader(getInputSource().getByteStream());
         else
            mReader = new InputStreamReader(getInputSource().getByteStream(), getInputSource().getEncoding());
      }

      return mReader;
   }

   /**
    * @see org.exolab.castor.net.URILocation#getRelativeURI()
    */
   public String getRelativeURI()
   {
      return mLocation;
   }

   /**
    * Returns the WSDL locator.
    */
   protected WSDLLocator getWSDLLocator()
   {
      return mWSDLLocator;
   }
}
