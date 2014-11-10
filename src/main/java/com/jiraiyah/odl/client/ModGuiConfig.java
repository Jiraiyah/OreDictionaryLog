/**
 * Created by Alireza Khodakarami on 11/10/2014.
 */

package com.jiraiyah.odl.client;

import com.jiraiyah.odl.Lang;
import com.jiraiyah.odl.Reference;
import com.jiraiyah.odl.handler.ConfigurationHandler;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;

import java.util.ArrayList;
import java.util.List;

public class ModGuiConfig extends GuiConfig
{

	public ModGuiConfig ( GuiScreen parentScreen)
	{
		super( parentScreen, getConfigElements(parentScreen), Reference.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath( ConfigurationHandler.configuration.toString() ));
	}

	private static List< IConfigElement > getConfigElements ( GuiScreen parent )
	{
		List<IConfigElement> list = new ArrayList< IConfigElement >(  );
		String prefix = Lang.prefix +"config.";
		for (ConfigurationHandler.Section section : ConfigurationHandler.Sections)
		{
			list.add( new ConfigElement<ConfigCategory>( ConfigurationHandler.configuration.getCategory( section.lc() ).setLanguageKey( prefix + section.lang ) ) );
		}
		return list;
	}
}
