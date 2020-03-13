package me.elb1to.frozedrun.utils.command;

import me.elb1to.frozedrun.FrozedUHCRun;

public abstract class BaseCommand {

	public FrozedUHCRun main = FrozedUHCRun.getInstance();

	public BaseCommand() {
		main.getFramework().registerCommands(this);
	}

	public abstract void onCommand(CommandArgs command);

}
