/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

public class ClimberDriveCommand extends TimedCommand {
  int sequencePart;
  public ClimberDriveCommand(int sequencePart, double  timeout) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    super(timeout);
    this.sequencePart = sequencePart;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("ClimberDriveCommand Initialized!!!");

    Robot.climber.COMMAND_GROUP_STAGE = sequencePart;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.climber.drive(0.2);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {


    boolean isFinished=  Robot.climber.driveHitWall();
    if (isFinished) {
      System.out.println("ClimberDriveCommand Finished!!!");
      Robot.climber.drive(0.0);
    }

    if (isTimedOut()) 
      isFinished = true;
      return isFinished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.climber.drive(0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
