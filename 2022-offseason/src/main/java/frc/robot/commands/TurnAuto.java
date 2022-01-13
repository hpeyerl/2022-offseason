// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.subsystems.DriveSubsystem;

public class TurnAuto extends CommandBase {
  private DriveSubsystem driveSubsystem;
  private PIDController pidController;

  private double angle;
  private double turnPower;

  /** Creates a new TurnAuto. */
  public TurnAuto(DriveSubsystem driveSubsystem, double angle, double turnPower) {
    this.driveSubsystem = driveSubsystem;

    pidController = new PIDController(0, 0, 0); //tunable values

    pidController.enableContinuousInput(-180, 180);
    pidController.setTolerance(2,5);

    // Use addRequirements() here to declare subsystem dependencies.

    this.angle = angle; this.turnPower = turnPower;

    addRequirements(driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    turnRobot(angle, turnPower);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public void turnRobot(double angle, double turnPower) {
    if (angle < 0) {
      turnPower *= -1;
    }

    double leftMotorPower = turnPower; double rightMotorPower = turnPower*-1;

    while (!onHeading(2, getError(angle, driveSubsystem.returnBotHeading()))) { //consider adding a threshold tolerance level
      double PIDTurnPower = MathUtil.clamp(pidController.calculate(driveSubsystem.returnBotHeading(), angle),-1,1);
      driveSubsystem.setMotors(leftMotorPower*PIDTurnPower, rightMotorPower*PIDTurnPower);
    }

    driveSubsystem.resetMotors();
  }

  public double getError(double setAngle, double currentAngle) {
    double error = setAngle-currentAngle;

    return error;
  }

  public boolean onHeading(double threshold, double error) {
    boolean onHeading = false;
    if (Math.abs(error)<=threshold) {
      onHeading = !onHeading;
    }

    return onHeading;
  }
}
