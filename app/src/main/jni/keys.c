//
// Created by Wifimorfi on 9/27/2018.
//

#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_apps_morfiwifi_morfi_1project_1samane_App_getNativeKey1(JNIEnv *env, jobject instance) {
 //_71_-40_-6_-6_70_-90_44_-77_55_19_43_59_-62_77_42_21_-31_-54_77_-27_16_-32_122_42_120_42_-32_108_-9_114_12_-9_36_77_-22_-14_-34_113_27_62_79_-110_123_11_-26_-60_-29_-4
    //"z8ABZI7P44AzqQHSShS09qtKpcVjx8Dk8F9WipRB"
//    return (*env)->  NewStringUTF(env, "z8ABZI7P44AzqQHSShS09qtKpcVjx8Dk8F9WipRB");
    return (*env)->  NewStringUTF(env, "first");
//    return (*env)->  NewStringUTF(env, "_71_-40_-6_-6_70_-90_44_-77_55_19_43_59_-62_77_42_21_-31_-54_77_-27_16_-32_122_42_120_42_-32_108_-9_114_12_-9_36_77_-22_-14_-34_113_27_62_79_-110_123_11_-26_-60_-29_-4");
}

JNIEXPORT jstring JNICALL
Java_com_apps_morfiwifi_morfi_1project_1samane_App_getNativeKey2(JNIEnv *env, jobject instance) {
//_-98_-11_91_-109_-123_18_46_-72_30_57_90_-92_-52_37_89_6_-76_118_-81_-70_-63_41_-45_57_61_-42_119_108_-89_116_-91_108_-66_-6_-126_-121_-123_90_118_-108_67_-65_-103_79_-72_-93_79_-54
//"fmbYAhCnAHlXvk9p8B2UBPK8vaeyTm1ZOZ1zPxq9"
 return (*env)->NewStringUTF(env, "fmbYAhCnAHlXvk9p8B2UBPK8vaeyTm1ZOZ1zPxq9");
// return (*env)->NewStringUTF(env, "_-98_-11_91_-109_-123_18_46_-72_30_57_90_-92_-52_37_89_6_-76_118_-81_-70_-63_41_-45_57_61_-42_119_108_-89_116_-91_108_-66_-6_-126_-121_-123_90_118_-108_67_-65_-103_79_-72_-93_79_-54");
}

JNIEXPORT jstring JNICALL
Java_com_apps_morfiwifi_morfi_1project_1samane_App_getserver(JNIEnv *env, jobject instance) {
    //_56_-99_-126_64_40_-103_15_1_37_49_108_-118_12_-53_-18_-29_107_64_-77_-73_-6_-90_115_-75_5_-49_-16_-16_-48_125_28_98
    //"https://parseapi.back4app.com/"
//    return (*env)->NewStringUTF(env,"https://parseapi.back4app.com/" );
//    return (*env)->NewStringUTF(env,"http://192.168.1.107:1337/parse/" );
//    return (*env)->NewStringUTF(env,"http://192.168.43.38:1337/parse/" );
//    return (*env)->NewStringUTF(env,"http://10.0.2.2:1337/parse/" );
    return (*env)->NewStringUTF(env,"http://5.57.36.104:1337/parse/" );
//    return (*env)->NewStringUTF(env,"_56_-99_-126_64_40_-103_15_1_37_49_108_-118_12_-53_-18_-29_107_64_-77_-73_-6_-90_115_-75_5_-49_-16_-16_-48_125_28_98" );
}

JNIEXPORT jstring JNICALL
Java_com_apps_morfiwifi_morfi_1project_1samane_util_Security_getraw(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "SOME ENQ KEY AS INPUT");
}