/*
 * Question 8: Checking Password using Dialog Box
 *
 * This program creates a login dialog that:
 * - Takes username and password input
 * - Validates credentials
 * - Shows appropriate messages
 * - Limits login attempts
 */

#include <windows.h>
#include <string.h>

const char g_szClassName[] = "PasswordCheckerClass";

// Control IDs
#define IDC_USERNAME    1001
#define IDC_PASSWORD    1002
#define IDC_LOGIN       1003
#define IDC_CANCEL      1004
#define IDC_STATUS      1005

// Correct credentials (for demonstration)
const char CORRECT_USERNAME[] = "admin";
const char CORRECT_PASSWORD[] = "tnou123";

int loginAttempts = 0;
const int MAX_ATTEMPTS = 3;

LRESULT CALLBACK WndProc(HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam);
void CheckPassword(HWND hwnd);

// Global control handles
HWND hwndUsername, hwndPassword, hwndStatus;

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
        WS_EX_CLIENTEDGE | WS_EX_TOPMOST,
        g_szClassName,
        "TNOU - Password Checker (Q8)",
        WS_OVERLAPPED | WS_CAPTION | WS_SYSMENU | WS_MINIMIZEBOX,
        CW_USEDEFAULT, CW_USEDEFAULT, 500, 400,
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

void CheckPassword(HWND hwnd)
{
    char username[256];
    char password[256];
    char statusMsg[512];

    // Get username and password
    GetWindowText(hwndUsername, username, 256);
    GetWindowText(hwndPassword, password, 256);

    // Validate input
    if (strlen(username) == 0)
    {
        MessageBox(hwnd, "Please enter username!", "Validation Error", MB_OK | MB_ICONWARNING);
        SetFocus(hwndUsername);
        return;
    }

    if (strlen(password) == 0)
    {
        MessageBox(hwnd, "Please enter password!", "Validation Error", MB_OK | MB_ICONWARNING);
        SetFocus(hwndPassword);
        return;
    }

    // Check credentials
    if (strcmp(username, CORRECT_USERNAME) == 0 && strcmp(password, CORRECT_PASSWORD) == 0)
    {
        // Successful login
        MessageBox(hwnd,
                  "Login Successful!\n\n"
                  "Welcome to TNOU MCA Program!\n\n"
                  "You have successfully authenticated.",
                  "Success",
                  MB_OK | MB_ICONINFORMATION);

        sprintf(statusMsg, "STATUS: Login successful! Welcome %s!", username);
        SetWindowText(hwndStatus, statusMsg);

        // Reset attempts
        loginAttempts = 0;

        // Clear password field
        SetWindowText(hwndPassword, "");

        // You could close the window or proceed to main application here
        // DestroyWindow(hwnd);
    }
    else
    {
        // Failed login
        loginAttempts++;
        int remainingAttempts = MAX_ATTEMPTS - loginAttempts;

        if (remainingAttempts > 0)
        {
            sprintf(statusMsg,
                   "Login Failed!\n\n"
                   "Incorrect username or password.\n\n"
                   "Attempts remaining: %d",
                   remainingAttempts);

            MessageBox(hwnd, statusMsg, "Login Failed", MB_OK | MB_ICONERROR);

            sprintf(statusMsg, "STATUS: Login failed. %d attempts remaining.", remainingAttempts);
            SetWindowText(hwndStatus, statusMsg);

            // Clear password field
            SetWindowText(hwndPassword, "");
            SetFocus(hwndUsername);
        }
        else
        {
            // Max attempts reached
            MessageBox(hwnd,
                      "Maximum login attempts exceeded!\n\n"
                      "The application will now close.\n\n"
                      "Please contact the administrator.",
                      "Access Denied",
                      MB_OK | MB_ICONERROR);

            DestroyWindow(hwnd);
        }
    }
}

