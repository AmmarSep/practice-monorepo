/*
 * Question 5: Window Controls
 *
 * This program demonstrates various Windows controls:
 * - Button, Edit Box, Static Text, List Box, Combo Box, Check Box, Radio Button
 */

#include <windows.h>
#include <string.h>

const char g_szClassName[] = "WindowControlsClass";

// Control IDs
#define IDC_BUTTON1     101
#define IDC_BUTTON2     102
#define IDC_EDIT1       103
#define IDC_EDIT2       104
#define IDC_STATIC1     105
#define IDC_LISTBOX     106
#define IDC_COMBOBOX    107
#define IDC_CHECKBOX1   108
#define IDC_CHECKBOX2   109
#define IDC_RADIO1      110
#define IDC_RADIO2      111
#define IDC_RADIO3      112
#define IDC_RESULT      113

LRESULT CALLBACK WndProc(HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam);

int WINAPI WinMain(HINSTANCE hInstance, HINSTANCE hPrevInstance,
                   LPSTR lpCmdLine, int nCmdShow)
{
    WNDCLASSEX wc;
    HWND hwnd;
    MSG Msg;

    wc.cbSize        = sizeof(WNDCLASSEX);
    wc.style         = 0;
    wc.lpfnWndProc   = WndProc;
    wc.cbClsExtra    = 0;
    wc.cbWndExtra    = 0;
    wc.hInstance     = hInstance;
    wc.hIcon         = LoadIcon(NULL, IDI_APPLICATION);
    wc.hCursor       = LoadCursor(NULL, IDC_ARROW);
    wc.hbrBackground = (HBRUSH)(COLOR_WINDOW + 1);
    wc.lpszMenuName  = NULL;
    wc.lpszClassName = g_szClassName;
    wc.hIconSm       = LoadIcon(NULL, IDI_APPLICATION);

    if (!RegisterClassEx(&wc))
    {
        MessageBox(NULL, "Window Registration Failed!", "Error!", MB_ICONEXCLAMATION | MB_OK);
        return 0;
    }

    hwnd = CreateWindowEx(
        WS_EX_CLIENTEDGE,
        g_szClassName,
        "TNOU - Window Controls (Q5)",
        WS_OVERLAPPEDWINDOW,
        CW_USEDEFAULT, CW_USEDEFAULT, 700, 650,
        NULL, NULL, hInstance, NULL);

    if (hwnd == NULL)
    {
        MessageBox(NULL, "Window Creation Failed!", "Error!", MB_ICONEXCLAMATION | MB_OK);
        return 0;
    }

    ShowWindow(hwnd, nCmdShow);
    UpdateWindow(hwnd);

    while (GetMessage(&Msg, NULL, 0, 0) > 0)
    {
        TranslateMessage(&Msg);
        DispatchMessage(&Msg);
    }
    return Msg.wParam;
}

