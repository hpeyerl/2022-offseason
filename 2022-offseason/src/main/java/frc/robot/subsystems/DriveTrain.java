// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {

  private final TalonSRX leftMotor1 = new TalonSRX(Constants.MOTORS.LEFT_MOTOR_1.ordinal());  //to be changed sydney is the best
  private final VictorSPX leftMotor2 = new VictorSPX(Constants.MOTORS.LEFT_MOTOR_2.ordinal());  //to be changed
  private final TalonSRX rightMotor1 = new TalonSRX(Constants.MOTORS.RIGHT_MOTOR_1.ordinal());  //to be changed sydney is the best
  private final VictorSPX rightMotor2 = new VictorSPX(Constants.MOTORS.RIGHT_MOTOR_2.ordinal());  //to be changed


  public void SetMotors(double leftSpeed, double rightSpeed){
    leftMotor1.set(ControlMode.PercentOutput, leftSpeed);
    rightMotor1.set(ControlMode.PercentOutput, rightSpeed);
  }

  public DriveTrain() { 
    leftMotor2.follow(leftMotor1);
    rightMotor2.follow(rightMotor1);
  }

  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
