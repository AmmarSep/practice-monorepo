# Visual C++ Programming - MCA-P4 (LAB 4)

This repository contains solutions for all 16 practical questions for Visual C++ Programming.

## Project Structure

```
VisualCPP_Claude/
├── SDK_Programs/          # Questions 1-8 (Win32 SDK)
├── MFC_Programs/          # Questions 9-16 (MFC)
└── Resources/             # Common resources (icons, cursors, etc.)
```

## Questions Overview

### SDK Programs (Win32 API)
1. **Q1_UserDefinedWindow** - SDK program to display user-defined window
2. **Q2_DisplayMessage** - Displaying given message
3. **Q3_DisplayLines** - Displaying set of lines
4. **Q4_ColoredRectangles** - Displaying rectangles with different colors
5. **Q5_WindowControls** - Window controls implementation
6. **Q6_DialogBox** - Manipulation of dialog box
7. **Q7_ApplicationForm** - Displaying application form
8. **Q8_PasswordChecker** - Checking password using dialog box

### MFC Programs
9. **Q9_StandardWindow** - MFC program for standard window
10. **Q10_IconCursor** - Program for user-defined icon & cursor
11. **Q11_WindowControl** - MFC program for window control
12. **Q12_FontDialog** - MFC program to create font dialog box
13. **Q13_ColorDialog** - MFC program for color dialog box
14. **Q14_DeleteFile** - MFC program for deleting file using dialog box
15. **Q15_CopyFile** - MFC program for copy file dialog box
16. **Q16_Notepad** - MFC program for Notepad application

## Compilation Instructions

### For SDK Programs (Win32 API)
```bash
cl /EHsc /W4 filename.cpp user32.lib gdi32.lib comctl32.lib
```

### For MFC Programs
```bash
cl /EHsc /W4 /D_AFXDLL /MD filename.cpp
```

## Requirements
- Visual Studio (2015 or later recommended)
- Windows SDK
- MFC libraries

## Notes
- All programs are written in C++ for Windows platform
- SDK programs use Win32 API directly
- MFC programs use Microsoft Foundation Classes
- Each program is self-contained with comments explaining the code

## Author
Created for TNOU MCA-P4 Lab Practice

## License
Educational purposes only
