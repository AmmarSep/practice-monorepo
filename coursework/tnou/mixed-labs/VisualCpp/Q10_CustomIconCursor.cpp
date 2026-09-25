#include <afxwin.h>

class CMainFrame : public CFrameWnd {
public:
    CMainFrame() {
        Create(NULL, "Custom Icon & Cursor");
    }
};

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
