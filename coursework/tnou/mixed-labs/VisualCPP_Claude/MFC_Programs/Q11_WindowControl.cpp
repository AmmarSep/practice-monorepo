/*
 * Question 11: MFC Program for Window Control
 *
 * This program demonstrates MFC window controls:
 * - CEdit, CButton, CListBox, CComboBox
 * - CStatic, CCheckBox, CRadioButton
 * - Event handling for controls
 */

#include <afxwin.h>
#include <afxext.h>

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
    // Control pointers
    CEdit* m_pEditSingle;
    CEdit* m_pEditMulti;
    CButton* m_pBtnAction;
    CButton* m_pBtnClear;
    CListBox* m_pListBox;
    CComboBox* m_pComboBox;
    CButton* m_pCheckBox1;
    CButton* m_pCheckBox2;
    CButton* m_pRadio1;
    CButton* m_pRadio2;
    CButton* m_pRadio3;
    CStatic* m_pStatusText;

public:
    CMyFrameWnd();
    ~CMyFrameWnd();

protected:
    afx_msg int OnCreate(LPCREATESTRUCT lpCreateStruct);
    afx_msg void OnActionButton();
    afx_msg void OnClearButton();
    afx_msg void OnListBoxSelChange();
    afx_msg void OnComboBoxSelChange();
    afx_msg void OnCheckBox1();
    afx_msg void OnCheckBox2();
    afx_msg void OnRadio1();
    afx_msg void OnRadio2();
    afx_msg void OnRadio3();
    afx_msg void OnPaint();

    DECLARE_MESSAGE_MAP()
};

// Global app object
CMyApp theApp;

// Message map
BEGIN_MESSAGE_MAP(CMyFrameWnd, CFrameWnd)
    ON_WM_CREATE()
    ON_WM_PAINT()
    ON_COMMAND(1001, OnActionButton)
    ON_COMMAND(1002, OnClearButton)
    ON_COMMAND(1003, OnCheckBox1)
    ON_COMMAND(1004, OnCheckBox2)
    ON_COMMAND(1005, OnRadio1)
    ON_COMMAND(1006, OnRadio2)
    ON_COMMAND(1007, OnRadio3)
    ON_LBN_SELCHANGE(1008, OnListBoxSelChange)
    ON_CBN_SELCHANGE(1009, OnComboBoxSelChange)
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
    // Initialize pointers
    m_pEditSingle = NULL;
    m_pEditMulti = NULL;
    m_pBtnAction = NULL;
    m_pBtnClear = NULL;
    m_pListBox = NULL;
    m_pComboBox = NULL;
    m_pCheckBox1 = NULL;
    m_pCheckBox2 = NULL;
    m_pRadio1 = NULL;
    m_pRadio2 = NULL;
    m_pRadio3 = NULL;
    m_pStatusText = NULL;

    Create(NULL, _T("TNOU - MFC Window Controls (Q11)"),
           WS_OVERLAPPEDWINDOW,
           CRect(100, 100, 850, 750));
}

// Destructor
CMyFrameWnd::~CMyFrameWnd()
{
    // MFC automatically cleans up child windows
    delete m_pEditSingle;
    delete m_pEditMulti;
    delete m_pBtnAction;
    delete m_pBtnClear;
    delete m_pListBox;
    delete m_pComboBox;
    delete m_pCheckBox1;
    delete m_pCheckBox2;
    delete m_pRadio1;
    delete m_pRadio2;
    delete m_pRadio3;
    delete m_pStatusText;
}

