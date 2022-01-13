// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
 // remote values
 public static final int DRIVER_CONTROLLER = 0;
 public static final int OPERATOR_CONTROLLER = 1;

 public static final int BUTTON_A = 1;
 public static final int BUTTON_B = 2;
 public static final int BUTTON_X = 3;
 public static final int BUTTON_Y = 4;
 public static final int LEFT_BUMPER = 5;
 public static final int RIGHT_BUMPER = 6;
 public static final int BACK_BUTTON = 7;
 public static final int START_BUTTON = 8;
 public static final int LEFT_STICK_BUTTON = 9;
 public static final int RIGHT_STICK_BUTTON = 10;

 public static final int DPAD_UP = 0;
 public static final int DPAD_UPRIGHT = 45;
 public static final int DPAD_RIGHT = 90;
 public static final int DPAD_DOWN = 180;
 public static final int DPAD_LEFT = 270;
 public static final int DPAD_UPLEFT = 315;

 public static final int LEFT_STICK_X = 0;
 public static final int LEFT_STICK_Y = 1;
 public static final int RIGHT_STICK_Y = 5;
 public static final int RIGHT_STICK_X = 4;

 public static final int LEFT_TRIGGER = 2;
 public static final int RIGHT_TRIGGER = 3;

 public static final int CONTINOUS_CURRENT = 60;
 public static final int PEAK_CURRENT = 100; //Subject to change

 public static final int BOT_IMU = 11;
 // end of remote values

 // motors, solenoids and other
 public static enum MOTORS {
   LEFT_MOTOR_1, // Talon 0
   LEFT_MOTOR_2, // Victor 1
   RIGHT_MOTOR_1, // Talon 2
   RIGHT_MOTOR_2, // Victor 3
 }
}
