//$Header: /Development/AEDevelopment/projects/org.activebpel.rt.bpel.server/src/org/activebpel/rt/bpel/server/engine/AeEngineLifecycleWrapper.java,v 1.2 2005/08/10 23:08:2
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
package org.activebpel.rt.bpel.server.engine;

import commonj.timers.TimerListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;

import org.activebpel.rt.AeException;
import org.activebpel.rt.bpel.server.AeMessages;
import org.activebpel.rt.bpel.config.AeDefaultEngineConfiguration;
import org.activebpel.rt.bpel.config.IAeEngineConfiguration;
import org.activebpel.rt.bpel.server.deploy.scanner.AeDeploymentFileInfo;
import org.activebpel.rt.bpel.server.deploy.scanner.IAeDeploymentFileHandler;
import org.activebpel.rt.bpel.server.engine.config.AeFileBasedEngineConfig;
import org.activebpel.rt.bpel.server.logging.IAeLogWrapper;
import org.activebpel.rt.util.AeCloser;
import org.activebpel.rt.util.AeUtil;
import org.activebpel.timer.AeAbstractTimerWork;

/**
 * ActiveBPEL <code>IAeEngineHandler<code> impl.
 */
public class AeEngineLifecycleWrapper implements IAeEngineLifecycleWrapper
{

   /** Initial delay before initiating start sequence. */
   protected long mInitialDelay;
   /** Platform logging. */
   protected IAeLogWrapper mLog;
   /** The file handler. */
   protected IAeDeploymentFileHandler mFileHandler;
   
   /**
    * Constructor.
    * @param aFileHandler
    * @param aInitialDelay
    */
   public AeEngineLifecycleWrapper( IAeLogWrapper aLog, IAeDeploymentFileHandler aFileHandler, long aInitialDelay )
   {
      mLog = aLog;
      mFileHandler = aFileHandler;
      mInitialDelay = aInitialDelay;
   }
   
   /**
    * @see org.activebpel.rt.bpel.server.engine.IAeEngineLifecycleWrapper#init()
    */
   public void init() throws AeException
   {
      AeEngineFactory.preInit(loadEngineConfig());
      AeEngineFactory.init();
      logInfo( "********** " + getConfigDescription() + AeMessages.getString("AeEngineLifecycleWrapper.1") ); //$NON-NLS-1$ //$NON-NLS-2$
   }
   
   protected String getConfigDescription()
   {
      return AeEngineFactory.getEngineAdministration().getEngineConfig().getDescription();
   }
   
   /**
    * Load the engine configuration.
    * @throws AeException
    */
   protected IAeEngineConfiguration loadEngineConfig() throws AeException
   {
      InputStream in = null;
      try
      {
         File engineConfigFile = loadConfigFile();
         in = new FileInputStream( engineConfigFile );
         ClassLoader cl = Thread.currentThread().getContextClassLoader();
         AeFileBasedEngineConfig engineConfig = new AeFileBasedEngineConfig(engineConfigFile,cl);
         AeFileBasedEngineConfig.loadConfig( engineConfig, in, cl );
         return engineConfig;
      }
      catch( Exception e )
      {
         throw new AeException(e);
      }
      finally
      {
         AeCloser.close( in );
      }
   }

    private void dumpStreamToFile(InputStream is, File file)
        throws IOException
    {
        FileOutputStream fOS = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int bytesRead = 0;
        while ((bytesRead = is.read(buffer)) != -1) {
            fOS.write(buffer, 0, bytesRead);
        }
        is.close();
        fOS.close();
    }

