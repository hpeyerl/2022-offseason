

package frc.robot.subsystems;

import java.util.ArrayList;
import java.util.List;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  private final PigeonIMU pigeonIMU = new PigeonIMU(Constants.BOT_IMU);
  
  private final TalonSRX leftMotor1 = new TalonSRX(Constants.MOTORS.LEFT_MOTOR_1.ordinal()); 
  private final TalonSRX rightMotor1 = new TalonSRX(Constants.MOTORS.RIGHT_MOTOR_1.ordinal());
  private final VictorSPX leftMotor2 = new VictorSPX(Constants.MOTORS.LEFT_MOTOR_2.ordinal());
  private final VictorSPX rightMotor2 = new VictorSPX(Constants.MOTORS.RIGHT_MOTOR_2.ordinal()); 

  private Timer timer;
  
  List<TalonSRX> motorList = new ArrayList<>();

  public DriveSubsystem() {
    leftMotor2.follow(leftMotor1);
    rightMotor2.follow(rightMotor1);

  }

  public void setMotors(double leftPower, double rightPower) {
    leftMotor1.set(TalonSRXControlMode.PercentOutput, leftPower);
    rightMotor1.set(TalonSRXControlMode.PercentOutput,rightPower);
  }

  public void resetMotors() {
    leftMotor1.set(TalonSRXControlMode.PercentOutput, 0);
    rightMotor1.set(TalonSRXControlMode.PercentOutput, 0);
  }

  public double returnBotHeading() {
    double[] yawPitchRoll = new double[3];
    pigeonIMU.getYawPitchRoll(yawPitchRoll);

    return yawPitchRoll[1];
  }

  public void resetPigeon() {
    //reset pigeon imu
  }




  @Override
  public void periodic() {
    //This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    //This method will be called once per scheduler run during simulation
  }
}

