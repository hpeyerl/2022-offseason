// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
  XboxController driverController = new XboxController(Constants.DRIVER_CONTROLLER);
	XboxController operatorController = new XboxController(Constants.OPERATOR_CONTROLLER);
	
	Button dButtonA = new JoystickButton(this.driverController, Constants.BUTTON_A);
	Button dButtonB = new JoystickButton(this.driverController, Constants.BUTTON_B);
	Button dButtonX = new JoystickButton(this.driverController, Constants.BUTTON_X);
	Button dButtonY = new JoystickButton(this.driverController, Constants.BUTTON_Y);
	Button dButtonBack = new JoystickButton(this.driverController, Constants.BACK_BUTTON);
	Button dButtonStart = new JoystickButton(this.driverController, Constants.START_BUTTON);
	Button dButtonRightBumper = new JoystickButton(this.driverController, Constants.RIGHT_BUMPER);
	Button dButtonLeftBumper = new JoystickButton(this.driverController, Constants.LEFT_BUMPER);

	
	Button oButtonA = new JoystickButton(this.operatorController, Constants.BUTTON_A);
	Button oButtonB = new JoystickButton(this.operatorController, Constants.BUTTON_B);
	Button oButtonY = new JoystickButton(this.operatorController, Constants.BUTTON_Y);
	Button oButtonX = new JoystickButton(this.operatorController, Constants.BUTTON_X);
	Button oButtonBack = new JoystickButton(this.operatorController, Constants.BACK_BUTTON);
	Button oButtonStart = new JoystickButton(this.operatorController, Constants.START_BUTTON);
  	Button oButtonRightStick = new JoystickButton(this.operatorController, Constants.RIGHT_STICK_BUTTON);
	Button oButtonRightBumper = new JoystickButton(this.operatorController, Constants.RIGHT_BUMPER);
	Button oButtonLeftBumper = new JoystickButton(this.operatorController, Constants.LEFT_BUMPER);

	POVButton oDPADUp = new POVButton(this.operatorController, Constants.DPAD_UP);
	POVButton oDPADDown = new POVButton(this.operatorController, Constants.DPAD_DOWN);
	POVButton oDPADLeft = new POVButton(this.operatorController, Constants.DPAD_LEFT);
	POVButton oDPADRight = new POVButton(this.operatorController, Constants.DPAD_RIGHT);

	POVButton dDPADUp = new POVButton(this.driverController, Constants.DPAD_UP);
	POVButton dDPADDown = new POVButton(this.driverController, Constants.DPAD_DOWN);
	POVButton dDPADLeft = new POVButton(this.driverController, Constants.DPAD_LEFT);
	POVButton dDPADRight = new POVButton(this.driverController, Constants.DPAD_RIGHT);




  	public boolean getOperatorButton(int axis) {
		return this.operatorController.getRawButton(axis);
	}
	
	public boolean getDriverButton(int axis) {
		return this.driverController.getRawButton(axis);
	}
	
	public double getOperatorRawAxis(int axis) {
		return this.operatorController.getRawAxis(axis);
	}
	
	public double getDriverRawAxis(int axis) {
		return this.driverController.getRawAxis(axis);
	}
	
	public int getOperatorPOV(){
		return this.operatorController.getPOV();
	}

	public int getDriverPOV(){
		return this.driverController.getPOV();
	}
	
}