   /**
    * Attempt to load the engine config file using the name of the
    * file specified by the "engine.config" init param.  If the file
    * cannot be located, copy the default version (aeEngineConfig.xml)
    * from the classpath into a temporary file.
    * @throws AeException
    */
   protected File loadConfigFile() throws AeException
   {
      File configFile = AeDeploymentFileInfo.getEngineConfigFile();

      if( !configFile.isFile() )
      {
         logError(MessageFormat.format(
             AeMessages.getString("AeEngineLifecycleWrapper.ERROR_0"), //$NON-NLS-1$
             new Object[] {configFile.getPath()}), null );

         try {
             InputStream is = getClass().getResourceAsStream(
                 "/" + AeDefaultEngineConfiguration.DEFAULT_CONFIG_FILE);
             if (is == null) {
                 throw new AeException("Could not find default configuration");
             }
             File tempFile = File.createTempFile("aeEngineConfig", ".xml");
             dumpStreamToFile(is, tempFile);
             return tempFile;
         } catch (Exception ex) {
             throw new AeException(ex);
         }
      }
      else {
          logInfo(MessageFormat.format(
              AeMessages.getString("AeEngineLifecycleWrapper.3"),
              new Object[] {configFile.getPath()})); //$NON-NLS-1$
      }
      return configFile;
   }


   /**
    * Proceed with the engine start sequence. The start routine kicks off a timer to delay actual start.  
    * @throws AeException
    */
   public void start() throws AeException
   {
      long delay = getInitialDelay();

      if (delay <= 0)
      {
         doStart();
      }
      else
      {
         // We need to schedule the start after the preset delay interval
         TimerListener timerWork = new AeAbstractTimerWork()
         {
            public void run()
            {
               doStart();
            }
         };
      
         AeEngineFactory.getTimerManager().schedule(timerWork, getInitialDelay());
      }
   }
   
   /**
    * Start the server if the storage is ready. 
    */
   protected void doStart()
   {
      if (AeEngineFactory.isEngineStorageReady())
      {
         boolean startScanning = true;

         // if the initial deployments fail then
         // something is wrong with the bpr dir
         try
         {
            doInitialDeployments();
         }
         catch( Throwable t )
         {
            startScanning = false;
            AeException.logError( t, AeMessages.getString("AeEngineLifecycleWrapper.ERROR_3") ); //$NON-NLS-1$
         }
         
         startBpelEngine();
         
         // don't scan if initial deployments failed
         if( startScanning )
         {
            startScanning();
         }
      }
   } 
   
   /**
    * Process any deployments necessary before starting the BPEL engine.
    */
   protected void doInitialDeployments()
   {
      mFileHandler.handleInitialDeployments();
   }

   /**
    * Start the BPEL engine.
    */
   protected void startBpelEngine()
   {
      try
      {
         AeEngineFactory.getEngine().start();
         logInfo( "********** " + getConfigDescription() + AeMessages.getString("AeEngineLifecycleWrapper.9") ); //$NON-NLS-1$ //$NON-NLS-2$
      }
      catch( AeException ae )
      {
         ae.logError();
      }
   }
   
   /**
    * Start the directory scanner.
    */
   protected void startScanning()
   {
      mFileHandler.startScanning();
   }

   /**
    * Convenience log for information messages.
    * @param aMessage
    */
   protected void logInfo( String aMessage )
   {
      if( mLog != null )
      {
         mLog.logInfo( aMessage );
      }
   }
   
   /**
    * Convenience log for error messages.
    * @param aMessage
    * @param aProblem
    */
   protected void logError( String aMessage, Throwable aProblem )
   {
      if( mLog != null )
      {
         mLog.logError( aMessage, aProblem );
      }
   }
   
   /**
    * Stop the engine and release any associated resources.
    */
   public void stop()
   {
      String description = getConfigDescription();
      logInfo( description + AeMessages.getString("AeEngineLifecycleWrapper.10")); //$NON-NLS-1$

      try
      {
         mFileHandler.stopScanning();
         AeEngineFactory.getEngine().shutDown();
         logInfo( description + AeMessages.getString("AeEngineLifecycleWrapper.11")); //$NON-NLS-1$
      }
      catch (Exception e)
      {
         // should never happen
         logError( MessageFormat.format("Error shutting down {0}", new Object[] {description}), e); //$NON-NLS-1$
      }
   }
   
   /**
    * Returns the initialDelay to be used during start.
    */
   protected long getInitialDelay()
   {
      return mInitialDelay;
   }
}