// OnCreate handler - create all controls
int CMyFrameWnd::OnCreate(LPCREATESTRUCT lpCreateStruct)
{
    if (CFrameWnd::OnCreate(lpCreateStruct) == -1)
        return -1;

    // Title
    CStatic* pTitle = new CStatic();
    pTitle->Create(_T("MFC WINDOW CONTROLS DEMONSTRATION"),
                  WS_CHILD | WS_VISIBLE | SS_CENTER,
                  CRect(50, 10, 700, 40), this);

    // Section 1: Edit Controls
    CStatic* pLabel1 = new CStatic();
    pLabel1->Create(_T("1. EDIT CONTROLS:"),
                   WS_CHILD | WS_VISIBLE | SS_LEFT,
                   CRect(30, 60, 200, 80), this);

    CStatic* pLabel2 = new CStatic();
    pLabel2->Create(_T("Single Line:"),
                   WS_CHILD | WS_VISIBLE | SS_LEFT,
                   CRect(30, 90, 120, 110), this);

    m_pEditSingle = new CEdit();
    m_pEditSingle->Create(WS_CHILD | WS_VISIBLE | WS_BORDER | ES_LEFT | ES_AUTOHSCROLL,
                         CRect(140, 88, 400, 113), this, 2001);

    CStatic* pLabel3 = new CStatic();
    pLabel3->Create(_T("Multi Line:"),
                   WS_CHILD | WS_VISIBLE | SS_LEFT,
                   CRect(30, 125, 120, 145), this);

    m_pEditMulti = new CEdit();
    m_pEditMulti->Create(WS_CHILD | WS_VISIBLE | WS_BORDER | ES_MULTILINE |
                        ES_AUTOVSCROLL | ES_WANTRETURN | WS_VSCROLL,
                        CRect(140, 123, 400, 203), this, 2002);

    // Section 2: Buttons
    CStatic* pLabel4 = new CStatic();
    pLabel4->Create(_T("2. BUTTONS:"),
                   WS_CHILD | WS_VISIBLE | SS_LEFT,
                   CRect(30, 220, 200, 240), this);

    m_pBtnAction = new CButton();
    m_pBtnAction->Create(_T("Process Input"),
                        WS_CHILD | WS_VISIBLE | BS_DEFPUSHBUTTON,
                        CRect(30, 245, 180, 285), this, 1001);

    m_pBtnClear = new CButton();
    m_pBtnClear->Create(_T("Clear All"),
                       WS_CHILD | WS_VISIBLE | BS_PUSHBUTTON,
                       CRect(190, 245, 310, 285), this, 1002);

    // Section 3: Check Boxes
    CStatic* pLabel5 = new CStatic();
    pLabel5->Create(_T("3. CHECK BOXES:"),
                   WS_CHILD | WS_VISIBLE | SS_LEFT,
                   CRect(30, 305, 200, 325), this);

    m_pCheckBox1 = new CButton();
    m_pCheckBox1->Create(_T("Enable Feature A"),
                        WS_CHILD | WS_VISIBLE | BS_AUTOCHECKBOX,
                        CRect(30, 330, 200, 350), this, 1003);

    m_pCheckBox2 = new CButton();
    m_pCheckBox2->Create(_T("Enable Feature B"),
                        WS_CHILD | WS_VISIBLE | BS_AUTOCHECKBOX,
                        CRect(30, 355, 200, 375), this, 1004);

    // Section 4: Radio Buttons
    CStatic* pLabel6 = new CStatic();
    pLabel6->Create(_T("4. RADIO BUTTONS:"),
                   WS_CHILD | WS_VISIBLE | SS_LEFT,
                   CRect(30, 395, 200, 415), this);

    m_pRadio1 = new CButton();
    m_pRadio1->Create(_T("Option 1"),
                     WS_CHILD | WS_VISIBLE | BS_AUTORADIOBUTTON | WS_GROUP,
                     CRect(30, 420, 150, 440), this, 1005);

    m_pRadio2 = new CButton();
    m_pRadio2->Create(_T("Option 2"),
                     WS_CHILD | WS_VISIBLE | BS_AUTORADIOBUTTON,
                     CRect(30, 445, 150, 465), this, 1006);

    m_pRadio3 = new CButton();
    m_pRadio3->Create(_T("Option 3"),
                     WS_CHILD | WS_VISIBLE | BS_AUTORADIOBUTTON,
                     CRect(30, 470, 150, 490), this, 1007);

    // Section 5: List Box
    CStatic* pLabel7 = new CStatic();
    pLabel7->Create(_T("5. LIST BOX:"),
                   WS_CHILD | WS_VISIBLE | SS_LEFT,
                   CRect(430, 60, 580, 80), this);

    m_pListBox = new CListBox();
    m_pListBox->Create(WS_CHILD | WS_VISIBLE | WS_BORDER | WS_VSCROLL |
                      LBS_STANDARD | LBS_NOTIFY,
                      CRect(430, 88, 650, 208), this, 1008);

    // Add items to list box
    m_pListBox->AddString(_T("List Item 1"));
    m_pListBox->AddString(_T("List Item 2"));
    m_pListBox->AddString(_T("List Item 3"));
    m_pListBox->AddString(_T("List Item 4"));
    m_pListBox->AddString(_T("List Item 5"));

    // Section 6: Combo Box
    CStatic* pLabel8 = new CStatic();
    pLabel8->Create(_T("6. COMBO BOX:"),
                   WS_CHILD | WS_VISIBLE | SS_LEFT,
                   CRect(430, 230, 580, 250), this);

    m_pComboBox = new CComboBox();
    m_pComboBox->Create(WS_CHILD | WS_VISIBLE | CBS_DROPDOWNLIST | WS_VSCROLL,
                       CRect(430, 255, 650, 350), this, 1009);

    // Add items to combo box
    m_pComboBox->AddString(_T("Choice A"));
    m_pComboBox->AddString(_T("Choice B"));
    m_pComboBox->AddString(_T("Choice C"));
    m_pComboBox->AddString(_T("Choice D"));
    m_pComboBox->SetCurSel(0);

    // Status display
    CStatic* pLabel9 = new CStatic();
    pLabel9->Create(_T("STATUS:"),
                   WS_CHILD | WS_VISIBLE | SS_LEFT,
                   CRect(30, 520, 200, 540), this);

    m_pStatusText = new CStatic();
    m_pStatusText->Create(_T("Ready. Interact with controls to see status updates."),
                         WS_CHILD | WS_VISIBLE | SS_LEFT | WS_BORDER,
                         CRect(30, 545, 700, 595), this, 2003);

    return 0;
}

