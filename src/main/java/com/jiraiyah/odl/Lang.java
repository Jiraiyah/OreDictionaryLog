/**
 * Created by Alireza Khodakarami on 11/10/2014.
 */

package com.jiraiyah.odl;

import net.minecraft.util.StatCollector;

public class Lang
{
	public static final String prefix ="odl.";

	public static String localize ( String s )
	{
		return localize( s, true );
	}

	public static String localize ( String s, boolean append)
	{
		if (append)
			s = prefix+s;
		return StatCollector.translateToLocal( s );
	}

	public static String[] localizeList ( String string )
	{
		String s = localize( string );
		return s.split( "\\|" );
	}
}
