/*
 * Created on Jul 28, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.concord.view;

import java.util.Vector;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.concord.framework.util.SimpleTreeNode;

/**
 * @author scott
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SimpleTreeModel implements TreeModel 
{
	protected SimpleTreeNode root;
	protected Vector treeModelListeners = new Vector();

	public void setRoot(SimpleTreeNode root)
	{
		this.root = root; 
	}
	
	
	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getRoot()
	 */
	public Object getRoot() 
	{
		return root;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getChildCount(java.lang.Object)
	 */
	public int getChildCount(Object parent) 
	{
		return ((SimpleTreeNode)parent).getChildCount();
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#isLeaf(java.lang.Object)
	 */
	public boolean isLeaf(Object node) 
	{
		return ((SimpleTreeNode)node).getChildCount() == 0;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#addTreeModelListener(javax.swing.event.TreeModelListener)
	 */
	public void addTreeModelListener(TreeModelListener listener) 
	{
		treeModelListeners.addElement(listener);
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#removeTreeModelListener(javax.swing.event.TreeModelListener)
	 */
	public void removeTreeModelListener(TreeModelListener listener) 
	{
		treeModelListeners.removeElement(listener);	
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getChild(java.lang.Object, int)
	 */
	public Object getChild(Object parent, int index) 
	{
		if(parent instanceof SimpleTreeNode) {
			return ((SimpleTreeNode)parent).getChild(index);		
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getIndexOfChild(java.lang.Object, java.lang.Object)
	 */
	public int getIndexOfChild(Object parent, Object child) 
	{
		if ((parent instanceof SimpleTreeNode) && 
				(child instanceof SimpleTreeNode)) {
			return ((SimpleTreeNode)parent).getIndexOfChild((SimpleTreeNode)child);
		}

		return -1;		
	}

    /**
     * Messaged when the user has altered the value for the item
     * identified by path to newValue.  Not used by this model.
	 * @see javax.swing.tree.TreeModel#valueForPathChanged(javax.swing.tree.TreePath, java.lang.Object)
     */
    public void valueForPathChanged(TreePath path, Object newValue) {
        System.out.println("*** valueForPathChanged : "
                           + path + " --> " + newValue);
        SimpleTreeNode node = (SimpleTreeNode)path.getLastPathComponent();
        node.setName((String)newValue);
        fireTreeStructureChanged(node);
    }

    /**
     * The only event raised by this model is TreeStructureChanged with the
     * root as path, i.e. the whole tree has changed.
     */
    public void fireTreeStructureChanged(SimpleTreeNode rootOfChange) 
    {
        int len = treeModelListeners.size();
        TreeModelEvent e = new TreeModelEvent(this, 
                                              new Object[] {rootOfChange});
        for (int i = 0; i < len; i++) {
            ((TreeModelListener)treeModelListeners.elementAt(i)).
                    treeStructureChanged(e);
        }
    }



}
