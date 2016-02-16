package org.usfirst.frc.team3501.robot.commands;

import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/*
 * Resets the Gyro
 */
public class ResetGyro extends Command {

  @Override
  protected void initialize() {
    Robot.driveTrain.resetGyro();
  }

  @Override
  protected void execute() {

  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {

  }

  @Override
  protected void interrupted() {

  }

}
