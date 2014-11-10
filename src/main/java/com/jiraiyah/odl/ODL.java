/**
 * Created by Alireza Khodakarami on 11/10/2014.
 */

package com.jiraiyah.odl;

import com.jiraiyah.odl.handler.ConfigurationHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Mod ( modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION,
	   guiFactory = Reference.GUI_FACTORY_CLASS)
public class ODL
{
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		ConfigurationHandler.init( event.getSuggestedConfigurationFile() );
		FMLCommonHandler.instance().bus().register( new ConfigurationHandler() );
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent even)
	{
		if (Configurations.LOAD)
		{
			File logDirectory = new File( Reference.LOG_DIRECTORY );
			if ( ! logDirectory.exists() )
			{
				try
				{
					logDirectory.mkdir();
				}
				catch ( Exception e )
				{
					Log.error( e.getCause() );
				}
			}
			Path path = Paths.get( logDirectory + "/" + Reference.LOG_NAME );

			if ( Files.exists( path ) )
			{
				try
				{
					Files.delete( path );
				}
				catch ( Exception e )
				{
					Log.error( e.getCause() );
				}
			}
			WriteToFile data = new WriteToFile( logDirectory + "/" + Reference.LOG_NAME, true );
			data.WriteToFile( "**********************************************************************************************************************" );
			data.WriteToFile( "**********************************************  Ore Dictionary Ore Names  ********************************************" );
			data.WriteToFile( "**********************************************************************************************************************" );
			for ( String oreName : OreDictionary.getOreNames() )
			{
				try
				{
					String temp = OreDictionary.getOres( oreName ).get( 0 ).getUnlocalizedName();
					String sub = temp.substring( 0, 4 );
					if ( sub.equalsIgnoreCase( "tile" ) )
					{
						data.WriteToFile( "Block Name : " + temp.substring( 5, temp.length() ) );
					}
					else
					{
						data.WriteToFile( "Item Name : " + temp.substring( 5, temp.length() ) );
					}
					data.WriteToFile( "Ore Dictionary Name : " + oreName );
					data.WriteToFile( "***************************************************" );
				}
				catch ( Exception e )
				{

				}
			}
			Log.info( "****************************** ODL DONE ******************************" );
		}
	}
}
