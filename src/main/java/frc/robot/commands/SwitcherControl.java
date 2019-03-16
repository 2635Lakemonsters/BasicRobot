package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Switcher.*;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SwitcherControl extends Command {

    public SwitcherControl() {
        // Use requires() here to declare subsystem dependencies
        //requires(Robot.switcher);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.switcher.encoderStart();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.elevator.limitSwitchEncoderReset();
    	Robot.switcher.motorControl(); 
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
    
    
    // Called once after isFinished returns true
    protected void end() {
    	Robot.switcher.moveSwitch(SwitcherState.FLOOR);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.switcher.moveSwitch(SwitcherState.FLOOR);
    }
}