LRESULT CALLBACK WndProc(HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam)
{
    static HWND hwndEdit1, hwndEdit2, hwndListBox, hwndComboBox;
    static HWND hwndCheckBox1, hwndCheckBox2, hwndRadio1, hwndRadio2, hwndRadio3;
    static HWND hwndResult;

    switch (msg)
    {
        case WM_CREATE:
        {
            // Title
            CreateWindow("STATIC", "TNOU - Demonstration of Window Controls",
                        WS_VISIBLE | WS_CHILD | SS_CENTER,
                        10, 10, 660, 25, hwnd, (HMENU)IDC_STATIC1, NULL, NULL);

            // Section 1: Buttons
            CreateWindow("STATIC", "1. BUTTONS:",
                        WS_VISIBLE | WS_CHILD,
                        20, 50, 150, 20, hwnd, NULL, NULL, NULL);

            CreateWindow("BUTTON", "Click Me",
                        WS_VISIBLE | WS_CHILD | BS_DEFPUSHBUTTON,
                        20, 75, 100, 30, hwnd, (HMENU)IDC_BUTTON1, NULL, NULL);

            CreateWindow("BUTTON", "Exit",
                        WS_VISIBLE | WS_CHILD | BS_PUSHBUTTON,
                        130, 75, 100, 30, hwnd, (HMENU)IDC_BUTTON2, NULL, NULL);

            // Section 2: Edit Controls
            CreateWindow("STATIC", "2. EDIT CONTROLS:",
                        WS_VISIBLE | WS_CHILD,
                        20, 120, 150, 20, hwnd, NULL, NULL, NULL);

            CreateWindow("STATIC", "Single Line:",
                        WS_VISIBLE | WS_CHILD,
                        20, 145, 100, 20, hwnd, NULL, NULL, NULL);

            hwndEdit1 = CreateWindow("EDIT", "",
                        WS_VISIBLE | WS_CHILD | WS_BORDER | ES_LEFT | ES_AUTOHSCROLL,
                        120, 143, 200, 25, hwnd, (HMENU)IDC_EDIT1, NULL, NULL);

            CreateWindow("STATIC", "Multi Line:",
                        WS_VISIBLE | WS_CHILD,
                        20, 175, 100, 20, hwnd, NULL, NULL, NULL);

            hwndEdit2 = CreateWindow("EDIT", "",
                        WS_VISIBLE | WS_CHILD | WS_BORDER | ES_MULTILINE |
                        ES_AUTOVSCROLL | WS_VSCROLL,
                        120, 173, 200, 60, hwnd, (HMENU)IDC_EDIT2, NULL, NULL);

            // Section 3: List Box
            CreateWindow("STATIC", "3. LIST BOX:",
                        WS_VISIBLE | WS_CHILD,
                        360, 50, 150, 20, hwnd, NULL, NULL, NULL);

            hwndListBox = CreateWindow("LISTBOX", "",
                        WS_VISIBLE | WS_CHILD | WS_BORDER | LBS_STANDARD,
                        360, 75, 150, 100, hwnd, (HMENU)IDC_LISTBOX, NULL, NULL);

            // Add items to list box
            SendMessage(hwndListBox, LB_ADDSTRING, 0, (LPARAM)"Item 1");
            SendMessage(hwndListBox, LB_ADDSTRING, 0, (LPARAM)"Item 2");
            SendMessage(hwndListBox, LB_ADDSTRING, 0, (LPARAM)"Item 3");
            SendMessage(hwndListBox, LB_ADDSTRING, 0, (LPARAM)"Item 4");
            SendMessage(hwndListBox, LB_ADDSTRING, 0, (LPARAM)"Item 5");

            // Section 4: Combo Box
            CreateWindow("STATIC", "4. COMBO BOX:",
                        WS_VISIBLE | WS_CHILD,
                        360, 190, 150, 20, hwnd, NULL, NULL, NULL);

            hwndComboBox = CreateWindow("COMBOBOX", "",
                        WS_VISIBLE | WS_CHILD | CBS_DROPDOWNLIST | WS_VSCROLL,
                        360, 215, 150, 100, hwnd, (HMENU)IDC_COMBOBOX, NULL, NULL);

            // Add items to combo box
            SendMessage(hwndComboBox, CB_ADDSTRING, 0, (LPARAM)"Option 1");
            SendMessage(hwndComboBox, CB_ADDSTRING, 0, (LPARAM)"Option 2");
            SendMessage(hwndComboBox, CB_ADDSTRING, 0, (LPARAM)"Option 3");
            SendMessage(hwndComboBox, CB_ADDSTRING, 0, (LPARAM)"Option 4");
            SendMessage(hwndComboBox, CB_SETCURSEL, 0, 0);

            // Section 5: Check Boxes
            CreateWindow("STATIC", "5. CHECK BOXES:",
                        WS_VISIBLE | WS_CHILD,
                        20, 250, 150, 20, hwnd, NULL, NULL, NULL);

            hwndCheckBox1 = CreateWindow("BUTTON", "Check Box 1",
                        WS_VISIBLE | WS_CHILD | BS_AUTOCHECKBOX,
                        20, 275, 150, 20, hwnd, (HMENU)IDC_CHECKBOX1, NULL, NULL);

            hwndCheckBox2 = CreateWindow("BUTTON", "Check Box 2",
                        WS_VISIBLE | WS_CHILD | BS_AUTOCHECKBOX,
                        20, 300, 150, 20, hwnd, (HMENU)IDC_CHECKBOX2, NULL, NULL);

            // Section 6: Radio Buttons
            CreateWindow("STATIC", "6. RADIO BUTTONS:",
                        WS_VISIBLE | WS_CHILD,
                        20, 340, 150, 20, hwnd, NULL, NULL, NULL);

            hwndRadio1 = CreateWindow("BUTTON", "Option A",
                        WS_VISIBLE | WS_CHILD | BS_AUTORADIOBUTTON | WS_GROUP,
                        20, 365, 150, 20, hwnd, (HMENU)IDC_RADIO1, NULL, NULL);

            hwndRadio2 = CreateWindow("BUTTON", "Option B",
                        WS_VISIBLE | WS_CHILD | BS_AUTORADIOBUTTON,
                        20, 390, 150, 20, hwnd, (HMENU)IDC_RADIO2, NULL, NULL);

            hwndRadio3 = CreateWindow("BUTTON", "Option C",
                        WS_VISIBLE | WS_CHILD | BS_AUTORADIOBUTTON,
                        20, 415, 150, 20, hwnd, (HMENU)IDC_RADIO3, NULL, NULL);

            // Result display area
            CreateWindow("STATIC", "RESULT DISPLAY:",
                        WS_VISIBLE | WS_CHILD,
                        20, 460, 150, 20, hwnd, NULL, NULL, NULL);

            hwndResult = CreateWindow("EDIT", "Click buttons to see results...",
                        WS_VISIBLE | WS_CHILD | WS_BORDER | ES_MULTILINE |
                        ES_AUTOVSCROLL | WS_VSCROLL | ES_READONLY,
                        20, 485, 640, 100, hwnd, (HMENU)IDC_RESULT, NULL, NULL);
        }
        break;

        case WM_COMMAND:
        {
            char buffer[256];
            switch (LOWORD(wParam))
            {
                case IDC_BUTTON1:
                {
                    char text[256];
                    GetWindowText(hwndEdit1, text, 256);
                    sprintf(buffer, "Button clicked!\r\nEdit box text: %s", text);
                    SetWindowText(hwndResult, buffer);
                }
                break;

                case IDC_BUTTON2:
                    PostMessage(hwnd, WM_CLOSE, 0, 0);
                    break;

                case IDC_LISTBOX:
                    if (HIWORD(wParam) == LBN_SELCHANGE)
                    {
                        int index = SendMessage(hwndListBox, LB_GETCURSEL, 0, 0);
                        if (index != LB_ERR)
                        {
                            char item[256];
                            SendMessage(hwndListBox, LB_GETTEXT, index, (LPARAM)item);
                            sprintf(buffer, "List box selection: %s (Index: %d)", item, index);
                            SetWindowText(hwndResult, buffer);
                        }
                    }
                    break;

                case IDC_COMBOBOX:
                    if (HIWORD(wParam) == CBN_SELCHANGE)
                    {
                        int index = SendMessage(hwndComboBox, CB_GETCURSEL, 0, 0);
                        if (index != CB_ERR)
                        {
                            char item[256];
                            SendMessage(hwndComboBox, CB_GETLBTEXT, index, (LPARAM)item);
                            sprintf(buffer, "Combo box selection: %s (Index: %d)", item, index);
                            SetWindowText(hwndResult, buffer);
                        }
                    }
                    break;

                case IDC_CHECKBOX1:
                case IDC_CHECKBOX2:
                {
                    int check1 = SendMessage(hwndCheckBox1, BM_GETCHECK, 0, 0);
                    int check2 = SendMessage(hwndCheckBox2, BM_GETCHECK, 0, 0);
                    sprintf(buffer, "Check Box 1: %s\r\nCheck Box 2: %s",
                           check1 == BST_CHECKED ? "Checked" : "Unchecked",
                           check2 == BST_CHECKED ? "Checked" : "Unchecked");
                    SetWindowText(hwndResult, buffer);
                }
                break;

                case IDC_RADIO1:
                case IDC_RADIO2:
                case IDC_RADIO3:
                {
                    if (SendMessage(hwndRadio1, BM_GETCHECK, 0, 0) == BST_CHECKED)
                        strcpy(buffer, "Radio button selected: Option A");
                    else if (SendMessage(hwndRadio2, BM_GETCHECK, 0, 0) == BST_CHECKED)
                        strcpy(buffer, "Radio button selected: Option B");
                    else if (SendMessage(hwndRadio3, BM_GETCHECK, 0, 0) == BST_CHECKED)
                        strcpy(buffer, "Radio button selected: Option C");
                    SetWindowText(hwndResult, buffer);
                }
                break;
            }
        }
        break;

        case WM_CLOSE:
            DestroyWindow(hwnd);
            break;

        case WM_DESTROY:
            PostQuitMessage(0);
            break;

        default:
            return DefWindowProc(hwnd, msg, wParam, lParam);
    }
    return 0;
}

/*
 * Compilation Instructions:
 * -------------------------
 * cl Q5_WindowControls.cpp user32.lib gdi32.lib
 *
 * Or:
 * g++ Q5_WindowControls.cpp -o Q5_WindowControls.exe -luser32 -lgdi32 -mwindows
 *
 * Expected Output:
 * ----------------
 * Window displays various controls:
 * 1. Buttons (Click Me, Exit)
 * 2. Edit controls (single line, multi-line)
 * 3. List box with items
 * 4. Combo box with options
 * 5. Check boxes
 * 6. Radio buttons
 * 7. Result display area showing control interactions
 */
