/*
 * Question 1: SDK Program to Display User-Defined Window
 *
 * This program creates a basic window using Win32 SDK.
 * It demonstrates the fundamental structure of a Windows application.
 */

#include <windows.h>

// Global variables
const char g_szClassName[] = "UserDefinedWindow";

// Forward declarations
LRESULT CALLBACK WndProc(HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam);

// WinMain - Entry point for Windows applications
int WINAPI WinMain(HINSTANCE hInstance, HINSTANCE hPrevInstance,
                   LPSTR lpCmdLine, int nCmdShow)
{
    WNDCLASSEX wc;
    HWND hwnd;
    MSG Msg;

    // Step 1: Registering the Window Class
    wc.cbSize        = sizeof(WNDCLASSEX);
    wc.style         = CS_HREDRAW | CS_VREDRAW;
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
        MessageBox(NULL, "Window Registration Failed!", "Error!",
                   MB_ICONEXCLAMATION | MB_OK);
        return 0;
    }

    // Step 2: Creating the Window
    hwnd = CreateWindowEx(
        WS_EX_CLIENTEDGE,
        g_szClassName,
        "TNOU - User Defined Window (Q1)",
        WS_OVERLAPPEDWINDOW,
        CW_USEDEFAULT, CW_USEDEFAULT, 640, 480,
        NULL, NULL, hInstance, NULL);

    if (hwnd == NULL)
    {
        MessageBox(NULL, "Window Creation Failed!", "Error!",
                   MB_ICONEXCLAMATION | MB_OK);
        return 0;
    }

    ShowWindow(hwnd, nCmdShow);
    UpdateWindow(hwnd);

    // Step 3: The Message Loop
    while (GetMessage(&Msg, NULL, 0, 0) > 0)
    {
        TranslateMessage(&Msg);
        DispatchMessage(&Msg);
    }
    return Msg.wParam;
}

// Window Procedure - Handles messages sent to the window
LRESULT CALLBACK WndProc(HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam)
{
    switch (msg)
    {
        case WM_PAINT:
        {
            PAINTSTRUCT ps;
            HDC hdc = BeginPaint(hwnd, &ps);

            // Display welcome text
            RECT rect;
            GetClientRect(hwnd, &rect);
            DrawText(hdc, "Welcome to User Defined Window!\n\nTNOU MCA-P4 Lab\nQuestion 1: SDK Program",
                     -1, &rect, DT_CENTER | DT_VCENTER | DT_SINGLELINE);

            EndPaint(hwnd, &ps);
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
 * Using Visual Studio Command Prompt:
 *   cl Q1_UserDefinedWindow.cpp user32.lib gdi32.lib
 *
 * Or using g++ (MinGW):
 *   g++ Q1_UserDefinedWindow.cpp -o Q1_UserDefinedWindow.exe -lgdi32 -luser32 -mwindows
 *
 * Expected Output:
 * ----------------
 * A window with title "TNOU - User Defined Window (Q1)" will appear
 * displaying "Welcome to User Defined Window!" in the center.
 */
