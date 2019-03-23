/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimberRaiseFrontCommand extends Command {
  public ClimberRaiseFrontCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.driveSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.climber.COMMAND_GROUP_STAGE = 2;
    System.out.println("ClimberRaiseFrontCommand Initialized!!!");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.climber.raiseFrontClimber();
    //Robot.driveSubsystem.tankDrive(0.3, 0.3);
    //What if this puts pressure on legs and they can't retract?
    //Run back wheel at same time and transition from wall contact on back legs?
    //This is likely the worst stage to be cancelled out of, as there is a higher likelyhood of falling at an angle, causing damage to the mast
    //What can we do in an instant before the robot is disabled???
    //The pneumatic switch would be good, allowing us to remain stable if the front wheel is fully supported on the ledge
    //Can we have a separate processor on the robot to slowly descend the robot, but what if part of the robot is over the ledge and it tips
    //I also believe using a separate board to control motors specifically to drive while disabled would likely be a violation, being unsafe
    //Not only PID values need to be tuned, but the speeds of motors no doubt need some very accurate tuning to not be too fast and make the robot bounce
    //Will we have enough time with the robot to test this to standards? Or at all?
    //What if the battery is low at the end of the match, how do we protect against brownouts ruining the climb?
    
    //How much time do we have to save our robot before we lose control???
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    boolean isFinished = Robot.climber.raiseFrontClimberIsFinished();
    if (isFinished) {
      System.out.println("ClimberRaiseFrontCommand Finished!!!");
    }
    return isFinished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.climber.setClimberZero();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    //Robot.climberRaiseBackCommand.start();
    end();
  }
}
