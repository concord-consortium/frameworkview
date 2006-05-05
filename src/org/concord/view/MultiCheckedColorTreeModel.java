/*
 * Last modification information:
 * $Revision: 1.1 $
 * $Date: 2006-05-05 15:47:33 $
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
            if(itemList != null) {
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
