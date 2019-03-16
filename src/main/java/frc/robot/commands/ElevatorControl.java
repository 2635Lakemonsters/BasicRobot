package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Elevator.Height;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorControl extends Command {

    public ElevatorControl() {
        // Use requires() here to declare subsystem dependencies
        //requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.elevator.limitSwitchEncoderReset();
    	Robot.elevator.motorControl(); 
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
    
    
    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.setTargetHeight(Height.GROUND);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.elevator.setTargetHeight(Height.GROUND);
    }
}
