#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_heaven7_android_hook_app_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
