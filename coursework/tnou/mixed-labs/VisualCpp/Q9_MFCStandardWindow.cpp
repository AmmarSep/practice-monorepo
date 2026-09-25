#include <afxwin.h>

class CMyApp : public CWinApp {
public:
    BOOL InitInstance() {
        m_pMainWnd = new CFrameWnd();
        m_pMainWnd->Create(NULL, "MFC Standard Window");
        m_pMainWnd->ShowWindow(m_nCmdShow);
        m_pMainWnd->UpdateWindow();
        return TRUE;
    }
};

CMyApp theApp;
