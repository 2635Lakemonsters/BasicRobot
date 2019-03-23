/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.Timer;

//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
//import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.DriveCommand;
// import jaci.pathfinder.Pathfinder;
// import jaci.pathfinder.PathfinderFRC;
// import jaci.pathfinder.PathfinderJNI;
// import jaci.pathfinder.Trajectory;
// import jaci.pathfinder.followers.EncoderFollower;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.SerialPort.Port;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import com.ctre.phoenix.motorcontrol.*;

import java.io.*;
import java.util.*;

/**
 * Add your docs here.
 */
public class DriveSubsystemNeo extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public CANSparkMax FRMotor;
  CANSparkMax BRMotor;
  public CANSparkMax FLMotor;
  CANSparkMax BLMotor;

  public CANEncoder FREncoder;
  public CANEncoder FLEncoder;

  public boolean isReversed = false;

  public long startTime;
  int  index;
  Solenoid gearBoxSolenoid;

  //---------EXPERIMENTAL PATH WEAVER CODE----------
  // Encoder m_left_encoder;
  // Encoder m_right_encoder;

  //AnalogGyro m_gyro;
  //AHRS m_gyro;

  // public EncoderFollower m_left_follower;
  // public EncoderFollower m_right_follower;
  
  Notifier m_follower_notifier;
  //-----------------------------------------------

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new DriveCommand());
  }

  public DriveSubsystemNeo(){
    //use talons to read external encoder values!
    FRMotor = new CANSparkMax(RobotMap.FRONT_RIGHT_MOTOR_CHANNEL, MotorType.kBrushless);
    BRMotor = new CANSparkMax(RobotMap.BACK_RIGHT_MOTOR_CHANNEL, MotorType.kBrushless);
    FLMotor = new CANSparkMax(RobotMap.FRONT_LEFT_MOTOR_CHANNEL, MotorType.kBrushless);
    BLMotor = new CANSparkMax(RobotMap.BACK_LEFT_MOTOR_CHANNEL, MotorType.kBrushless);

    FLMotor.restoreFactoryDefaults();
    FRMotor.restoreFactoryDefaults();
    BLMotor.restoreFactoryDefaults();
    BRMotor.restoreFactoryDefaults();

    // FREncoder = new CANEncoder(FRMotor);
    // FLEncoder = new CANEncoder(FLMotor);
    
    // FRMotor.setSensorPhase(false);
    // FLMotor.setSensorPhase(true);

    //2018 Bot = Right:true   Left:false
    

    // gearBoxSolenoid = new Solenoid(7);

    BRMotor.follow(FRMotor);
    BLMotor.follow(FLMotor);
    
    
    //----EXPERIMENTAL PATH WEAVER CODE----------------
    // m_left_encoder = new Encoder();
    // m_right_encoder = new Encoder();
    // m_gyro = new AHRS(I2C.Port.kMXP);
    
    
    //m_gyro = new AnalogGyro(RobotMap.k_gyro_port);
    //-------------------------------------------
  }

  // public void setReverse(boolean isReversed) {
  //     this.isReversed = isReversed;
  //     // if(isReversed) {

  
  //     //   FRMotor.setSensorPhase(true);
  //     //   FLMotor.setSensorPhase(false);
  //     // } else {
  //     //   FRMotor.setSensorPhase(false);
  //     //   FLMotor.setSensorPhase(true);
  //     // }

  // }

  // public boolean getReverse() {
  //   return isReversed;
  // }

  public double leftValue;
  public double rightValue;

  // public void driveLoop(){
  //   leftValue = -Robot.oi.leftJoy.getRawAxis(1);
  //   rightValue = Robot.oi.rightJoy.getRawAxis(1);
  // }

  public void tankDrive(double left, double right) {
    //System.out.println("Left Joystick:" + left);
    //System.out.println("Right Joystick: " + right);
    double deadzone = 0.08;
    // if(left > deadzone) {
    //   left = ((left - deadzone)/(1.0 - deadzone));
    // } else if(left < -deadzone) {
    //   left = ((left + deadzone)/(1.0 - deadzone));
    // } else {
    //   left = 0;
    // }
    // if(right > deadzone) {
    //   right = ((right - deadzone)/(1.0 - deadzone));
    // } else if(right < -deadzone) {
    //   right = ((right + deadzone)/(1.0 - deadzone));
    // } else {
    //   right = 0;
    // }
    // if(getReverse()) {
    //   tempLeft = right;
    //   right = -left;
    //   left = -tempLeft;
    // } 

    
    
    FRMotor.set(-right);
    FLMotor.set(left);
    //System.out.println("Left: " + FLMotor.getSelectedSensorPosition());
    //System.out.println("Right: " + FRMotor.getSelectedSensorPosition());
			
		}
  //---------EXPERIMENTAL PATH WEAVER CODE-----------
  
  public void encoderReset() {
    FREncoder.setPosition(0);
    FLEncoder.setPosition(0);
  }

  // public boolean PathAutoInit(String pathname) {
  //   encoderReset();
  //   // FRMotor.setSelectedSensorPosition(0, 0, 0);
  //   // FLMotor.setSelectedSensorPosition(0, 0, 0);
    
  //   //System.out.println("Test Out");
  //   //File tmep = new File(Filesystem.getDeployDirectory(), "paths/" + "Straight.left" + ".pf1.csv");
  //   //File tmep = new File("/home/lvuser/deploy/paths/Straight.left.pf1.csv");
  //   //Trajectory feu = new Trajectory(PathfinderJNI.trajectoryDeserializeCSV(tmep.getAbsolutePath()));
  //   //System.out.println(tmep.exists());
  //   //System.out.println(Filesystem.getDeployDirectory().toString() + "/paths/" + "Straight.left" + ".pf1.csv");
  //   //System.out.println("TestEnd");
  //   Trajectory left_trajectory;
  //   Trajectory right_trajectory;
  //   File file = new  File(Filesystem.getDeployDirectory(), "paths/" + pathname + ".pf1.csv");
  
  //   if (!file.exists())
  //   {
  //      System.out.println("Path Init Error!!!!");
  //      System.out.println("File '" + file.getAbsolutePath() + "' not found.");
  //      return false;
       
  //   }

    
  //   left_trajectory = PathfinderFRC.getTrajectory(pathname + ".right"); //change this depending on which side
  //   right_trajectory = PathfinderFRC.getTrajectory(pathname + ".left");
  //   System.out.println("PathAutoInit: pathname: " + pathname);
   
    
  //   m_left_follower = new EncoderFollower(left_trajectory);
  //   m_right_follower = new EncoderFollower(right_trajectory);

  //   int leftPosInTicks = (int) (FLEncoder.getPosition()*RobotMap.k_ticks_per_motor_rev);
  //   int rightPosInTicks = (int) (-FREncoder.getPosition()*RobotMap.k_ticks_per_motor_rev);

  //   m_left_follower.configureEncoder(leftPosInTicks, (int) RobotMap.k_ticks_per_wheel_rev, RobotMap.k_wheel_diameter);
  //   // You must tune the PID values on the following line!
  //   //m_left_follower.configurePIDVA(0.1, 0.0, 0.25, 1 / RobotMap.k_max_velocity, 0); //1 / RobotMap.k_max_velocity
  //   //m_left_follower.configurePIDVA(0.2, 0.0, 0.08, 0.075, 0.045); //1 / RobotMap.k_max_velocity
  //   //m_left_follower.configurePIDVA(0.03, 0.0, 0.0, 0.07, 0.04);
  //   //m_left_follower.configurePIDVA(0.05, 0.0, 0.0, 0.06, 0.03);
  //   m_left_follower.configurePIDVA(RobotMap.DRIVE_P_VALUE, RobotMap.DRIVE_I_VALUE, RobotMap.DRIVE_D_VALUE, RobotMap.DRIVE_V_VALUE, RobotMap.DRIVE_A_VALUE);

  //   m_right_follower.configureEncoder(rightPosInTicks, (int) RobotMap.k_ticks_per_wheel_rev, RobotMap.k_wheel_diameter);
  //   // You must tune the PID values on the following line!
  //   //m_right_follower.configurePIDVA(0.1, 0.0, 0.25, 1 / RobotMap.k_max_velocity, 0);
  //   //m_right_follower.configurePIDVA(0.2, 0.0, 0.08,0.075, 0.045);
  //   //m_right_follower.configurePIDVA(0.03, 0.0, 0.0, 0.07, 0.04);
  //   //m_right_follower.configurePIDVA(0.05, 0.0, 0.0, 0.06, 0.03);
  //   m_right_follower.configurePIDVA(RobotMap.DRIVE_P_VALUE, RobotMap.DRIVE_I_VALUE, RobotMap.DRIVE_D_VALUE, RobotMap.DRIVE_V_VALUE, RobotMap.DRIVE_A_VALUE);
  //   m_gyro.reset();
    
  //   m_follower_notifier = new Notifier(this::followPath);
  //   m_follower_notifier.startPeriodic(left_trajectory.get(0).dt);
  //   return true;
  // }

