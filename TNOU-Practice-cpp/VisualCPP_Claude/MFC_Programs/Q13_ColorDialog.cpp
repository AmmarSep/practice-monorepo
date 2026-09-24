/*
 * Question 13: MFC Program for Color Dialog Box
 *
 * This program demonstrates:
 * - Using CColorDialog class
 * - Selecting colors for text and background
 * - RGB color manipulation
 * - Custom color palette
 */

#include <afxwin.h>
#include <afxdlgs.h>

// Application class
class CMyApp : public CWinApp
{
public:
    virtual BOOL InitInstance();
};

// Frame window class
class CMyFrameWnd : public CFrameWnd
{
private:
    COLORREF m_textColor;
    COLORREF m_backgroundColor;
    COLORREF m_customColors[16];
    CString m_displayText;

public:
    CMyFrameWnd();

protected:
    afx_msg void OnPaint();
    afx_msg void OnChooseTextColor();
    afx_msg void OnChooseBackgroundColor();
    afx_msg void OnResetColors();
    afx_msg void OnShowColorInfo();
    afx_msg void OnAbout();
    afx_msg void OnExit();

    DECLARE_MESSAGE_MAP()
};

// Global app object
CMyApp theApp;

// Message map
BEGIN_MESSAGE_MAP(CMyFrameWnd, CFrameWnd)
    ON_WM_PAINT()
    ON_COMMAND(1001, OnChooseTextColor)
    ON_COMMAND(1002, OnChooseBackgroundColor)
    ON_COMMAND(1003, OnResetColors)
    ON_COMMAND(1004, OnShowColorInfo)
    ON_COMMAND(1005, OnAbout)
    ON_COMMAND(1006, OnExit)
END_MESSAGE_MAP()

// App initialization
BOOL CMyApp::InitInstance()
{
    m_pMainWnd = new CMyFrameWnd();
    m_pMainWnd->ShowWindow(m_nCmdShow);
    m_pMainWnd->UpdateWindow();
    return TRUE;
}

// Constructor
CMyFrameWnd::CMyFrameWnd()
{
    // Initialize colors
    m_textColor = RGB(0, 0, 0);          // Black
    m_backgroundColor = RGB(255, 255, 255); // White

    // Initialize custom colors array
    for (int i = 0; i < 16; i++)
    {
        m_customColors[i] = RGB(255, 255, 255);
    }

    m_displayText = _T("TNOU MCA Program\n\n")
                   _T("Visual C++ Programming Lab\n")
                   _T("Question 13: Color Dialog Box\n\n")
                   _T("This application demonstrates the use of\n")
                   _T("CColorDialog class in MFC.\n\n")
                   _T("Features:\n")
                   _T("• Choose text color\n")
                   _T("• Choose background color\n")
                   _T("• Custom color palette\n")
                   _T("• RGB color values\n")
                   _T("• Color preview\n\n")
                   _T("Use the 'Color' menu to change colors!");

    Create(NULL, _T("TNOU - Color Dialog (Q13)"),
           WS_OVERLAPPEDWINDOW,
           CRect(100, 100, 900, 700));

    // Create menu
    CMenu menu;
    menu.CreateMenu();

    CMenu* pColorMenu = new CMenu();
    pColorMenu->CreatePopupMenu();
    pColorMenu->AppendMenu(MF_STRING, 1001, _T("Choose &Text Color..."));
    pColorMenu->AppendMenu(MF_STRING, 1002, _T("Choose &Background Color..."));
    pColorMenu->AppendMenu(MF_SEPARATOR);
    pColorMenu->AppendMenu(MF_STRING, 1003, _T("&Reset Colors"));
    pColorMenu->AppendMenu(MF_STRING, 1004, _T("Show Color &Info"));
    pColorMenu->AppendMenu(MF_SEPARATOR);
    pColorMenu->AppendMenu(MF_STRING, 1005, _T("&About"));
    pColorMenu->AppendMenu(MF_STRING, 1006, _T("E&xit"));

    menu.AppendMenu(MF_POPUP, (UINT_PTR)pColorMenu->m_hMenu, _T("&Color"));
    SetMenu(&menu);

    menu.Detach();
    pColorMenu->Detach();
}

