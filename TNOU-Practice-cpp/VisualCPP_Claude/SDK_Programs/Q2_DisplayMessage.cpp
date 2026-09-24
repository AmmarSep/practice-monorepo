/*
 * Question 2: Displaying the Given Message
 *
 * This program displays various messages using different methods:
 * - MessageBox for popup messages
 * - TextOut for drawing text in the window
 */

#include <windows.h>

const char g_szClassName[] = "DisplayMessageWindow";

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
        "TNOU - Display Message (Q2)",
        WS_OVERLAPPEDWINDOW,
        CW_USEDEFAULT, CW_USEDEFAULT, 600, 400,
        NULL, NULL, hInstance, NULL);

    if (hwnd == NULL)
    {
        MessageBox(NULL, "Window Creation Failed!", "Error!", MB_ICONEXCLAMATION | MB_OK);
        return 0;
    }

    // Display a welcome message box
    MessageBox(hwnd, "Welcome to TNOU Visual C++ Lab!\n\nThis program demonstrates message display.",
               "Welcome", MB_ICONINFORMATION | MB_OK);

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
    switch (msg)
    {
        case WM_CREATE:
        {
            // Create a button to show message
            CreateWindow(
                "BUTTON",
                "Show Message",
                WS_VISIBLE | WS_CHILD | BS_DEFPUSHBUTTON,
                200, 150, 150, 30,
                hwnd, (HMENU)1, NULL, NULL);
        }
        break;

        case WM_COMMAND:
        {
            if (LOWORD(wParam) == 1)
            {
                // Button clicked - show different types of messages
                int result = MessageBox(hwnd,
                    "This is a demonstration of message display.\n\n"
                    "Click 'Yes' for Information\n"
                    "Click 'No' for Warning\n"
                    "Click 'Cancel' to close",
                    "Message Display Demo",
                    MB_YESNOCANCEL | MB_ICONQUESTION);

                if (result == IDYES)
                {
                    MessageBox(hwnd, "Information: You clicked YES!",
                               "Info", MB_ICONINFORMATION | MB_OK);
                }
                else if (result == IDNO)
                {
                    MessageBox(hwnd, "Warning: You clicked NO!",
                               "Warning", MB_ICONWARNING | MB_OK);
                }
            }
        }
        break;

        case WM_PAINT:
        {
            PAINTSTRUCT ps;
            HDC hdc = BeginPaint(hwnd, &ps);

            // Set text color and background
            SetTextColor(hdc, RGB(0, 0, 255));
            SetBkMode(hdc, TRANSPARENT);

            // Display messages at different positions
            TextOut(hdc, 50, 30, "TNOU - MCA Program", 18);
            TextOut(hdc, 50, 60, "Visual C++ Programming Lab", 26);
            TextOut(hdc, 50, 90, "Question 2: Displaying Messages", 31);

            // Change color and display more text
            SetTextColor(hdc, RGB(255, 0, 0));
            TextOut(hdc, 100, 200, "Click the button to see message boxes!", 39);

            EndPaint(hwnd, &ps);
        }
        break;

        case WM_CLOSE:
        {
            int result = MessageBox(hwnd, "Are you sure you want to exit?",
                                   "Confirm Exit", MB_YESNO | MB_ICONQUESTION);
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
 * cl Q2_DisplayMessage.cpp user32.lib gdi32.lib
 *
 * Or:
 * g++ Q2_DisplayMessage.cpp -o Q2_DisplayMessage.exe -luser32 -lgdi32 -mwindows
 *
 * Expected Output:
 * ----------------
 * 1. A welcome message box appears on startup
 * 2. Window displays text messages using TextOut
 * 3. Button click shows interactive message boxes
 * 4. Exit confirmation message on close
 */
