package org.usfirst.frc.team3501.robot.auton.defaultCommands;

import edu.wpi.first.wpilibj.command.Command;

public class DefaultCommand extends Command {

  public DefaultCommand() {

  }

  @Override
  protected void initialize() {
    System.out.println("Default Command Startup Successful");
  }

  @Override
  protected void execute() {

  }

  @Override
  protected void end() {
    System.out.println("Default Command End");
  }

  @Override
  protected void interrupted() {
    end();
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

}
