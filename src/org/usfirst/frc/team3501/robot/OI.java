package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  private static OI instance;
  public static Joystick leftJoystick;
  public static Joystick rightJoystick;
  public static Button trigger;

  private OI() {

    leftJoystick = new Joystick(C.OI.LEFT_STICK_PORT);
    rightJoystick = new Joystick(C.OI.RIGHT_STICK_PORT);
  }

  public static OI getInstance() {
    if (OI.instance == null) {
      OI.instance = new OI();
    }
    return OI.instance;

  }
}
