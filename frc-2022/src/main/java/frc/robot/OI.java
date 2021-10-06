/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  	XboxController driverController = new XboxController(RobotMap.DRIVER_CONTROLLER);
	XboxController operatorController = new XboxController(RobotMap.OPERATOR_CONTROLLER);
	
	Button dButtonA = new JoystickButton(this.driverController, RobotMap.BUTTON_A);
	Button dButtonB = new JoystickButton(this.driverController, RobotMap.BUTTON_B);
	Button dButtonX = new JoystickButton(this.driverController, RobotMap.BUTTON_X);
	Button dButtonY = new JoystickButton(this.driverController, RobotMap.BUTTON_Y);
	Button dButtonBack = new JoystickButton(this.driverController, RobotMap.BACK_BUTTON);
	Button dButtonStart = new JoystickButton(this.driverController, RobotMap.START_BUTTON);
	Button dButtonRightBumper = new JoystickButton(this.driverController, RobotMap.RIGHT_BUMPER);
	Button dButtonLeftBumper = new JoystickButton(this.driverController, RobotMap.LEFT_BUMPER);

	
	Button oButtonA = new JoystickButton(this.operatorController, RobotMap.BUTTON_A);
	Button oButtonB = new JoystickButton(this.operatorController, RobotMap.BUTTON_B);
	Button oButtonY = new JoystickButton(this.operatorController, RobotMap.BUTTON_Y);
	Button oButtonX = new JoystickButton(this.operatorController, RobotMap.BUTTON_X);
	Button oButtonBack = new JoystickButton(this.operatorController, RobotMap.BACK_BUTTON);
	Button oButtonStart = new JoystickButton(this.operatorController, RobotMap.START_BUTTON);
  	Button oButtonRightStick = new JoystickButton(this.operatorController, RobotMap.RIGHT_STICK_BUTTON);
	Button oButtonRightBumper = new JoystickButton(this.operatorController, RobotMap.RIGHT_BUMPER);
	Button oButtonLeftBumper = new JoystickButton(this.operatorController, RobotMap.LEFT_BUMPER);

	POVButton oDPADUp = new POVButton(this.operatorController, RobotMap.DPAD_UP);
	POVButton oDPADDown = new POVButton(this.operatorController, RobotMap.DPAD_DOWN);
	POVButton oDPADLeft = new POVButton(this.operatorController, RobotMap.DPAD_LEFT);
	POVButton oDPADRight = new POVButton(this.operatorController, RobotMap.DPAD_RIGHT);

	POVButton dDPADUp = new POVButton(this.driverController, RobotMap.DPAD_UP);
	POVButton dDPADDown = new POVButton(this.driverController, RobotMap.DPAD_DOWN);
	POVButton dDPADLeft = new POVButton(this.driverController, RobotMap.DPAD_LEFT);
	POVButton dDPADRight = new POVButton(this.driverController, RobotMap.DPAD_RIGHT);




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
	
	public OI() {
		
		// this.dDPADUp.whenPressed(new Climb (0.80));
	

	}
	
}
