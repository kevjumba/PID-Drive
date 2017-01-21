package org.usfirst.frc.team3501.robot.auton;

import edu.wpi.first.wpilibj.command.Command;

public class TestCommand1 extends Command {

  public TestCommand1() {

  }

  @Override
  protected void initialize() {
    System.out.println("Test Command 1 Startup Successful");
  }

  @Override
  protected void execute() {

  }

  @Override
  protected void end() {
    System.out.println("Test Command 1 End");
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
