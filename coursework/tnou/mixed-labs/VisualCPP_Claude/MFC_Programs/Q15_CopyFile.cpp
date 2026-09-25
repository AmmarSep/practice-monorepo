/*
 * Question 15: MFC Program for Copy File Dialog Box
 *
 * This program demonstrates:
 * - Using CFileDialog for source and destination selection
 * - Copying files using CopyFile API
 * - Progress indication
 * - File operation error handling
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
    CString m_sourcePath;
    CString m_destPath;
    CStatic* m_pStatusBar;
    CEdit* m_pSourceEdit;
    CEdit* m_pDestEdit;

public:
    CMyFrameWnd();
    ~CMyFrameWnd();

protected:
    afx_msg int OnCreate(LPCREATESTRUCT lpCreateStruct);
    afx_msg void OnSelectSource();
    afx_msg void OnSelectDestination();
    afx_msg void OnCopyFile();
    afx_msg void OnClearAll();
    afx_msg void OnShowInfo();
    afx_msg void OnAbout();
    afx_msg void OnExit();
    void UpdateStatus(const CString& message);

    DECLARE_MESSAGE_MAP()
};

// Global app object
CMyApp theApp;

// Message map
BEGIN_MESSAGE_MAP(CMyFrameWnd, CFrameWnd)
    ON_WM_CREATE()
    ON_COMMAND(1001, OnSelectSource)
    ON_COMMAND(1002, OnSelectDestination)
    ON_COMMAND(1003, OnCopyFile)
    ON_COMMAND(1004, OnClearAll)
    ON_COMMAND(1005, OnShowInfo)
    ON_COMMAND(1006, OnAbout)
    ON_COMMAND(1007, OnExit)
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
    m_sourcePath = _T("");
    m_destPath = _T("");
    m_pStatusBar = NULL;
    m_pSourceEdit = NULL;
    m_pDestEdit = NULL;

    Create(NULL, _T("TNOU - Copy File Dialog (Q15)"),
           WS_OVERLAPPEDWINDOW,
           CRect(100, 100, 850, 650));

    // Create menu
    CMenu menu;
    menu.CreateMenu();

    CMenu* pFileMenu = new CMenu();
    pFileMenu->CreatePopupMenu();
    pFileMenu->AppendMenu(MF_STRING, 1001, _T("Select &Source File..."));
    pFileMenu->AppendMenu(MF_STRING, 1002, _T("Select &Destination..."));
    pFileMenu->AppendMenu(MF_SEPARATOR);
    pFileMenu->AppendMenu(MF_STRING, 1003, _T("&Copy File"));
    pFileMenu->AppendMenu(MF_STRING, 1004, _T("C&lear All"));
    pFileMenu->AppendMenu(MF_SEPARATOR);
    pFileMenu->AppendMenu(MF_STRING, 1005, _T("Show &Info"));
    pFileMenu->AppendMenu(MF_STRING, 1006, _T("&About"));
    pFileMenu->AppendMenu(MF_STRING, 1007, _T("E&xit"));

    menu.AppendMenu(MF_POPUP, (UINT_PTR)pFileMenu->m_hMenu, _T("&File"));
    SetMenu(&menu);

    menu.Detach();
    pFileMenu->Detach();
}

// Destructor
CMyFrameWnd::~CMyFrameWnd()
{
    delete m_pStatusBar;
    delete m_pSourceEdit;
    delete m_pDestEdit;
}

// OnCreate handler
int CMyFrameWnd::OnCreate(LPCREATESTRUCT lpCreateStruct)
{
    if (CFrameWnd::OnCreate(lpCreateStruct) == -1)
        return -1;

    // Title
    CStatic* pTitle = new CStatic();
    pTitle->Create(_T("FILE COPY UTILITY"),
                  WS_CHILD | WS_VISIBLE | SS_CENTER,
                  CRect(50, 10, 730, 45), this);

    CFont titleFont;
    titleFont.CreateFont(32, 0, 0, 0, FW_BOLD, FALSE, FALSE, FALSE,
                        DEFAULT_CHARSET, OUT_DEFAULT_PRECIS,
                        CLIP_DEFAULT_PRECIS, DEFAULT_QUALITY,
                        DEFAULT_PITCH | FF_DONTCARE, _T("Arial"));
    pTitle->SetFont(&titleFont);

    // Instructions
    CStatic* pInstructions = new CStatic();
    pInstructions->Create(
        _T("Instructions:\n")
        _T("1. Select the source file (file to copy from)\n")
        _T("2. Select the destination (where to copy the file)\n")
        _T("3. Click 'Copy File' to perform the copy operation\n")
        _T("4. The destination can be a new filename or existing location"),
        WS_CHILD | WS_VISIBLE | SS_LEFT,
        CRect(50, 65, 730, 155), this);

    // Source file section
    CStatic* pSourceLabel = new CStatic();
    pSourceLabel->Create(_T("Source File:"),
                        WS_CHILD | WS_VISIBLE | SS_LEFT,
                        CRect(50, 175, 150, 195), this);

    m_pSourceEdit = new CEdit();
    m_pSourceEdit->Create(WS_CHILD | WS_VISIBLE | WS_BORDER | ES_LEFT |
                         ES_AUTOHSCROLL | ES_READONLY,
                         CRect(50, 200, 630, 225), this, 2001);

    CButton* pBtnSource = new CButton();
    pBtnSource->Create(_T("Browse..."),
                      WS_CHILD | WS_VISIBLE | BS_PUSHBUTTON,
                      CRect(650, 198, 750, 228), this, 1001);

    // Destination file section
    CStatic* pDestLabel = new CStatic();
    pDestLabel->Create(_T("Destination:"),
                      WS_CHILD | WS_VISIBLE | SS_LEFT,
                      CRect(50, 245, 150, 265), this);

    m_pDestEdit = new CEdit();
    m_pDestEdit->Create(WS_CHILD | WS_VISIBLE | WS_BORDER | ES_LEFT |
                       ES_AUTOHSCROLL,
                       CRect(50, 270, 630, 295), this, 2002);

    CButton* pBtnDest = new CButton();
    pBtnDest->Create(_T("Browse..."),
                    WS_CHILD | WS_VISIBLE | BS_PUSHBUTTON,
                    CRect(650, 268, 750, 298), this, 1002);

    // File information section
    CStatic* pInfoLabel = new CStatic();
    pInfoLabel->Create(_T("File Information:"),
                      WS_CHILD | WS_VISIBLE | SS_LEFT,
                      CRect(50, 320, 200, 340), this);

    CEdit* pInfoBox = new CEdit();
    pInfoBox->Create(WS_CHILD | WS_VISIBLE | WS_BORDER | ES_MULTILINE |
                    ES_AUTOVSCROLL | ES_READONLY | WS_VSCROLL,
                    CRect(50, 345, 750, 465), this, 2003);
    pInfoBox->SetWindowText(_T("No files selected yet.\n\n")
                           _T("Please select source and destination files."));

    // Action buttons
    CButton* pBtnCopy = new CButton();
    pBtnCopy->Create(_T("COPY FILE"),
                    WS_CHILD | WS_VISIBLE | BS_DEFPUSHBUTTON,
                    CRect(50, 485, 220, 525), this, 1003);

    CFont buttonFont;
    buttonFont.CreateFont(16, 0, 0, 0, FW_BOLD, FALSE, FALSE, FALSE,
                         DEFAULT_CHARSET, OUT_DEFAULT_PRECIS,
                         CLIP_DEFAULT_PRECIS, DEFAULT_QUALITY,
                         DEFAULT_PITCH | FF_DONTCARE, _T("Arial"));
    pBtnCopy->SetFont(&buttonFont);

    CButton* pBtnClear = new CButton();
    pBtnClear->Create(_T("Clear All"),
                     WS_CHILD | WS_VISIBLE | BS_PUSHBUTTON,
                     CRect(240, 485, 400, 525), this, 1004);

    CButton* pBtnInfo = new CButton();
    pBtnInfo->Create(_T("Show Info"),
                    WS_CHILD | WS_VISIBLE | BS_PUSHBUTTON,
                    CRect(420, 485, 580, 525), this, 1005);

    // Status bar
    m_pStatusBar = new CStatic();
    m_pStatusBar->Create(_T("Status: Ready. Please select source and destination files."),
                        WS_CHILD | WS_VISIBLE | SS_LEFT | WS_BORDER,
                        CRect(10, 545, 780, 575), this, 2004);

    UpdateStatus(_T("Ready. Please select source and destination files."));

    return 0;
}

// Update status bar
void CMyFrameWnd::UpdateStatus(const CString& message)
{
    CString status;
    status.Format(_T("Status: %s"), message);
    if (m_pStatusBar)
        m_pStatusBar->SetWindowText(status);
}

// Select source file handler
void CMyFrameWnd::OnSelectSource()
{
    CFileDialog dlg(TRUE,
                   NULL,
                   NULL,
                   OFN_HIDEREADONLY | OFN_FILEMUSTEXIST,
                   _T("All Files (*.*)|*.*|")
                   _T("Text Files (*.txt)|*.txt|")
                   _T("Document Files (*.doc;*.docx)|*.doc;*.docx|")
                   _T("Image Files (*.jpg;*.png;*.bmp)|*.jpg;*.png;*.bmp|")
                   _T("PDF Files (*.pdf)|*.pdf|")
                   _T("||"),
                   this);

    dlg.m_ofn.lpstrTitle = _T("Select Source File to Copy");

    if (dlg.DoModal() == IDOK)
    {
        m_sourcePath = dlg.GetPathName();
        m_pSourceEdit->SetWindowText(m_sourcePath);

        // Auto-suggest destination path
        if (m_destPath.IsEmpty())
        {
            CString fileName = dlg.GetFileName();
            CString fileTitle = dlg.GetFileTitle();
            CString ext = dlg.GetFileExt();

            CString suggestedDest;
            suggestedDest.Format(_T("%s_copy.%s"), fileTitle, ext);
            m_pDestEdit->SetWindowText(suggestedDest);
        }

        UpdateStatus(_T("Source file selected. Now select destination."));

        // Update info box
        WIN32_FILE_ATTRIBUTE_DATA fileInfo;
        if (GetFileAttributesEx(m_sourcePath, GetFileExInfoStandard, &fileInfo))
        {
            LARGE_INTEGER fileSize;
            fileSize.LowPart = fileInfo.nFileSizeLow;
            fileSize.HighPart = fileInfo.nFileSizeHigh;

            CString info;
            info.Format(_T("Source File Information:\n\n")
                       _T("Path: %s\n")
                       _T("Size: %I64d bytes (%.2f KB)\n")
                       _T("Type: %s\n\n")
                       _T("Ready to copy."),
                       m_sourcePath,
                       fileSize.QuadPart,
                       fileSize.QuadPart / 1024.0,
                       dlg.GetFileExt());

            CWnd* pInfoBox = GetDlgItem(2003);
            if (pInfoBox)
                pInfoBox->SetWindowText(info);
        }
    }
}

// Select destination handler
void CMyFrameWnd::OnSelectDestination()
{
    CString defaultName = _T("");

    // Get suggested filename from edit box
    m_pDestEdit->GetWindowText(defaultName);

    CFileDialog dlg(FALSE,  // FALSE for Save As dialog
                   NULL,
                   defaultName,
                   OFN_HIDEREADONLY | OFN_OVERWRITEPROMPT,
                   _T("All Files (*.*)|*.*|")
                   _T("Text Files (*.txt)|*.txt|")
                   _T("Document Files (*.doc;*.docx)|*.doc;*.docx|")
                   _T("Image Files (*.jpg;*.png;*.bmp)|*.jpg;*.png;*.bmp|")
                   _T("PDF Files (*.pdf)|*.pdf|")
                   _T("||"),
                   this);

    dlg.m_ofn.lpstrTitle = _T("Select Destination for Copy");

    if (dlg.DoModal() == IDOK)
    {
        m_destPath = dlg.GetPathName();
        m_pDestEdit->SetWindowText(m_destPath);
        UpdateStatus(_T("Destination selected. Ready to copy."));
    }
}

// Copy file handler
void CMyFrameWnd::OnCopyFile()
{
    // Validate source
    if (m_sourcePath.IsEmpty())
    {
        MessageBox(_T("Please select a source file first!"),
                  _T("Error"), MB_OK | MB_ICONWARNING);
        UpdateStatus(_T("Error: No source file selected."));
        return;
    }

    // Get destination
    m_pDestEdit->GetWindowText(m_destPath);

    if (m_destPath.IsEmpty())
    {
        MessageBox(_T("Please specify a destination file!"),
                  _T("Error"), MB_OK | MB_ICONWARNING);
        UpdateStatus(_T("Error: No destination specified."));
        return;
    }

    // Check if source exists
    if (GetFileAttributes(m_sourcePath) == INVALID_FILE_ATTRIBUTES)
    {
        MessageBox(_T("Source file does not exist!"),
                  _T("Error"), MB_OK | MB_ICONERROR);
        UpdateStatus(_T("Error: Source file not found."));
        return;
    }

    // Check if destination already exists
    if (GetFileAttributes(m_destPath) != INVALID_FILE_ATTRIBUTES)
    {
        CString msg;
        msg.Format(_T("Destination file already exists:\n\n%s\n\n")
                  _T("Do you want to overwrite it?"),
                  m_destPath);

        if (MessageBox(msg, _T("Confirm Overwrite"),
                      MB_YESNO | MB_ICONQUESTION) != IDYES)
        {
            UpdateStatus(_T("Copy cancelled by user."));
            return;
        }
    }

    // Perform the copy
    UpdateStatus(_T("Copying file..."));

    if (CopyFile(m_sourcePath, m_destPath, FALSE))
    {
        CString successMsg;
        successMsg.Format(_T("File copied successfully!\n\n")
                         _T("From: %s\n\n")
                         _T("To: %s"),
                         m_sourcePath, m_destPath);

        MessageBox(successMsg, _T("Success"), MB_OK | MB_ICONINFORMATION);
        UpdateStatus(_T("File copied successfully."));

        // Update info box
        CString info;
        info.Format(_T("Copy Operation Completed!\n\n")
                   _T("Source: %s\n\n")
                   _T("Destination: %s\n\n")
                   _T("Operation: SUCCESS"),
                   m_sourcePath, m_destPath);

        CWnd* pInfoBox = GetDlgItem(2003);
        if (pInfoBox)
            pInfoBox->SetWindowText(info);
    }
    else
    {
        DWORD errorCode = GetLastError();
        CString errorMsg;
        errorMsg.Format(_T("Failed to copy file!\n\n")
                       _T("Error Code: %d\n\n")
                       _T("Possible reasons:\n")
                       _T("• Insufficient disk space\n")
                       _T("• Destination is write-protected\n")
                       _T("• Source file is in use\n")
                       _T("• Invalid destination path"),
                       errorCode);

        MessageBox(errorMsg, _T("Copy Failed"), MB_OK | MB_ICONERROR);
        UpdateStatus(_T("Error: File copy failed."));
    }
}

// Clear all handler
void CMyFrameWnd::OnClearAll()
{
    m_sourcePath = _T("");
    m_destPath = _T("");
    m_pSourceEdit->SetWindowText(_T(""));
    m_pDestEdit->SetWindowText(_T(""));

    CWnd* pInfoBox = GetDlgItem(2003);
    if (pInfoBox)
        pInfoBox->SetWindowText(_T("All fields cleared.\n\n")
                               _T("Ready for new copy operation."));

    UpdateStatus(_T("All fields cleared."));
}

// Show info handler
void CMyFrameWnd::OnShowInfo()
{
    CString info;
    info.Format(_T("FILE COPY UTILITY INFORMATION\n")
                _T("══════════════════════════════════\n\n")
                _T("Source File: %s\n")
                _T("Destination: %s\n\n")
                _T("Operations Available:\n")
                _T("• Copy single files\n")
                _T("• Overwrite confirmation\n")
                _T("• File size display\n")
                _T("• Error handling\n\n")
                _T("Status: %s"),
                m_sourcePath.IsEmpty() ? _T("(not selected)") : m_sourcePath,
                m_destPath.IsEmpty() ? _T("(not specified)") : m_destPath,
                (m_sourcePath.IsEmpty() || m_destPath.IsEmpty()) ?
                _T("Waiting for file selection") : _T("Ready to copy"));

    MessageBox(info, _T("Copy Utility Info"), MB_OK | MB_ICONINFORMATION);
}

// About handler
void CMyFrameWnd::OnAbout()
{
    MessageBox(_T("File Copy Utility\n\n")
               _T("TNOU MCA Program\n")
               _T("Visual C++ Programming Lab\n")
               _T("Question 15: MFC Program for Copy File Dialog Box\n\n")
               _T("Features:\n")
               _T("• Source file selection (Open dialog)\n")
               _T("• Destination selection (Save As dialog)\n")
               _T("• CopyFile API usage\n")
               _T("• File size display\n")
               _T("• Overwrite protection\n")
               _T("• Error handling\n")
               _T("• Progress indication\n")
               _T("• Status updates\n\n")
               _T("Demonstrates:\n")
               _T("CFileDialog with Open and Save modes\n")
               _T("File I/O operations\n")
               _T("Windows file system APIs"),
               _T("About File Copy Utility"),
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
 * cl /EHsc /D_AFXDLL /MD Q15_CopyFile.cpp /link /SUBSYSTEM:WINDOWS
 *
 * Expected Output:
 * ----------------
 * MFC window with file copy functionality:
 * - Source file selection (Open dialog)
 * - Destination selection (Save As dialog)
 * - File path display (editable)
 * - Copy button
 * - File information display
 * - Status bar
 * - Progress and error messages
 *
 * Features Demonstrated:
 * ----------------------
 * 1. CFileDialog for opening (TRUE parameter)
 * 2. CFileDialog for saving (FALSE parameter)
 * 3. CopyFile() Windows API
 * 4. GetFileAttributes() and GetFileAttributesEx()
 * 5. File existence checking
 * 6. Overwrite confirmation (OFN_OVERWRITEPROMPT)
 * 7. File size calculation
 * 8. Error handling with GetLastError()
 * 9. Dialog title customization
 * 10. Multiple file filters
 *
 * Dialog Modes:
 * -------------
 * Open Dialog (source):
 *   - OFN_FILEMUSTEXIST: File must exist
 *   - OFN_HIDEREADONLY: Hide read-only checkbox
 *
 * Save Dialog (destination):
 *   - OFN_OVERWRITEPROMPT: Confirm overwrite
 *   - OFN_HIDEREADONLY: Hide read-only checkbox
 *
 * Usage:
 * ------
 * 1. Click "Browse..." next to Source File
 * 2. Select file to copy
 * 3. Click "Browse..." next to Destination (or type path)
 * 4. Specify destination filename
 * 5. Click "COPY FILE" button
 * 6. Confirm overwrite if needed
 * 7. File will be copied
 *
 * Safety Features:
 * ----------------
 * - Source existence validation
 * - Destination overwrite confirmation
 * - Error messages for failures
 * - File size display
 * - Status updates
 */
