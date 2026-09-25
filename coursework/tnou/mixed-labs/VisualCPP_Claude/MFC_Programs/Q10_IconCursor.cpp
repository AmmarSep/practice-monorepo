/*
 * Question 10: Program for User-Defined Icon & Cursor
 *
 * This program demonstrates:
 * - Creating and using custom icons
 * - Creating and using custom cursors
 * - Loading resources in MFC
 */

#include <afxwin.h>

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
    HCURSOR m_hCrossCursor;
    HCURSOR m_hHandCursor;
    HCURSOR m_hCustomCursor;
    HICON m_hCustomIcon;
    BOOL m_bUseCustomCursor;
    int m_nCursorType;

public:
    CMyFrameWnd();
    ~CMyFrameWnd();

protected:
    afx_msg void OnPaint();
    afx_msg BOOL OnSetCursor(CWnd* pWnd, UINT nHitTest, UINT message);
    afx_msg void OnLButtonDown(UINT nFlags, CPoint point);
    afx_msg void OnRButtonDown(UINT nFlags, CPoint point);
    afx_msg void OnChangeCursor();
    afx_msg void OnChangeIcon();
    afx_msg void OnExit();

    DECLARE_MESSAGE_MAP()
};

// Global app object
CMyApp theApp;

// Message map
BEGIN_MESSAGE_MAP(CMyFrameWnd, CFrameWnd)
    ON_WM_PAINT()
    ON_WM_SETCURSOR()
    ON_WM_LBUTTONDOWN()
    ON_WM_RBUTTONDOWN()
    ON_COMMAND(1001, OnChangeCursor)
    ON_COMMAND(1002, OnChangeIcon)
    ON_COMMAND(1003, OnExit)
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
    m_bUseCustomCursor = TRUE;
    m_nCursorType = 0;

    // Load standard cursors
    m_hCrossCursor = LoadCursor(NULL, IDC_CROSS);
    m_hHandCursor = LoadCursor(NULL, IDC_HAND);

    // Create a custom cursor programmatically
    // For demonstration, we'll use standard cursors
    m_hCustomCursor = LoadCursor(NULL, IDC_HELP);

    // Load custom icon (using standard icon for demonstration)
    m_hCustomIcon = LoadIcon(NULL, IDI_INFORMATION);

    // Create window with custom icon
    WNDCLASS wc;
    GetClassInfo(AfxGetInstanceHandle(), _T("MFCFrameWndClass"), &wc);
    wc.lpszClassName = _T("CustomIconCursorClass");
    wc.hIcon = m_hCustomIcon;
    wc.hCursor = m_hCrossCursor;
    AfxRegisterClass(&wc);

    Create(_T("CustomIconCursorClass"),
           _T("TNOU - Icon & Cursor Demo (Q10)"),
           WS_OVERLAPPEDWINDOW,
           CRect(100, 100, 900, 650));

    // Create menu
    CMenu menu;
    menu.CreateMenu();

    CMenu* pSubMenu = new CMenu();
    pSubMenu->CreatePopupMenu();
    pSubMenu->AppendMenu(MF_STRING, 1001, _T("Change &Cursor"));
    pSubMenu->AppendMenu(MF_STRING, 1002, _T("Change &Icon"));
    pSubMenu->AppendMenu(MF_SEPARATOR);
    pSubMenu->AppendMenu(MF_STRING, 1003, _T("E&xit"));

    menu.AppendMenu(MF_POPUP, (UINT_PTR)pSubMenu->m_hMenu, _T("&Options"));
    SetMenu(&menu);

    menu.Detach();
    pSubMenu->Detach();
}

// Destructor
CMyFrameWnd::~CMyFrameWnd()
{
    // Cleanup is automatic for standard cursors/icons
}