//   public void followPath() {
//     int leftPosInTicks = (int) (FLEncoder.getPosition()*RobotMap.k_ticks_per_motor_rev);
//     int rightPosInTicks = (int) (-FREncoder.getPosition()*RobotMap.k_ticks_per_motor_rev); //negation is equivalent to changing sensor polarity for right side
    
//     if (m_left_follower.isFinished() || m_right_follower.isFinished()) {
//       endPath();
//     } else {  

//       double left_speed = m_left_follower.calculate(leftPosInTicks);
//       double right_speed = m_right_follower.calculate(rightPosInTicks);
//       double distance = rightPosInTicks/RobotMap.k_ticks_per_wheel_rev*RobotMap.k_wheel_diameter*3.14159;
      
      
//       //System.out.println("Left Encoder pos: " + FLEncoder.getPosition() + " left_speed: " + left_speed);
//       //System.out.println("Left Encoder pos: " + leftPosInTicks + " left_speed: " + left_speed);
//       //System.out.println("Right Encoder pos: " + rightPosInTicks + " right_speed: " + right_speed);
//      // System.out.println(distance + ", " + right_speed);
//       //double heading = m_gyro.getAngle();
//       double heading = getGyroAngle();
//       double desired_heading;
      
//       desired_heading = Pathfinder.r2d(m_left_follower.getHeading());
      
