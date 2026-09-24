#include <windows.h>

LRESULT CALLBACK WndProc(HWND, UINT, WPARAM, LPARAM);

int WINAPI WinMain(HINSTANCE hInst, HINSTANCE, LPSTR, int nShow) {
    WNDCLASS wc = {0};
    wc.lpfnWndProc = WndProc;
    wc.hInstance = hInst;
    wc.lpszClassName = "DialogWindow";
    wc.hbrBackground = (HBRUSH)(COLOR_WINDOW+1);
    RegisterClass(&wc);
    
    HWND hwnd = CreateWindow("DialogWindow", "Dialog Box Demo", WS_OVERLAPPEDWINDOW,
                             100, 100, 400, 300, NULL, NULL, hInst, NULL);
    
    CreateWindow("BUTTON", "Show Dialog", WS_VISIBLE | WS_CHILD | BS_DEFPUSHBUTTON,
                 100, 100, 150, 40, hwnd, (HMENU)1, hInst, NULL);
    
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
                MessageBox(hwnd, "This is a Dialog Box!", "Dialog", MB_OK | MB_ICONINFORMATION);
            break;
        case WM_DESTROY:
            PostQuitMessage(0);
            break;
    }
    return DefWindowProc(hwnd, msg, wParam, lParam);
}
