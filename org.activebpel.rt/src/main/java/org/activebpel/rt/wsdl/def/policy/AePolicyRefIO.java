// $Header: /Development/AEDevelopment/projects/org.activebpel.rt/src/org/activebpel/rt/wsdl/def/policy/AePolicyRefIO.java,v 1.1 2007/07/27 18:08:5
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
package org.activebpel.rt.wsdl.def.policy;

import java.io.PrintWriter;

import javax.wsdl.Definition;
import javax.wsdl.WSDLException;
import javax.wsdl.extensions.ExtensibilityElement;
import javax.wsdl.extensions.ExtensionRegistry;
import javax.xml.namespace.QName;

import org.activebpel.rt.util.AeUtil;
import org.activebpel.rt.wsdl.def.AeWSDLExtensionIO;
import org.activebpel.rt.wsdl.def.AeWSDLPrefixes;
import org.activebpel.rt.wsdl.def.IAeBPELExtendedWSDLConst;
import org.w3c.dom.Element;

/**
 * This class serializes and deserializes a PolicyReference extension
 * implementation. 
 */
public class AePolicyRefIO extends AeWSDLExtensionIO implements IAeBPELExtendedWSDLConst
{
   /**
    * Serialize a wsp:PolicyReference extension element into a the PrintWriter.
    * 
    * @param aParentType a class object indicating where in the WSDL document
    * this extensibility element was encountered. 
    * @param aQname the QName of this extension element.
    * @param aExtElement the extensibility element to be serialized.
    * @param aWriter the PrintWriter where we're serializing to.
    * @param aDefinition the Definition that this extensibility element was
    * encountered in.
    * @param aExtReg the associated ExtensionRegistry.
    */
   public void marshall(
      Class aParentType,
      QName aQname,
      ExtensibilityElement aExtElement,
      PrintWriter aWriter,
      Definition aDefinition,
      ExtensionRegistry aExtReg)
      throws WSDLException
   {
      IAePolicyReference policyRef = (IAePolicyReference)aExtElement;

      Element domElem = createElement(aQname);

      // Determine the Namespace prefix of the policy extension.
      String preferredPrefix = AeWSDLPrefixes.getPolicyPrefix(aQname.getNamespaceURI());
      String prefix = getPrefixOrCreateLocally(aQname.getNamespaceURI(), aDefinition, domElem, preferredPrefix); 
      domElem.setPrefix(prefix);
      
      if (!AeUtil.isNullOrEmpty(policyRef.getReferenceURI()))
      {
         domElem.setAttribute(IAePolicyReference.URI_ATTRIBUTE, policyRef.getReferenceURI());         
      }
      
      writeElement(domElem, aWriter);
   }

   /**
    * Deserialize a wsp:PolicyReference extension element into a
    * AePolicyRefImpl implementation object.
    * 
    * @param aParentType a class object indicating where in the WSDL document
    * this extensibility element was encountered. 
    * @param aQName the QName of this extension element.
    * @param aPolicyElem the extensibility DOM element to be deserialized.
    * @param aDefinition the Definition that this extensibility element was
    * encountered in.
    * @param aExtReg the associated ExtensionRegistry.
    * 
    * @return ExtensibilyElement the implementation mapping class for this
    * extension.
    */
   public ExtensibilityElement unmarshall(
      Class aParentType,
      QName aQName,
      Element aPolicyElem,
      Definition aDefinition,
      ExtensionRegistry aExtReg)
      throws WSDLException
   {
      // Create a new policy reference implementation object.
      AePolicyRefImpl policy = new AePolicyRefImpl(aPolicyElem);
      policy.setRequired(Boolean.TRUE);
      policy.setElementType(aQName);
      return policy;
   }

}
