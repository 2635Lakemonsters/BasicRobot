/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
  public static int LEFT_JOYSTICK_CHANNEL = 0;
  public static int RIGHT_JOYSTICK_CHANNEL = 1;

  public static int FRONT_RIGHT_MOTOR_CHANNEL = 1;
  public static int BACK_RIGHT_MOTOR_CHANNEL = 2;
  public static int FRONT_LEFT_MOTOR_CHANNEL = 3;
  public static int BACK_LEFT_MOTOR_CHANNEL = 4;
  public static int SWITCH_MOTOR_CHANNEL = 7;

  public static int LEFT_INTAKE_MOTOR_CHANNEL = 8;
  public static int RIGHT_INTAKE_MOTOR_CHANNEL = 9;

  public static int FRONT_LEFT_EXTENDER_CHANNEL = 11;
  public static int FRONT_RIGHT_EXTENDER_CHANNEL = 10;
  public static int BACK_EXTENDER_CHANNEL = 12;
  public static int BACK_EXTENDER_DRIVE_CHANNEL = 13;

  public static int S_PATH_LEFT_BUTTON = 8;
  public static int EXTENDER_BUTTON = 3;
  //public static int LEFT_FLOWER_BUTTON = 1;
  //public static int RIGHT_FLOWER_BUTTON = 1;
  //public static int REVERSE_BUTTON = 2;
  public static int ENCODER_RESET_BUTTON = 6;
  public static int SWITCHER_UP_BUTTON = 5;
  public static int SWITCHER_DOWN_BUTTON = 4;
  public static int GAME_TOOL_INCREMENT_BUTTON = 5;
  public static int GAME_TOOL_DECREMENT_BUTTON = 4;
  public static int GAME_TOOL_SWAP_BUTTON = 2;
  public static int GAME_TOOL_FLOWER_BUTTON = 1;

  //--------EXPERIMENTAL PATHWEAVER CONSTANTS------------
  // public static final int k_ticks_per_rev = 4096/*/3*/;//2018 Comp Bot
  public static final double k_drive_gear_ratio = 7.31;
  public static final double k_ticks_per_motor_rev = 42.0;
  public static final double k_ticks_per_wheel_rev = k_ticks_per_motor_rev*k_drive_gear_ratio;
  public static final double k_wheel_diameter = 0.49; //feet!
  public static final double k_max_velocity = 10.0;

  public static final double DRIVE_P_VALUE = 0.1;
  public static final double DRIVE_I_VALUE = 0.0;
  public static final double DRIVE_D_VALUE = 0.0;
  public static final double DRIVE_V_VALUE = 0.054;
  public static final double DRIVE_A_VALUE = 0.025;
  // public static final int k_left_channel = 0;
  // public static final int k_right_channel = 1;


  // public static final int k_left_encoder_port_a = 0;
  // public static final int k_left_encoder_port_b = 1;
  // public static final int k_right_encoder_port_a = 2;
  // public static final int k_right_encoder_port_b = 3;

  public static final int k_gyro_port = 0;

  /* Elevator Values */
  public static final int ELEVATOR_TOLERANCE = 1;
  public static final int ELEVATOR_UPPER_MOTOR_CHANNEL = 6;
  public static final int ELEVATOR_LOWER_MOTOR1_CHANNEL = 5;
  // public static final int SMALL_ELEVATOR_MAX = 138;
  public static final int SMALL_ELEVATOR_MAX = 152;

  // public static final int ELEVATOR_ACCELERATION = 5000;
  // public static final int ELEVATOR_VELOCITY = 3000;
  public static final int ELEVATOR_GROUND_LOWER_HEIGHT = 0;
  public static final int ELEVATOR_EXCHANGE_LOWER_HEIGHT = 0;
  public static final int ELEVATOR_STACK_LOWER_HEIGHT = 0;

  public static final int ELEVATOR_SWITCH_LOWER_HEIGHT = 0;
  public static final int ELEVATOR_SCALE_LOWER_HEIGHT = 0;
  public static final int ELEVATOR_CLIMB_LOWER_HEIGHT = 0;

  public static final int ELEVATOR_GROUND_HEIGHT = 0;
  public static final double ELEVATOR_LEVEL1_HATCH_HEIGHT = 1.25;
  // public static final int ELEVATOR_TRANSIT_BALL_HEIGHT = 5;
  public static final int ELEVATOR_TRANSIT_BALL_HEIGHT = 7;
  // public static final int ELEVATOR_LEVEL1_BALL_HEIGHT = 58;
  public static final int ELEVATOR_LEVEL1_BALL_HEIGHT = 100;
  // public static final int ELEVATOR_LEVEL2_HATCH_HEIGHT = 76;
  public static final int ELEVATOR_LEVEL2_HATCH_HEIGHT = 115;
  // public static final int ELEVATOR_LEVEL2_BALL_HEIGHT = 166;
  public static final int ELEVATOR_LEVEL2_BALL_HEIGHT = 230;
  // public static final int ELEVATOR_LEVEL3_HATCH_HEIGHT = 197;
  public static final int ELEVATOR_LEVEL3_HATCH_HEIGHT = 244;
  // public static final int ELEVATOR_LEVEL3_BALL_HEIGHT = 235;
  public static final int ELEVATOR_LEVEL3_BALL_HEIGHT = 290;
  // public static final int ELEVATOR_SHIP_BALL_HEIGHT = 94;
  public static final int ELEVATOR_SHIP_BALL_HEIGHT = 155;

  public static final double ELEVATOR_AUTO_HATCH_HEIGHT = 27.25;

  public static final double ELEVATOR_AUTO_CARRIAGE_HEIGHT = 7.0;
  public static final double SWITCHER_AUTO_HEIGHT = 17.0;


  //public static final String k_path_name = "example";
  //------------------------------------------------------
  public static final double SWITCHER_FLOOR = 0;
  public static final double SWITCHER_CARGO = 0;
  public static final double SWITCHER_HATCH = 18;
  //public static final double SWITCHER_REAR = 35; // rear deploy
  public static final double SWITCHER_REAR = 8; // front deploy 
}
