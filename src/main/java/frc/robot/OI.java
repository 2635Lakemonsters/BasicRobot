/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

  public Joystick leftJoy = new Joystick(RobotMap.LEFT_JOYSTICK_CHANNEL);
  public Joystick rightJoy = new Joystick(RobotMap.RIGHT_JOYSTICK_CHANNEL);

  public Button gameToolIncrementButton = new JoystickButton(leftJoy, RobotMap.GAME_TOOL_INCREMENT_BUTTON);
  public Button gameToolDecrementButton = new JoystickButton(leftJoy, RobotMap.GAME_TOOL_DECREMENT_BUTTON);
  public Button gameToolSwapButton = new JoystickButton(leftJoy, RobotMap.GAME_TOOL_SWAP_BUTTON);
  public Button gameToolFlowerButton = new JoystickButton(rightJoy, RobotMap.GAME_TOOL_FLOWER_BUTTON);
  public Button cargoInButton = new JoystickButton(rightJoy, 3);
  public Button cargoOutButton = new JoystickButton(rightJoy, 2);
  public Button cargoOutLeftButton = new JoystickButton(rightJoy, 7);
  public Button cargoOutRightButton = new JoystickButton(rightJoy, 10);
  public Button autoInitGameToolButton = new JoystickButton(rightJoy, 8);

}
