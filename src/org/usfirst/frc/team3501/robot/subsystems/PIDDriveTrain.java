package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.GyroLib;
import org.usfirst.frc.team3501.robot.MathLib;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class PIDDriveTrain extends PIDSubsystem {
  private static double pidOutput = 0;
  private static double encoderTolerance = 8.0, gyroTolerance = 5.0;
  private int DRIVE_MODE = 1;

  private static final int MANUAL_MODE = 1, ENCODER_MODE = 2, GYRO_MODE = 3;

  private Encoder leftEncoder, rightEncoder;
  private CANTalon frontLeft, frontRight, rearLeft, rearRight;
  private RobotDrive robotDrive;

  private GyroLib gyro;

  public PIDDriveTrain() {
    super(kp, ki, kd);

    frontLeft = new CANTalon(Constants.DriveTrain.FRONT_LEFT);
    frontRight = new CANTalon(Constants.DriveTrain.FRONT_RIGHT);
    rearLeft = new CANTalon(Constants.DriveTrain.REAR_LEFT);
    rearRight = new CANTalon(Constants.DriveTrain.REAR_RIGHT);

    robotDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
    leftEncoder = new Encoder(Constants.DriveTrain.ENCODER_LEFT_A,
        Constants.DriveTrain.ENCODER_LEFT_B, false, EncodingType.k4X);
    rightEncoder = new Encoder(Constants.DriveTrain.ENCODER_RIGHT_A,
        Constants.DriveTrain.ENCODER_RIGHT_B, false, EncodingType.k4X);
    leftEncoder.setDistancePerPulse(Constants.DriveTrain.INCHES_PER_PULSE);
    rightEncoder.setDistancePerPulse(Constants.DriveTrain.INCHES_PER_PULSE);

    leftEncoder.setDistancePerPulse(Constants.DriveTrain.INCHES_PER_PULSE);
    rightEncoder.setDistancePerPulse(Constants.DriveTrain.INCHES_PER_PULSE);

    gyro = new GyroLib(I2C.Port.kOnboard, false);

    DRIVE_MODE = Constants.DriveTrain.ENCODER_MODE;
    setEncoderPID();
    this.disable();
    gyro.start();

  }

  @Override
  protected void initDefaultCommand() {
    // setDefaultCommand(new JoystickDrive());
  }

  public void printOutput() {
    System.out.println("PIDOutput: " + pidOutput);
  }

  private double getAvgEncoderDistance() {
    return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
  }

  public boolean reachedTarget() {
    if (this.onTarget()) {
      this.disable();
      return true;
    } else {
      return false;
    }
  }

  public void stop() {
    drive(0, 0);
  }

  public void resetEncoders() {
    leftEncoder.reset();
    rightEncoder.reset();
  }

  public double getRightSpeed() {
    return rightEncoder.getRate(); // in inches per second
  }

  public double getLeftSpeed() {
    return leftEncoder.getRate(); // in inches per second
  }

  public double getSpeed() {
    return (getLeftSpeed() + getRightSpeed()) / 2.0; // in inches per second
  }

  public double getRightDistance() {
    return rightEncoder.getDistance(); // in inches
  }

  public double getLeftDistance() {
    return leftEncoder.getDistance(); // in inches
  }

  public double getError() {
    if (DRIVE_MODE == Constants.DriveTrain.ENCODER_MODE)
      return Math.abs(this.getSetpoint() - getAvgEncoderDistance());
    else
      return Math.abs(this.getSetpoint() + getGyroAngle());
  }

  public double getGyroAngle() {
    return gyro.getRotationZ().getAngle();
  }

  public void resetGyro() {
    gyro.reset();
  }

  public void printEncoder(int i, int n) {
    if (i % n == 0) {
      System.out.println("Left: " + this.getLeftDistance());
      System.out.println("Right: " + this.getRightDistance());

    }
  }

  public void printGyroOutput() {
    System.out.println("Gyro Angle" + -this.getGyroAngle());
  }

  public double getOutput() {
    return pidOutput;
  }

  public void updatePID() {
    if (DRIVE_MODE == Constants.DriveTrain.ENCODER_MODE)
      this.getPIDController().setPID(kp, ki, kd);
    else
      this.getPIDController().setPID(gp, gd, gi);
  }

  public CANTalon getFrontLeft() {
    return frontLeft;
  }

  public CANTalon getFrontRight() {
    return frontRight;
  }

  public CANTalon getRearLeft() {
    return rearLeft;
  }

  public CANTalon getRearRight() {
    return rearRight;
  }

  public int getMode() {
    return DRIVE_MODE;
  }

  @Override
  protected void usePIDOutput(double output) {
    double left = 0;
    double right = 0;
    if (DRIVE_MODE == Constants.DriveTrain.ENCODER_MODE) {
      double drift = this.getLeftDistance() - this.getRightDistance();
      if (Math.abs(output) > 0 && Math.abs(output) < 0.3)
        output = Math.signum(output) * 0.3;
      left = output;
      right = output + drift * kp / 10;
    }
    else if (DRIVE_MODE == Constants.DriveTrain.GYRO_MODE) {
      left = output;
      right = -output;
    }
    drive(left / 1.3, right / 1.3);
    System.out.println("left: " + left);
    System.out.println("right: " + right);
    pidOutput = output;
  }

  @Override
  protected double returnPIDInput() {
    return sensorFeedback();
  }

  private double sensorFeedback() {
    // if (DRIVE_MODE == Constants.DriveTrain.ENCODER_MODE)
    // return getAvgEncoderDistance();
    // else if (DRIVE_MODE == Constants.DriveTrain.GYRO_MODE)
    return -this.getGyroAngle();
    // counterclockwise is positive on gyro but we want it to be negative
    // else
    // return 0;
  }

  public void drive(double left, double right) {
    robotDrive.tankDrive(-left, -right);
    // dunno why but inverted drive (- values is forward)
  }

  public void driveDistance(double dist, double maxTimeOut) {
    dist = MathLib.constrain(dist, -100, 100);
    setEncoderPID();
    setSetpoint(dist);
  }

  public void setEncoderPID() {
    DRIVE_MODE = Constants.DriveTrain.ENCODER_MODE;
    this.updatePID();
    this.setAbsoluteTolerance(encoderTolerance);
    this.setOutputRange(-1.0, 1.0);
    this.setInputRange(-200.0, 200.0);
    this.enable();
  }

  private void setGyroPID() {
    DRIVE_MODE = Constants.DriveTrain.GYRO_MODE;
    this.updatePID();
    this.getPIDController().setPID(gp, gi, gd);

    this.setAbsoluteTolerance(gyroTolerance);
    this.setOutputRange(-1.0, 1.0);
    this.setInputRange(-360.0, 360.0);
    this.enable();
  }

  public void turnAngle(double angle) {
    setGyroPID();
    setSetpoint(angle);
  }

  public void setMotorSpeeds(double left, double right) {
    // positive setpoint to left side makes it go backwards
    // positive setpoint to right side makes it go forwards.
    frontLeft.set(-left);
    rearLeft.set(-left);
    frontRight.set(right);
    rearRight.set(right);
  }

  private static double kp = 0.013, ki = 0.000015, kd = -0.002;
  private static double gp = 0.018, gi = 0.000015, gd = 0;
}
