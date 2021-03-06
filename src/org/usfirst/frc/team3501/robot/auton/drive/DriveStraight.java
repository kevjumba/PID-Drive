package org.usfirst.frc.team3501.robot.auton.drive;

import org.usfirst.frc.team3501.robot.C;
import org.usfirst.frc.team3501.robot.subsystems.Drive;
import org.usfirst.frc.team3501.robot.utils.Lib;
import org.usfirst.frc.team3501.robot.utils.PID;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

public class DriveStraight extends Command {
  private double maxTimeOut;
  private Drive driveTrain;
  private PID encControl;
  private PID gyroControl;
  private Preferences prefs;

  private double target;
  private double gyroP;
  private double gyroI;
  private double gyroD;

  private double encP;
  private double encI;
  private double encD;

  public DriveStraight(double distance, double maxTimeOut) {
    driveTrain = Drive.getInstance();
    requires(driveTrain);
    this.maxTimeOut = maxTimeOut;
    this.target = distance;

    this.encP = prefs.getDouble("DriveP", C.Drive.defaultEncP);
    this.encI = prefs.getDouble("DriveI", C.Drive.defaultEncI);
    this.encD = prefs.getDouble("DriveD", C.Drive.defaultEncD);
    this.gyroP = prefs.getDouble("DriveP", 0.0009);
    this.gyroI = prefs.getDouble("DriveI", 0.0000);
    this.gyroD = prefs.getDouble("DriveD", -0.000);
    this.encControl = new PID(this.encP, this.encI, this.encD);
    this.encControl.setDoneRange(0.5);
    this.encControl.setMaxOutput(1.0);
    this.encControl.setMinDoneCycles(5);

    this.gyroControl = new PID(this.gyroP, this.gyroI, this.gyroD);
    this.gyroControl.setDoneRange(1);
    this.gyroControl.setMinDoneCycles(5);
  }

  @Override
  protected void initialize() {
    this.driveTrain.resetEncoders();
    this.driveTrain.resetGyro();
    this.encControl.setDesiredValue(this.target);
    this.gyroControl.setDesiredValue(this.driveTrain.getZero());

  }

  @Override
  protected void execute() {
    double xVal = 0;
    double yVal = this.encControl.calcPID(this.driveTrain.getEncoderDistance());
    if (this.driveTrain.getAngle()
        - this.driveTrain.getZero() < 30) {
      xVal = -this.gyroControl.calcPID(this.driveTrain.getAngle()
          - this.driveTrain.getZero());
    }
    double leftDrive = Lib.calcLeftTankDrive(-xVal, yVal);
    double rightDrive = Lib.calcRightTankDrive(xVal, -yVal);

    this.driveTrain.setDriveLeft(leftDrive);
    this.driveTrain.setDriveRight(rightDrive);

  }

  @Override
  protected boolean isFinished() {
    return timeSinceInitialized() >= maxTimeOut
        || this.encControl.isDone();
  }

  @Override
  protected void end() {
    System.out.println("Drive Straight Command End");
    this.driveTrain.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }

}
