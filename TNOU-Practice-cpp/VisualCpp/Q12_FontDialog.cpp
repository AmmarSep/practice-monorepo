#include <afxwin.h>
#include <afxdlgs.h>

class CMainFrame : public CFrameWnd {
    CButton btn;
public:
    CMainFrame() {
        Create(NULL, "Font Dialog Box");
        btn.Create("Choose Font", WS_VISIBLE | WS_CHILD | BS_PUSHBUTTON, CRect(100, 100, 200, 130), this, 1);
    }
    
    afx_msg void OnButton() {
        CFontDialog dlg;
        if(dlg.DoModal() == IDOK) {
            MessageBox("Font Selected!", "Info");
        }
    }
    
    DECLARE_MESSAGE_MAP()
};

BEGIN_MESSAGE_MAP(CMainFrame, CFrameWnd)
    ON_BN_CLICKED(1, OnButton)
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
