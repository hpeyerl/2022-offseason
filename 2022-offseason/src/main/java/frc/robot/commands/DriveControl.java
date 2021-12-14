// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain; 

/** An example command that uses an example subsystem. */
public class DriveControl extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveTrain m_subsystem;
  private Double TriggerR; 
  private Double TriggerL; 
  private Double LStickX;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveControl(DriveTrain subsystem, 
  Supplier <Double> rightTrigger,
   Supplier <Double> leftTrigger,
   Supplier <Double> leftStickX) {
    m_subsystem = subsystem;

    this.TriggerR = rightTrigger.get(); 
    this.TriggerL = leftTrigger.get();
    this.LStickX = leftStickX.get(); 
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
   }

// Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Double speed = TriggerR - TriggerL;
    m_subsystem.setMotors(-speed - LStickX, speed + LStickX);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