//       double heading_difference = Pathfinder.boundHalfDegrees(desired_heading - heading);
//       //double turn =  1.5 * (-1.0/80.0) * heading_difference;
//       double turn =  0.02 * heading_difference;
//       //double turn = 0;
//       // FRMotor.set(ControlMode.PercentOutput, -(right_speed) );
//       // FLMotor.set(ControlMode.PercentOutput, left_speed);
//       // if(!swapped){
//          //FRMotor.set(ControlMode.PercentOutput, -(right_speed + turn) );
//          //FLMotor.set(ControlMode.PercentOutput, left_speed - turn);
//          tankDrive(left_speed + turn, (right_speed - turn));
//         // tankDrive(right_speed + turn, (right_speed - turn));
//       // }else{
//       //   FRMotor.set(ControlMode.PercentOutput, (right_speed + turn) );
//       //   FLMotor.set(ControlMode.PercentOutput, -(left_speed - turn));
//       // }
//       // //System.out.println("Controller Position: " + right_speed + " Left: " + left_speed);
//       //System.out.println("Gyro heading: " + heading + " Heading Diff: " + heading_difference);
//      // System.out.println("left Speed: " + left_speed + " Right Speed: " + right_speed);
      
//     }
//   }
//   public double getGyroAngle() {
//     double angle = m_gyro.getAngle();
//     // if(isReversed) {
//     //   angle = -angle;
//     // }
//     return angle;
//   }

  
//   public void endPath() {
//     if(m_follower_notifier != null){
//       m_follower_notifier.stop();
//     }
//     tankDrive(0.0,0.0);
//     //FRMotor.set(0);
//     //FLMotor.set(0);
//   }

// //-----------------------------------------------------------------------------
 

//  boolean bullyMode = true;
//  public void switchDriveMode(){
//   /*
//   This function is for changing in and out of buly mode on the 2018 robot. 
//   This code may or may not need to be removed on the final robot if we decide to have another bull mode this year
//   */

//   bullyMode = !bullyMode;
//   gearBoxSolenoid.set(bullyMode);
//  }

//  public void bullyOff(){
//    bullyMode= false;

//    gearBoxSolenoid.set(bullyMode);
//  }

}
