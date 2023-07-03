// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private final CANSparkMax leftFrontMotor = new CANSparkMax(1, MotorType.kBrushless);
  private final CANSparkMax leftBackMotor = new CANSparkMax(3, MotorType.kBrushless);
  private final CANSparkMax rightFrontMotor = new CANSparkMax(4, MotorType.kBrushless);
  private final CANSparkMax rightBackMotor = new CANSparkMax(2, MotorType.kBrushless);

  private final MotorControllerGroup leftRobotDrive = new MotorControllerGroup(leftBackMotor, leftFrontMotor);
  private final MotorControllerGroup rightRobotDrive = new MotorControllerGroup(rightBackMotor, rightFrontMotor);

  private final DifferentialDrive robotDrive = new DifferentialDrive(leftRobotDrive,rightRobotDrive);

  private final CANSparkMax armMotorLeft = new CANSparkMax(6, MotorType.kBrushless);
  private final CANSparkMax armMotorRight = new CANSparkMax(7, MotorType.kBrushless);
  private final CANSparkMax climber = new CANSparkMax(8, MotorType.kBrushless);
  private final CANSparkMax garra = new CANSparkMax(9, MotorType.kBrushless);
  private final CANSparkMax coletor = new CANSparkMax(10, MotorType.kBrushless);

  private XboxController xbox = new XboxController(0);
  private XboxController xbox2 = new XboxController(1);


  RelativeEncoder climberEncoder = climber.getEncoder();
  RelativeEncoder garraEncoder = garra.getEncoder();
  RelativeEncoder mototrLeftEncoder = armMotorLeft.getEncoder();

  DigitalInput limitSwitch0 = new DigitalInput(0);


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    climberEncoder.setPosition(0);
    garraEncoder.setPosition(0);
    mototrLeftEncoder.setPosition(0);

    armMotorLeft.setIdleMode(IdleMode.kBrake);
    armMotorRight.setIdleMode(IdleMode.kBrake);
    climber.setIdleMode(IdleMode.kBrake);
    garra.setIdleMode(IdleMode.kBrake);
    coletor.setIdleMode(IdleMode.kBrake);

    leftBackMotor.setIdleMode(IdleMode.kBrake);
    leftFrontMotor.setIdleMode(IdleMode.kBrake);
    rightBackMotor.setIdleMode(IdleMode.kBrake);
    rightFrontMotor.setIdleMode(IdleMode.kBrake);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    SmartDashboard.putNumber("Encoder Climber", climberEncoder.getPosition());
    SmartDashboard.putNumber("Encoder Garra", garraEncoder.getPosition());
    SmartDashboard.putNumber("Encoder Braco", mototrLeftEncoder.getPosition());

    leftBackMotor.setIdleMode(IdleMode.kBrake);
    leftFrontMotor.setIdleMode(IdleMode.kBrake);
    rightBackMotor.setIdleMode(IdleMode.kBrake);
    rightFrontMotor.setIdleMode(IdleMode.kBrake);

    armMotorLeft.setIdleMode(IdleMode.kBrake);
    armMotorRight.setIdleMode(IdleMode.kBrake);
    climber.setIdleMode(IdleMode.kBrake);
    garra.setIdleMode(IdleMode.kBrake);
    coletor.setIdleMode(IdleMode.kBrake);

    armMotorRight.setInverted(true);
    armMotorLeft.setInverted(false);

      //double forwardSpeed = xbox.getRightX()/1.4; 
      //double turningSpeed = xbox.getLeftY()/1.4;

      robotDrive.arcadeDrive(xbox.getRightX()/1.4, xbox.getLeftY()/1.4);

    double leftY = xbox2.getLeftY()*-1;
    
    double leftTrigger = xbox2.getLeftTriggerAxis();
    double rightTrigger = xbox2.getRightTriggerAxis();

    //climber.set(leftY);

    if (climberEncoder.getPosition() <= 350 && xbox2.getLeftY() < -0.3){
      climber.set(leftY);
    } else {
     if (climberEncoder.getPosition() >= 3 && xbox2.getLeftY() > 0.3){
      climber.set(leftY);
     }
     else {
      if (xbox2.getLeftY() >=-0.2 && xbox2.getLeftY() <=0.2 || climberEncoder.getPosition() > 350 || climberEncoder.getPosition() < 3) {
        climber.set(0);
      }
     }
    }

    if (mototrLeftEncoder.getPosition() <= 70 && xbox2.getRightTriggerAxis() > 0.2){
      armMotorLeft.set(rightTrigger/5);
      armMotorRight.set(rightTrigger/5);
    }
    else {
      if (limitSwitch0.get() == false && xbox2.getLeftTriggerAxis() > 0.2){
        armMotorLeft.set(-leftTrigger/5);
        armMotorRight.set(-leftTrigger/5);
    }
      else {
        if (mototrLeftEncoder.getPosition() > 70 || xbox2.getRightTriggerAxis() < 0.2 && xbox2.getLeftTriggerAxis() < 0.2)
        armMotorLeft.set(0);
        armMotorRight.set(0);
      }
    }

    if (garraEncoder.getPosition() <= 55 && xbox2.getLeftBumper()) {
      garra.set(0.15);
    }
    else {
      if (garraEncoder.getPosition() > 0 && xbox2.getRightBumper()){
      garra.set(-0.2);
      }
      else {
        if (xbox2.getLeftBumper() == false && xbox2.getRightBumper() == false || garraEncoder.getPosition() > 55 || garraEncoder.getPosition() <= 0){
        garra.set(0);
        }
      }
    }

    if  (xbox2.getPOV() == 90){
      coletor.set(0.2);
     }
     
    coletor.set(xbox2.getRightY()*-1);
   
   if (limitSwitch0.get() == true) {
    mototrLeftEncoder.setPosition(0);
   }

   if (xbox2.getRawButton(1)) {
      coletor.set(1);
    } else {
      if (xbox2.getRawButton(2)) {
      coletor.set(-1);
      } 
      else {
        if (xbox2.getRawButton(3)) {
          coletor.set(-0.7);
      }
      else {
        coletor.set(0);  
      }
    }
   }
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
