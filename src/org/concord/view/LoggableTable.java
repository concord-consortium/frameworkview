
/*
 *  Copyright (C) 2004  The Concord Consortium, Inc.,
 *  10 Concord Crossing, Concord, MA 01741
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
 */

package org.concord.view;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import org.concord.framework.logging.LogHintMessage;
import org.concord.framework.logging.LogManager;
import org.concord.framework.logging.Loggable;



public class LoggableTable
extends JTable
implements Loggable
{
	protected int logmode = Loggable.LOG_NODE_EXIT;
	LogManager logManager;
	
    public LoggableTable() {
    	super();
    }
    public LoggableTable(TableModel dm) {
        super(dm);
    }
    public LoggableTable(TableModel dm, TableColumnModel cm) {
    	super(dm,cm);
    }
    public LoggableTable(TableModel dm, TableColumnModel cm, ListSelectionModel sm) {
    	super(dm,cm,sm);
    }
    public LoggableTable(int numRows, int numColumns) {
     	super(numRows,numColumns);
   }
    public LoggableTable(final Vector rowData, final Vector columnNames) {
     	super(rowData,columnNames);
    }
    
	public LogManager getLogManager(){return logManager;}
	public void setLogManager(LogManager logManager){this.logManager = logManager;}
   
    
    public void setRowCount(int rowCount) { 
		TableModel tableModel = getModel();
		if(tableModel instanceof DefaultTableModel){
			try{
				((DefaultTableModel)tableModel).setRowCount(rowCount); 
			}catch(java.lang.NoSuchMethodError e){//JDK 1.1 
				((DefaultTableModel)tableModel).setNumRows(rowCount); 
			}
    	}
    } 

	public void setColumnIdentifiers(Vector newIdentifiers) {
		TableModel tableModel = getModel();
		if(tableModel instanceof DefaultTableModel){
			((DefaultTableModel)tableModel).setColumnIdentifiers(newIdentifiers); 
    	}
	}
    public void setColumnIdentifiers(Object[] newIdentifiers) {
		TableModel tableModel = getModel();
		if(tableModel instanceof DefaultTableModel){
			((DefaultTableModel)tableModel).setColumnIdentifiers(newIdentifiers); 
    	}
    }

	public void log(Writer writer,LogHintMessage hint){
		if((getLogMode() & Loggable.LOG_NODE_EXIT) == 0) return;
		if((hint == null) || (hint.getLogFrom() != Loggable.LOG_FROM_NODE_EXIT && hint.getLogFrom() != Loggable.LOG_FROM_FORCE_LOG)) return;
		if(writer == null) return;
        System.out.println("Log JTable");
	}
	public void log(OutputStream out,LogHintMessage hint){
		log(new OutputStreamWriter(out),hint);
	}
	
	public Loggable getLoggable(Object selector){
		return this;
	}
	
	public void setLogMode(int logmode){
		this.logmode = logmode;
	}
	public int  getLogMode(){
		return logmode;
	}
    
//	AbstractTableModel.java
//	DefaultTableModel.java
//			tableModel1.setColumnCount(5);

}

/*
<event name="My lonely Table">
    <date> 2003.11.18.23.12.46&nbsp;11/18/03 | 23:12:46 </date>
    <eventlogcontent>
        <property name="class" value="org.concord.pedagogica.ui.LogableTable">
            <property name="columns">
                <property name="1" value="name1"/>
                <property name="2" value="name2"/>
                <property name="3" value="name3"/>
            </property>
            <property name="raw" value="1">
                <property name="1" value="1bla-bla-bla1"/>
                <property name="2" value="1bla-bla-bla2"/>
                <property name="3" value="1bla-bla-bla33"/>
            </property>
            <property name="raw" value="2">
                <property name="1" value="2bla-bla-bla1"/>
                <property name="2" value="2bla-bla-bla2"/>
                <property name="3" value="2bla-bla-bla33"/>
            </property>
            <property name="raw" value="3">
                <property name="1" value="3bla-bla-bla1"/>
                <property name="2" value="3bla-bla-bla2"/>
                <property name="3" value="3bla-bla-bla33"/>
            </property>
            <property name="raw" value="4">
                <property name="1" value="4bla-bla-bla1"/>
                <property name="2" value="4bla-bla-bla2"/>
                <property name="3" value="4bla-bla-bla33"/>
            </property>
        </property>
    </eventlogcontent>
</event>


*/

//JTable.java