/*
 * Question 7: Displaying the Application Form
 *
 * This program creates a complete application form with:
 * - Personal information fields
 * - Address fields
 * - Contact information
 * - Submit and Clear buttons
 */

#include <windows.h>
#include <stdio.h>

const char g_szClassName[] = "ApplicationFormClass";

// Control IDs
#define IDC_NAME        1001
#define IDC_FATHER      1002
#define IDC_DOB         1003
#define IDC_GENDER_M    1004
#define IDC_GENDER_F    1005
#define IDC_ADDRESS     1006
#define IDC_CITY        1007
#define IDC_STATE       1008
#define IDC_PIN         1009
#define IDC_PHONE       1010
#define IDC_EMAIL       1011
#define IDC_COURSE      1012
#define IDC_SUBMIT      1013
#define IDC_CLEAR       1014

LRESULT CALLBACK WndProc(HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam);

// Global handles for controls
HWND hwndName, hwndFather, hwndDOB, hwndGenderM, hwndGenderF;
HWND hwndAddress, hwndCity, hwndState, hwndPin, hwndPhone, hwndEmail, hwndCourse;

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
        "TNOU - Application Form (Q7)",
        WS_OVERLAPPEDWINDOW,
        CW_USEDEFAULT, CW_USEDEFAULT, 700, 700,
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

void ClearForm()
{
    SetWindowText(hwndName, "");
    SetWindowText(hwndFather, "");
    SetWindowText(hwndDOB, "");
    SendMessage(hwndGenderM, BM_SETCHECK, BST_UNCHECKED, 0);
    SendMessage(hwndGenderF, BM_SETCHECK, BST_UNCHECKED, 0);
    SetWindowText(hwndAddress, "");
    SetWindowText(hwndCity, "");
    SetWindowText(hwndState, "");
    SetWindowText(hwndPin, "");
    SetWindowText(hwndPhone, "");
    SetWindowText(hwndEmail, "");
    SendMessage(hwndCourse, CB_SETCURSEL, -1, 0);
}

void SubmitForm(HWND hwnd)
{
    char name[256], father[256], dob[256], gender[20];
    char address[512], city[100], state[100], pin[20];
    char phone[50], email[100], course[100];
    char result[2048];

    // Get all form data
    GetWindowText(hwndName, name, 256);
    GetWindowText(hwndFather, father, 256);
    GetWindowText(hwndDOB, dob, 256);

    if (SendMessage(hwndGenderM, BM_GETCHECK, 0, 0) == BST_CHECKED)
        strcpy(gender, "Male");
    else if (SendMessage(hwndGenderF, BM_GETCHECK, 0, 0) == BST_CHECKED)
        strcpy(gender, "Female");
    else
        strcpy(gender, "Not specified");

    GetWindowText(hwndAddress, address, 512);
    GetWindowText(hwndCity, city, 100);
    GetWindowText(hwndState, state, 100);
    GetWindowText(hwndPin, pin, 20);
    GetWindowText(hwndPhone, phone, 50);
    GetWindowText(hwndEmail, email, 100);

    int courseIndex = SendMessage(hwndCourse, CB_GETCURSEL, 0, 0);
    if (courseIndex != CB_ERR)
        SendMessage(hwndCourse, CB_GETLBTEXT, courseIndex, (LPARAM)course);
    else
        strcpy(course, "Not selected");

    // Validate required fields
    if (strlen(name) == 0)
    {
        MessageBox(hwnd, "Please enter your name!", "Validation Error", MB_OK | MB_ICONWARNING);
        SetFocus(hwndName);
        return;
    }

    // Format result
    sprintf(result,
            "APPLICATION FORM SUBMITTED SUCCESSFULLY!\n\n"
            "PERSONAL INFORMATION:\n"
            "Name: %s\n"
            "Father's Name: %s\n"
            "Date of Birth: %s\n"
            "Gender: %s\n\n"
            "ADDRESS:\n"
            "%s\n"
            "City: %s\n"
            "State: %s\n"
            "PIN: %s\n\n"
            "CONTACT INFORMATION:\n"
            "Phone: %s\n"
            "Email: %s\n\n"
            "COURSE: %s\n",
            name, father, dob, gender, address, city, state, pin, phone, email, course);

    MessageBox(hwnd, result, "Form Submitted", MB_OK | MB_ICONINFORMATION);
}

