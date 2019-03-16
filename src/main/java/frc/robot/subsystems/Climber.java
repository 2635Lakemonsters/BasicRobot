/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
//import jdk.internal.vm.compiler.collections.EconomicMap;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {
  // Put methods for controlling this subsystem
  WPI_TalonSRX FRExtender;
  WPI_TalonSRX FLExtender;
  WPI_TalonSRX BExtender;
  WPI_TalonSRX BackDriveMotor;
  double intermediateSetPoint;
  double intermediateSetPointBack;
  boolean reached;
  boolean reachedBack;

  int EXTENDER_HEIGHT = 22000;

  //This int will be used to track what stage the climb was cancelled in, and what to do about that cancel
  public int COMMAND_GROUP_STAGE = 0;

  public Climber() {
    FRExtender = new WPI_TalonSRX(RobotMap.FRONT_RIGHT_EXTENDER_CHANNEL);
    FLExtender = new WPI_TalonSRX(RobotMap.FRONT_LEFT_EXTENDER_CHANNEL);
    BExtender = new WPI_TalonSRX(RobotMap.BACK_EXTENDER_CHANNEL);
    BackDriveMotor = new WPI_TalonSRX(RobotMap.BACK_EXTENDER_DRIVE_CHANNEL);
    //FRExtender.setSensorPhase(true);

    FRExtender.config_kP(0, 0.4);
    FRExtender.config_kI(0, 0);
    FRExtender.config_kD(0, 0);
    FRExtender.config_kF(0, 0);
    //FRExtender.configMotionCruiseVelocity(300);
    //FRExtender.configMotionAcceleration(300);

    FLExtender.config_kP(0, 0.4);
    FLExtender.config_kI(0, 0);
    FLExtender.config_kD(0, 0);
    FLExtender.config_kF(0, 0);
    //FLExtender.configMotionCruiseVelocity(300);
    //FLExtender.configMotionAcceleration(300);

    BExtender.config_kP(0, 0.4);
    BExtender.config_kI(0, 0);
    BExtender.config_kD(0, 0);
    BExtender.config_kF(0, 0);
    //BExtender.configMotionCruiseVelocity(300);
    //BExtender.configMotionAcceleration(300);

    intermediateSetPoint = 0;
    intermediateSetPointBack = 0;
    reached = false;
    reachedBack = false;

  }

  public void reset() {
    FRExtender.setSelectedSensorPosition(0);
    FLExtender.setSelectedSensorPosition(0);
    BExtender.setSelectedSensorPosition(0);
  }
 
  public void lowerClimber(){
    moveClimber(EXTENDER_HEIGHT, EXTENDER_HEIGHT, EXTENDER_HEIGHT);
  }
  
  public boolean lowerClimberIsFinished(){
    int rightValue = FRExtender.getSelectedSensorPosition(0);
    int leftValue = FLExtender.getSelectedSensorPosition(0);
    int backValue = BExtender.getSelectedSensorPosition(0);
    int errorThreshold = 1500;
  
    int rightError = Math.abs(EXTENDER_HEIGHT - Math.abs(rightValue));
    int leftError = Math.abs(EXTENDER_HEIGHT - Math.abs(leftValue));
    int backError = Math.abs(EXTENDER_HEIGHT - Math.abs(backValue));

    //System.out.println("rightError: " + rightError + "\t leftError: " + leftError + "\t backError: " + backError);
    
    if ( rightError < errorThreshold
     && leftError < errorThreshold
     &&  backError < errorThreshold)
      return true;
    else
      return false;
    
    // if(rightValue == EXTENDER_HEIGHT && leftValue == EXTENDER_HEIGHT && backValue == EXTENDER_HEIGHT){
    //   return true;
    // }else{
    //   return false;
    // }
  }

  public void raiseFrontClimber(){
    moveClimber(0, 0, EXTENDER_HEIGHT);
  }

  public boolean raiseFrontClimberIsFinished(){
    int rightValue = FRExtender.getSelectedSensorPosition(0);
    int leftValue = FLExtender.getSelectedSensorPosition(0);
    int errorThreshold = 1500;
    
    int rightError = Math.abs(rightValue);
    int leftError = Math.abs(leftValue);

    System.out.println("rightError: " + rightError + "\t leftError: " + leftError);

    if(rightError < errorThreshold && leftError < errorThreshold){
      return true;
    }else{
      return false;
    }
  }

  public void raiseBackClimber(){
    moveClimber(0, 0, 0);
  }

  public boolean raiseBackClimberIsFinished(){
    int backValue = BExtender.getSelectedSensorPosition(0);
    int errorThreshold = 1500;

    int backError = Math.abs(backValue);

    //System.out.println("backError: " + backError);

    if(backError < errorThreshold){
      return true;
    }else{
      return false;
    }
  }

  public void drive(double driveSpeed){
    BackDriveMotor.set(driveSpeed);
  }

  
  public void moveClimber(double right, double left, double back){
    
    if(intermediateSetPoint - right < 0.15 && intermediateSetPoint - right > -0.15){
      if(!reached){
        System.out.println("intermediateSetPoint at Target");
        reached = true;
      }
      
    } else if(intermediateSetPoint < right){
      intermediateSetPoint += 200;
      reached = false;
    } else{
      intermediateSetPoint -= 200;
      reached = false;
    }

    if(intermediateSetPointBack - back < 0.15 && intermediateSetPointBack - back > -0.15){
      if(!reachedBack){
        System.out.println("intermediateSetPoint at Target");
        reachedBack = true;
      }
      
    } else if(intermediateSetPointBack < back){
      intermediateSetPointBack += 200;
      reachedBack = false;
    } else{
      intermediateSetPointBack -= 200;
      reachedBack = false;
    }

  
  }
 
  public void setClimber(){
    FRExtender.set(ControlMode.Position, -intermediateSetPoint);
    FLExtender.set(ControlMode.Position, intermediateSetPoint);
    BExtender.set(ControlMode.Position, -intermediateSetPointBack);
    System.out.println(FLExtender.getSelectedSensorPosition());
  }

  /*
  * One of two options needs to be chosen.
  * Either we use the wall stallout to see when we hit the wall
  * Or we assume we always start directly against the wall, 
  * And use a constant amount of encoder counts to proceed
  */
  public boolean driveHitWall(){
    if( BackDriveMotor.getOutputCurrent() > 10.0) {
      System.out.println("current > 2");
      return true;
    }else{
      return false;
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
