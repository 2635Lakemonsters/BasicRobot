/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.ExampleCommand;
import frc.robot.model.GameToolStateMachine;
import frc.robot.subsystems.DriveSubsystemNeo;
import frc.robot.subsystems.*;
import frc.robot.commands.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  public static OI oi;

  public static DriveSubsystemNeo driveSubsystem;
  public static Cargo cargo;
  public static Elevator elevator;
  public static Flower flower;
  public static Switcher switcher;
  public static Vision vision;
  public static Climber climber;

  public static GameToolStateMachine gameToolStateMachine;
  DriveCommand driveCommand;
  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  ElevatorControl elevatorControl;
  SwitcherControl switcherControl;
  GameToolIncrementCommand gameToolIncrementCommand;
  GameToolDecrementCommand gameToolDecrementCommand;
  GameToolSwapCommand gameToolSwapCommand;
  GameToolFlowerCommand gameToolFlowerCommand;
  CargoOutCommand cargoOutCommand;
  CargoOutCommand cargoOutLeftCommand;
  CargoOutCommand cargoOutRightCommand;
  CargoInCommand cargoInCommand;
  AutoInitGameToolCommand autoInitGameToolCommand;
  RaiseRobotCommand raiseRobotCommand;
  ClimberRaiseBackCommand climberRaiseBackCommand;
  ClimberRaiseFrontCommand climberRaiseFrontCommand;
  ClimberDriveCommand climberDriveCommand;
  public static ClimberControl climberControl;
  DriveClimberInCommand driveClimberInCommand;
  boolean autoHappened;
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    oi = new OI();
    driveSubsystem = new DriveSubsystemNeo();
    cargo = new Cargo();
    elevator = new Elevator();
    flower = new Flower();
    switcher = new Switcher();
    //vision = new Vision();
    climber = new Climber();

    gameToolStateMachine = new GameToolStateMachine();
    m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    //SmartDashboard.putData("Auto mode", m_chooser);
    driveCommand = new DriveCommand();
    elevatorControl = new ElevatorControl();
    switcherControl = new SwitcherControl();

    gameToolIncrementCommand = new GameToolIncrementCommand();
    gameToolDecrementCommand = new GameToolDecrementCommand();
    gameToolSwapCommand = new GameToolSwapCommand();
    gameToolFlowerCommand = new GameToolFlowerCommand();
    cargoOutCommand = new CargoOutCommand(0.6,0.6);
    cargoOutLeftCommand = new CargoOutCommand(-1.0,0);
    cargoOutRightCommand = new CargoOutCommand(0,-1.0);
    cargoInCommand = new CargoInCommand();
    autoInitGameToolCommand = new AutoInitGameToolCommand();
    raiseRobotCommand = new RaiseRobotCommand();
    climberRaiseBackCommand = new ClimberRaiseBackCommand();
    climberRaiseFrontCommand = new ClimberRaiseFrontCommand();
    climberDriveCommand = new ClimberDriveCommand(1, 9);
    climberControl = new ClimberControl();
    driveClimberInCommand = new DriveClimberInCommand(10);


    oi.gameToolIncrementButton.whenPressed(gameToolIncrementCommand);
    oi.gameToolDecrementButton.whenPressed(gameToolDecrementCommand);
    oi.gameToolSwapButton.whenPressed(gameToolSwapCommand);
    oi.gameToolFlowerButton.whenPressed(gameToolFlowerCommand);
    oi.cargoInButton.whileHeld(cargoInCommand);
    oi.cargoOutButton.toggleWhenPressed(cargoOutCommand);
    oi.cargoOutLeftButton.toggleWhenPressed(cargoOutLeftCommand);
    oi.cargoOutRightButton.toggleWhenPressed(cargoOutRightCommand);
    oi.autoInitGameToolButton.whenPressed(autoInitGameToolCommand);
    oi.climbUpButton.whileHeld(raiseRobotCommand);
    oi.climbRaiseBackButton.whileHeld(climberRaiseBackCommand);
    oi.climbRaiseFrontButton.whileHeld(climberRaiseFrontCommand);
    oi.climbDriveButton.whileHeld(climberDriveCommand);
    oi.climbResetButton.whileHeld(driveClimberInCommand);
    autoHappened = false;
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */
    if (driveCommand != null && !driveCommand.isRunning()) {
			driveCommand.start();
		}
    
    if (elevatorControl != null && !elevatorControl.isRunning()){
      elevatorControl.start();
    }
    
    gameToolStateMachine.autonomousReset();
    //gameToolStateMachine.autoHatch();
    switcherControl.start();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
    autoHappened = true;
    switcher.setIntermediateSetPoint((int) switcher.currentSwitcherState.switcherEncoderPosition);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    driveCommand.start();
    switcher.setIntermediateSetPoint((int) switcher.currentSwitcherState.switcherEncoderPosition);
    elevator.encoderStart();
    elevatorControl.start();
    switcherControl.start();
    gameToolStateMachine.reset();
    // Climber is now controlled only turned on when needed, by the button controls
    //climberControl.start(); 
    if(autoHappened){
      switcher.encoderReset();
    }
    autoHappened = false;
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
