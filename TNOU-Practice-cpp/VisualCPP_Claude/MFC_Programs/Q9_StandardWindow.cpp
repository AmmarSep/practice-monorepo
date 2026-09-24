/*
 * Question 9: MFC Program for Standard Window
 *
 * This program demonstrates creating a standard MFC application window with:
 * - CWinApp derived application class
 * - CFrameWnd derived frame window class
 * - Basic MFC window structure
 */

#include <afxwin.h>

// Application class derived from CWinApp
class CMyApp : public CWinApp
{
public:
    virtual BOOL InitInstance();
};

// Frame window class derived from CFrameWnd
class CMyFrameWnd : public CFrameWnd
{
public:
    CMyFrameWnd();

protected:
    // Message handlers
    afx_msg void OnPaint();
    afx_msg void OnLButtonDown(UINT nFlags, CPoint point);
    afx_msg void OnRButtonDown(UINT nFlags, CPoint point);
    afx_msg void OnAbout();
    afx_msg void OnExit();

    DECLARE_MESSAGE_MAP()
};

// Global application object
CMyApp theApp;

// CMyApp implementation
BOOL CMyApp::InitInstance()
{
    // Create the main window
    m_pMainWnd = new CMyFrameWnd();
    m_pMainWnd->ShowWindow(m_nCmdShow);
    m_pMainWnd->UpdateWindow();

    return TRUE;
}

// Message map for CMyFrameWnd
BEGIN_MESSAGE_MAP(CMyFrameWnd, CFrameWnd)
    ON_WM_PAINT()
    ON_WM_LBUTTONDOWN()
    ON_WM_RBUTTONDOWN()
    ON_COMMAND(1001, OnAbout)
    ON_COMMAND(1002, OnExit)
END_MESSAGE_MAP()

// CMyFrameWnd constructor
CMyFrameWnd::CMyFrameWnd()
{
    // Create the window with a title
    Create(NULL,
           _T("TNOU - MFC Standard Window (Q9)"),
           WS_OVERLAPPEDWINDOW,
           CRect(100, 100, 800, 600));

    // Create a simple menu
    CMenu menu;
    menu.CreateMenu();

    CMenu* pSubMenu = new CMenu();
    pSubMenu->CreatePopupMenu();
    pSubMenu->AppendMenu(MF_STRING, 1001, _T("&About"));
    pSubMenu->AppendMenu(MF_SEPARATOR);
    pSubMenu->AppendMenu(MF_STRING, 1002, _T("E&xit"));

    menu.AppendMenu(MF_POPUP, (UINT_PTR)pSubMenu->m_hMenu, _T("&File"));
    SetMenu(&menu);

    menu.Detach();
    pSubMenu->Detach();
}

// OnPaint handler
void CMyFrameWnd::OnPaint()
{
    CPaintDC dc(this); // Device context for painting

    // Get client rectangle
    CRect rect;
    GetClientRect(&rect);

    // Set text properties
    dc.SetTextColor(RGB(0, 0, 255));
    dc.SetBkMode(TRANSPARENT);

    // Create fonts
    CFont titleFont;
    titleFont.CreateFont(32, 0, 0, 0, FW_BOLD, FALSE, FALSE, FALSE,
                        DEFAULT_CHARSET, OUT_DEFAULT_PRECIS,
                        CLIP_DEFAULT_PRECIS, DEFAULT_QUALITY,
                        DEFAULT_PITCH | FF_DONTCARE, _T("Arial"));

    CFont normalFont;
    normalFont.CreateFont(18, 0, 0, 0, FW_NORMAL, FALSE, FALSE, FALSE,
                         DEFAULT_CHARSET, OUT_DEFAULT_PRECIS,
                         CLIP_DEFAULT_PRECIS, DEFAULT_QUALITY,
                         DEFAULT_PITCH | FF_DONTCARE, _T("Arial"));

    // Draw title
    CFont* pOldFont = dc.SelectObject(&titleFont);
    dc.TextOut(150, 50, _T("MFC Standard Window Application"));

    // Draw information
    dc.SelectObject(&normalFont);
    dc.SetTextColor(RGB(0, 0, 0));

    dc.TextOut(100, 120, _T("TNOU MCA Program - Visual C++ Lab"));
    dc.TextOut(100, 150, _T("Question 9: MFC Program for Standard Window"));

    dc.TextOut(100, 200, _T("This is a standard MFC application demonstrating:"));
    dc.TextOut(120, 230, _T("• CWinApp derived application class"));
    dc.TextOut(120, 260, _T("• CFrameWnd derived frame window class"));
    dc.TextOut(120, 290, _T("• Message handling in MFC"));
    dc.TextOut(120, 320, _T("• Basic window creation and display"));

    dc.SetTextColor(RGB(255, 0, 0));
    dc.TextOut(100, 370, _T("Interactions:"));
    dc.SetTextColor(RGB(0, 0, 0));
    dc.TextOut(120, 400, _T("• Click left mouse button anywhere on the window"));
    dc.TextOut(120, 430, _T("• Click right mouse button for additional message"));
    dc.TextOut(120, 460, _T("• Use File menu for About and Exit options"));

    // Restore old font
    dc.SelectObject(pOldFont);
}