LRESULT CALLBACK WndProc(HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam)
{
    switch (msg)
    {
        case WM_CREATE:
        {
            // Form Title
            HWND hwndTitle = CreateWindow("STATIC",
                        "TNOU MCA PROGRAM - APPLICATION FORM",
                        WS_VISIBLE | WS_CHILD | SS_CENTER,
                        50, 10, 580, 30, hwnd, NULL, NULL, NULL);

            HFONT hFont = CreateFont(24, 0, 0, 0, FW_BOLD, FALSE, FALSE, FALSE,
                                     DEFAULT_CHARSET, OUT_DEFAULT_PRECIS,
                                     CLIP_DEFAULT_PRECIS, DEFAULT_QUALITY,
                                     DEFAULT_PITCH | FF_DONTCARE, "Arial");
            SendMessage(hwndTitle, WM_SETFONT, (WPARAM)hFont, TRUE);

            // Personal Information Section
            CreateWindow("STATIC", "PERSONAL INFORMATION",
                        WS_VISIBLE | WS_CHILD,
                        30, 50, 300, 20, hwnd, NULL, NULL, NULL);

            CreateWindow("STATIC", "Full Name:",
                        WS_VISIBLE | WS_CHILD,
                        30, 80, 120, 20, hwnd, NULL, NULL, NULL);
            hwndName = CreateWindow("EDIT", "",
                        WS_VISIBLE | WS_CHILD | WS_BORDER | ES_LEFT,
                        160, 78, 450, 25, hwnd, (HMENU)IDC_NAME, NULL, NULL);

            CreateWindow("STATIC", "Father's Name:",
                        WS_VISIBLE | WS_CHILD,
                        30, 115, 120, 20, hwnd, NULL, NULL, NULL);
            hwndFather = CreateWindow("EDIT", "",
                        WS_VISIBLE | WS_CHILD | WS_BORDER | ES_LEFT,
                        160, 113, 450, 25, hwnd, (HMENU)IDC_FATHER, NULL, NULL);

            CreateWindow("STATIC", "Date of Birth:",
                        WS_VISIBLE | WS_CHILD,
                        30, 150, 120, 20, hwnd, NULL, NULL, NULL);
            hwndDOB = CreateWindow("EDIT", "DD/MM/YYYY",
                        WS_VISIBLE | WS_CHILD | WS_BORDER | ES_LEFT,
                        160, 148, 150, 25, hwnd, (HMENU)IDC_DOB, NULL, NULL);

            CreateWindow("STATIC", "Gender:",
                        WS_VISIBLE | WS_CHILD,
                        330, 150, 60, 20, hwnd, NULL, NULL, NULL);
            hwndGenderM = CreateWindow("BUTTON", "Male",
                        WS_VISIBLE | WS_CHILD | BS_AUTORADIOBUTTON | WS_GROUP,
                        400, 148, 80, 25, hwnd, (HMENU)IDC_GENDER_M, NULL, NULL);
            hwndGenderF = CreateWindow("BUTTON", "Female",
                        WS_VISIBLE | WS_CHILD | BS_AUTORADIOBUTTON,
                        490, 148, 100, 25, hwnd, (HMENU)IDC_GENDER_F, NULL, NULL);

            // Address Section
            CreateWindow("STATIC", "ADDRESS",
                        WS_VISIBLE | WS_CHILD,
                        30, 190, 200, 20, hwnd, NULL, NULL, NULL);

            CreateWindow("STATIC", "Street Address:",
                        WS_VISIBLE | WS_CHILD,
                        30, 220, 120, 20, hwnd, NULL, NULL, NULL);
            hwndAddress = CreateWindow("EDIT", "",
                        WS_VISIBLE | WS_CHILD | WS_BORDER | ES_LEFT | ES_MULTILINE,
                        160, 218, 450, 50, hwnd, (HMENU)IDC_ADDRESS, NULL, NULL);

            CreateWindow("STATIC", "City:",
                        WS_VISIBLE | WS_CHILD,
                        30, 285, 120, 20, hwnd, NULL, NULL, NULL);
            hwndCity = CreateWindow("EDIT", "",
                        WS_VISIBLE | WS_CHILD | WS_BORDER | ES_LEFT,
                        160, 283, 200, 25, hwnd, (HMENU)IDC_CITY, NULL, NULL);

            CreateWindow("STATIC", "State:",
                        WS_VISIBLE | WS_CHILD,
                        30, 320, 120, 20, hwnd, NULL, NULL, NULL);
            hwndState = CreateWindow("EDIT", "",
                        WS_VISIBLE | WS_CHILD | WS_BORDER | ES_LEFT,
                        160, 318, 200, 25, hwnd, (HMENU)IDC_STATE, NULL, NULL);

            CreateWindow("STATIC", "PIN Code:",
                        WS_VISIBLE | WS_CHILD,
                        380, 320, 80, 20, hwnd, NULL, NULL, NULL);
            hwndPin = CreateWindow("EDIT", "",
                        WS_VISIBLE | WS_CHILD | WS_BORDER | ES_LEFT | ES_NUMBER,
                        470, 318, 140, 25, hwnd, (HMENU)IDC_PIN, NULL, NULL);

            // Contact Information Section
            CreateWindow("STATIC", "CONTACT INFORMATION",
                        WS_VISIBLE | WS_CHILD,
                        30, 360, 300, 20, hwnd, NULL, NULL, NULL);

            CreateWindow("STATIC", "Phone Number:",
                        WS_VISIBLE | WS_CHILD,
                        30, 390, 120, 20, hwnd, NULL, NULL, NULL);
            hwndPhone = CreateWindow("EDIT", "",
                        WS_VISIBLE | WS_CHILD | WS_BORDER | ES_LEFT,
                        160, 388, 200, 25, hwnd, (HMENU)IDC_PHONE, NULL, NULL);

            CreateWindow("STATIC", "Email Address:",
                        WS_VISIBLE | WS_CHILD,
                        30, 425, 120, 20, hwnd, NULL, NULL, NULL);
            hwndEmail = CreateWindow("EDIT", "",
                        WS_VISIBLE | WS_CHILD | WS_BORDER | ES_LEFT,
                        160, 423, 450, 25, hwnd, (HMENU)IDC_EMAIL, NULL, NULL);

            // Course Selection
            CreateWindow("STATIC", "COURSE DETAILS",
                        WS_VISIBLE | WS_CHILD,
                        30, 465, 200, 20, hwnd, NULL, NULL, NULL);

            CreateWindow("STATIC", "Select Course:",
                        WS_VISIBLE | WS_CHILD,
                        30, 495, 120, 20, hwnd, NULL, NULL, NULL);
            hwndCourse = CreateWindow("COMBOBOX", "",
                        WS_VISIBLE | WS_CHILD | CBS_DROPDOWNLIST | WS_VSCROLL,
                        160, 493, 300, 150, hwnd, (HMENU)IDC_COURSE, NULL, NULL);

            // Add courses
            SendMessage(hwndCourse, CB_ADDSTRING, 0, (LPARAM)"MCA (Master of Computer Applications)");
            SendMessage(hwndCourse, CB_ADDSTRING, 0, (LPARAM)"MSc Computer Science");
            SendMessage(hwndCourse, CB_ADDSTRING, 0, (LPARAM)"BCA (Bachelor of Computer Applications)");
            SendMessage(hwndCourse, CB_ADDSTRING, 0, (LPARAM)"BSc Computer Science");
            SendMessage(hwndCourse, CB_ADDSTRING, 0, (LPARAM)"Diploma in Computer Applications");

            // Buttons
            CreateWindow("BUTTON", "SUBMIT APPLICATION",
                        WS_VISIBLE | WS_CHILD | BS_DEFPUSHBUTTON,
                        150, 570, 200, 40, hwnd, (HMENU)IDC_SUBMIT, NULL, NULL);

            CreateWindow("BUTTON", "CLEAR FORM",
                        WS_VISIBLE | WS_CHILD | BS_PUSHBUTTON,
                        370, 570, 150, 40, hwnd, (HMENU)IDC_CLEAR, NULL, NULL);
        }
        break;

        case WM_COMMAND:
        {
            switch (LOWORD(wParam))
            {
                case IDC_SUBMIT:
                    SubmitForm(hwnd);
                    break;

                case IDC_CLEAR:
                    ClearForm();
                    MessageBox(hwnd, "Form cleared successfully!", "Info", MB_OK | MB_ICONINFORMATION);
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
 * cl Q7_ApplicationForm.cpp user32.lib gdi32.lib
 *
 * Or:
 * g++ Q7_ApplicationForm.cpp -o Q7_ApplicationForm.exe -luser32 -lgdi32 -mwindows
 *
 * Expected Output:
 * ----------------
 * A complete application form window with:
 * - Personal information fields (Name, Father's name, DOB, Gender)
 * - Address fields (Street, City, State, PIN)
 * - Contact information (Phone, Email)
 * - Course selection dropdown
 * - Submit and Clear buttons
 * - Form validation and submission display
 */
