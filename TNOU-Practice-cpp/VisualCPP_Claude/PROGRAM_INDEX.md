# Visual C++ Programming - Complete Program Index

## Quick Navigation Guide for TNOU MCA-P4 Lab

---

## SDK Programs (Win32 API) - Questions 1-8

Located in: `SDK_Programs/`

### Q1: User-Defined Window
**File:** `Q1_UserDefinedWindow.cpp`
**Topics:** Window creation, WinMain, Window procedure, Message loop
**Difficulty:** ⭐ Beginner
**Key Concepts:**
- WNDCLASSEX structure
- RegisterClassEx
- CreateWindowEx
- Message pump (GetMessage, TranslateMessage, DispatchMessage)

---

### Q2: Display Message
**File:** `Q2_DisplayMessage.cpp`
**Topics:** MessageBox, TextOut, Text rendering
**Difficulty:** ⭐ Beginner
**Key Concepts:**
- MessageBox API
- CPaintDC and HDC
- TextOut function
- Button controls
- WM_COMMAND handling

---

### Q3: Display Lines
**File:** `Q3_DisplayLines.cpp`
**Topics:** GDI drawing, Lines, Pen styles
**Difficulty:** ⭐⭐ Intermediate
**Key Concepts:**
- CreatePen (PS_SOLID, PS_DASH, PS_DOT)
- MoveToEx, LineTo
- Polyline function
- Color RGB values
- Drawing patterns (grid, star, triangle)

---

### Q4: Colored Rectangles
**File:** `Q4_ColoredRectangles.cpp`
**Topics:** Rectangles, Brushes, Colors
**Difficulty:** ⭐⭐ Intermediate
**Key Concepts:**
- CreateSolidBrush
- Rectangle function
- FillRect
- CreateHatchBrush
- RGB color model
- Gradient effects

---

### Q5: Window Controls
**File:** `Q5_WindowControls.cpp`
**Topics:** Edit, Button, ListBox, ComboBox, CheckBox, RadioButton
**Difficulty:** ⭐⭐⭐ Intermediate
**Key Concepts:**
- CreateWindow for various control types
- Control IDs
- WM_COMMAND notifications
- LBN_SELCHANGE, CBN_SELCHANGE
- SendMessage for control manipulation
- Control state management

---

### Q6: Dialog Box Manipulation
**File:** `Q6_DialogBox.cpp`
**Topics:** Dialog boxes, Modal dialogs, Message boxes
**Difficulty:** ⭐⭐ Intermediate
**Key Concepts:**
- Creating dialog windows
- Modal vs Modeless dialogs
- MessageBox types (MB_OK, MB_YESNO, etc.)
- Dialog icons (MB_ICONINFORMATION, MB_ICONWARNING, etc.)
- Custom dialog creation

---

### Q7: Application Form
**File:** `Q7_ApplicationForm.cpp`
**Topics:** Form design, Input validation, Data collection
**Difficulty:** ⭐⭐⭐ Advanced
**Key Concepts:**
- Complex form layout
- Multiple input types
- Data validation
- Form submission
- Personal information collection
- Radio button groups
- Combo box population

---

### Q8: Password Checker
**File:** `Q8_PasswordChecker.cpp`
**Topics:** Authentication, Password masking, Security
**Difficulty:** ⭐⭐⭐ Advanced
**Key Concepts:**
- ES_PASSWORD style
- Login attempt limiting
- String comparison (strcmp)
- Input validation
- Security best practices
- User feedback

---

## MFC Programs - Questions 9-16

Located in: `MFC_Programs/`

### Q9: Standard Window
**File:** `Q9_StandardWindow.cpp`
**Topics:** MFC basics, CWinApp, CFrameWnd
**Difficulty:** ⭐⭐ Intermediate
**Key Concepts:**
- CWinApp derived class
- CFrameWnd derived class
- InitInstance()
- Message maps (BEGIN_MESSAGE_MAP)
- OnPaint handler
- Mouse event handlers (OnLButtonDown, OnRButtonDown)
- Menu creation

---

