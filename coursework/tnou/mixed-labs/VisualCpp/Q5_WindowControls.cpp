#include <windows.h>

LRESULT CALLBACK WndProc(HWND, UINT, WPARAM, LPARAM);

int WINAPI WinMain(HINSTANCE hInst, HINSTANCE, LPSTR, int nShow) {
    WNDCLASS wc = {0};
    wc.lpfnWndProc = WndProc;
    wc.hInstance = hInst;
    wc.lpszClassName = "ControlWindow";
    wc.hbrBackground = (HBRUSH)(COLOR_WINDOW+1);
    RegisterClass(&wc);
    
    HWND hwnd = CreateWindow("ControlWindow", "Window Controls", WS_OVERLAPPEDWINDOW,
                             100, 100, 400, 300, NULL, NULL, hInst, NULL);
    
    CreateWindow("BUTTON", "Click Me", WS_VISIBLE | WS_CHILD | BS_DEFPUSHBUTTON,
                 50, 50, 100, 30, hwnd, (HMENU)1, hInst, NULL);
    CreateWindow("EDIT", "", WS_VISIBLE | WS_CHILD | WS_BORDER | ES_LEFT,
                 50, 100, 200, 25, hwnd, (HMENU)2, hInst, NULL);
    CreateWindow("STATIC", "Enter Text:", WS_VISIBLE | WS_CHILD,
                 50, 75, 100, 20, hwnd, NULL, hInst, NULL);
    
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
                MessageBox(hwnd, "Button Clicked!", "Info", MB_OK);
            break;
        case WM_DESTROY:
            PostQuitMessage(0);
            break;
    }
    return DefWindowProc(hwnd, msg, wParam, lParam);
}
