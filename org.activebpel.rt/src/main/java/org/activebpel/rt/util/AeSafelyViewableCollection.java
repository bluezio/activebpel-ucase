//$Header: /Development/AEDevelopment/projects/org.activebpel.rt/src/org/activebpel/rt/util/AeSafelyViewableCollection.java,v 1.3 2006/02/14 22:34:0
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
package org.activebpel.rt.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Implements a {@link java.util.Collection} wrapper that maintains a safely
 * readable view of an underlying base collection. All methods that modify the
 * collection are synchronized on the base collection, but all methods that
 * merely examine the collection delegate to a copy of the base collection.
 */
public class AeSafelyViewableCollection implements Collection
{
   /** The base collection. */
   private final Collection mBaseCollection;

   /** Internal safe view of the base collection. */
   private Collection mSafeView;

   /**
    * Constructs a safely viewable wrapper for the specified base collection.
    */
   public AeSafelyViewableCollection(Collection aBaseCollection)
   {
      mBaseCollection = aBaseCollection;

      synchronized (mBaseCollection)
      {
         createSafeView();
      }
   }

   /**
    * Creates the internal safe view for the base collection.
    */
   protected void createSafeView()
   {
      if (mBaseCollection instanceof Set)
      {
         // Create a view that preserves the base collection's iteration order
         // and is efficient for contains().
         mSafeView = new LinkedHashSet(mBaseCollection);
      }
      else
      {
         // Create a view that preserves the base collection's iteration order.
         mSafeView = new ArrayList(mBaseCollection);
      }
   }

   /**
    * @see java.util.Collection#add(java.lang.Object)
    */
   public boolean add(Object aObject)
   {
      synchronized (mBaseCollection)
      {
         boolean changed = mBaseCollection.add(aObject);

         if (changed)
         {
            createSafeView();
         }

         return changed;
      }
   }

   /**
    * @see java.util.Collection#addAll(java.util.Collection)
    */
   public boolean addAll(Collection aCollection)
   {
      synchronized (mBaseCollection)
      {
         boolean changed = mBaseCollection.addAll(aCollection);

         if (changed)
         {
            createSafeView();
         }

         return changed;
      }
   }

   /**
    * @see java.util.Collection#clear()
    */
   public void clear()
   {
      synchronized (mBaseCollection)
      {
         mBaseCollection.clear();

         createSafeView();
      }
   }

   /**
    * @see java.util.Collection#contains(java.lang.Object)
    */
   public boolean contains(Object aObject)
   {
      return mSafeView.contains(aObject);
   }

   /**
    * @see java.util.Collection#containsAll(java.util.Collection)
    */
   public boolean containsAll(Collection aCollection)
   {
      return mSafeView.containsAll(aCollection);
   }

   /**
    * @see java.util.Collection#isEmpty()
    */
   public boolean isEmpty()
   {
      return mSafeView.isEmpty();
   }

   /**
    * @see java.util.Collection#iterator()
    */
   public Iterator iterator()
   {
      return mSafeView.iterator();
   }

   /**
    * @see java.util.Collection#remove(java.lang.Object)
    */
   public boolean remove(Object aObject)
   {
      synchronized (mBaseCollection)
      {
         boolean changed = mBaseCollection.remove(aObject);

         if (changed)
         {
            createSafeView();
         }

         return changed;
      }
   }

   /**
    * @see java.util.Collection#removeAll(java.util.Collection)
    */
   public boolean removeAll(Collection aCollection)
   {
      synchronized (mBaseCollection)
      {
         boolean changed = mBaseCollection.removeAll(aCollection);

         if (changed)
         {
            createSafeView();
         }

         return changed;
      }
   }

   /**
    * @see java.util.Collection#retainAll(java.util.Collection)
    */
   public boolean retainAll(Collection aCollection)
   {
      synchronized (mBaseCollection)
      {
         boolean changed = mBaseCollection.retainAll(aCollection);

         if (changed)
         {
            createSafeView();
         }

         return changed;
      }
   }

   /**
    * @see java.util.Collection#size()
    */
   public int size()
   {
      return mSafeView.size();
   }

   /**
    * @see java.util.Collection#toArray()
    */
   public Object[] toArray()
   {
      return mSafeView.toArray();
   }

   /**
    * @see java.util.Collection#toArray(java.lang.Object[])
    */
   public Object[] toArray(Object[] aArray)
   {
      return mSafeView.toArray(aArray);
   }
}
