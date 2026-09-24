#include <windows.h>

LRESULT CALLBACK WndProc(HWND, UINT, WPARAM, LPARAM);

int WINAPI WinMain(HINSTANCE hInst, HINSTANCE, LPSTR, int nShow) {
    WNDCLASS wc = {0};
    wc.lpfnWndProc = WndProc;
    wc.hInstance = hInst;
    wc.lpszClassName = "FormWindow";
    wc.hbrBackground = (HBRUSH)(COLOR_WINDOW+1);
    RegisterClass(&wc);
    
    HWND hwnd = CreateWindow("FormWindow", "Application Form", WS_OVERLAPPEDWINDOW,
                             100, 100, 400, 350, NULL, NULL, hInst, NULL);
    
    CreateWindow("STATIC", "Name:", WS_VISIBLE | WS_CHILD, 50, 30, 80, 20, hwnd, NULL, hInst, NULL);
    CreateWindow("EDIT", "", WS_VISIBLE | WS_CHILD | WS_BORDER, 150, 30, 200, 25, hwnd, NULL, hInst, NULL);
    
    CreateWindow("STATIC", "Email:", WS_VISIBLE | WS_CHILD, 50, 70, 80, 20, hwnd, NULL, hInst, NULL);
    CreateWindow("EDIT", "", WS_VISIBLE | WS_CHILD | WS_BORDER, 150, 70, 200, 25, hwnd, NULL, hInst, NULL);
    
    CreateWindow("STATIC", "Phone:", WS_VISIBLE | WS_CHILD, 50, 110, 80, 20, hwnd, NULL, hInst, NULL);
    CreateWindow("EDIT", "", WS_VISIBLE | WS_CHILD | WS_BORDER, 150, 110, 200, 25, hwnd, NULL, hInst, NULL);
    
    CreateWindow("BUTTON", "Submit", WS_VISIBLE | WS_CHILD | BS_DEFPUSHBUTTON,
                 150, 160, 100, 30, hwnd, (HMENU)1, hInst, NULL);
    
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
            if(LOWORD(wParam) == 1)
                MessageBox(hwnd, "Form Submitted!", "Success", MB_OK);
            break;
        case WM_DESTROY:
            PostQuitMessage(0);
            break;
    }
    return DefWindowProc(hwnd, msg, wParam, lParam);
}
