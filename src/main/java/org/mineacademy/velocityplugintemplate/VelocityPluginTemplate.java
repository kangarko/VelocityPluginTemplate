package org.mineacademy.velocityplugintemplate;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import org.slf4j.Logger;

import com.google.inject.Inject;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;

/**
* The main plugin class.
*/
@Plugin(id = "velocityplugintemplate",
		name = "VelocityPluginTemplate",
		version = "1.0.0",
		authors = { "kangarko" })
public final class VelocityPluginTemplate {

	private final ProxyServer server;
	private final Logger logger;
	private final File dataFolder;
	private final File file;

	@Inject
	public VelocityPluginTemplate(ProxyServer server, Logger logger, @DataDirectory Path dataFolder) {
		this.server = server;
		this.logger = logger;
		this.dataFolder = new File(dataFolder.toFile().getParentFile(), this.getClass().getAnnotation(Plugin.class).name());

		try {
			this.file = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI());

		} catch (final URISyntaxException ex) {
			throw new RuntimeException(ex);
		}

		this.onLoad();
	}

	@Subscribe
	public void onProxyInitialization(ProxyInitializeEvent event) {
		this.onEnable();
	}

	@Subscribe
	public void onProxyShutdown(ProxyShutdownEvent event) {
		this.onDisable();
	}

	public void onLoad() {
		System.out.println("VelocityPluginTemplate loaded.");
	}

	public void onEnable() {
		System.out.println("VelocityPluginTemplate enabled");
	}

	public void onDisable() {
		System.out.println("VelocityPluginTemplate disabled");
	}
}
