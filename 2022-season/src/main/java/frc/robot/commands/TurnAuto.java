// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class TurnAuto extends CommandBase {
  private DriveSubsystem driveSubsystem;

  private double angle;
  private boolean finish = false;

  /** Creates a new TurnAuto. */
  public TurnAuto(DriveSubsystem driveSubsystem, double angle) {
    this.driveSubsystem = driveSubsystem;

    driveSubsystem.setPIDForTurn(2, 5);
    // Use addRequirements() here to declare subsystem dependencies.

    this.angle = angle;

    addRequirements(driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    driveSubsystem.resetPigeon();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveSubsystem.resetPigeon();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finish;
  }

  private class DriveThread extends Thread {
    public DriveThread()
    {
        this.setName("DriveThread");
    }

    // called when tread.start is called. thread stays in loop to do what it does until exit is
    // signaled by main code calling thread.interrupt.
    @Override
    public void run()
    {
        try
        {
            //stuff here I guess
            if (!driveSubsystem.onHeading(2, driveSubsystem.getError(angle, driveSubsystem.returnBotHeading()))) { //consider adding a threshold tolerance level
              double PIDTurnPower = MathUtil.clamp(driveSubsystem.returnPIDError(driveSubsystem.returnBotHeading(), angle),-1,1);
              if (angle < 0) {
                PIDTurnPower *= -1;
              }
              driveSubsystem.setMotors(PIDTurnPower, -PIDTurnPower);
            }
            else {
              driveSubsystem.resetMotors();
              finish=true;
            }
        }
        catch (Exception e) {}
    }
}
}
