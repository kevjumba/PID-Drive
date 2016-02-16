package org.usfirst.frc.team3501.robot.commands;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/***
 * @param angle
 *          is the setpoint we want to turn for
 *          maxTimeOut catch just in case robot malfunctions and never reaches
 *          setpoint
 * @initialize resets the Gyro and prints the current mode
 * @code repeatedly sets a new setpoint angle to the motor
 * @end ends when the setpoint is reached.
 */
public class TurnForAngle extends Command {
  private double maxTimeOut;
  double angle;

  public TurnForAngle(double angle, double maxTimeOut) {
    requires(Robot.driveTrain);
    this.maxTimeOut = maxTimeOut;
    this.angle = angle;
  }

  @Override
  protected void initialize() {
    Robot.driveTrain.resetGyro();
    System.out.println(Robot.driveTrain.getMode());
    Robot.driveTrain.turnAngle(angle);
  }

  @Override
  protected void execute() {
    Robot.driveTrain.printGyroOutput();
    Robot.driveTrain.printOutput();

  }

  @Override
  protected boolean isFinished() {
    if (timeSinceInitialized() >= maxTimeOut
        || Robot.driveTrain
        .reachedTarget() || Robot.driveTrain.getError() < 8) {
      System.out.println("time: " + timeSinceInitialized());
      Constants.DriveTrain.time = timeSinceInitialized();
      return true;
    }
    return false;
  }

  @Override
  protected void end() {
    Robot.driveTrain.disable();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
