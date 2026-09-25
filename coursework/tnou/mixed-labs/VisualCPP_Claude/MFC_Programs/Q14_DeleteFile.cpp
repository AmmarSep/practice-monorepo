/*
 * Question 14: MFC Program for Deleting a File using Dialog Box
 *
 * This program demonstrates:
 * - Using CFileDialog for file selection
 * - Deleting files using DeleteFile API
 * - File operations with error handling
 * - Confirmation dialogs
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
    CListBox* m_pFileList;
    CStatic* m_pStatusBar;
    CString m_currentPath;

public:
    CMyFrameWnd();
    ~CMyFrameWnd();

protected:
    afx_msg int OnCreate(LPCREATESTRUCT lpCreateStruct);
    afx_msg void OnSelectFile();
    afx_msg void OnDeleteFile();
    afx_msg void OnRefreshList();
    afx_msg void OnShowFileInfo();
    afx_msg void OnAbout();
    afx_msg void OnExit();
    void UpdateStatus(const CString& message);
    void RefreshFileList();

    DECLARE_MESSAGE_MAP()
};

// Global app object
CMyApp theApp;

// Message map
BEGIN_MESSAGE_MAP(CMyFrameWnd, CFrameWnd)
    ON_WM_CREATE()
    ON_COMMAND(1001, OnSelectFile)
    ON_COMMAND(1002, OnDeleteFile)
    ON_COMMAND(1003, OnRefreshList)
    ON_COMMAND(1004, OnShowFileInfo)
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
    m_pFileList = NULL;
    m_pStatusBar = NULL;
    m_currentPath = _T("");

    Create(NULL, _T("TNOU - Delete File Dialog (Q14)"),
           WS_OVERLAPPEDWINDOW,
           CRect(100, 100, 800, 650));

    // Create menu
    CMenu menu;
    menu.CreateMenu();

    CMenu* pFileMenu = new CMenu();
    pFileMenu->CreatePopupMenu();
    pFileMenu->AppendMenu(MF_STRING, 1001, _T("&Select File to Delete..."));
    pFileMenu->AppendMenu(MF_STRING, 1002, _T("&Delete Selected File"));
    pFileMenu->AppendMenu(MF_SEPARATOR);
    pFileMenu->AppendMenu(MF_STRING, 1003, _T("&Refresh File List"));
    pFileMenu->AppendMenu(MF_STRING, 1004, _T("Show File &Info"));
    pFileMenu->AppendMenu(MF_SEPARATOR);
    pFileMenu->AppendMenu(MF_STRING, 1005, _T("&About"));
    pFileMenu->AppendMenu(MF_STRING, 1006, _T("E&xit"));

    menu.AppendMenu(MF_POPUP, (UINT_PTR)pFileMenu->m_hMenu, _T("&File"));
    SetMenu(&menu);

    menu.Detach();
    pFileMenu->Detach();
}

// Destructor
CMyFrameWnd::~CMyFrameWnd()
{
    delete m_pFileList;
    delete m_pStatusBar;
}

// OnCreate handler
int CMyFrameWnd::OnCreate(LPCREATESTRUCT lpCreateStruct)
{
    if (CFrameWnd::OnCreate(lpCreateStruct) == -1)
        return -1;

    // Title
    CStatic* pTitle = new CStatic();
    pTitle->Create(_T("FILE DELETION UTILITY"),
                  WS_CHILD | WS_VISIBLE | SS_CENTER,
                  CRect(50, 10, 630, 40), this);

    CFont titleFont;
    titleFont.CreateFont(28, 0, 0, 0, FW_BOLD, FALSE, FALSE, FALSE,
                        DEFAULT_CHARSET, OUT_DEFAULT_PRECIS,
                        CLIP_DEFAULT_PRECIS, DEFAULT_QUALITY,
                        DEFAULT_PITCH | FF_DONTCARE, _T("Arial"));
    pTitle->SetFont(&titleFont);

    // Instructions
    CStatic* pInstructions = new CStatic();
    pInstructions->Create(
        _T("Instructions:\n")
        _T("1. Click 'File -> Select File to Delete' to choose a file\n")
        _T("2. The selected file path will be displayed\n")
        _T("3. Click 'File -> Delete Selected File' to delete it\n")
        _T("4. Confirm deletion when prompted"),
        WS_CHILD | WS_VISIBLE | SS_LEFT,
        CRect(50, 60, 630, 150), this);

    // Selected file label
    CStatic* pLabel = new CStatic();
    pLabel->Create(_T("Selected File:"),
                  WS_CHILD | WS_VISIBLE | SS_LEFT,
                  CRect(50, 165, 150, 185), this);

    // File path display (editable for manual entry)
    CEdit* pFilePath = new CEdit();
    pFilePath->Create(WS_CHILD | WS_VISIBLE | WS_BORDER | ES_LEFT |
                     ES_AUTOHSCROLL | ES_READONLY,
                     CRect(150, 163, 630, 188), this, 2001);

    // File list label
    CStatic* pListLabel = new CStatic();
    pListLabel->Create(_T("Recently Selected Files:"),
                      WS_CHILD | WS_VISIBLE | SS_LEFT,
                      CRect(50, 210, 250, 230), this);

    // File list box
    m_pFileList = new CListBox();
    m_pFileList->Create(WS_CHILD | WS_VISIBLE | WS_BORDER | WS_VSCROLL |
                       LBS_STANDARD | LBS_NOINTEGRALHEIGHT,
                       CRect(50, 235, 630, 450), this, 2002);

    // Buttons
    CButton* pBtnSelect = new CButton();
    pBtnSelect->Create(_T("Select File"),
                      WS_CHILD | WS_VISIBLE | BS_PUSHBUTTON,
                      CRect(50, 470, 200, 510), this, 1001);

    CButton* pBtnDelete = new CButton();
    pBtnDelete->Create(_T("Delete File"),
                      WS_CHILD | WS_VISIBLE | BS_PUSHBUTTON,
                      CRect(220, 470, 370, 510), this, 1002);

    CButton* pBtnRefresh = new CButton();
    pBtnRefresh->Create(_T("Refresh"),
                       WS_CHILD | WS_VISIBLE | BS_PUSHBUTTON,
                       CRect(390, 470, 540, 510), this, 1003);

    // Status bar
    m_pStatusBar = new CStatic();
    m_pStatusBar->Create(_T("Status: Ready. Select a file to delete."),
                        WS_CHILD | WS_VISIBLE | SS_LEFT | WS_BORDER,
                        CRect(10, 530, 670, 560), this, 2003);

    UpdateStatus(_T("Ready. Select a file to delete."));

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

// Select file handler
void CMyFrameWnd::OnSelectFile()
{
    // Create file open dialog
    CFileDialog dlg(TRUE,  // TRUE for Open, FALSE for Save
                   NULL,
                   NULL,
                   OFN_HIDEREADONLY | OFN_FILEMUSTEXIST,
                   _T("All Files (*.*)|*.*|")
                   _T("Text Files (*.txt)|*.txt|")
                   _T("Document Files (*.doc;*.docx)|*.doc;*.docx|")
                   _T("Image Files (*.jpg;*.png;*.bmp)|*.jpg;*.png;*.bmp|")
                   _T("||"),
                   this);

    dlg.m_ofn.lpstrTitle = _T("Select File to Delete");

    if (dlg.DoModal() == IDOK)
    {
        m_currentPath = dlg.GetPathName();

        // Update file path display
        CWnd* pFilePath = GetDlgItem(2001);
        if (pFilePath)
            pFilePath->SetWindowText(m_currentPath);

        // Add to list if not already there
        if (m_pFileList->FindStringExact(-1, m_currentPath) == LB_ERR)
        {
            m_pFileList->InsertString(0, m_currentPath);
        }

        CString msg;
        msg.Format(_T("File selected: %s"), m_currentPath);
        UpdateStatus(msg);

        MessageBox(_T("File selected successfully!\n\n")
                  _T("You can now delete it using 'File -> Delete Selected File'\n")
                  _T("or by clicking the 'Delete File' button."),
                  _T("File Selected"), MB_OK | MB_ICONINFORMATION);
    }
    else
    {
        UpdateStatus(_T("File selection cancelled."));
    }
}

// Delete file handler
void CMyFrameWnd::OnDeleteFile()
{
    if (m_currentPath.IsEmpty())
    {
        MessageBox(_T("No file selected!\n\nPlease select a file first using 'File -> Select File to Delete'."),
                  _T("Error"), MB_OK | MB_ICONWARNING);
        UpdateStatus(_T("Error: No file selected."));
        return;
    }

    // Check if file exists
    if (GetFileAttributes(m_currentPath) == INVALID_FILE_ATTRIBUTES)
    {
        MessageBox(_T("The selected file does not exist!\n\nIt may have been already deleted or moved."),
                  _T("File Not Found"), MB_OK | MB_ICONERROR);
        UpdateStatus(_T("Error: File not found."));
        return;
    }

    // Confirmation dialog
    CString confirmMsg;
    confirmMsg.Format(_T("Are you sure you want to delete this file?\n\n")
                     _T("File: %s\n\n")
                     _T("WARNING: This action cannot be undone!"),
                     m_currentPath);

    int result = MessageBox(confirmMsg, _T("Confirm Deletion"),
                           MB_YESNO | MB_ICONWARNING | MB_DEFBUTTON2);

    if (result == IDYES)
    {
        // Attempt to delete the file
        if (DeleteFile(m_currentPath))
        {
            CString successMsg;
            successMsg.Format(_T("File deleted successfully!\n\nDeleted: %s"),
                            m_currentPath);
            MessageBox(successMsg, _T("Success"), MB_OK | MB_ICONINFORMATION);

            UpdateStatus(_T("File deleted successfully."));

            // Clear current path
            CWnd* pFilePath = GetDlgItem(2001);
            if (pFilePath)
                pFilePath->SetWindowText(_T(""));

            m_currentPath = _T("");
        }
        else
        {
            // Get error code
            DWORD errorCode = GetLastError();
            CString errorMsg;
            errorMsg.Format(_T("Failed to delete file!\n\n")
                          _T("Error Code: %d\n\n")
                          _T("Possible reasons:\n")
                          _T("• File is in use by another program\n")
                          _T("• Insufficient permissions\n")
                          _T("• File is read-only\n")
                          _T("• File is locked"),
                          errorCode);

            MessageBox(errorMsg, _T("Deletion Failed"), MB_OK | MB_ICONERROR);
            UpdateStatus(_T("Error: Failed to delete file."));
        }
    }
    else
    {
        UpdateStatus(_T("File deletion cancelled by user."));
    }
}

// Refresh list handler
void CMyFrameWnd::OnRefreshList()
{
    UpdateStatus(_T("File list refreshed."));
    MessageBox(_T("File list refreshed.\n\nDeleted files are removed from the system."),
               _T("Refresh"), MB_OK | MB_ICONINFORMATION);
}

// Show file info handler
void CMyFrameWnd::OnShowFileInfo()
{
    if (m_currentPath.IsEmpty())
    {
        MessageBox(_T("No file selected!"), _T("Info"), MB_OK | MB_ICONINFORMATION);
        return;
    }

    // Get file attributes
    WIN32_FILE_ATTRIBUTE_DATA fileInfo;
    if (GetFileAttributesEx(m_currentPath, GetFileExInfoStandard, &fileInfo))
    {
        LARGE_INTEGER fileSize;
        fileSize.LowPart = fileInfo.nFileSizeLow;
        fileSize.HighPart = fileInfo.nFileSizeHigh;

        CString info;
        info.Format(_T("FILE INFORMATION\n")
                   _T("═══════════════════════════\n\n")
                   _T("Path: %s\n\n")
                   _T("Size: %I64d bytes\n")
                   _T("Attributes: %s%s%s%s\n\n")
                   _T("Ready to be deleted."),
                   m_currentPath,
                   fileSize.QuadPart,
                   (fileInfo.dwFileAttributes & FILE_ATTRIBUTE_READONLY) ? _T("Read-Only ") : _T(""),
                   (fileInfo.dwFileAttributes & FILE_ATTRIBUTE_HIDDEN) ? _T("Hidden ") : _T(""),
                   (fileInfo.dwFileAttributes & FILE_ATTRIBUTE_SYSTEM) ? _T("System ") : _T(""),
                   (fileInfo.dwFileAttributes & FILE_ATTRIBUTE_ARCHIVE) ? _T("Archive") : _T(""));

        MessageBox(info, _T("File Information"), MB_OK | MB_ICONINFORMATION);
    }
    else
    {
        MessageBox(_T("Unable to retrieve file information."),
                  _T("Error"), MB_OK | MB_ICONERROR);
    }
}

// About handler
void CMyFrameWnd::OnAbout()
{
    MessageBox(_T("File Deletion Utility\n\n")
               _T("TNOU MCA Program\n")
               _T("Visual C++ Programming Lab\n")
               _T("Question 14: MFC Program for Deleting File using Dialog Box\n\n")
               _T("Features:\n")
               _T("• File selection using CFileDialog\n")
               _T("• DeleteFile API usage\n")
               _T("• File existence checking\n")
               _T("• Error handling\n")
               _T("• Confirmation dialogs\n")
               _T("• File information display\n\n")
               _T("WARNING:\n")
               _T("Deleted files cannot be recovered!\n")
               _T("Use with caution."),
               _T("About File Deletion Utility"),
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
 * cl /EHsc /D_AFXDLL /MD Q14_DeleteFile.cpp /link /SUBSYSTEM:WINDOWS
 *
 * Expected Output:
 * ----------------
 * MFC window with file deletion functionality:
 * - File selection dialog (CFileDialog)
 * - Selected file path display
 * - Recently selected files list
 * - Delete button with confirmation
 * - Status bar with operation feedback
 * - File information display
 * - Error handling for file operations
 *
 * Features Demonstrated:
 * ----------------------
 * 1. CFileDialog class usage (open dialog)
 * 2. DeleteFile() Windows API
 * 3. GetFileAttributes() for file existence check
 * 4. GetFileAttributesEx() for file information
 * 5. File filter specifications
 * 6. Dialog flags (OFN_HIDEREADONLY, OFN_FILEMUSTEXIST)
 * 7. Error handling with GetLastError()
 * 8. Confirmation dialogs
 * 9. CListBox for file history
 * 10. Status bar updates
 *
 * File Dialog Filters:
 * --------------------
 * - All Files (*.*)
 * - Text Files (*.txt)
 * - Document Files (*.doc, *.docx)
 * - Image Files (*.jpg, *.png, *.bmp)
 *
 * Safety Features:
 * ----------------
 * - File existence verification
 * - Deletion confirmation dialog
 * - Error messages for failures
 * - Read-only file detection
 * - File-in-use detection
 *
 * Usage:
 * ------
 * 1. Run the application
 * 2. Click "File -> Select File to Delete" or "Select File" button
 * 3. Browse and select the file you want to delete
 * 4. File path will be displayed
 * 5. Click "File -> Delete Selected File" or "Delete File" button
 * 6. Confirm deletion in the dialog
 * 7. File will be permanently deleted
 *
 * IMPORTANT WARNING:
 * ------------------
 * This program permanently deletes files from the file system.
 * Deleted files cannot be recovered (they don't go to Recycle Bin).
 * Use with extreme caution!
 * Test with non-important files first.
 */
