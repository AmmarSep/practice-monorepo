#include <windows.h>

LRESULT CALLBACK WndProc(HWND, UINT, WPARAM, LPARAM);
HWND hEdit;

int WINAPI WinMain(HINSTANCE hInst, HINSTANCE, LPSTR, int nShow) {
    WNDCLASS wc = {0};
    wc.lpfnWndProc = WndProc;
    wc.hInstance = hInst;
    wc.lpszClassName = "PassWindow";
    wc.hbrBackground = (HBRUSH)(COLOR_WINDOW+1);
    RegisterClass(&wc);
    
    HWND hwnd = CreateWindow("PassWindow", "Password Check", WS_OVERLAPPEDWINDOW,
                             100, 100, 400, 250, NULL, NULL, hInst, NULL);
    
    CreateWindow("STATIC", "Enter Password:", WS_VISIBLE | WS_CHILD, 50, 50, 120, 20, hwnd, NULL, hInst, NULL);
    hEdit = CreateWindow("EDIT", "", WS_VISIBLE | WS_CHILD | WS_BORDER | ES_PASSWORD,
                         180, 50, 150, 25, hwnd, NULL, hInst, NULL);
    CreateWindow("BUTTON", "Login", WS_VISIBLE | WS_CHILD | BS_DEFPUSHBUTTON,
                 150, 100, 100, 30, hwnd, (HMENU)1, hInst, NULL);
    
    ShowWindow(hwnd, nShow);
    UpdateWindow(hwnd);
    
    MSG msg;
    while(GetMessage(&msg, NULL, 0, 0)) {
        TranslateMessage(&msg);
        DispatchMessage(&msg);
    }
    return msg.wParam;
}

LRESULT CALLBACK WndProc(HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam) {
    switch(msg) {
        case WM_COMMAND:
            if(LOWORD(wParam) == 1) {
                char pass[50];
                GetWindowText(hEdit, pass, 50);
                if(strcmp(pass, "admin") == 0)
                    MessageBox(hwnd, "Login Successful!", "Success", MB_OK | MB_ICONINFORMATION);
                else
                    MessageBox(hwnd, "Invalid Password!", "Error", MB_OK | MB_ICONERROR);
            }
            break;
        case WM_DESTROY:
            PostQuitMessage(0);
            break;
    }
    return DefWindowProc(hwnd, msg, wParam, lParam);
}
