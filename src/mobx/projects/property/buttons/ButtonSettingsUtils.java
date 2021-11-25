/*
* Copyright (C) 2018 The Pixel Experience Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package mobx.projects.property.buttons;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import com.android.internal.util.custom.NavbarUtils;

import static com.android.internal.util.custom.hwkeys.DeviceKeysConstants.*;

public class ButtonSettingsUtils {
    public static int getDeviceKeys(Context context) {
        return context.getResources().getInteger(
                com.android.internal.R.integer.config_deviceHardwareKeys);
    }

    public static int getDeviceWakeKeys(Context context) {
        return context.getResources().getInteger(
                com.android.internal.R.integer.config_deviceHardwareWakeKeys);
    }

    /* returns whether the device has power key or not. */
    public static boolean hasPowerKey() {
        return KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_POWER);
    }

    /* returns whether the device has home key or not. */
    public static boolean hasHomeKey(Context context) {
        return (getDeviceKeys(context) & KEY_MASK_HOME) != 0;
    }

    /* returns whether the device has back key or not. */
    public static boolean hasBackKey(Context context) {
        return (getDeviceKeys(context) & KEY_MASK_BACK) != 0;
    }

    /* returns whether the device has menu key or not. */
    public static boolean hasMenuKey(Context context) {
        return (getDeviceKeys(context) & KEY_MASK_MENU) != 0;
    }

    /* returns whether the device has assist key or not. */
    public static boolean hasAssistKey(Context context) {
        return (getDeviceKeys(context) & KEY_MASK_ASSIST) != 0;
    }

    /* returns whether the device has app switch key or not. */
    public static boolean hasAppSwitchKey(Context context) {
        return (getDeviceKeys(context) & KEY_MASK_APP_SWITCH) != 0;
    }

    /* returns whether the device has camera key or not. */
    public static boolean hasCameraKey(Context context) {
        return (getDeviceKeys(context) & KEY_MASK_CAMERA) != 0;
    }

    /* returns whether the device has volume rocker or not. */
    public static boolean hasVolumeKeys(Context context) {
        return (getDeviceKeys(context) & KEY_MASK_VOLUME) != 0;
    }

    /* returns whether the device can be waken using the home key or not. */
    public static boolean canWakeUsingHomeKey(Context context) {
        return (getDeviceWakeKeys(context) & KEY_MASK_HOME) != 0;
    }

    /* returns whether the device can be waken using the back key or not. */
    public static boolean canWakeUsingBackKey(Context context) {
        return (getDeviceWakeKeys(context) & KEY_MASK_BACK) != 0;
    }

    /* returns whether the device can be waken using the menu key or not. */
    public static boolean canWakeUsingMenuKey(Context context) {
        return (getDeviceWakeKeys(context) & KEY_MASK_MENU) != 0;
    }

    /* returns whether the device can be waken using the assist key or not. */
    public static boolean canWakeUsingAssistKey(Context context) {
        return (getDeviceWakeKeys(context) & KEY_MASK_ASSIST) != 0;
    }

    /* returns whether the device can be waken using the app switch key or not. */
    public static boolean canWakeUsingAppSwitchKey(Context context) {
        return (getDeviceWakeKeys(context) & KEY_MASK_APP_SWITCH) != 0;
    }

    /* returns whether the device can be waken using the camera key or not. */
    public static boolean canWakeUsingCameraKey(Context context) {
        return (getDeviceWakeKeys(context) & KEY_MASK_CAMERA) != 0;
    }

    /* returns whether the device can be waken using the volume rocker or not. */
    public static boolean canWakeUsingVolumeKeys(Context context) {
        return (getDeviceWakeKeys(context) & KEY_MASK_VOLUME) != 0;
    }

    /* returns whether the device supports button backlight adjusment or not. */
    public static boolean hasButtonBacklightSupport(Context context) {
        final boolean buttonBrightnessControlSupported = context.getResources().getInteger(
                com.android.internal.R.integer
                        .config_deviceSupportsButtonBrightnessControl) != 0;

        // All hardware keys besides volume and camera can possibly have a backlight
        return buttonBrightnessControlSupported
                && (hasHomeKey(context) || hasBackKey(context) || hasMenuKey(context)
                || hasAssistKey(context) || hasAppSwitchKey(context));
    }

    /* returns whether the device supports keyboard backlight adjusment or not. */
    public static boolean hasKeyboardBacklightSupport(Context context) {
        return context.getResources().getInteger(com.android.internal.R.integer
                .config_deviceSupportsKeyboardBrightnessControl) != 0;
    }

    public static boolean deviceSupportsFlashLight(Context context) {
        CameraManager cameraManager = (CameraManager) context.getSystemService(
                Context.CAMERA_SERVICE);
        try {
            String[] ids = cameraManager.getCameraIdList();
            for (String id : ids) {
                CameraCharacteristics c = cameraManager.getCameraCharacteristics(id);
                Boolean flashAvailable = c.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
                Integer lensFacing = c.get(CameraCharacteristics.LENS_FACING);
                if (flashAvailable != null
                        && flashAvailable
                        && lensFacing != null
                        && lensFacing == CameraCharacteristics.LENS_FACING_BACK) {
                    return true;
                }
            }
        } catch (CameraAccessException | AssertionError e) {
            // Ignore
        }
        return false;
    }

}
