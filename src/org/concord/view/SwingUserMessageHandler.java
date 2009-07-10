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
 * Created on Jan 13, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.concord.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.concord.framework.text.UserMessageHandler;
import org.concord.framework.text.UserMessageHandlerExt1;

/**
 * @author Informaiton Services
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SwingUserMessageHandler 
	implements UserMessageHandlerExt1 {

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

	public void showMessage(String message, String title, final String details) {
		String[] options = new String[]{"Ok", "Details"};		
		final JOptionPane optionsPane = 
			new JOptionPane(message,
					JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, 
					null, options, options[0]){
			JScrollPane detailsScroll = null;
			
			public void setValue(Object newValue) {
				if(newValue.equals("Details")){
					JDialog d = (JDialog) SwingUtilities.getRoot(this);
					if(detailsScroll != null){
					    getRootPane().getContentPane().remove(detailsScroll);
					    detailsScroll = null;
					    d.setResizable(false);
					} else {
						JTextArea textArea = new JTextArea(7, 50);
						textArea.setLineWrap(true);
						textArea.setWrapStyleWord(true);
						textArea.setText(details);
						detailsScroll = new JScrollPane(textArea);
						detailsScroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
						detailsScroll.setBackground(null);
						textArea.setCaretPosition(0);
						getRootPane().getContentPane().add(detailsScroll, BorderLayout.SOUTH);
						d.setResizable(true);
					}
					d.pack();
				} else {
					super.setValue(newValue);
				}
			}

		};

		final JDialog dialog = optionsPane.createDialog(component, title);
		
		dialog.pack();
		dialog.setVisible(true);		
	}
	
	

}
