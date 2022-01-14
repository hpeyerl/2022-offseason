// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TurretSubsystem extends SubsystemBase {
  /** Creates a new TurretSubsystem. */

  private final TalonSRX flyWheelMotor = new TalonSRX(Constants.MOTORS.FLYWHEEL_MOTOR_1.ordinal());

  private final double RPM = 0;

  private final double kP = 0; //tunable value
  private final double kI = 0; //tunable value
  private final double kD = 0; //tunable value

  private final PIDController pidController = new PIDController(kP, kI, kD);
  
  public TurretSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


}