### Q10: Icon & Cursor
**File:** `Q10_IconCursor.cpp`
**Topics:** Custom icons, Custom cursors, Resource handling
**Difficulty:** ⭐⭐⭐ Advanced
**Key Concepts:**
- LoadIcon, LoadCursor
- SetIcon, SetCursor
- OnSetCursor handler
- HICON, HCURSOR types
- Icon/cursor types (IDI_*, IDC_*)
- Dynamic cursor changes
- DrawIcon function

---

### Q11: Window Control
**File:** `Q11_WindowControl.cpp`
**Topics:** MFC controls, CEdit, CButton, CListBox, CComboBox
**Difficulty:** ⭐⭐⭐ Advanced
**Key Concepts:**
- MFC control classes (CEdit, CButton, etc.)
- OnCreate handler
- Control creation with Create()
- Event notification handlers
- GetWindowText, SetWindowText
- AddString, GetCurSel
- Check box and radio button states

---

### Q12: Font Dialog
**File:** `Q12_FontDialog.cpp`
**Topics:** CFontDialog, Font selection, Text formatting
**Difficulty:** ⭐⭐⭐ Advanced
**Key Concepts:**
- CFontDialog class
- LOGFONT structure
- CFont object
- CreateFontIndirect
- Font attributes (size, weight, style)
- Color selection (CF_EFFECTS)
- DoModal()

---

### Q13: Color Dialog
**File:** `Q13_ColorDialog.cpp`
**Topics:** CColorDialog, Color selection, RGB values
**Difficulty:** ⭐⭐⭐ Advanced
**Key Concepts:**
- CColorDialog class
- COLORREF type
- RGB macro
- GetRValue, GetGValue, GetBValue
- Custom color palette
- CC_FULLOPEN flag
- Text and background colors

---

### Q14: Delete File
**File:** `Q14_DeleteFile.cpp`
**Topics:** File operations, CFileDialog, DeleteFile API
**Difficulty:** ⭐⭐⭐⭐ Advanced
**Key Concepts:**
- CFileDialog (Open mode)
- DeleteFile() Windows API
- GetFileAttributes
- File existence checking
- Error handling (GetLastError)
- Confirmation dialogs
- File information display
- Safety features

---

### Q15: Copy File
**File:** `Q15_CopyFile.cpp`
**Topics:** File copying, Source/Destination selection
**Difficulty:** ⭐⭐⭐⭐ Advanced
**Key Concepts:**
- CFileDialog (Open and Save modes)
- CopyFile() Windows API
- OFN_OVERWRITEPROMPT flag
- File size calculation
- Progress indication
- Source validation
- Destination path handling

---

### Q16: Notepad Application
**File:** `Q16_Notepad.cpp`
**Topics:** Complete text editor, File I/O, Menu system
**Difficulty:** ⭐⭐⭐⭐⭐ Expert
**Key Concepts:**
- Complete MFC application
- CEdit multiline control
- CFile for file operations
- CStatusBar implementation
- Menu system (File, Edit, Format, Help)
- Clipboard operations (Cut, Copy, Paste)
- Undo functionality
- Modified state tracking
- Save confirmation
- Unicode/ANSI conversion
- Title bar updates

---

## Learning Path Recommendations

### Beginner Level (Start Here)
1. Q1 - User-Defined Window
2. Q2 - Display Message
3. Q3 - Display Lines
4. Q4 - Colored Rectangles

**Estimated Time:** 1-2 weeks

### Intermediate Level
5. Q5 - Window Controls
6. Q6 - Dialog Box
9. Q9 - MFC Standard Window

**Estimated Time:** 2-3 weeks

### Advanced Level
7. Q7 - Application Form
8. Q8 - Password Checker
10. Q10 - Icon & Cursor
11. Q11 - Window Control
12. Q12 - Font Dialog
13. Q13 - Color Dialog

**Estimated Time:** 3-4 weeks

### Expert Level
14. Q14 - Delete File
15. Q15 - Copy File
16. Q16 - Notepad

**Estimated Time:** 2-3 weeks

---

## Feature Matrix