LRESULT CALLBACK WndProc(HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam)
{
    switch (msg)
    {
        case WM_CREATE:
        {
            // Title
            HWND hwndTitle = CreateWindow("STATIC",
                        "SECURE LOGIN SYSTEM",
                        WS_VISIBLE | WS_CHILD | SS_CENTER,
                        50, 20, 380, 30, hwnd, NULL, NULL, NULL);

            HFONT hTitleFont = CreateFont(24, 0, 0, 0, FW_BOLD, FALSE, FALSE, FALSE,
                                         DEFAULT_CHARSET, OUT_DEFAULT_PRECIS,
                                         CLIP_DEFAULT_PRECIS, DEFAULT_QUALITY,
                                         DEFAULT_PITCH | FF_DONTCARE, "Arial");
            SendMessage(hwndTitle, WM_SETFONT, (WPARAM)hTitleFont, TRUE);

            // Subtitle
            CreateWindow("STATIC",
                        "TNOU MCA Program - Password Authentication",
                        WS_VISIBLE | WS_CHILD | SS_CENTER,
                        50, 55, 380, 20, hwnd, NULL, NULL, NULL);

            // Instructions
            CreateWindow("STATIC",
                        "Please enter your credentials to continue:",
                        WS_VISIBLE | WS_CHILD,
                        50, 90, 380, 20, hwnd, NULL, NULL, NULL);

            // Username section
            CreateWindow("STATIC", "Username:",
                        WS_VISIBLE | WS_CHILD,
                        50, 130, 100, 20, hwnd, NULL, NULL, NULL);

            hwndUsername = CreateWindow("EDIT", "",
                        WS_VISIBLE | WS_CHILD | WS_BORDER | ES_LEFT | ES_AUTOHSCROLL,
                        50, 155, 380, 30, hwnd, (HMENU)IDC_USERNAME, NULL, NULL);

            // Password section
            CreateWindow("STATIC", "Password:",
                        WS_VISIBLE | WS_CHILD,
                        50, 200, 100, 20, hwnd, NULL, NULL, NULL);

            hwndPassword = CreateWindow("EDIT", "",
                        WS_VISIBLE | WS_CHILD | WS_BORDER | ES_LEFT | ES_PASSWORD | ES_AUTOHSCROLL,
                        50, 225, 380, 30, hwnd, (HMENU)IDC_PASSWORD, NULL, NULL);

            // Buttons
            CreateWindow("BUTTON", "LOGIN",
                        WS_VISIBLE | WS_CHILD | BS_DEFPUSHBUTTON,
                        50, 275, 180, 40, hwnd, (HMENU)IDC_LOGIN, NULL, NULL);

            CreateWindow("BUTTON", "CANCEL",
                        WS_VISIBLE | WS_CHILD | BS_PUSHBUTTON,
                        250, 275, 180, 40, hwnd, (HMENU)IDC_CANCEL, NULL, NULL);

            // Status bar
            hwndStatus = CreateWindow("STATIC",
                        "STATUS: Ready to login. Please enter credentials.",
                        WS_VISIBLE | WS_CHILD | SS_LEFT | WS_BORDER,
                        10, 330, 460, 25, hwnd, (HMENU)IDC_STATUS, NULL, NULL);

            // Help text
            CreateWindow("STATIC",
                        "Demo Credentials: Username: admin, Password: tnou123",
                        WS_VISIBLE | WS_CHILD | SS_CENTER,
                        50, 360, 380, 20, hwnd, NULL, NULL, NULL);

            // Set focus to username
            SetFocus(hwndUsername);
        }
        break;

        case WM_COMMAND:
        {
            switch (LOWORD(wParam))
            {
                case IDC_LOGIN:
                    CheckPassword(hwnd);
                    break;

                case IDC_CANCEL:
                {
                    int result = MessageBox(hwnd,
                                          "Are you sure you want to cancel?",
                                          "Confirm Cancel",
                                          MB_YESNO | MB_ICONQUESTION);
                    if (result == IDYES)
                    {
                        DestroyWindow(hwnd);
                    }
                }
                break;

                case IDC_USERNAME:
                case IDC_PASSWORD:
                {
                    // Handle Enter key in edit controls
                    if (HIWORD(wParam) == EN_CHANGE)
                    {
                        // Optional: Update status when user types
                    }
                }
                break;
            }
        }
        break;

        case WM_CLOSE:
        {
            int result = MessageBox(hwnd,
                                   "Exit the login system?",
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
 * cl Q8_PasswordChecker.cpp user32.lib gdi32.lib
 *
 * Or:
 * g++ Q8_PasswordChecker.cpp -o Q8_PasswordChecker.exe -luser32 -lgdi32 -mwindows
 *
 * Expected Output:
 * ----------------
 * A secure login dialog window with:
 * - Username input field
 * - Password input field (masked with *)
 * - Login button
 * - Cancel button
 * - Status bar showing login status
 * - Maximum 3 login attempts
 * - Success message on correct credentials
 * - Error message on incorrect credentials
 *
 * Demo Credentials:
 * Username: admin
 * Password: tnou123
 *
 * Features:
 * - Password masking
 * - Input validation
 * - Attempt limiting (3 attempts max)
 * - Status messages
 * - Secure authentication flow
 */
