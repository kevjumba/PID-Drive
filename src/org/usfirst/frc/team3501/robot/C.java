package org.usfirst.frc.team3501.robot;

/**
 * The Constants stores constant values for all subsystems. This includes the
 * port values for motors and sensors, as well as important operational
 * constants for subsystems such as max and min values.
 */

public class C {
  public static class OI {
    // Computer Ports
    public final static int LEFT_STICK_PORT = 0;
    public final static int RIGHT_STICK_PORT = 1;
    // Ports on the Joystick
    public final static int TRIGGER_PORT = 1;
    public final static int DECREMENT_SHOOTER_SPEED_PORT = 2;
    public final static int INCREMENT_SHOOTER_SPEED_PORT = 3;
    public final static int SHOOT_PORT = 4;
    public final static int LOG_PORT = 5;
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

    private final static double WHEEL_DIAMETER = 6.0; // in inches
    private final static double PULSES_PER_ROTATION = 256; // in pulses
    private final static double OUTPUT_SPROCKET_DIAMETER = 2.0; // in inches
    private final static double WHEEL_SPROCKET_DIAMETER = 3.5; // in inches
    public static final double INCHES_PER_PULSE =
        (6 * Math.PI) / 256;

    public static final int MANUAL_MODE = 1, ENCODER_MODE = 2, GYRO_MODE = 3;
    public static double time = 0;

    public static double kp = 0.009, ki = 0.0008, kd = -0.002;
    public static double gp = 0.006, gi = 0.00000, gd = -0.004;
  }
}