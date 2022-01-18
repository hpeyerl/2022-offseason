// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DriveAuto extends CommandBase {
  private Timer timer;

  private DriveSubsystem driveSubsystem;

  private boolean finish = false;

  private double leftPower;
  private double rightPower;
  private double duration;  
  
  /** Creates a new DriveAuto. */
  public DriveAuto(DriveSubsystem driveSubsystem, double leftPower, double rightPower, double duration) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.driveSubsystem = driveSubsystem;

    this.leftPower = leftPower; this.rightPower = rightPower; this.duration = duration;
    
    addRequirements(driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //basicDrive(leftPower,rightPower,duration); //replace empty values with private variables in the future
    timer.start();
    driveSubsystem.setMotors(leftPower, rightPower);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (timer.advanceIfElapsed(duration)) {
      driveSubsystem.resetMotors();
      finish = true;
    }  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}



  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finish;
  }
}
