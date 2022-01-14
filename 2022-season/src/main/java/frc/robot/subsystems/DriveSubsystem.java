

package frc.robot.subsystems;

import java.util.ArrayList;
import java.util.List;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private Timer timer;

  private final PigeonIMU pigeonIMU = new PigeonIMU(Constants.BOT_IMU);
  
  private final TalonSRX leftMotor1 = new TalonSRX(Constants.MOTORS.LEFT_MOTOR_1.ordinal()); 
  private final TalonSRX rightMotor1 = new TalonSRX(Constants.MOTORS.RIGHT_MOTOR_1.ordinal());
  private final VictorSPX leftMotor2 = new VictorSPX(Constants.MOTORS.LEFT_MOTOR_2.ordinal());
  private final VictorSPX rightMotor2 = new VictorSPX(Constants.MOTORS.RIGHT_MOTOR_2.ordinal()); 

  private final Encoder leftEncoder = new Encoder(0,1);
  private final Encoder rightEncoder = new Encoder(2,3);

  private final double RPM = 0;
  private final double WHEEL_RADIUS = 0;
  private final double TICKS_PER_REV = 0;

  private final double kP = 0; //tunable value
  private final double kI = 0; //tunable value
  private final double kD = 0; //tunable value

  private final double kPTurn = 0; //tunable value
  private final double kITurn = 0; //tunable value
  private final double kDTurn = 0; //tunable value

  private double turnDir = 0;
  private double setAngle = 0;
  

  double integral, prev_error, desired_angle = 0;


  private final PIDController pidController = new PIDController(kP, kI, kD);

  private final PIDController pidTurnController = new PIDController(kPTurn, kITurn, kDTurn);

  

  //gotta calculate ticksPerInch in the future.

  float ticksPerInch = 106; //NOT THE ACTUAL TPI 

  //ticksPerInch = TICKS_PER_REV / 6.28*WHEEL_RADIUS

  
  List<TalonSRX> talonMotorList = new ArrayList<TalonSRX>();
  List<VictorSPX> victorMotorList = new ArrayList<VictorSPX>();


  public DriveSubsystem() {
    leftMotor2.follow(leftMotor1);
    rightMotor2.follow(rightMotor1);

    talonMotorList.add(rightMotor1);talonMotorList.add(leftMotor1);
    victorMotorList.add(rightMotor2);victorMotorList.add(leftMotor2);

  }

  public void initialize() {
    for (VictorSPX victor : victorMotorList) {
      //pass      
    }
    for (TalonSRX talon : talonMotorList) {
      talon.configPeakCurrentLimit(Constants.CURRENT_LIMIT,0);
      talon.configPeakCurrentDuration(Constants.CURRENT_LIMIT_DURATION, 0);
      talon.configContinuousCurrentLimit(Constants.CONTINUOUS_CURRENT_LIMIT,0);
      talon.enableCurrentLimit(true);
      talon.configOpenloopRamp(Constants.VOLTAGE_RAMP_RATE);
    }
  }

  public void resetEncoders() {
    leftEncoder.reset();
    rightEncoder.reset();
  }

  public ArrayList<Integer> returnEncoderReadings() {
    ArrayList<Integer> encoderReadings = new ArrayList<>();

    encoderReadings.add(leftEncoder.get(),rightEncoder.get());

    return encoderReadings;
  }

  public double returnPIDError(double output, double setInput) {
    double pidError = pidController.calculate(output, setInput);

    //pidController.getPositionError();
    //this is very barebones, there will be more math stuff here I guess
    return pidError; 
  }

  public ArrayList<Double> returnDistanceTravelledInches(ArrayList<Integer> encoderReadings) {
    ArrayList<Double> distanceTravelled = new ArrayList<>();

    for (Integer encoderReading : encoderReadings) {
      distanceTravelled.add(Double.valueOf(encoderReading/ticksPerInch));
    }

    return distanceTravelled;
  }

  public void setMotors(double leftPower, double rightPower) {
    leftMotor1.set(TalonSRXControlMode.PercentOutput, leftPower);
    rightMotor1.set(TalonSRXControlMode.PercentOutput,rightPower);
  }

  public void setPIDForTurn(double min_tolerance, double max_tolerance) {
    pidController.enableContinuousInput(-180, 180);
    pidController.setTolerance(min_tolerance,max_tolerance);
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

  public void turnRobot(double angle) {

    while (!onHeading(2, getError(angle, returnBotHeading()))) { //consider adding a threshold tolerance level
      double PIDTurnPower = MathUtil.clamp(returnPIDError(returnBotHeading(), angle),-1,1);
      if (angle < 0) {
        PIDTurnPower *= -1;
      }
      setMotors(PIDTurnPower, -PIDTurnPower);
    }
    resetMotors();
  }

  public void setTurnPID(double angle) {
    pidTurnController.setSetpoint(angle);

    turnDir = 1;

    if(returnBotHeading() > 0) {
      turnDir = -1;
    }

    setAngle = angle;
  }

  public void turnRobotWithPID() {
    setMotors((pidTurnController.calculate(returnBotHeading(), setAngle) * -1 * turnDir),
      (pidTurnController.calculate(returnBotHeading(), setAngle) * turnDir));
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


  public void setDesiredAngle(double setpoint) {
    this.desired_angle = setpoint;
  }

  public void maintainAngle() {
    double error = desired_angle - returnBotHeading();
    this.integral += (error*=0.2);
    double derivative = (error-this.prev_error);
    
  }

  public void gyroDrive(double motorPower, double targetAngle, double tolerance) {
    if (returnBotHeading() < targetAngle-tolerance) {
      setMotors(motorPower, motorPower/2);
    }
    else if (returnBotHeading() > targetAngle+tolerance) {
      setMotors(motorPower/2, motorPower);
    }
    else {
      setMotors(motorPower, motorPower);
    }
  }

  public void encoderDrive(double inputValue, double motorPower, String driveMethod) {
    switch (driveMethod) {
      case "ticks":
        if (returnEncoderReadings().get(0)<inputValue) {
          //keep driving
          setMotors(motorPower, motorPower);
        }
        else {
          resetMotors();
        }
      case "inches":
        if (returnDistanceTravelledInches(returnEncoderReadings()).get(0)<inputValue) {
          //keep driving inches version
          setMotors(motorPower, motorPower);
        }
        else {
          resetMotors();
        }
    }
  }

  public void basicDrive(double leftPower, double rightPower, double duration) {
    setMotors(leftPower, rightPower);

    boolean isFinished = false;

    timer.start();

    while (!isFinished) {
      if (timer.advanceIfElapsed(duration)) {
        resetMotors();
        isFinished = !isFinished;
      }
    }
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

