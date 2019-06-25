package com.oblador.keychain;

import android.os.Build;
import android.content.Context;
import android.app.KeyguardManager;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.biometrics.BiometricManager;

public class DeviceAvailability {
    // public static boolean isFingerprintAuthAvailable(Context context) {
    //     if (android.os.Build.VERSION.SDK_INT >= 23) {
    //         FingerprintManager fingerprintManager =
    //             (FingerprintManager) context.getSystemService(Context.FINGERPRINT_SERVICE);
    //         return fingerprintManager != null && fingerprintManager.isHardwareDetected() &&
    //             fingerprintManager.hasEnrolledFingerprints();
    //     }
    //     return false;
    // }

    public static boolean isBiometricAuthEnabled(Context context) {
        if (android.os.Build.VERSION.SDK_INT >= 28) {
            BiometricManager biometricManager = (BiometricManager) context.getSystemService(Context.BIOMETRIC_SERVICE);
            return biometricManager != null && biometricManager.canAuthenticate() != BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED;
        }

        return false;

    }

    public static boolean isDeviceSecure(Context context) {
        KeyguardManager keyguardManager =
                (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        return Build.VERSION.SDK_INT >= 23 && keyguardManager != null && keyguardManager.isDeviceSecure();
    }
}
