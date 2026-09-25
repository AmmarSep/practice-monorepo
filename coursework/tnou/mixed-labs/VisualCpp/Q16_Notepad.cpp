#include <afxwin.h>
#include <afxdlgs.h>

class CMainFrame : public CFrameWnd {
    CEdit edit;
public:
    CMainFrame() {
        Create(NULL, "Simple Notepad", WS_OVERLAPPEDWINDOW, rectDefault, NULL, "MainMenu");
        edit.Create(WS_CHILD | WS_VISIBLE | WS_VSCROLL | ES_MULTILINE | ES_AUTOVSCROLL,
                    CRect(0, 0, 600, 400), this, 1);
        
        CMenu menu;
        menu.CreateMenu();
        menu.AppendMenu(MF_STRING, 101, "Open");
        menu.AppendMenu(MF_STRING, 102, "Save");
        menu.AppendMenu(MF_STRING, 103, "Exit");
        SetMenu(&menu);
        menu.Detach();
    }
    
    afx_msg void OnOpen() {
        CFileDialog dlg(TRUE, "txt", NULL, OFN_FILEMUSTEXIST, "Text Files (*.txt)|*.txt||");
        if(dlg.DoModal() == IDOK) {
            CStdioFile file;
            if(file.Open(dlg.GetPathName(), CFile::modeRead)) {
                CString content, line;
                while(file.ReadString(line)) {
                    content += line + "\r\n";
                }
                edit.SetWindowText(content);
                file.Close();
            }
        }
    }
    
    afx_msg void OnSave() {
        CFileDialog dlg(FALSE, "txt", "untitled.txt", OFN_OVERWRITEPROMPT, "Text Files (*.txt)|*.txt||");
        if(dlg.DoModal() == IDOK) {
            CStdioFile file;
            if(file.Open(dlg.GetPathName(), CFile::modeCreate | CFile::modeWrite)) {
                CString content;
                edit.GetWindowText(content);
                file.WriteString(content);
                file.Close();
                MessageBox("File Saved!", "Success");
            }
        }
    }
    
    afx_msg void OnExit() {
        PostQuitMessage(0);
    }
    
    DECLARE_MESSAGE_MAP()
};

BEGIN_MESSAGE_MAP(CMainFrame, CFrameWnd)
    ON_COMMAND(101, OnOpen)
    ON_COMMAND(102, OnSave)
    ON_COMMAND(103, OnExit)
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
