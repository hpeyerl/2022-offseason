// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class EncoderAuto extends CommandBase {
  /** Creates a new EncoderAuto. */
  private DriveSubsystem driveSubsystem;

  private double motorPower;
  private double inputValue;
  private String driveMethod;
  private boolean finish = false;

  public EncoderAuto(DriveSubsystem driveSubsystem, String driveMethod,double inputValue, double motorPower) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.driveMethod = driveMethod;
    this.inputValue = inputValue;
    this.motorPower = motorPower;

    addRequirements(driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    driveSubsystem.resetEncoders();
    driveSubsystem.setMotors(motorPower, motorPower);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    switch (driveMethod) {
      case "ticks":
        if (driveSubsystem.returnEncoderReadings().get(0)<inputValue) {
          //keep driving
          //telemetry stuff here
        }
        else {
          driveSubsystem.resetMotors();
          finish = true;
        }
      case "inches":
        if (driveSubsystem.returnDistanceTravelledInches(driveSubsystem.returnEncoderReadings()).get(0)<inputValue) {
          //keep driving inches version
        }
        else {
          driveSubsystem.resetMotors();
          finish = true;
        }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveSubsystem.resetEncoders();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finish;
  }
}
