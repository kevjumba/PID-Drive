package org.usfirst.frc.team3501.robot;

import org.usfirst.frc.team3501.robot.auton.DefaultCommand;
import org.usfirst.frc.team3501.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

  public static Drive driveTrain;
  public static Preferences prefs;
  public static SendableChooser<Command> step1;
  public static SendableChooser<Command> step2;

  @Override
  public void robotInit() {
    driveTrain = Drive.getInstance();
    prefs = Preferences.getInstance();
    step1 = new SendableChooser<>();
    step2 = new SendableChooser<>();
    addAutonChooser();
    runSelectedAuton();

  }

  private void runSelectedAuton() {
    Command firstCommand = step1.getSelected();
    Command secondCommand = step2.getSelected();
    Scheduler.getInstance().add(firstCommand);
    Scheduler.getInstance().add(secondCommand);
  }

  private void addAutonChooser() {
    step1.addDefault("Default", new DefaultCommand());
    // add step1 commands
    step2.addDefault("Default", new DefaultCommand());
    // add step2 commands
    SmartDashboard.putData("Step1 Chooser", step1);
    SmartDashboard.putData("Step2 Chooser", step2);
  }

  @Override
  public void disabledInit() {

  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    Scheduler.getInstance().run();
    // Scheduler.getInstance().add(new DriveDistance(50, 10));
    // System.out.println(this.driveTrain.getRightDistance());
    // Scheduler.getInstance().add(new DriveStraight(100, 10));
    // Scheduler.getInstance().add(
    // new TurnForAngle(-(360 + this.driveTrain.getAngle()), 6));

  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();

  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    // System.out.println("Angle: " + driveTrain.getAngle());

  }

  @Override
  public void testPeriodic() {
    LiveWindow.run();
  }
}
