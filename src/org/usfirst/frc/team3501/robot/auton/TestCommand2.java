package org.usfirst.frc.team3501.robot.auton;

import edu.wpi.first.wpilibj.command.Command;

public class TestCommand2 extends Command {

  public TestCommand2() {

  }

  @Override
  protected void initialize() {
    System.out.println("Test Command 2 Startup Successful");
  }

  @Override
  protected void execute() {

  }

  @Override
  protected void end() {
    System.out.println("Test Command 2 End");
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