| Program | Window | Controls | Dialogs | GDI | File I/O | MFC | Difficulty |
|---------|--------|----------|---------|-----|----------|-----|------------|
| Q1      | ✓      | -        | -       | -   | -        | -   | ⭐         |
| Q2      | ✓      | ✓        | ✓       | ✓   | -        | -   | ⭐         |
| Q3      | ✓      | -        | -       | ✓   | -        | -   | ⭐⭐       |
| Q4      | ✓      | -        | -       | ✓   | -        | -   | ⭐⭐       |
| Q5      | ✓      | ✓        | -       | -   | -        | -   | ⭐⭐⭐     |
| Q6      | ✓      | ✓        | ✓       | -   | -        | -   | ⭐⭐       |
| Q7      | ✓      | ✓        | -       | -   | -        | -   | ⭐⭐⭐     |
| Q8      | ✓      | ✓        | ✓       | -   | -        | -   | ⭐⭐⭐     |
| Q9      | ✓      | -        | -       | ✓   | -        | ✓   | ⭐⭐       |
| Q10     | ✓      | -        | -       | ✓   | -        | ✓   | ⭐⭐⭐     |
| Q11     | ✓      | ✓        | -       | -   | -        | ✓   | ⭐⭐⭐     |
| Q12     | ✓      | -        | ✓       | ✓   | -        | ✓   | ⭐⭐⭐     |
| Q13     | ✓      | -        | ✓       | ✓   | -        | ✓   | ⭐⭐⭐     |
| Q14     | ✓      | ✓        | ✓       | -   | ✓        | ✓   | ⭐⭐⭐⭐   |
| Q15     | ✓      | ✓        | ✓       | -   | ✓        | ✓   | ⭐⭐⭐⭐   |
| Q16     | ✓      | ✓        | ✓       | -   | ✓        | ✓   | ⭐⭐⭐⭐⭐ |

---

## Topic Coverage

### Windows Programming Basics
- Q1, Q2, Q3, Q4

### Control Programming
- Q5, Q7, Q8, Q11

### Dialog Boxes
- Q6, Q12, Q13

### Graphics (GDI)
- Q3, Q4, Q9, Q10

### File Operations
- Q14, Q15, Q16

### MFC Framework
- Q9, Q10, Q11, Q12, Q13, Q14, Q15, Q16

---

## Study Tips

### For Each Program:
1. **Read the complete source code** - Don't just copy-paste
2. **Understand the comments** - They explain the logic
3. **Compile and run** - See it in action
4. **Modify and experiment** - Change values, add features
5. **Debug errors** - Learn from mistakes

### Practice Exercises:
1. **Combine features** - Add Q3's drawing to Q1's window
2. **Extend functionality** - Add more controls to Q5
3. **Customize appearance** - Change colors, fonts, sizes
4. **Add error handling** - Improve robustness
5. **Create variations** - Make your own versions

---

## Common Use Cases

### Need to learn Windows basics?
→ Start with Q1, Q2

### Want to understand controls?
→ Study Q5, Q11

### Interested in graphics?
→ Check Q3, Q4, Q10

### Learning dialogs?
→ Review Q6, Q12, Q13

### File operations?
→ Study Q14, Q15, Q16

### Complete applications?
→ Analyze Q7, Q8, Q16

---

## Quick Reference

### Compilation:
```bash
# SDK Programs
cl /EHsc program.cpp user32.lib gdi32.lib

# MFC Programs
cl /EHsc /D_AFXDLL /MD program.cpp /link /SUBSYSTEM:WINDOWS
```

### Running:
```bash
# After compilation
program.exe
```

---

## Additional Resources

- **COMPILATION_GUIDE.md** - Detailed compilation instructions
- **README.md** - Project overview and structure
- Individual program files - Extensive inline comments
- Windows API Documentation - MSDN
- MFC Class Library Reference - Microsoft Docs

---

**Total Programs:** 16
**SDK Programs:** 8
**MFC Programs:** 8
**Total Lines of Code:** ~5,000+
**Estimated Study Time:** 8-12 weeks

---

*Created for TNOU MCA-P4 Visual C++ Programming Lab Practice*
