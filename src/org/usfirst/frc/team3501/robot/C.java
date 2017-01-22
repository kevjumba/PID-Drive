package org.usfirst.frc.team3501.robot;

/**
 * The Constants stores constant values for all subsystems. This includes the
 * port values for motors and sensors, as well as important operational
 * constants for subsystems such as max and min values.
 */

public class C {
  public static class OI {
    public final static int LEFT_STICK_PORT = 0;
    public final static int RIGHT_STICK_PORT = 1;
    public final static int TRIGGER_PORT = 1;
  }

  public static class Drive {
    // Drivetrain Motor Related Ports
    public static final int FRONT_LEFT = 1;
    public static final int FRONT_RIGHT = 3;
    public static final int REAR_LEFT = 2;
    public static final int REAR_RIGHT = 4;

    // Encoder related ports
    public final static int ENCODER_LEFT_A = 1;
    public final static int ENCODER_LEFT_B = 0;
    public final static int ENCODER_RIGHT_A = 2;
    public final static int ENCODER_RIGHT_B = 3;
    public static final double INCHES_PER_PULSE =
        (6 * Math.PI) / 256;

    public static double defaultEncP = 0.009, defaultEncI = 0.0008,
        defaultEncD = -0.002;
    public static double defaultGyroP = 0.006, defaultGyroI = 0.00000,
        defaultGyroD = -0.004;
  }
}
