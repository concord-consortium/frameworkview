/*
 * Created on Jan 13, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.concord.view;

import java.awt.Component;

import javax.swing.JOptionPane;

import org.concord.framework.text.UserMessageHandler;

/**
 * @author Informaiton Services
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SwingUserMessageHandler 
	implements UserMessageHandler {

	private Component component;

	/**
	 * Create a message handler
	 * @param parentComponent
	 *  determines the Frame in which the dialog is displayed,
	 *  if it is null a default Frame is used.  
	 */
	public SwingUserMessageHandler(Component parentComponent) {
		super();
		component = parentComponent;
	}

	/* (non-Javadoc)
	 * @see org.concord.framework.text.UserMessageHandler#showOptionMessage(java.lang.String, java.lang.String, java.lang.String[], java.lang.String)
	 */
	public int showOptionMessage(String message, String title,
			String[] options, String defaultOption) 
	{
		return JOptionPane.showOptionDialog(component, message, title,
				-1, JOptionPane.INFORMATION_MESSAGE, null,
				options, defaultOption);
	}

	/* (non-Javadoc)
	 * @see org.concord.framework.text.UserMessageHandler#showMessage(java.lang.String, java.lang.String)
	 */
	public void showMessage(String message, String title) 
	{
		JOptionPane.showMessageDialog(component, message,
				title, JOptionPane.INFORMATION_MESSAGE);
	}

}
