// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
public class drivetrain extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private TalonSRX LeftMotor1 = new TalonSRX(Constants.MOTORS.LEFT_MOTOR_1.ordinal());
  private VictorSPX LeftMotor2 = new VictorSPX(Constants.MOTORS.LEFT_MOTOR_2.ordinal());
  private TalonSRX RightMotor1 = new TalonSRX(Constants.MOTORS.RIGHT_MOTOR_2.ordinal());
  private VictorSPX RightMotor2 = new VictorSPX(Constants.MOTORS.RIGHT_MOTOR_2.ordinal());

  public drivetrain() {
    LeftMotor2.follow(LeftMotor1);
    RightMotor2.follow(RightMotor1);
  }
  public void setmotors(double leftmotor, double rightmotor) {
    LeftMotor1.set(ControlMode.PercentOutput, leftmotor);
    RightMotor1.set(ControlMode.PercentOutput, rightmotor);
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
