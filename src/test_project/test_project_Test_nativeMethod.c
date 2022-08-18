#include "D:\Project\Java\Test_project\src\test_project\test_project_Test_nativeMethod.h"
#include <stdio.h>

JNIEXPORT void JNICALL Java_test_1project_Test_1nativeMethod_helloworld(JNIEnv * env, jobject o) {
    printf("Hello, Native!!");  
}
