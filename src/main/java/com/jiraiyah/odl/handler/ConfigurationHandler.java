/**
 * Created by Alireza Khodakarami on 11/10/2014.
 */

package com.jiraiyah.odl.handler;

import com.jiraiyah.odl.Configurations;
import com.jiraiyah.odl.Reference;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationHandler
{
	//region Section related classes ** DO NOT CHANGE **

	public static class Section
	{
		public final String name;
		public final String lang;

		public Section ( String name, String lang )
		{
			this.name = name;
			this.lang = lang;
			register();
		}

		private void register ()
		{
			Sections.add( this );
		}

		public String lc ()
		{
			return name.toLowerCase();
		}
	}

	public static final List< Section > Sections;

	static
	{
		Sections = new ArrayList< Section >();
	}
	//endregion

	public static Configuration configuration;

	//region Section Defenitions

	public static final Section SectionGlobal = new Section( "Global", "global" );

	//endregion

	public static void init ( File configFile )
	{
		if ( configuration == null )
		{
			configuration = new Configuration( configFile );
			loadConfiguration();
		}
	}

	@SubscribeEvent
	public void onConfigurationChangedEvenet ( ConfigChangedEvent.OnConfigChangedEvent event )
	{
		if ( event.modID.equalsIgnoreCase( Reference.MOD_ID ) )
		{
			loadConfiguration();
		}
	}

	private static void loadConfiguration ()
	{
		Configurations.LOAD = configuration.get( SectionGlobal.name, "Get Ore Dictionary Entry", Configurations.LOAD, "Turn to false to disable the mod." ).getBoolean(Configurations.LOAD);
		if (configuration.hasChanged())
			configuration.save();
	}
}
