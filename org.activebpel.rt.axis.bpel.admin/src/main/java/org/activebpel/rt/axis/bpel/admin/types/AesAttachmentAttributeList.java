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
package org.activebpel.rt.axis.bpel.admin.types;

public class AesAttachmentAttributeList  implements java.io.Serializable {
    private org.activebpel.rt.axis.bpel.admin.types.AesAttachmentAttribute[] attribute;

    public AesAttachmentAttributeList() {
    }

    public AesAttachmentAttributeList(
           org.activebpel.rt.axis.bpel.admin.types.AesAttachmentAttribute[] attribute) {
           this.attribute = attribute;
    }


    /**
     * Gets the attribute value for this AesAttachmentAttributeList.
     * 
     * @return attribute
     */
    public org.activebpel.rt.axis.bpel.admin.types.AesAttachmentAttribute[] getAttribute() {
        return attribute;
    }


    /**
     * Sets the attribute value for this AesAttachmentAttributeList.
     * 
     * @param attribute
     */
    public void setAttribute(org.activebpel.rt.axis.bpel.admin.types.AesAttachmentAttribute[] attribute) {
        this.attribute = attribute;
    }

    public org.activebpel.rt.axis.bpel.admin.types.AesAttachmentAttribute getAttribute(int i) {
        return this.attribute[i];
    }

    public void setAttribute(int i, org.activebpel.rt.axis.bpel.admin.types.AesAttachmentAttribute _value) {
        this.attribute[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AesAttachmentAttributeList)) return false;
        AesAttachmentAttributeList other = (AesAttachmentAttributeList) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.attribute==null && other.getAttribute()==null) || 
             (this.attribute!=null &&
              java.util.Arrays.equals(this.attribute, other.getAttribute())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAttribute() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAttribute());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAttribute(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
