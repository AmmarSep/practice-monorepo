/*
 * Question 12: MFC Program to Create Font Dialog Box
 *
 * This program demonstrates:
 * - Using CFontDialog class
 * - Selecting fonts dynamically
 * - Applying font properties to text
 * - Getting font attributes
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
    CFont m_font;
    LOGFONT m_lf;
    CString m_displayText;
    COLORREF m_textColor;

public:
    CMyFrameWnd();

protected:
    afx_msg void OnPaint();
    afx_msg void OnChooseFont();
    afx_msg void OnResetFont();
    afx_msg void OnShowFontInfo();
    afx_msg void OnExit();

    DECLARE_MESSAGE_MAP()
};

// Global app object
CMyApp theApp;

// Message map
BEGIN_MESSAGE_MAP(CMyFrameWnd, CFrameWnd)
    ON_WM_PAINT()
    ON_COMMAND(1001, OnChooseFont)
    ON_COMMAND(1002, OnResetFont)
    ON_COMMAND(1003, OnShowFontInfo)
    ON_COMMAND(1004, OnExit)
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
    // Initialize default font
    memset(&m_lf, 0, sizeof(LOGFONT));
    m_lf.lfHeight = -24;
    m_lf.lfWeight = FW_NORMAL;
    m_lf.lfCharSet = DEFAULT_CHARSET;
    _tcscpy_s(m_lf.lfFaceName, _T("Arial"));
    m_font.CreateFontIndirect(&m_lf);

    m_displayText = _T("TNOU MCA Program - Visual C++ Lab\n\n")
                   _T("This is sample text to demonstrate font changes.\n")
                   _T("Click 'Choose Font' to select a different font.\n\n")
                   _T("You can change:\n")
                   _T("• Font family (Arial, Times New Roman, etc.)\n")
                   _T("• Font size\n")
                   _T("• Font style (Bold, Italic, Underline)\n")
                   _T("• Text color");

    m_textColor = RGB(0, 0, 0);

    Create(NULL, _T("TNOU - Font Dialog (Q12)"),
           WS_OVERLAPPEDWINDOW,
           CRect(100, 100, 900, 700));

    // Create menu
    CMenu menu;
    menu.CreateMenu();

    CMenu* pFileMenu = new CMenu();
    pFileMenu->CreatePopupMenu();
    pFileMenu->AppendMenu(MF_STRING, 1001, _T("&Choose Font..."));
    pFileMenu->AppendMenu(MF_STRING, 1002, _T("&Reset Font"));
    pFileMenu->AppendMenu(MF_STRING, 1003, _T("Show Font &Info"));
    pFileMenu->AppendMenu(MF_SEPARATOR);
    pFileMenu->AppendMenu(MF_STRING, 1004, _T("E&xit"));

    menu.AppendMenu(MF_POPUP, (UINT_PTR)pFileMenu->m_hMenu, _T("&Font"));
    SetMenu(&menu);

    menu.Detach();
    pFileMenu->Detach();
}

// Paint handler
void CMyFrameWnd::OnPaint()
{
    CPaintDC dc(this);
    CRect rect;
    GetClientRect(&rect);

    // Title section
    CFont titleFont;
    titleFont.CreateFont(32, 0, 0, 0, FW_BOLD, FALSE, FALSE, FALSE,
                        DEFAULT_CHARSET, OUT_DEFAULT_PRECIS,
                        CLIP_DEFAULT_PRECIS, DEFAULT_QUALITY,
                        DEFAULT_PITCH | FF_DONTCARE, _T("Arial"));

    dc.SetBkMode(TRANSPARENT);
    CFont* pOldFont = dc.SelectObject(&titleFont);
    dc.SetTextColor(RGB(0, 0, 255));
    dc.TextOut(150, 20, _T("Font Dialog Box Demonstration"));

    // Instructions
    CFont normalFont;
    normalFont.CreateFont(18, 0, 0, 0, FW_NORMAL, FALSE, FALSE, FALSE,
                         DEFAULT_CHARSET, OUT_DEFAULT_PRECIS,
                         CLIP_DEFAULT_PRECIS, DEFAULT_QUALITY,
                         DEFAULT_PITCH | FF_DONTCARE, _T("Arial"));

    dc.SelectObject(&normalFont);
    dc.SetTextColor(RGB(0, 0, 0));
    dc.TextOut(50, 80, _T("Instructions: Use 'Font -> Choose Font' menu to open the font dialog"));
    dc.TextOut(50, 110, _T("The text below will change according to your font selection:"));

    // Draw separator line
    CPen pen(PS_SOLID, 2, RGB(0, 0, 255));
    CPen* pOldPen = dc.SelectObject(&pen);
    dc.MoveTo(50, 140);
    dc.LineTo(750, 140);

    // Display text with selected font
    dc.SelectObject(&m_font);
    dc.SetTextColor(m_textColor);

    CRect textRect(50, 160, 750, 600);
    dc.DrawText(m_displayText, &textRect, DT_LEFT | DT_WORDBREAK);

    // Restore old objects
    dc.SelectObject(pOldFont);
    dc.SelectObject(pOldPen);
}

// Choose font handler
void CMyFrameWnd::OnChooseFont()
{
    // Create font dialog with current font
    CFontDialog dlg(&m_lf, CF_EFFECTS | CF_SCREENFONTS, NULL, this);

    // Set initial color
    dlg.m_cf.rgbColors = m_textColor;

    // Show dialog
    if (dlg.DoModal() == IDOK)
    {
        // Get selected font attributes
        dlg.GetCurrentFont(&m_lf);
        m_textColor = dlg.GetColor();

        // Delete old font and create new one
        m_font.DeleteObject();
        m_font.CreateFontIndirect(&m_lf);

        // Refresh window
        Invalidate();

        // Show confirmation
        CString msg;
        msg.Format(_T("Font changed successfully!\n\n")
                  _T("Font Name: %s\n")
                  _T("Size: %d\n")
                  _T("Weight: %s\n")
                  _T("Italic: %s\n")
                  _T("Underline: %s"),
                  m_lf.lfFaceName,
                  -MulDiv(m_lf.lfHeight, 72, GetDeviceCaps(GetDC()->m_hDC, LOGPIXELSY)),
                  (m_lf.lfWeight >= FW_BOLD) ? _T("Bold") : _T("Normal"),
                  m_lf.lfItalic ? _T("Yes") : _T("No"),
                  m_lf.lfUnderline ? _T("Yes") : _T("No"));

        MessageBox(msg, _T("Font Changed"), MB_OK | MB_ICONINFORMATION);
    }
}

// Reset font handler
void CMyFrameWnd::OnResetFont()
{
    // Reset to default font
    m_font.DeleteObject();

    memset(&m_lf, 0, sizeof(LOGFONT));
    m_lf.lfHeight = -24;
    m_lf.lfWeight = FW_NORMAL;
    m_lf.lfCharSet = DEFAULT_CHARSET;
    _tcscpy_s(m_lf.lfFaceName, _T("Arial"));

    m_font.CreateFontIndirect(&m_lf);
    m_textColor = RGB(0, 0, 0);

    Invalidate();
    MessageBox(_T("Font has been reset to default (Arial, 24pt, Normal, Black)"),
               _T("Font Reset"), MB_OK | MB_ICONINFORMATION);
}

// Show font info handler
void CMyFrameWnd::OnShowFontInfo()
{
    CString info;
    info.Format(_T("CURRENT FONT INFORMATION\n")
                _T("═══════════════════════════════════\n\n")
                _T("Font Name: %s\n")
                _T("Height: %d\n")
                _T("Point Size: %d\n")
                _T("Weight: %d (%s)\n")
                _T("Italic: %s\n")
                _T("Underline: %s\n")
                _T("Strikeout: %s\n")
                _T("CharSet: %d\n")
                _T("Orientation: %d\n")
                _T("Escapement: %d\n")
                _T("Quality: %d\n")
                _T("Pitch and Family: %d\n\n")
                _T("Text Color: RGB(%d, %d, %d)"),
                m_lf.lfFaceName,
                m_lf.lfHeight,
                -MulDiv(m_lf.lfHeight, 72, GetDeviceCaps(GetDC()->m_hDC, LOGPIXELSY)),
                m_lf.lfWeight,
                (m_lf.lfWeight >= FW_BOLD) ? _T("Bold") : _T("Normal"),
                m_lf.lfItalic ? _T("Yes") : _T("No"),
                m_lf.lfUnderline ? _T("Yes") : _T("No"),
                m_lf.lfStrikeOut ? _T("Yes") : _T("No"),
                m_lf.lfCharSet,
                m_lf.lfOrientation,
                m_lf.lfEscapement,
                m_lf.lfQuality,
                m_lf.lfPitchAndFamily,
                GetRValue(m_textColor),
                GetGValue(m_textColor),
                GetBValue(m_textColor));

    MessageBox(info, _T("Font Information"), MB_OK | MB_ICONINFORMATION);
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
 * cl /EHsc /D_AFXDLL /MD Q12_FontDialog.cpp /link /SUBSYSTEM:WINDOWS
 *
 * Expected Output:
 * ----------------
 * MFC window with font dialog functionality:
 * - Menu option to choose font
 * - Font dialog with options:
 *   • Font family selection
 *   • Font size selection
 *   • Font style (Bold, Italic, Underline, Strikeout)
 *   • Text color selection
 *   • Effects (underline, strikeout, color)
 * - Live preview of selected font
 * - Display sample text with selected font
 * - Font information display
 * - Reset to default font option
 *
 * Features Demonstrated:
 * ----------------------
 * 1. CFontDialog class usage
 * 2. LOGFONT structure manipulation
 * 3. CFont object creation and management
 * 4. Font attribute retrieval (GetCurrentFont)
 * 5. Color selection (CF_EFFECTS flag)
 * 6. Font metrics and properties
 * 7. Dynamic font changes
 * 8. Font information display
 * 9. Text rendering with custom fonts
 * 10. Font dialog flags (CF_SCREENFONTS, CF_EFFECTS)
 *
 * Dialog Options Available:
 * -------------------------
 * - Font: List of available system fonts
 * - Font Style: Regular, Italic, Bold, Bold Italic
 * - Size: Font size in points
 * - Effects: Strikeout, Underline
 * - Color: Text color selection
 * - Script: Character set selection
 * - Sample: Live preview of font
 *
 * Usage:
 * ------
 * 1. Run the application
 * 2. Click "Font -> Choose Font" menu
 * 3. Select desired font properties
 * 4. Click OK to apply changes
 * 5. Sample text will update with new font
 * 6. Use "Show Font Info" to see detailed properties
 * 7. Use "Reset Font" to restore defaults
 */
