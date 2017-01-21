package org.usfirst.frc.team3501.robot.commands;

import org.usfirst.frc.team3501.robot.C;
import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Drive;
import org.usfirst.frc.team3501.robot.utils.Lib;
import org.usfirst.frc.team3501.robot.utils.PID;

import edu.wpi.first.wpilibj.command.Command;

public class DriveStraight extends Command {
  private double maxTimeOut;
  private Drive drive;
  private PID encControl;
  private PID gyroControl;

  private double target;
  private double gyroP;
  private double gyroI;
  private double gyroD;
  private double gyroEps;

  private double encP;
  private double encI;
  private double encD;
  private double encEps;

  public DriveStraight(double distance, double maxTimeOut) {
    requires(Robot.driveTrain);
    drive = Drive.getInstance();
    this.maxTimeOut = maxTimeOut;
    this.target = distance;

    this.encP = C.Drive.kp;
    this.encI = C.Drive.ki;
    this.encD = C.Drive.kd;
    this.gyroP = 0.0009;
    this.gyroI = 0.0000;
    this.gyroD = -0.0000;
    this.gyroEps = 5;
    this.encControl = new PID(this.encP, this.encI, this.encD);
    this.encControl.setDoneRange(0.5);
    this.encControl.setMaxOutput(1.0);
    this.encControl.setMinDoneCycles(5);

    this.gyroControl = new PID(this.gyroP, this.gyroI, this.gyroD,
        this.gyroEps);
    this.gyroControl.setDoneRange(1);
    this.gyroControl.setMinDoneCycles(5);
  }

  @Override
  protected void initialize() {
    this.drive.resetEncoders();
    this.drive.resetGyro();
    this.encControl.setDesiredValue(this.target);
    this.gyroControl.setDesiredValue(this.drive.getZero());

  }

  @Override
  protected void execute() {
    double xVal = 0;
    double yVal = this.encControl.calcPID(this.drive.getEncoderDistance());
    if (this.drive.getAngle()
        - this.drive.getZero() < 30) {
      xVal = -this.gyroControl.calcPID(this.drive.getAngle()
          - this.drive.getZero());
    }
    double leftDrive = Lib.calcLeftTankDrive(-xVal, yVal);
    double rightDrive = Lib.calcRightTankDrive(xVal, -yVal);

    this.drive.setDriveLeft(leftDrive);
    this.drive.setDriveRight(rightDrive);

  }

  @Override
  protected boolean isFinished() {
    return this.encControl.isDone();
  }

  @Override
  protected void end() {
    this.drive.stop();
    System.out.println("Drive Straight has Stopped");
  }

  @Override
  protected void interrupted() {
    end();
  }

}
