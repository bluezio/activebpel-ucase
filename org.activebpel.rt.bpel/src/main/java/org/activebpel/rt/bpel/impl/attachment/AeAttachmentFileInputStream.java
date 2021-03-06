// $Header: /Development/AEDevelopment/projects/org.activebpel.rt.bpel/src/org/activebpel/rt/bpel/impl/attachment/AeAttachmentFileInputStream.java,v 1.1 2007/05/24 00:57:1
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
package org.activebpel.rt.bpel.impl.attachment;

import java.io.File;
import java.io.FileNotFoundException;

import org.activebpel.rt.util.AeBlobInputStream;

/**
 * Extends {@link AeBlobInputStream} to automatically add a reference to an
 * {@link AeAttachmentFile}.
 */
public class AeAttachmentFileInputStream extends AeBlobInputStream
{
   /**
    * Constructs a new input stream on the given file.
    *
    * @param aFile
    * @throws FileNotFoundException
    */
   public AeAttachmentFileInputStream(File aFile) throws FileNotFoundException
   {
      super(aFile);
      
      if (aFile instanceof AeAttachmentFile)
      {
         ((AeAttachmentFile) aFile).addReference();
      }
   }
}
