package org.usfirst.frc.team3501.robot.commands;

import org.usfirst.frc.team3501.robot.C;
import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/***
 * This command will drive the drivetrain a certain distance in inches
 *
 * @param distance
 *          is the distance we want to drive
 *          maxTimeOut is a catch just in case the robot malfunctions and never
 *          gets to the setpoint
 *
 * @code
 *       Repeatedly updates the driveTrain setpoint
 *       Finishes when the time goes over maxTimeOut or the driveTrain hits the
 *       setpoint
 *       end() disables the PID driveTrain
 */
public class DriveDistance extends Command {
  private double maxTimeOut;
  private DriveTrain drive;
  double distance;
  int count = 0;

  public DriveDistance(double distance, double maxTimeOut) {
    requires(Robot.driveTrain);
    drive = DriveTrain.getInstance();
    drive.setEncoderPID();
    this.maxTimeOut = maxTimeOut;
    this.distance = distance;
  }

  @Override
  protected void initialize() {
    Robot.driveTrain.resetEncoders();
  }

  @Override
  protected void execute() {
    drive.startPID(distance, maxTimeOut);

  }

  @Override
  protected boolean isFinished() {
    if (timeSinceInitialized() >= maxTimeOut
        || drive.reachedTarget() || drive.getError() < 1) {
      System.out.println("time: " + timeSinceInitialized());
      C.Drive.time = timeSinceInitialized();
      return true;
    }
    return false;
  }

  @Override
  protected void end() {
    drive.disable();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