// Paint handler
void CMyFrameWnd::OnPaint()
{
    CPaintDC dc(this);
    // Additional custom painting can be done here if needed
}

// Action button handler
void CMyFrameWnd::OnActionButton()
{
    CString singleText, multiText, status;

    m_pEditSingle->GetWindowText(singleText);
    m_pEditMulti->GetWindowText(multiText);

    int listSel = m_pListBox->GetCurSel();
    CString listText;
    if (listSel != LB_ERR)
        m_pListBox->GetText(listSel, listText);

    CString comboText;
    int comboSel = m_pComboBox->GetCurSel();
    if (comboSel != CB_ERR)
        m_pComboBox->GetLBText(comboSel, comboText);

    BOOL check1 = m_pCheckBox1->GetCheck();
    BOOL check2 = m_pCheckBox2->GetCheck();

    int radioSel = 0;
    if (m_pRadio1->GetCheck()) radioSel = 1;
    else if (m_pRadio2->GetCheck()) radioSel = 2;
    else if (m_pRadio3->GetCheck()) radioSel = 3;

    status.Format(_T("PROCESSED:\nEdit: %s\nList: %s\nCombo: %s\nChecks: %d,%d\nRadio: %d"),
                 singleText.IsEmpty() ? _T("(empty)") : singleText,
                 listText.IsEmpty() ? _T("(none)") : listText,
                 comboText, check1, check2, radioSel);

    m_pStatusText->SetWindowText(status);

    MessageBox(status, _T("Control Values"), MB_OK | MB_ICONINFORMATION);
}

