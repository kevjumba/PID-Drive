package org.usfirst.frc.team3501.robot;

import org.usfirst.frc.team3501.robot.commands.DriveStraight;
import org.usfirst.frc.team3501.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.IterativeRobot;
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

  public static Drive driveTrain;
  int count = 0;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    driveTrain = Drive.getInstance();

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

  @Override
  public void autonomousInit() {
    // Scheduler.getInstance().add(new DriveDistance(50, 10));
    // System.out.println(this.driveTrain.getRightDistance());
    Scheduler.getInstance().add(new DriveStraight(100, 10));
    // Scheduler.getInstance().add(
    // new TurnForAngle(-(360 + this.driveTrain.getAngle()), 6));
  }

  /**
   * This function is called periodically during autonomous
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    // System.out.println(this.driveTrain.getAngle());
    System.out.println("l:" + this.driveTrain.getLeftDistance() + " r:"
        + this.driveTrain.getRightDistance());

  }

  @Override
  public void teleopInit() {
  }

  /**
   * This function is called periodically during operator control
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    // System.out.println("Angle: " + driveTrain.getAngle());

  }

  /**
   * This function is called periodically during test mode
   */
  @Override
  public void testPeriodic() {
    LiveWindow.run();
  }
}
