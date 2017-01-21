package org.usfirst.frc.team3501.robot.commands;

import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Runs throughout teleop and listens for joystick inputs and drives the
 * driveTrain
 * Never finishes until teleop ends
 */
public class JoystickDrive extends Command {

  public JoystickDrive() {
    requires(Robot.driveTrain);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    // IDK why but the joystick gives positive values for pulling backwards
    double left = -Robot.oi.rightJoystick.getY();
    double twist = -Robot.oi.rightJoystick.getTwist();
    Robot.driveTrain.arcadeDrive(left, twist);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.driveTrain.stop();
  }

  @Override
  protected void interrupted() {
  }
}
