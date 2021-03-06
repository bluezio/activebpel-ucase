//$Header: /Development/AEDevelopment/projects/org.activebpel.rt.bpel/src/org/activebpel/rt/bpel/coord/AeCoordinationFaultException.java,v 1.2 2006/01/19 20:13:5
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
package org.activebpel.rt.bpel.coord;

/**
 * Exception generated by either the coordinator or the particpant
 * due to errors related to the coordination activity.
 * 
 * Note: This is an internal implementation tailored to be used with requirement 111
 * i.e. the coordination error type is a simple string code instead of the wscoord name spaced
 * fault code (which would be sent as part of the SOAP faults). 
 * 
 * The final implemention should follow the Coordination faults described as per
 * WS-Coordination RFC section 4.
 *  
 */
public class AeCoordinationFaultException extends AeCoordinationException
{
   public static final String  INVALID_STATE       = "aecoor:InvalidState"; //$NON-NLS-1$
   public static final String  INVALID_PROTOCOL    = "aecoor:InvalidProtocol"; //$NON-NLS-1$
   public static final String  INVALID_PARAMETERS  = "aecoor:InvalidParameters"; //$NON-NLS-1$
   public static final String  NO_ACTIVITY         = "aecoor:NoActivity"; //$NON-NLS-1$
   public static final String  CONTEXT_REFUSED     = "aecoor:ContextRefused"; //$NON-NLS-1$
   public static final String  ALREADY_REGISTERED  = "aecoor:AlreadyRegistered"; //$NON-NLS-1$
   
   /**
    * Fault code.
    */
   private String mFaultCode = ""; //$NON-NLS-1$
   
   /**
    * Constructs the fault given the fault code.
    * @param aFaultCode faultCode as per WSBA specifcation.
    */
   public AeCoordinationFaultException(String aFaultCode)
   {
      super(aFaultCode);
      setFaultCode(aFaultCode);
   }

   /**
    * Construct the fault object given the fault code and a descriptive fault message.
    * @param aFaultCode faultCode as per WSBA specifcation.   
    * @param aInfo error message.
    */
   public AeCoordinationFaultException(String aFaultCode, String aInfo)
   {
      super(aInfo);
      setFaultCode(aFaultCode);
   }

   /**
    * Construct the fault object given the fault code and the root cause. 
    * @param aFaultCode faultCode as per WSBA specifcation.  
    * @param aRootCause root cause.
    */
   public AeCoordinationFaultException(String aFaultCode, Throwable aRootCause)
   {
      super(aRootCause);
      setFaultCode(aFaultCode);
   }

   /**
    * Construct the fault object given the fault code, description and the root cause. 
    * @param aFaultCode faultCode as per WSBA specifcation.   
    * @param aInfo fault description or message.
    * @param aRootCause root cause.
    */
   public AeCoordinationFaultException(String aFaultCode, String aInfo, Throwable aRootCause)
   {
      super(aInfo, aRootCause);
      setFaultCode(aFaultCode);
   }
   
   /**
    * @return Returns the faultCode.
    */
   public String getFaultCode()
   {
      return mFaultCode;
   }
   
   /**
    * @param aFaultCode The faultCode to set.
    */
   public void setFaultCode(String aFaultCode)
   {
      mFaultCode = aFaultCode;
   }
}
