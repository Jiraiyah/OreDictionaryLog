/**
 * Created by Alireza Khodakarami on 11/10/2014.
 */

package com.jiraiyah.odl;

import java.io.FileWriter;
import java.io.PrintWriter;

public class WriteToFile
{
	private String path = "";
	private boolean appendToFile = true;

	public WriteToFile (String path)
	{
		this.path = path;
	}

	public WriteToFile ( String path, boolean appendToFile )
	{
		this.path = path;
		this.appendToFile = appendToFile;
	}

	public void WriteToFile(String textLine)
	{
		try
		{
			FileWriter writer = new FileWriter( path, appendToFile );
			PrintWriter printLine = new PrintWriter( writer );
			printLine.printf( "%s" + "%n", textLine );
			printLine.close();
		}
		catch ( Exception e )
		{
			Log.error( e.getCause() );
		}
	}
}
