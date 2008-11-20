/*
 * JBoss, Home of Professional Open Source
 * Copyright 2008, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.webbeans.contexts;

import javax.webbeans.ContextNotActiveException;
import javax.webbeans.Dependent;
import javax.webbeans.manager.Bean;

/**
 * The dependent context
 * 
 * @author Nicklas Karlsson
 */
public class DependentContext extends PrivateContext
{

   public DependentContext()
   {
      super(Dependent.class);
      // TODO starts as non-active?
      setActive(false);
   }

   /**
    * Overriden method always creating a new instance
    * 
    *  @param bean The bean to create
    *  @param create Should a new one be created
    */
   @Override
   public <T> T get(Bean<T> bean, boolean create)
   {
      if (!isActive())
      {
         throw new ContextNotActiveException();
      }
      // Dependent contexts don't really use any BeanMap storage
      return create == false ? null : bean.create();
   }

}