// Paint handler
void CMyFrameWnd::OnPaint()
{
    CPaintDC dc(this);
    CRect rect;
    GetClientRect(&rect);

    // Create fonts
    CFont titleFont, normalFont, boldFont;
    titleFont.CreateFont(32, 0, 0, 0, FW_BOLD, FALSE, FALSE, FALSE,
                        DEFAULT_CHARSET, OUT_DEFAULT_PRECIS,
                        CLIP_DEFAULT_PRECIS, DEFAULT_QUALITY,
                        DEFAULT_PITCH | FF_DONTCARE, _T("Arial"));

    normalFont.CreateFont(18, 0, 0, 0, FW_NORMAL, FALSE, FALSE, FALSE,
                         DEFAULT_CHARSET, OUT_DEFAULT_PRECIS,
                         CLIP_DEFAULT_PRECIS, DEFAULT_QUALITY,
                         DEFAULT_PITCH | FF_DONTCARE, _T("Arial"));

    boldFont.CreateFont(20, 0, 0, 0, FW_BOLD, FALSE, FALSE, FALSE,
                       DEFAULT_CHARSET, OUT_DEFAULT_PRECIS,
                       CLIP_DEFAULT_PRECIS, DEFAULT_QUALITY,
                       DEFAULT_PITCH | FF_DONTCARE, _T("Arial"));

    dc.SetBkMode(TRANSPARENT);

    // Title
    CFont* pOldFont = dc.SelectObject(&titleFont);
    dc.SetTextColor(RGB(0, 0, 255));
    dc.TextOut(150, 30, _T("User-Defined Icon & Cursor"));

    // Info section
    dc.SelectObject(&normalFont);
    dc.SetTextColor(RGB(0, 0, 0));
    dc.TextOut(50, 100, _T("TNOU MCA Program - Question 10"));

    // Draw icon demonstration
    dc.SelectObject(&boldFont);
    dc.SetTextColor(RGB(255, 0, 0));
    dc.TextOut(50, 150, _T("ICON DEMONSTRATION:"));

    dc.SelectObject(&normalFont);
    dc.SetTextColor(RGB(0, 0, 0));
    dc.TextOut(70, 190, _T("• Look at the window's title bar icon (top-left corner)"));
    dc.TextOut(70, 220, _T("• Look at the taskbar icon"));
    dc.TextOut(70, 250, _T("• Use 'Options -> Change Icon' to change the window icon"));

    // Draw some icons
    dc.TextOut(70, 290, _T("Sample Icons:"));
    DrawIcon(dc.m_hDC, 100, 320, LoadIcon(NULL, IDI_APPLICATION));
    DrawIcon(dc.m_hDC, 150, 320, LoadIcon(NULL, IDI_INFORMATION));
    DrawIcon(dc.m_hDC, 200, 320, LoadIcon(NULL, IDI_WARNING));
    DrawIcon(dc.m_hDC, 250, 320, LoadIcon(NULL, IDI_ERROR));
    DrawIcon(dc.m_hDC, 300, 320, LoadIcon(NULL, IDI_QUESTION));

    // Cursor demonstration
    dc.SelectObject(&boldFont);
    dc.SetTextColor(RGB(255, 0, 0));
    dc.TextOut(450, 150, _T("CURSOR DEMONSTRATION:"));

    dc.SelectObject(&normalFont);
    dc.SetTextColor(RGB(0, 0, 0));
    dc.TextOut(470, 190, _T("• Move your mouse over this window"));
    dc.TextOut(470, 220, _T("• Notice the cursor shape"));
    dc.TextOut(470, 250, _T("• Left-click to cycle through cursors"));
    dc.TextOut(470, 280, _T("• Right-click for cursor information"));

    // Current cursor info
    CString cursorName;
    switch (m_nCursorType)
    {
        case 0: cursorName = _T("Cross Cursor"); break;
        case 1: cursorName = _T("Hand Cursor"); break;
        case 2: cursorName = _T("Help Cursor"); break;
        default: cursorName = _T("Default Cursor");
    }

    dc.SetTextColor(RGB(0, 128, 0));
    CString msg;
    msg.Format(_T("Current Cursor: %s"), cursorName);
    dc.TextOut(470, 320, msg);

    // Instructions
    dc.SelectObject(&boldFont);
    dc.SetTextColor(RGB(0, 0, 255));
    dc.TextOut(50, 410, _T("INSTRUCTIONS:"));

    dc.SelectObject(&normalFont);
    dc.SetTextColor(RGB(0, 0, 0));
    dc.TextOut(70, 450, _T("1. Use 'Options -> Change Cursor' menu to cycle cursors"));
    dc.TextOut(70, 480, _T("2. Use 'Options -> Change Icon' menu to change window icon"));
    dc.TextOut(70, 510, _T("3. Left-click anywhere to change cursor"));
    dc.TextOut(70, 540, _T("4. Right-click for cursor details"));

    dc.SelectObject(pOldFont);
}

