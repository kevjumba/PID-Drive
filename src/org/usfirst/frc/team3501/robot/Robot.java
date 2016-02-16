package org.usfirst.frc.team3501.robot;

import org.usfirst.frc.team3501.robot.commands.TurnForAngle;
import org.usfirst.frc.team3501.robot.subsystems.PIDDriveTrain;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

  public static PIDDriveTrain driveTrain;
  public static OI oi;
  public static Preferences prefs;
  public static LiveWindow lw;
  int count = 0;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    oi = new OI();
    prefs = Preferences.getInstance();
    driveTrain = new PIDDriveTrain();

    if (prefs == null)
      System.out.println("Preferences not initialized");
    else
      System.out.println("Preferences initialized");
    System.out.println("Current prefs values: ");
    System.out.println("P: " + prefs.getDouble("kP", 0));
    System.out.println("I: " + prefs.getDouble("kI", 0));
    System.out.println("D: " + prefs.getDouble("kD", 0));

    lw = new LiveWindow();

  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {

  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes
   * using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW
   * Dashboard, remove all of the chooser code and uncomment the getString code
   * to get the auto name from the text box
   * below the Gyro
   *
   * You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example)
   * or additional comparisons to the switch structure below with additional
   * strings & commands.
   */
  @Override
  public void autonomousInit() {
    Scheduler.getInstance().add(new TurnForAngle(40, 10));

  }

  /**
   * This function is called periodically during autonomous
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    count++;

  }

  @Override
  public void teleopInit() {
    // new JoystickDrive().start();
  }

  /**
   * This function is called periodically during operator control
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    // driveTrain.printGyroOutput();

  }

  /**
   * This function is called periodically during test mode
   */
  @Override
  public void testPeriodic() {
    LiveWindow.run();
  }
}
