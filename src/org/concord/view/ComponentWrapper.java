package org.concord.view;

import java.awt.Component;

public interface ComponentWrapper
{
	public Component wrapComponent(Component comp);
	public String getOriginalClassName();
}