// Paint handler
void CMyFrameWnd::OnPaint()
{
    CPaintDC dc(this);
    CRect rect;
    GetClientRect(&rect);

    // Fill background
    CBrush backgroundBrush(m_backgroundColor);
    CRect contentRect(50, 150, 750, 600);
    dc.FillRect(&contentRect, &backgroundBrush);

    // Draw border around content
    CPen borderPen(PS_SOLID, 3, RGB(0, 0, 255));
    CPen* pOldPen = dc.SelectObject(&borderPen);
    dc.SelectStockObject(NULL_BRUSH);
    dc.Rectangle(&contentRect);

    // Title
    CFont titleFont;
    titleFont.CreateFont(32, 0, 0, 0, FW_BOLD, FALSE, FALSE, FALSE,
                        DEFAULT_CHARSET, OUT_DEFAULT_PRECIS,
                        CLIP_DEFAULT_PRECIS, DEFAULT_QUALITY,
                        DEFAULT_PITCH | FF_DONTCARE, _T("Arial"));

    dc.SetBkMode(TRANSPARENT);
    CFont* pOldFont = dc.SelectObject(&titleFont);
    dc.SetTextColor(RGB(0, 0, 255));
    dc.TextOut(180, 20, _T("Color Dialog Box Demonstration"));

    // Instructions
    CFont normalFont;
    normalFont.CreateFont(16, 0, 0, 0, FW_NORMAL, FALSE, FALSE, FALSE,
                         DEFAULT_CHARSET, OUT_DEFAULT_PRECIS,
                         CLIP_DEFAULT_PRECIS, DEFAULT_QUALITY,
                         DEFAULT_PITCH | FF_DONTCARE, _T("Arial"));

    dc.SelectObject(&normalFont);
    dc.SetTextColor(RGB(0, 0, 0));
    dc.TextOut(50, 80, _T("Instructions: Use 'Color' menu to change text and background colors"));

    // Color information boxes
    CString colorInfo;
    colorInfo.Format(_T("Text Color: RGB(%d, %d, %d)"),
                    GetRValue(m_textColor),
                    GetGValue(m_textColor),
                    GetBValue(m_textColor));
    dc.TextOut(50, 110, colorInfo);

    // Draw text color sample
    CBrush textColorBrush(m_textColor);
    CRect textColorRect(300, 105, 350, 125);
    dc.FillRect(&textColorRect, &textColorBrush);
    dc.Rectangle(&textColorRect);

    colorInfo.Format(_T("Background Color: RGB(%d, %d, %d)"),
                    GetRValue(m_backgroundColor),
                    GetGValue(m_backgroundColor),
                    GetBValue(m_backgroundColor));
    dc.TextOut(380, 110, colorInfo);

    // Draw background color sample
    CBrush bgColorBrush(m_backgroundColor);
    CRect bgColorRect(680, 105, 730, 125);
    dc.FillRect(&bgColorRect, &bgColorBrush);
    dc.Rectangle(&bgColorRect);

    // Display text with selected colors
    CFont displayFont;
    displayFont.CreateFont(20, 0, 0, 0, FW_NORMAL, FALSE, FALSE, FALSE,
                          DEFAULT_CHARSET, OUT_DEFAULT_PRECIS,
                          CLIP_DEFAULT_PRECIS, DEFAULT_QUALITY,
                          DEFAULT_PITCH | FF_DONTCARE, _T("Arial"));

    dc.SelectObject(&displayFont);
    dc.SetTextColor(m_textColor);
    dc.SetBkColor(m_backgroundColor);

    CRect textRect(70, 170, 730, 580);
    dc.DrawText(m_displayText, &textRect, DT_LEFT | DT_WORDBREAK);

    // Restore old objects
    dc.SelectObject(pOldFont);
    dc.SelectObject(pOldPen);
}

