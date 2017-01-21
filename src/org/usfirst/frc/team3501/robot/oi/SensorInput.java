package org.usfirst.frc.team3501.robot.oi;

import org.usfirst.frc.team3501.robot.C;
import org.usfirst.frc.team3501.robot.utils.BNO055;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SensorInput {

  private static SensorInput instance;
  private RobotOutput robotOut;
  private Encoder encDriveLeft;
  private Encoder encDriveRight;

  private BNO055 imu; // gyro

  private double gyroZero = 0;

  private SensorInput() {
    this.robotOut = RobotOutput.getInstance();
    this.encDriveLeft = new Encoder(C.Drive.ENCODER_LEFT_A,
        C.Drive.ENCODER_LEFT_B); // was 0,1 MUST FLIP FOR COMP BOT
    this.encDriveRight = new Encoder(C.Drive.ENCODER_RIGHT_A,
        C.Drive.ENCODER_RIGHT_B); // was 2,3MUST FLIP FOR CO

    this.imu = BNO055.getInstance(BNO055.opmode_t.OPERATION_MODE_IMUPLUS,
        BNO055.vector_type_t.VECTOR_EULER, Port.kOnboard, (byte) 0x28);

    encDriveLeft.setDistancePerPulse(C.Drive.INCHES_PER_PULSE);
    encDriveRight.setDistancePerPulse(C.Drive.INCHES_PER_PULSE);
    gyroZero = imu.getHeading();
    this.reset();
  }

  public static SensorInput getInstance() {
    if (instance == null) {
      instance = new SensorInput();
    }

    return instance;
  }

  public void update() {
    // this.camera.update();

    // System.out.println("Gyro Speed" + this.gyro.getSpeed());

    SmartDashboard.putNumber("GYRO: ", this.getAngle());
    SmartDashboard.putNumber("Left Enc", this.getLeftDriveInches());
    SmartDashboard.putNumber("Right Enc", this.getRightDriveInches());

  }

  public void reset() {
    this.encDriveLeft.reset();
    this.encDriveRight.reset();
    gyroZero = imu.getHeading();
  }

  // -----------------------------------------------------
  // ---- Component Specific Methods ---------------------
  // -----------------------------------------------------

  // ----------------- DRIVE ------------------------------

  public double getEncoderLeftSpeed() {
    return this.encDriveLeft.getRate();
  }

  public double getEncoderRightSpeed() {
    return this.encDriveLeft.getRate();
  }

  public double getLeftDriveInches() {
    return this.encDriveLeft.getDistance();
  }

  public double getRightDriveInches() {
    return this.encDriveRight.getDistance();
  }

  public double getDriveEncoderAverage() {
    return (this.getRightDriveInches() + this.getLeftDriveInches()) / 2;
  }

  public Encoder getEncoderLeftObj() {
    return this.encDriveLeft;
  }

  public Encoder getEncoderRightObj() {
    return this.encDriveRight;
  }

  public double getDriveEncoderSpeedAverage() {
    return (this.getEncoderLeftSpeed() + this.getEncoderRightSpeed()) / 2.0;
  }

  // ----------------------- GYRO ----------------------------------

  public double getAngle() {
    if (!this.imu.isInitialized())
      return -1;
    return this.imu.getHeading() - this.gyroZero;
  }
}
