package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.C;
import org.usfirst.frc.team3501.robot.auton.JoystickDrive;
import org.usfirst.frc.team3501.robot.utils.BNO055;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drive extends Subsystem {
  private static Drive instance;

  private Encoder leftEncoder, rightEncoder;
  private CANTalon frontLeft, frontRight, rearLeft, rearRight;
  private RobotDrive robotDrive;

  private BNO055 imu;

  private double gyroZero = 0;

  public Drive() {

    frontLeft = new CANTalon(C.Drive.FRONT_LEFT);
    frontRight = new CANTalon(C.Drive.FRONT_RIGHT);
    rearLeft = new CANTalon(C.Drive.REAR_LEFT);
    rearRight = new CANTalon(C.Drive.REAR_RIGHT);

    robotDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
    leftEncoder = new Encoder(C.Drive.ENCODER_LEFT_A,
        C.Drive.ENCODER_LEFT_B, false, EncodingType.k4X);
    rightEncoder = new Encoder(C.Drive.ENCODER_RIGHT_A,
        C.Drive.ENCODER_RIGHT_B, false, EncodingType.k4X);
    leftEncoder.setDistancePerPulse(C.Drive.INCHES_PER_PULSE);
    rightEncoder.setDistancePerPulse(C.Drive.INCHES_PER_PULSE);

    leftEncoder.setDistancePerPulse(C.Drive.INCHES_PER_PULSE);
    rightEncoder.setDistancePerPulse(C.Drive.INCHES_PER_PULSE);

    this.imu = BNO055.getInstance(BNO055.opmode_t.OPERATION_MODE_IMUPLUS,
        BNO055.vector_type_t.VECTOR_EULER, Port.kOnboard, (byte) 0x28);
    gyroZero = imu.getHeading();

  }

  public static Drive getInstance() {
    if (Drive.instance == null) {
      Drive.instance = new Drive();
    }
    return Drive.instance;

  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new JoystickDrive());
  }

  // -----------------------------------------------------
  // ---- Component Specific Methods ---------------------
  // -----------------------------------------------------

  // ----------------- Encoders ------------------------------

  public double getEncoderDistance() {
    return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
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

  // ----------------- Gyro ------------------------------

  public double getAngle() {
    if (!this.imu.isInitialized())
      return -1;
    return this.imu.getHeading() - this.gyroZero;
  }

  public void resetGyro() {
    this.gyroZero = this.getAngle();

  }

  public double getZero() {
    return this.gyroZero;
  }

  // ----------------- Drive ------------------------------

  public void setDriveLeft(double leftDrive) {
    this.frontLeft.set(leftDrive);
    this.rearLeft.set(leftDrive);
  }

  public void setDriveRight(double rightDrive) {
    this.frontRight.set(rightDrive);
    this.rearRight.set(rightDrive);

  }

  public void arcadeDrive(double thrust, double twist) {
    robotDrive.arcadeDrive(thrust, twist);
  }

  public void stop() {
    setDriveLeft(0.0);
    setDriveRight(0.0);
  }

}
