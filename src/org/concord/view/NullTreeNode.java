/**
 * 
 */
package org.concord.view;

import org.concord.framework.util.SimpleTreeNode;

/**
 * NullTreeNode <br>
 * A tree node for displaying a null object.  This is useful for collections or other objects
 * which can hold null objects.  
 * <p>
 * Date created: Oct 9, 2007
 * 
 * @author scytacki<p>
 *
 */
public class NullTreeNode
    implements SimpleTreeNode
{
	String name = "null object";
	
	public NullTreeNode()
	{		
	}
	
	public NullTreeNode(String name)
	{
		this.name = name;
	}
	
    public SimpleTreeNode getChild(int index)
    {
        return null;
    }

    public int getChildCount()
    {
        return 0;
    }

    public int getIndexOfChild(SimpleTreeNode child)
    {
        return -1;
    }

    public Object getObject()
    {
    	return null;
    }

    public void setName(String name)
    {
    }

    public String toString()
    {
    	return name;
    }
}