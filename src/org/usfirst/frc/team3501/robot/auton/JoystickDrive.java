package org.usfirst.frc.team3501.robot.auton;

import org.usfirst.frc.team3501.robot.OI;
import org.usfirst.frc.team3501.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Runs throughout teleop and listens for joystick inputs and drives the
 * driveTrain
 * Never finishes until teleop ends
 */
public class JoystickDrive extends Command {
  private Drive driveTrain;

  public JoystickDrive() {
    driveTrain = Drive.getInstance();
    requires(driveTrain);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    double thrust = OI.rightJoystick.getY();
    double twist = OI.rightJoystick.getTwist();
    driveTrain.arcadeDrive(-thrust, -twist);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    driveTrain.stop();
  }

  @Override
  protected void interrupted() {
  }
}