// Left button click handler
void CMyFrameWnd::OnLButtonDown(UINT nFlags, CPoint point)
{
    CString message;
    message.Format(_T("Left mouse button clicked at position:\nX = %d\nY = %d"),
                   point.x, point.y);
    MessageBox(message, _T("Mouse Click"), MB_OK | MB_ICONINFORMATION);

    CFrameWnd::OnLButtonDown(nFlags, point);
}

// Right button click handler
void CMyFrameWnd::OnRButtonDown(UINT nFlags, CPoint point)
{
    CString message;
    message.Format(_T("Right mouse button clicked!\n\nPosition: (%d, %d)\n\n")
                   _T("This demonstrates MFC message handling."),
                   point.x, point.y);
    MessageBox(message, _T("Right Click"), MB_OK | MB_ICONINFORMATION);

    CFrameWnd::OnRButtonDown(nFlags, point);
}

// About menu handler
void CMyFrameWnd::OnAbout()
{
    MessageBox(_T("MFC Standard Window Application\n\n")
               _T("TNOU MCA Program\n")
               _T("Visual C++ Programming Lab\n\n")
               _T("Question 9: MFC Program for Standard Window\n\n")
               _T("This application demonstrates the basic structure\n")
               _T("of an MFC application using CWinApp and CFrameWnd classes.\n\n")
               _T("Features:\n")
               _T("• Standard window creation\n")
               _T("• Menu handling\n")
               _T("• Mouse event handling\n")
               _T("• Message boxes\n")
               _T("• Text and graphics output"),
               _T("About MFC Standard Window"),
               MB_OK | MB_ICONINFORMATION);
}

// Exit menu handler
void CMyFrameWnd::OnExit()
{
    if (MessageBox(_T("Are you sure you want to exit?"),
                   _T("Confirm Exit"),
                   MB_YESNO | MB_ICONQUESTION) == IDYES)
    {
        PostQuitMessage(0);
    }
}

/*
 * Compilation Instructions:
 * -------------------------
 * Using Visual Studio with MFC:
 *   cl /EHsc /D_AFXDLL /MD Q9_StandardWindow.cpp /link /SUBSYSTEM:WINDOWS
 *
 * Or create a Visual Studio MFC project:
 * 1. File -> New -> Project
 * 2. Select "MFC Application"
 * 3. Choose "Single Document" or copy this code to your project
 *
 * Note: This requires MFC libraries to be installed
 *
 * Expected Output:
 * ----------------
 * A standard MFC window application with:
 * - Title: "TNOU - MFC Standard Window (Q9)"
 * - Menu bar with File menu (About, Exit)
 * - Text displaying program information
 * - Mouse click event handlers
 * - Interactive message boxes
 *
 * Features Demonstrated:
 * ----------------------
 * 1. CWinApp derived application class
 * 2. CFrameWnd derived frame window
 * 3. Message map (BEGIN_MESSAGE_MAP/END_MESSAGE_MAP)
 * 4. Paint handler (OnPaint)
 * 5. Mouse event handlers
 * 6. Menu command handlers
 * 7. CPaintDC for drawing
 * 8. CFont for text formatting
 * 9. MessageBox for user interaction
 */