// Choose text color handler
void CMyFrameWnd::OnChooseTextColor()
{
    CColorDialog dlg(m_textColor, CC_FULLOPEN | CC_RGBINIT, this);

    // Set custom colors
    dlg.m_cc.lpCustColors = m_customColors;

    if (dlg.DoModal() == IDOK)
    {
        m_textColor = dlg.GetColor();

        // Save custom colors
        for (int i = 0; i < 16; i++)
        {
            m_customColors[i] = dlg.m_cc.lpCustColors[i];
        }

        Invalidate();

        CString msg;
        msg.Format(_T("Text color changed successfully!\n\n")
                  _T("New Color: RGB(%d, %d, %d)\n")
                  _T("Hexadecimal: #%02X%02X%02X"),
                  GetRValue(m_textColor),
                  GetGValue(m_textColor),
                  GetBValue(m_textColor),
                  GetRValue(m_textColor),
                  GetGValue(m_textColor),
                  GetBValue(m_textColor));

        MessageBox(msg, _T("Text Color Changed"), MB_OK | MB_ICONINFORMATION);
    }
}

// Choose background color handler
void CMyFrameWnd::OnChooseBackgroundColor()
{
    CColorDialog dlg(m_backgroundColor, CC_FULLOPEN | CC_RGBINIT, this);

    // Set custom colors
    dlg.m_cc.lpCustColors = m_customColors;

    if (dlg.DoModal() == IDOK)
    {
        m_backgroundColor = dlg.GetColor();

        // Save custom colors
        for (int i = 0; i < 16; i++)
        {
            m_customColors[i] = dlg.m_cc.lpCustColors[i];
        }

        Invalidate();

        CString msg;
        msg.Format(_T("Background color changed successfully!\n\n")
                  _T("New Color: RGB(%d, %d, %d)\n")
                  _T("Hexadecimal: #%02X%02X%02X"),
                  GetRValue(m_backgroundColor),
                  GetGValue(m_backgroundColor),
                  GetBValue(m_backgroundColor),
                  GetRValue(m_backgroundColor),
                  GetGValue(m_backgroundColor),
                  GetBValue(m_backgroundColor));

        MessageBox(msg, _T("Background Color Changed"), MB_OK | MB_ICONINFORMATION);
    }
}

// Reset colors handler
void CMyFrameWnd::OnResetColors()
{
    m_textColor = RGB(0, 0, 0);
    m_backgroundColor = RGB(255, 255, 255);
    Invalidate();

    MessageBox(_T("Colors have been reset to default:\n\n")
               _T("Text: Black RGB(0, 0, 0)\n")
               _T("Background: White RGB(255, 255, 255)"),
               _T("Colors Reset"), MB_OK | MB_ICONINFORMATION);
}

// Show color info handler
void CMyFrameWnd::OnShowColorInfo()
{
    CString info;
    info.Format(_T("CURRENT COLOR INFORMATION\n")
                _T("═══════════════════════════════════\n\n")
                _T("TEXT COLOR:\n")
                _T("  RGB: (%d, %d, %d)\n")
                _T("  Hex: #%02X%02X%02X\n")
                _T("  Red: %d (0x%02X)\n")
                _T("  Green: %d (0x%02X)\n")
                _T("  Blue: %d (0x%02X)\n\n")
                _T("BACKGROUND COLOR:\n")
                _T("  RGB: (%d, %d, %d)\n")
                _T("  Hex: #%02X%02X%02X\n")
                _T("  Red: %d (0x%02X)\n")
                _T("  Green: %d (0x%02X)\n")
                _T("  Blue: %d (0x%02X)\n\n")
                _T("COLOR MODEL:\n")
                _T("  RGB (Red, Green, Blue)\n")
                _T("  Each component: 0-255 (0x00-0xFF)\n")
                _T("  Total colors: 16,777,216 (24-bit)"),
                GetRValue(m_textColor), GetGValue(m_textColor), GetBValue(m_textColor),
                GetRValue(m_textColor), GetGValue(m_textColor), GetBValue(m_textColor),
                GetRValue(m_textColor), GetRValue(m_textColor),
                GetGValue(m_textColor), GetGValue(m_textColor),
                GetBValue(m_textColor), GetBValue(m_textColor),
                GetRValue(m_backgroundColor), GetGValue(m_backgroundColor), GetBValue(m_backgroundColor),
                GetRValue(m_backgroundColor), GetGValue(m_backgroundColor), GetBValue(m_backgroundColor),
                GetRValue(m_backgroundColor), GetRValue(m_backgroundColor),
                GetGValue(m_backgroundColor), GetGValue(m_backgroundColor),
                GetBValue(m_backgroundColor), GetBValue(m_backgroundColor));

    MessageBox(info, _T("Color Information"), MB_OK | MB_ICONINFORMATION);
}

