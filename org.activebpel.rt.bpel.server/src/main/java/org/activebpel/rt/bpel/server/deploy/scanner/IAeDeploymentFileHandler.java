//$Header: /Development/AEDevelopment/projects/org.activebpel.rt.bpel.server/src/org/activebpel/rt/bpel/server/deploy/scanner/IAeDeploymentFileHandler.java,v 1.1 2005/06/17 21:51:1
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
package org.activebpel.rt.bpel.server.deploy.scanner;

import java.io.File;

import org.activebpel.rt.AeException;
import org.activebpel.rt.bpel.server.logging.IAeDeploymentLogger;


/**
 * Interface for interacting with file system deployments.
 */
public interface IAeDeploymentFileHandler
{
   
   /**
    * Start scanning the deployment directory to look for changes.
    */
   public void startScanning();
   
   /**
    * Handle any initial deployments that need to be processed before
    * the engine can be started.
    */
   public void handleInitialDeployments();

   /**
    * Handle the deployment of a single BPR/WSR.
    * 
    * @param aFile
    * @param aBprName
    * @param aLogger
    * @throws AeException
    */
   public void handleDeployment(File aFile, String aBprName, IAeDeploymentLogger aLogger)  throws AeException;

   /**
    * Stop scanning and release any resources.
    */
   public void stopScanning();
   
}
