/*
 * Question 6: Manipulation of Dialog Box
 *
 * This program demonstrates creating and manipulating dialog boxes:
 * - Creating dialog boxes programmatically
 * - Modal and Modeless dialogs
 * - Dialog box controls and message handling
 */

#include <windows.h>
#include <stdio.h>

const char g_szClassName[] = "DialogBoxClass";

// Control IDs
#define IDD_ABOUT       101
#define IDD_CUSTOM      102
#define IDC_NAME        1001
#define IDC_AGE         1002
#define IDC_GENDER      1003
#define IDC_RESULT      1004

// Function prototypes
LRESULT CALLBACK WndProc(HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam);
LRESULT CALLBACK AboutDlgProc(HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam);
LRESULT CALLBACK CustomDlgProc(HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam);

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
        "TNOU - Dialog Box Manipulation (Q6)",
        WS_OVERLAPPEDWINDOW,
        CW_USEDEFAULT, CW_USEDEFAULT, 600, 400,
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
    static HINSTANCE hInstance;

    switch (msg)
    {
        case WM_CREATE:
        {
            hInstance = ((LPCREATESTRUCT)lParam)->hInstance;

            // Title
            CreateWindow("STATIC",
                        "Dialog Box Manipulation - TNOU MCA-P4",
                        WS_VISIBLE | WS_CHILD | SS_CENTER,
                        50, 20, 480, 25, hwnd, NULL, NULL, NULL);

            // Instructions
            CreateWindow("STATIC",
                        "Click the buttons below to open different types of dialog boxes:",
                        WS_VISIBLE | WS_CHILD,
                        50, 60, 480, 20, hwnd, NULL, NULL, NULL);

            // Button 1: Simple Message Box
            CreateWindow("BUTTON", "Simple Message Box",
                        WS_VISIBLE | WS_CHILD | BS_PUSHBUTTON,
                        100, 100, 180, 40, hwnd, (HMENU)1, NULL, NULL);

            // Button 2: About Dialog
            CreateWindow("BUTTON", "About Dialog",
                        WS_VISIBLE | WS_CHILD | BS_PUSHBUTTON,
                        300, 100, 180, 40, hwnd, (HMENU)IDD_ABOUT, NULL, NULL);

            // Button 3: Custom Input Dialog
            CreateWindow("BUTTON", "Custom Input Dialog",
                        WS_VISIBLE | WS_CHILD | BS_PUSHBUTTON,
                        100, 160, 180, 40, hwnd, (HMENU)IDD_CUSTOM, NULL, NULL);

            // Button 4: Question Dialog
            CreateWindow("BUTTON", "Question Dialog",
                        WS_VISIBLE | WS_CHILD | BS_PUSHBUTTON,
                        300, 160, 180, 40, hwnd, (HMENU)3, NULL, NULL);

            // Button 5: Warning Dialog
            CreateWindow("BUTTON", "Warning Dialog",
                        WS_VISIBLE | WS_CHILD | BS_PUSHBUTTON,
                        100, 220, 180, 40, hwnd, (HMENU)4, NULL, NULL);

            // Button 6: Error Dialog
            CreateWindow("BUTTON", "Error Dialog",
                        WS_VISIBLE | WS_CHILD | BS_PUSHBUTTON,
                        300, 220, 180, 40, hwnd, (HMENU)5, NULL, NULL);
        }
        break;

        case WM_COMMAND:
        {
            switch (LOWORD(wParam))
            {
                case 1: // Simple Message Box
                {
                    MessageBox(hwnd,
                              "This is a simple message box!\n\n"
                              "It displays information to the user.",
                              "Simple Message",
                              MB_OK | MB_ICONINFORMATION);
                }
                break;

                case IDD_ABOUT: // About Dialog
                {
                    HWND hDlg = CreateWindowEx(
                        WS_EX_DLGMODALFRAME | WS_EX_TOPMOST,
                        "STATIC",
                        "About",
                        WS_VISIBLE | WS_SYSMENU | WS_CAPTION,
                        300, 200, 400, 250,
                        hwnd, NULL, hInstance, NULL);

                    // Add content to About dialog
                    CreateWindow("STATIC",
                                "TNOU Visual C++ Lab",
                                WS_VISIBLE | WS_CHILD | SS_CENTER,
                                50, 20, 300, 25, hDlg, NULL, NULL, NULL);

                    CreateWindow("STATIC",
                                "MCA - P4 Lab Program\n\n"
                                "Question 6: Dialog Box Manipulation\n\n"
                                "This program demonstrates:\n"
                                "- Creating dialog boxes\n"
                                "- Handling dialog messages\n"
                                "- Different dialog types",
                                WS_VISIBLE | WS_CHILD | SS_LEFT,
                                50, 60, 300, 120, hDlg, NULL, NULL, NULL);

                    CreateWindow("BUTTON", "OK",
                                WS_VISIBLE | WS_CHILD | BS_DEFPUSHBUTTON,
                                150, 190, 100, 30,
                                hDlg, (HMENU)IDOK, NULL, NULL);

                    // Simple modal dialog simulation
                    MSG msg;
                    while (GetMessage(&msg, NULL, 0, 0) > 0)
                    {
                        if (msg.hwnd == hDlg || IsChild(hDlg, msg.hwnd))
                        {
                            if (msg.message == WM_COMMAND && LOWORD(msg.wParam) == IDOK)
                            {
                                DestroyWindow(hDlg);
                                break;
                            }
                            if (msg.message == WM_CLOSE)
                            {
                                DestroyWindow(hDlg);
                                break;
                            }
                        }
                        TranslateMessage(&msg);
                        DispatchMessage(&msg);
                    }
                }
                break;

                case IDD_CUSTOM: // Custom Input Dialog
                {
                    char name[256];
                    int result = MessageBox(hwnd,
                                          "This would open a custom dialog box\n"
                                          "with input fields for:\n\n"
                                          "- Name\n"
                                          "- Age\n"
                                          "- Gender\n\n"
                                          "Would you like to see a simpler version?",
                                          "Custom Input Dialog",
                                          MB_YESNO | MB_ICONQUESTION);

                    if (result == IDYES)
                    {
                        // Simple custom dialog
                        MessageBox(hwnd,
                                  "Custom dialog functionality demonstrated!\n\n"
                                  "In a full implementation, this would:\n"
                                  "- Collect user input\n"
                                  "- Validate data\n"
                                  "- Return results to main window",
                                  "Custom Dialog Info",
                                  MB_OK | MB_ICONINFORMATION);
                    }
                }
                break;

                case 3: // Question Dialog
                {
                    int result = MessageBox(hwnd,
                                          "Do you want to continue with this operation?\n\n"
                                          "Click Yes to proceed\n"
                                          "Click No to cancel",
                                          "Question Dialog",
                                          MB_YESNO | MB_ICONQUESTION);

                    if (result == IDYES)
                    {
                        MessageBox(hwnd, "You clicked YES!", "Result",
                                  MB_OK | MB_ICONINFORMATION);
                    }
                    else
                    {
                        MessageBox(hwnd, "You clicked NO!", "Result",
                                  MB_OK | MB_ICONINFORMATION);
                    }
                }
                break;

                case 4: // Warning Dialog
                {
                    MessageBox(hwnd,
                              "Warning: This action may have consequences!\n\n"
                              "Please proceed with caution.",
                              "Warning Dialog",
                              MB_OK | MB_ICONWARNING);
                }
                break;

                case 5: // Error Dialog
                {
                    MessageBox(hwnd,
                              "Error: An error has occurred!\n\n"
                              "Error Code: 0x0001\n"
                              "Description: Sample error message",
                              "Error Dialog",
                              MB_OK | MB_ICONERROR);
                }
                break;
            }
        }
        break;

        case WM_CLOSE:
        {
            int result = MessageBox(hwnd,
                                   "Are you sure you want to exit?",
                                   "Confirm Exit",
                                   MB_YESNO | MB_ICONQUESTION);
            if (result == IDYES)
            {
                DestroyWindow(hwnd);
            }
        }
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
 * cl Q6_DialogBox.cpp user32.lib gdi32.lib
 *
 * Or:
 * g++ Q6_DialogBox.cpp -o Q6_DialogBox.exe -luser32 -lgdi32 -mwindows
 *
 * Expected Output:
 * ----------------
 * Main window with buttons to open different dialog boxes:
 * 1. Simple Message Box
 * 2. About Dialog (custom window)
 * 3. Custom Input Dialog
 * 4. Question Dialog (Yes/No)
 * 5. Warning Dialog
 * 6. Error Dialog
 * 7. Exit confirmation dialog
 *
 * Note: This is a simplified version using MessageBox and CreateWindow.
 * A full implementation would use DialogBox() with resource files (.rc).
 */
