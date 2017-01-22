package org.usfirst.frc.team3501.robot;

import org.usfirst.frc.team3501.robot.auton.defaultMode.Default;
import org.usfirst.frc.team3501.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

  public static Drive driveTrain;
  public static Preferences prefs;
  public static SendableChooser<CommandGroup> step1;
  public static SendableChooser<CommandGroup> step2;
  public static SendableChooser<CommandGroup> step3;

  @Override
  public void robotInit() {
    driveTrain = Drive.getInstance();
    prefs = Preferences.getInstance();
    step1 = new SendableChooser<>();
    step2 = new SendableChooser<>();
    step3 = new SendableChooser<>();
    addAutonChooser();
    runSelectedAuton();

  }

  private void runSelectedAuton() {
    CommandGroup firstStep = step1.getSelected();
    CommandGroup secondStep = step2.getSelected();
    CommandGroup thirdStep = step3.getSelected();
    Scheduler.getInstance().add(firstStep);
    Scheduler.getInstance().add(secondStep);
    Scheduler.getInstance().add(thirdStep);
  }

  private void addAutonChooser() {
    step1.addDefault("Default", new Default());
    // add step1 commands
    step2.addDefault("Default", new Default());
    // add step2 commands
    step3.addDefault("Default", new Default());
    // add step3 commands
    SmartDashboard.putData("Step1 Chooser", step1);
    SmartDashboard.putData("Step2 Chooser", step2);
    SmartDashboard.putData("Step3 Chooser", step3);
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

  }

  @Override
  public void testPeriodic() {
    LiveWindow.run();
  }
}
