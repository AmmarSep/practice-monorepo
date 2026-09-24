#include <afxwin.h>

class CMainFrame : public CFrameWnd {
    CButton btn;
    CEdit edit;
    CStatic label;
public:
    CMainFrame() {
        Create(NULL, "MFC Window Controls");
        label.Create("Enter Text:", WS_VISIBLE | WS_CHILD, CRect(20, 20, 120, 40), this);
        edit.Create(WS_VISIBLE | WS_CHILD | WS_BORDER | ES_LEFT, CRect(20, 50, 220, 75), this, 1);
        btn.Create("Click Me", WS_VISIBLE | WS_CHILD | BS_PUSHBUTTON, CRect(20, 90, 120, 120), this, 2);
    }
    
    afx_msg void OnButton() {
        MessageBox("Button Clicked!", "Info");
    }
    
    DECLARE_MESSAGE_MAP()
};

BEGIN_MESSAGE_MAP(CMainFrame, CFrameWnd)
    ON_BN_CLICKED(2, OnButton)
END_MESSAGE_MAP()

class CMyApp : public CWinApp {
public:
    BOOL InitInstance() {
        m_pMainWnd = new CMainFrame();
        m_pMainWnd->ShowWindow(m_nCmdShow);
        m_pMainWnd->UpdateWindow();
        return TRUE;
    }
};

CMyApp theApp;
