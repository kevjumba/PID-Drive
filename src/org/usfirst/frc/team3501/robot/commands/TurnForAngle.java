package org.usfirst.frc.team3501.robot.commands;

import org.usfirst.frc.team3501.robot.C;
import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Drive;
import org.usfirst.frc.team3501.robot.utils.Lib;
import org.usfirst.frc.team3501.robot.utils.PID;

import edu.wpi.first.wpilibj.command.Command;

/***
 * @param targetAngle
 *          is the setpoint we want to turn for
 *          maxTimeOut catch just in case robot malfunctions and never reaches
 *          setpoint
 * @initialize resets the Gyro and prints the current mode
 * @code repeatedly sets a new setpoint angle to the motor
 * @end ends when the setpoint is reached.
 */
public class TurnForAngle extends Command {
  private PID gyroControl;
  private double epsilon;
  private double maxTimeOut;
  private double targetAngle;
  private Drive driveTrain;

  public TurnForAngle(double angle, double maxTimeOut) {
    requires(Robot.driveTrain);
    epsilon = 3; // 3 degrees of freedom
    driveTrain = Drive.getInstance();
    this.gyroControl = new PID(C.Drive.gp, C.Drive.gi, C.Drive.gd, 3.0);
    this.gyroControl.setMaxOutput(1.0);
    this.maxTimeOut = maxTimeOut;
    this.targetAngle = angle;
  }

  @Override
  protected void initialize() {
    Robot.driveTrain.resetGyro();
    this.gyroControl.setDesiredValue(this.targetAngle
        + this.driveTrain.getAngle());
  }

  @Override
  protected void execute() {
    // Robot.driveTrain.printGyroOutput();
    // Robot.driveTrain.printOutput();
    double xVal = -this.gyroControl.calcPID(this.driveTrain.getAngle());
    double leftDrive = Lib.calcLeftTankDrive(xVal, 0.0);
    double rightDrive = Lib.calcRightTankDrive(xVal, 0.0);

    this.driveTrain.setDriveLeft(-leftDrive);
    this.driveTrain.setDriveRight(rightDrive);

  }

  @Override
  protected boolean isFinished() {
    if (timeSinceInitialized() >= maxTimeOut
        || gyroControl.isDone()) {
      return true;
    }
    return false;
  }

  @Override
  protected void end() {
    System.out.println("END");
    this.driveTrain.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
