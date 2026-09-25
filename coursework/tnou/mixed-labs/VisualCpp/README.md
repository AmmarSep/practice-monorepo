# TNOU MCA-P4 Lab 4 - Visual C++ Programming

## Practical Questions

1. **Q1_UserDefinedWindow.cpp** - SDK Program to Display User Defined Window
2. **Q2_DisplayMessage.cpp** - Displaying the Given Message
3. **Q3_DisplayLines.cpp** - Displaying Set of Lines
4. **Q4_ColoredRectangles.cpp** - Displaying Rectangles with Different Colours
5. **Q5_WindowControls.cpp** - Window Controls
6. **Q6_DialogBox.cpp** - Manipulation of Dialog Box
7. **Q7_ApplicationForm.cpp** - Displaying the Application Form
8. **Q8_PasswordDialog.cpp** - Checking Password using Dialog Box
9. **Q9_MFCStandardWindow.cpp** - MFC Program for Standard Window
10. **Q10_CustomIconCursor.cpp** - Program for User Defined Icon & Cursor
11. **Q11_MFCWindowControl.cpp** - MFC Program for Window Control
12. **Q12_FontDialog.cpp** - MFC Program to Create Font Dialog Box
13. **Q13_ColorDialog.cpp** - MFC Program for Colour Dialog Box
14. **Q14_DeleteFile.cpp** - MFC Program for Deleting a File using Dialog Box
15. **Q15_CopyFile.cpp** - MFC Program for Copy File Dialog Box
16. **Q16_Notepad.cpp** - MFC Program for Notepad

## How to Compile and Run

### For SDK Programs (Q1-Q8):
```bash
cl /EHsc Q1_UserDefinedWindow.cpp user32.lib gdi32.lib
Q1_UserDefinedWindow.exe
```

### For MFC Programs (Q9-Q16):
```bash
cl /EHsc /D_AFXDLL /MD Q9_MFCStandardWindow.cpp
Q9_MFCStandardWindow.exe
```

## Notes
- SDK programs use Windows API directly
- MFC programs require MFC library support
- Password for Q8 is "admin"
- All programs are minimal implementations for lab submission
