package org.usfirst.frc.team3501.robot.oi;

import org.usfirst.frc.team3501.robot.C;

import edu.wpi.first.wpilibj.Joystick;

public class DriverInput {

  private static DriverInput instance;
  // private LogitechDualAction driver;
  private Joystick driver;
  private Joystick operator;

  private DriverInput() {
    this.operator = new Joystick(C.OI.LEFT_STICK_PORT); // left joystick

    this.driver = new Joystick(C.OI.RIGHT_STICK_PORT); // right joystick
  }

  public static DriverInput getInstance() {
    if (instance == null) {
      instance = new DriverInput();
    }
    return instance;
  }

  // -------------------------------------
  // --- DRIVER --------------------------
  // -------------------------------------

  public double getDriverX() {
    return this.driver.getX();
  }

  public double getDriverY() {
    return this.driver.getY();
  }

  public double getDriverTwist() {
    return this.driver.getTwist();
  }

}
