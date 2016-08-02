package serverhelper;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.PluginBase;
import serverhelper.gui.SettingFrame;

public class Main extends PluginBase {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				new SettingFrame();
			}
		};
		thread.setDaemon(true);
		thread.start();
		return true;
	}
}
