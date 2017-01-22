package org.usfirst.frc.team3501.robot.auton.defaultMode;

import org.usfirst.frc.team3501.robot.auton.defaultCommands.DefaultCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Default extends CommandGroup {
  public Default() {
    addSequential(new DefaultCommand());
  }
}
