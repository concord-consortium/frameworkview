/*
 *  Copyright (C) 2004  The Concord Consortium, Inc.,
 *  10 Concord Crossing, Concord, MA 01742
 *
 *  Web Site: http://www.concord.org
 *  Email: info@concord.org
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * END LICENSE */

/*
 * Last modification information:
 * $Revision: 1.3 $
 * $Date: 2006-05-15 20:54:51 $
 * $Author: scytacki $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
*/
package org.concord.view;

import java.awt.Color;
import java.util.Vector;

import org.concord.framework.util.CheckedColorTreeModel;

public class MultiCheckedColorTreeModel
    implements CheckedColorTreeModel
{
    Vector models = new Vector();
    
    public void addModel(CheckedColorTreeModel model)
    {
        models.add(model);
    }
    
    public String getItemTypeName()
    {
        CheckedColorTreeModel treeModel = 
            (CheckedColorTreeModel)models.get(0);
        return treeModel.getItemTypeName();
    }

    public Object addItem(Object parent, String name, Color color)
    {
        Vector itemList = new Vector();
        for(int i=0; i<models.size(); i++) {
            CheckedColorTreeModel treeModel = 
                (CheckedColorTreeModel)models.get(i);
            Object item = treeModel.addItem(null, name, color);
            itemList.add(item);
        }

        return itemList;
    }

    public Object removeItem(Object parent, Object item)
    {
        for(int i=0; i<models.size(); i++) {
            CheckedColorTreeModel treeModel = 
                (CheckedColorTreeModel)models.get(i);
            Vector itemList = (Vector)item;
            treeModel.removeItem(null, itemList.get(i));
        }
        
        return null;
    }

    public void setSelectedItem(Object item, boolean checked)
    {
        for(int i=0; i<models.size(); i++) {
            CheckedColorTreeModel treeModel = 
                (CheckedColorTreeModel)models.get(i);
            Vector itemList = (Vector)item;
            Object subItem = null;
            if(itemList != null && itemList.size() > i) {
                subItem = itemList.get(i);
            }
            treeModel.setSelectedItem(subItem, checked);
        }
    }

    public void updateItems()
    {
        for(int i=0; i<models.size(); i++) {
            CheckedColorTreeModel treeModel = 
                (CheckedColorTreeModel)models.get(i);
            treeModel.updateItems();
        }
    }

    public Color getItemColor(Object item)
    {
        CheckedColorTreeModel treeModel = 
            (CheckedColorTreeModel)models.get(0);
        Vector itemList = (Vector)item;
        return treeModel.getItemColor(itemList.get(0));        
    }

    public String getItemLabel(Object item)
    {
        CheckedColorTreeModel treeModel = 
            (CheckedColorTreeModel)models.get(0);
        Vector itemList = (Vector)item;
        return treeModel.getItemLabel(itemList.get(0));        
    }

    public void setItemLabel(Object item, String label)
    {
        for(int i=0; i<models.size(); i++) {
            CheckedColorTreeModel treeModel = 
                (CheckedColorTreeModel)models.get(i);
            Vector itemList = (Vector)item;
            treeModel.setItemLabel(itemList.get(i), label);
        }
    }

    public void setItemChecked(Object item, boolean checked)
    {
        for(int i=0; i<models.size(); i++) {
            CheckedColorTreeModel treeModel = 
                (CheckedColorTreeModel)models.get(i);
            Vector itemList = (Vector)item;
            treeModel.setItemChecked(itemList.get(i), checked);
        }
    }
    
    public Vector getItems(Object parent)
    {
        Vector multiItems = new Vector();
        for(int i=0; i<models.size(); i++) {
            CheckedColorTreeModel treeModel = 
                (CheckedColorTreeModel)models.get(i);
            Vector items = treeModel.getItems(parent);
            for(int j=0; j<items.size(); j++) {
                Object item = items.get(j);
                if(multiItems.size() <= j){
                    multiItems.add(new Vector());
                }
                Vector itemList = (Vector)multiItems.get(j);
                itemList.add(item);
            }
        }
        return multiItems;
    }

    public Color getNewColor()
    {
        CheckedColorTreeModel treeModel = 
            (CheckedColorTreeModel)models.get(0);
        return treeModel.getNewColor();
    }
}