// About handler
void CMyFrameWnd::OnAbout()
{
    MessageBox(_T("Color Dialog Box Demonstration\n\n")
               _T("TNOU MCA Program\n")
               _T("Visual C++ Programming Lab\n")
               _T("Question 13: MFC Program for Color Dialog Box\n\n")
               _T("This application demonstrates:\n")
               _T("• CColorDialog class usage\n")
               _T("• RGB color model\n")
               _T("• Custom color palette\n")
               _T("• Dynamic color changes\n")
               _T("• Color information display\n\n")
               _T("Features:\n")
               _T("• Basic color palette (48 colors)\n")
               _T("• Custom color creation\n")
               _T("• RGB value adjustment\n")
               _T("• Live color preview\n")
               _T("• Hex and decimal color values"),
               _T("About Color Dialog"),
               MB_OK | MB_ICONINFORMATION);
}

// Exit handler
void CMyFrameWnd::OnExit()
{
    if (MessageBox(_T("Exit the application?"), _T("Confirm"),
                   MB_YESNO | MB_ICONQUESTION) == IDYES)
    {
        PostQuitMessage(0);
    }
}

/*
 * Compilation Instructions:
 * -------------------------
 * cl /EHsc /D_AFXDLL /MD Q13_ColorDialog.cpp /link /SUBSYSTEM:WINDOWS
 *
 * Expected Output:
 * ----------------
 * MFC window with color dialog functionality:
 * - Menu options for text and background color selection
 * - Color dialog with:
 *   • Basic colors (48 predefined colors)
 *   • Custom colors (16 user-defined colors)
 *   • Color picker for custom colors
 *   • RGB value adjustments
 *   • HSL (Hue, Saturation, Luminance) controls
 *   • Live color preview
 * - Display text with selected colors
 * - Color information display (RGB and Hex)
 * - Color sample boxes
 * - Reset colors option
 *
 * Features Demonstrated:
 * ----------------------
 * 1. CColorDialog class usage
 * 2. COLORREF type manipulation
 * 3. RGB macro and color manipulation
 * 4. GetRValue, GetGValue, GetBValue functions
 * 5. Custom color palette (CC_FULLOPEN flag)
 * 6. Color initialization (CC_RGBINIT flag)
 * 7. Text and background color application
 * 8. CBrush for filling areas with colors
 * 9. Dynamic color changes
 * 10. Color information formatting
 *
 * Dialog Features:
 * ----------------
 * - Basic Colors: 48 predefined colors
 * - Custom Colors: 16 slots for user colors
 * - Define Custom Colors: Advanced color picker
 *   • Hue/Saturation selector
 *   • Luminance slider
 *   • RGB value input boxes
 *   • Color preview
 *
 * Usage:
 * ------
 * 1. Run the application
 * 2. Select "Color -> Choose Text Color" to change text color
 * 3. Select "Color -> Choose Background Color" for background
 * 4. In color dialog:
 *    - Click a basic color for quick selection
 *    - Click "Define Custom Colors" for advanced options
 *    - Adjust RGB values or use color picker
 *    - Save custom colors for later use
 * 5. Click OK to apply colors
 * 6. Use "Show Color Info" to see detailed RGB values
 * 7. Use "Reset Colors" to restore defaults
 *
 * Color Dialog Flags:
 * -------------------
 * CC_FULLOPEN: Opens dialog with custom colors visible
 * CC_RGBINIT: Uses rgbResult as initial color
 * CC_PREVENTFULLOPEN: Disables custom colors
 * CC_SHOWHELP: Shows help button
 * CC_SOLIDCOLOR: Shows only solid colors
 */
