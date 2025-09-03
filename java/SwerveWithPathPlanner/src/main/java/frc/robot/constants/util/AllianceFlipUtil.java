package frc.robot.constants.util;// Copyright (c) 2025 FRC 6328
// http://github.com/Mechanical-Advantage
//
// Use of this source code is governed by an MIT-style
// license that can be found in the LICENSE file at
// the root directory of this project.


import edu.wpi.first.math.geometry.*;
import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.constants.reef.Constants;
import frc.robot.constants.reef.Reef;

public class AllianceFlipUtil {
    public static double applyX(double x) {
        return shouldFlip() ? Reef.fieldLength - x : x;
    }

    public static double applyY(double y) {
        return shouldFlip() ? Reef.fieldWidth - y : y;
    }

    public static Translation2d apply(Translation2d translation) {
        return new Translation2d(applyX(translation.getX()), applyY(translation.getY()));
    }

    public static Rotation2d apply(Rotation2d rotation) {
        return shouldFlip() ? rotation.rotateBy(Rotation2d.kPi) : rotation;
    }

    public static Pose2d apply(Pose2d pose) {
        return shouldFlip()
                ? new Pose2d(apply(pose.getTranslation()), apply(pose.getRotation()))
                : pose;
    }

    public static Translation3d apply(Translation3d translation) {
        return new Translation3d(
                applyX(translation.getX()), applyY(translation.getY()), translation.getZ());
    }

    public static Rotation3d apply(Rotation3d rotation) {
        return shouldFlip() ? rotation.rotateBy(new Rotation3d(0.0, 0.0, Math.PI)) : rotation;
    }

    public static Pose3d apply(Pose3d pose) {
        return new Pose3d(apply(pose.getTranslation()), apply(pose.getRotation()));
    }

    public static boolean shouldFlip() {
        return !Constants.disableHAL
                && DriverStation.getAlliance().isPresent()
                && DriverStation.getAlliance().get() == DriverStation.Alliance.Red;
    }
}