/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Elevator.Height;

/**
 * Add your docs here.
 */
public class Switcher extends Subsystem {
  // Put methods for controlling this subsystem
  public CANSparkMax switchMotor;
  public CANPIDController controller;
  CANEncoder encoder;
  public SwitcherState currentSwitcherState;
  double initialEncoderPosition;
  double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;
  double intermediateSetPoint;
  double target;
  boolean reached;

  public Switcher() {
    switchMotor = new CANSparkMax(RobotMap.SWITCH_MOTOR_CHANNEL, MotorType.kBrushless);
    controller = new CANPIDController(switchMotor);
    encoder = new CANEncoder(switchMotor);
    currentSwitcherState = SwitcherState.FLOOR;
    encoderStart();
    intermediateSetPoint = 0.0;
    reached = false;
    //Does this effectively reset the encoder???
    //switchMotor.setParameter(ConfigParameter.kEncoderSampleDelta, 0);
  }
  public void encoderStart() {
    
    // PID coefficients
     //kP = 5e-7;
     kP = 0.05; 
     //kP = 0;
     //kI = 1e-4;
     //kD = 1; 
     //kI = 1e-8;
     kI = 0;
     kD = 0.0; 
     kIz = 0; 
     //kFF = 0.000156;
     kFF = 0; 
     kMaxOutput = 1; 
     kMinOutput = -1;
     // set PID coefficients\
    controller.setP(kP);
    controller.setI(kI);
    controller.setD(kD);
    controller.setFF(kFF);
    //controller.setSmartMotionMaxVelocity(2000, 0);
    //controller.setSmartMotionMaxAccel(1500, 0);
    controller.setOutputRange(-1, 1);
    //encoder.setPosition(0);
    
    // controller.setIZone(kIz);
    // controller.setFF(kFF);
    // controller.setOutputRange(kMinOutput, kMaxOutput);

  }

  public void encoderReset() {
    encoder.setPosition(0);
    this.currentSwitcherState = SwitcherState.FLOOR;
  }
  public void autoSwitcherEncoderReset() {
    encoder.setPosition(RobotMap.SWITCHER_AUTO_HEIGHT);
  }

  public void moveSwitch(SwitcherState setPoint) {
    //LOGIC HEAR FOR WHEN ABLE TO GO TO WHAT STATE
    //if elevator at top, may go to rear
    //if elevator at bottom, may not change state
    //Probably only go to floor level if elevator at bottom
    System.out.println("Switcher.moveSwitch to " + setPoint.switcherEncoderPosition);
    //System.out.println("Switcher.moveSwitch to " + setPoint.switcherEncoderPosition);
    //controller.setReference(setPoint.switcherEncoderPosition, ControlType.kSmartMotion);
    this.currentSwitcherState = setPoint;
    System.out.println("Current switcher position: " + encoder.getPosition());
    // if(Robot.elevator.currentTargetHeight == Height.GROUND){
    //   if((setPoint == Position.FLOOR && currentPosition == Position.CARGO) || (setPoint == Position.CARGO && currentPosition == Position.FLOOR)){
    //     controller.setReference(setPoint.position+initialEncoderPosition, ControlType.kPosition);
    //     this.currentPosition = setPoint;
    //   }
    // }else if(setPoint == Position.REAR){
    //   if(Robot.elevator.currentTargetHeight == Height.LEVEL3H){
    //     controller.setReference(setPoint.position+initialEncoderPosition, ControlType.kPosition);
    //     this.currentPosition = setPoint;
    //   }
    // }else if(Robot.elevator.currentTargetHeight == Height.GROUND){
    //   System.out.println("Tried to move elevator in bottom state");
    // } else{
    //   controller.setReference(setPoint.position+initialEncoderPosition, ControlType.kPosition);
    //     this.currentPosition = setPoint;
    // }
  }
  
	public boolean motorControl() {
    //System.out.println(currentTargetHeight.toString());
    //System.out.println("Target: " + currentSwitcherState.switcherEncoderPosition + " Current: " + encoder.getPosition());
    //System.out.println("Switcher motor pos: " + encoder.getPosition());
    target = currentSwitcherState.switcherEncoderPosition;

    if(intermediateSetPoint > target && (currentSwitcherState == SwitcherState.CARGO || currentSwitcherState == SwitcherState.FLOOR) && encoder.getPosition() < 0.75){
      controller.setReference(0, ControlType.kVoltage);
      //System.out.println("Switcher powered off");
      //SmartDashboard.putNumber("intermediateSetPoint", encoder.getPosition());
      return false;
    }

    if(intermediateSetPoint - target > -0.05 && intermediateSetPoint - target < 0.05){
      if(!reached){
        System.out.println("intermediateSetPoint at Target");
        reached = true;
      }
    } else if(intermediateSetPoint<target){
      intermediateSetPoint+=0.3;
      reached = false;
    }else if(intermediateSetPoint>target){
      
        intermediateSetPoint-=0.3;
        reached = false;
      
      
    }


    
    //SmartDashboard.putNumber("intermediateSetPoint", encoder.getPosition());


    controller.setReference(intermediateSetPoint, ControlType.kPosition);
    
        
      return true;
  
    }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public static enum SwitcherState {
    FLOOR(RobotMap.SWITCHER_FLOOR),
    CARGO(RobotMap.SWITCHER_CARGO),
    HATCH(RobotMap.SWITCHER_HATCH),
    REAR(RobotMap.SWITCHER_REAR);

    public double switcherEncoderPosition;
    private SwitcherState(double position) {
      this.switcherEncoderPosition = position;
    }
  }

  public SwitcherState getNextPosition(){
    //System.out.println("Switcher.getNextPosition");
    switch(currentSwitcherState){
      case FLOOR:
        return SwitcherState.CARGO;
      case CARGO:
        return SwitcherState.HATCH;
      case HATCH:
        return SwitcherState.REAR;
      case REAR:
        return SwitcherState.REAR;
      default:
        return SwitcherState.FLOOR;
    }
  }

  public SwitcherState getPrevPosition(){
    switch(currentSwitcherState){
      case FLOOR:
        return SwitcherState.FLOOR;
      case CARGO:
        return SwitcherState.FLOOR;
      case HATCH:
        return SwitcherState.CARGO;
      case REAR:
        return SwitcherState.HATCH;
      default:
        return SwitcherState.FLOOR;
    }
  }
  
  public double getCurrentSwitch(){
    return encoder.getPosition();
  }
}

