cmake_minimum_required(VERSION 4.0)
project(TNOU_Practice)

set(CMAKE_CXX_STANDARD 14)

add_executable(TNOU_Practice
        VisualCpp/Q10_CustomIconCursor.cpp
        VisualCpp/Q11_MFCWindowControl.cpp
        VisualCpp/Q12_FontDialog.cpp
        VisualCpp/Q13_ColorDialog.cpp
        VisualCpp/Q14_DeleteFile.cpp
        VisualCpp/Q15_CopyFile.cpp
        VisualCpp/Q16_Notepad.cpp
        VisualCpp/Q1_UserDefinedWindow.cpp
        VisualCpp/Q2_DisplayMessage.cpp
        VisualCpp/Q3_DisplayLines.cpp
        VisualCpp/Q4_ColoredRectangles.cpp
        VisualCpp/Q5_WindowControls.cpp
        VisualCpp/Q6_DialogBox.cpp
        VisualCpp/Q7_ApplicationForm.cpp
        VisualCpp/Q8_PasswordDialog.cpp
        VisualCpp/Q9_MFCStandardWindow.cpp
        VisualCPP_Claude/SDK_Programs/Q1_UserDefinedWindow.cpp
        VisualCPP_Claude/SDK_Programs/Q2_DisplayMessage.cpp
        VisualCPP_Claude/SDK_Programs/Q3_DisplayLines.cpp
        VisualCPP_Claude/SDK_Programs/Q4_ColoredRectangles.cpp
        VisualCPP_Claude/SDK_Programs/Q5_WindowControls.cpp
        VisualCPP_Claude/SDK_Programs/Q6_DialogBox.cpp
        VisualCPP_Claude/SDK_Programs/Q7_ApplicationForm.cpp)
