//$Header: /Development/AEDevelopment/projects/org.activebpel.rt.bpel/src/org/activebpel/rt/bpel/def/validation/expr/functions/AeGetProcessIdFunctionValidator.java,v 1.1 2007/06/29 22:24:1
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
package org.activebpel.rt.bpel.def.validation.expr.functions; 

import org.activebpel.rt.bpel.AeMessages;
import org.activebpel.rt.bpel.def.expr.AeScriptFuncDef;
import org.activebpel.rt.bpel.def.validation.expr.AeExpressionValidationResult;
import org.activebpel.rt.bpel.def.validation.expr.IAeExpressionValidationContext;

/**
 * Validates the ActiveBPEL extension function getProcessId().
 */
public class AeGetProcessIdFunctionValidator extends AeAbstractActiveBpelExtensionFunctionValidator
{   
   /**
    * @see org.activebpel.rt.bpel.def.validation.expr.functions.AeAbstractActiveBpelExtensionFunctionValidator#validate(org.activebpel.rt.bpel.def.expr.AeScriptFuncDef, org.activebpel.rt.bpel.def.validation.expr.AeExpressionValidationResult, org.activebpel.rt.bpel.def.validation.expr.IAeExpressionValidationContext)
    */
   public void validate(AeScriptFuncDef aScriptFunction,
         AeExpressionValidationResult aResult,
         IAeExpressionValidationContext aValidationContext)
   {
      super.validate(aScriptFunction, aResult, aValidationContext);         

      int numArgs = aScriptFunction.getArgs().size();
      if (numArgs != 0)
      {
         addError(aResult, AeMessages.getString("AeAbstractActiveBpelExtensionFunctionValidator.ERROR_INCORRECT_ARGS_NUMBER"), //$NON-NLS-1$
               new Object[] { aScriptFunction.getName(), new Integer(0), new Integer(numArgs), aResult.getParseResult().getExpression() });
      }
   }
}
 
