// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain;

import java.util.function.Supplier;


/** An example command that uses an example subsystem. */
public class driverControl extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final drivetrain m_subsystem;
  private double RTrigger;
  private double LTrigger;
  private double LStickX;


  //@param 
  /**
   * Creates a new ExampleCommand.
   *
   * @param 
   
    The subsystem used by this command.
   */
  //add left trigger
  public driverControl(drivetrain subsystem, 
  Supplier<Double> rightTrigger, 
  Supplier<Double> leftTrigger,  
  Supplier<Double> leftStick_X) {

    m_subsystem = subsystem;
    this.RTrigger = rightTrigger.get();
    this.LTrigger = leftTrigger.get();
    this.LStickX = leftStick_X.get();
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
