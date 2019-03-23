/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

//import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

public class DriveClimberInCommand extends TimedCommand {
  boolean leftFinished;
  boolean righttFinished;
  boolean backFinished;

  double leftValue;
  double rightValue;
  double backValue;

  public DriveClimberInCommand(int timeout) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    super(timeout);
    leftFinished = false;
    righttFinished = false;
    backFinished = false;

    leftValue = 0;
    rightValue = 0;
    backValue = 0;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    leftFinished = false;
    righttFinished = false;
    backFinished = false;
    if(Robot.climberControl.isRunning()){
      Robot.climberControl.cancel();
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //Robot.climber.driveClimberIn();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {

    if(Robot.climber.getLeftCurrent() > 5|| leftFinished == true) {
      //System.out.println("Left Climber isFinished true");
      leftFinished = true;
      leftValue = 0;
    } else {
      leftFinished = false;
      leftValue = 0.5;
    }
    if(Robot.climber.getRightCurrent() > 5 || righttFinished == true) {
      //System.out.println("Right Climber isFinished true");
      righttFinished = true;
      rightValue = 0;
    } else {
      righttFinished = false;
      rightValue = 0.5;
    }
    if(Robot.climber.getBackCurrent() > 5 || backFinished == true) {
      //System.out.println("Back Climber isFinished true");
      backFinished = true;
      backValue = 0;
    } else {
      backFinished = false;
      backValue = 0.5;
    }
    Robot.climber.setClimberCurrent(leftValue, rightValue, backValue);

    if(leftFinished && righttFinished && backFinished){
      System.out.println("Climber Reset Finished");
      Robot.climber.resetClimber();
      return true;
    }else{
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
