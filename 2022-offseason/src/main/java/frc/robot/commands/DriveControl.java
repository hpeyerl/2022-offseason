// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

/** An example command that uses an example subsystem. */
public class DriveControl extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final DriveTrain m_subsystem;

  private Double rightTrigger;
  private Double leftTrigger;
  private Double leftStickX;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveControl(DriveTrain subsystem, Supplier<Double> rTrigger, Supplier<Double> lTrigger,
      Supplier<Double> lStickX) {
    m_subsystem = subsystem;
    addRequirements(subsystem);

    this.rightTrigger = rTrigger.get();
    this.leftTrigger = lTrigger.get();
    this.leftStickX = lStickX.get();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Double speed = rightTrigger - leftTrigger;
    m_subsystem.SetMotors(-speed - leftStickX, speed + leftStickX);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