// Clear button handler
void CMyFrameWnd::OnClearButton()
{
    m_pEditSingle->SetWindowText(_T(""));
    m_pEditMulti->SetWindowText(_T(""));
    m_pListBox->SetCurSel(-1);
    m_pComboBox->SetCurSel(0);
    m_pCheckBox1->SetCheck(BST_UNCHECKED);
    m_pCheckBox2->SetCheck(BST_UNCHECKED);
    m_pRadio1->SetCheck(BST_UNCHECKED);
    m_pRadio2->SetCheck(BST_UNCHECKED);
    m_pRadio3->SetCheck(BST_UNCHECKED);
    m_pStatusText->SetWindowText(_T("All controls cleared."));
}

// List box selection change handler
void CMyFrameWnd::OnListBoxSelChange()
{
    int sel = m_pListBox->GetCurSel();
    if (sel != LB_ERR)
    {
        CString text;
        m_pListBox->GetText(sel, text);
        CString status;
        status.Format(_T("List Box selection changed to: %s"), text);
        m_pStatusText->SetWindowText(status);
    }
}

// Combo box selection change handler
void CMyFrameWnd::OnComboBoxSelChange()
{
    int sel = m_pComboBox->GetCurSel();
    if (sel != CB_ERR)
    {
        CString text;
        m_pComboBox->GetLBText(sel, text);
        CString status;
        status.Format(_T("Combo Box selection changed to: %s"), text);
        m_pStatusText->SetWindowText(status);
    }
}

// Check box handlers
void CMyFrameWnd::OnCheckBox1()
{
    BOOL checked = m_pCheckBox1->GetCheck();
    CString status;
    status.Format(_T("Feature A is now: %s"), checked ? _T("ENABLED") : _T("DISABLED"));
    m_pStatusText->SetWindowText(status);
}

void CMyFrameWnd::OnCheckBox2()
{
    BOOL checked = m_pCheckBox2->GetCheck();
    CString status;
    status.Format(_T("Feature B is now: %s"), checked ? _T("ENABLED") : _T("DISABLED"));
    m_pStatusText->SetWindowText(status);
}

// Radio button handlers
void CMyFrameWnd::OnRadio1()
{
    m_pStatusText->SetWindowText(_T("Radio button Option 1 selected"));
}

void CMyFrameWnd::OnRadio2()
{
    m_pStatusText->SetWindowText(_T("Radio button Option 2 selected"));
}

void CMyFrameWnd::OnRadio3()
{
    m_pStatusText->SetWindowText(_T("Radio button Option 3 selected"));
}

/*
 * Compilation Instructions:
 * -------------------------
 * cl /EHsc /D_AFXDLL /MD Q11_WindowControl.cpp /link /SUBSYSTEM:WINDOWS
 *
 * Expected Output:
 * ----------------
 * MFC window with various controls:
 * 1. Edit Controls (single-line and multi-line)
 * 2. Buttons (Process Input, Clear All)
 * 3. Check Boxes (two checkboxes)
 * 4. Radio Buttons (three options)
 * 5. List Box (with 5 items)
 * 6. Combo Box (dropdown with 4 choices)
 * 7. Status display area
 *
 * Features Demonstrated:
 * ----------------------
 * 1. CEdit - single and multi-line text input
 * 2. CButton - push buttons, checkboxes, radio buttons
 * 3. CListBox - list selection
 * 4. CComboBox - dropdown selection
 * 5. CStatic - labels and status display
 * 6. Control event handling
 * 7. Getting/setting control values
 * 8. Message map for control notifications
 * 9. Dynamic control creation in OnCreate
 * 10. Inter-control communication
 *
 * Interactions:
 * -------------
 * - Type in edit boxes
 * - Click buttons to process or clear
 * - Select items from list and combo boxes
 * - Toggle checkboxes
 * - Select radio buttons
 * - View status updates in real-time
 */