// Set cursor handler
BOOL CMyFrameWnd::OnSetCursor(CWnd* pWnd, UINT nHitTest, UINT message)
{
    if (nHitTest == HTCLIENT && m_bUseCustomCursor)
    {
        switch (m_nCursorType)
        {
            case 0:
                ::SetCursor(m_hCrossCursor);
                break;
            case 1:
                ::SetCursor(m_hHandCursor);
                break;
            case 2:
                ::SetCursor(m_hCustomCursor);
                break;
        }
        return TRUE;
    }

    return CFrameWnd::OnSetCursor(pWnd, nHitTest, message);
}

// Left button handler - cycle cursors
void CMyFrameWnd::OnLButtonDown(UINT nFlags, CPoint point)
{
    m_nCursorType = (m_nCursorType + 1) % 3;
    Invalidate();

    CString msg;
    switch (m_nCursorType)
    {
        case 0: msg = _T("Cursor changed to: Cross Cursor"); break;
        case 1: msg = _T("Cursor changed to: Hand Cursor"); break;
        case 2: msg = _T("Cursor changed to: Help Cursor"); break;
    }

    MessageBox(msg, _T("Cursor Changed"), MB_OK | MB_ICONINFORMATION);

    CFrameWnd::OnLButtonDown(nFlags, point);
}

// Right button handler - show cursor info
void CMyFrameWnd::OnRButtonDown(UINT nFlags, CPoint point)
{
    CString cursorInfo;
    cursorInfo.Format(_T("Current Cursor Information:\n\n")
                     _T("Cursor Type: %d\n")
                     _T("Position: (%d, %d)\n\n")
                     _T("Available Cursors:\n")
                     _T("0 - Cross Cursor (IDC_CROSS)\n")
                     _T("1 - Hand Cursor (IDC_HAND)\n")
                     _T("2 - Help Cursor (IDC_HELP)"),
                     m_nCursorType, point.x, point.y);

    MessageBox(cursorInfo, _T("Cursor Information"), MB_OK | MB_ICONINFORMATION);

    CFrameWnd::OnRButtonDown(nFlags, point);
}

// Change cursor menu handler
void CMyFrameWnd::OnChangeCursor()
{
    m_nCursorType = (m_nCursorType + 1) % 3;
    Invalidate();
}

// Change icon menu handler
void CMyFrameWnd::OnChangeIcon()
{
    static int iconType = 0;
    iconType = (iconType + 1) % 5;

    HICON hNewIcon;
    CString iconName;

    switch (iconType)
    {
        case 0:
            hNewIcon = LoadIcon(NULL, IDI_APPLICATION);
            iconName = _T("Application Icon");
            break;
        case 1:
            hNewIcon = LoadIcon(NULL, IDI_INFORMATION);
            iconName = _T("Information Icon");
            break;
        case 2:
            hNewIcon = LoadIcon(NULL, IDI_WARNING);
            iconName = _T("Warning Icon");
            break;
        case 3:
            hNewIcon = LoadIcon(NULL, IDI_ERROR);
            iconName = _T("Error Icon");
            break;
        case 4:
            hNewIcon = LoadIcon(NULL, IDI_QUESTION);
            iconName = _T("Question Icon");
            break;
        default:
            hNewIcon = LoadIcon(NULL, IDI_APPLICATION);
            iconName = _T("Default Icon");
    }

    SetIcon(hNewIcon, TRUE);  // Large icon
    SetIcon(hNewIcon, FALSE); // Small icon

    CString msg;
    msg.Format(_T("Window icon changed to:\n%s"), iconName);
    MessageBox(msg, _T("Icon Changed"), MB_OK | MB_ICONINFORMATION);
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
 * cl /EHsc /D_AFXDLL /MD Q10_IconCursor.cpp /link /SUBSYSTEM:WINDOWS
 *
 * Expected Output:
 * ----------------
 * MFC window with custom icon and cursor features:
 * - Custom window icon in title bar and taskbar
 * - Custom cursor that changes on mouse clicks
 * - Menu options to change icon and cursor
 * - Visual demonstration of different icons
 * - Interactive cursor changing
 *
 * Features Demonstrated:
 * ----------------------
 * 1. Loading and setting custom icons (SetIcon)
 * 2. Loading and setting custom cursors (::SetCursor)
 * 3. OnSetCursor message handler
 * 4. Drawing icons with DrawIcon
 * 5. Cycling through different cursors
 * 6. Changing window icon dynamically
 * 7. Standard Windows icons and cursors
 *
 * Note: In a production application, you would create custom
 * .ico and .cur files and load them as resources. This demo
 * uses standard Windows icons and cursors for simplicity.
 */
