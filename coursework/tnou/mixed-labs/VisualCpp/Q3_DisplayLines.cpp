#include <windows.h>

LRESULT CALLBACK WndProc(HWND, UINT, WPARAM, LPARAM);

int WINAPI WinMain(HINSTANCE hInst, HINSTANCE, LPSTR, int nShow) {
    WNDCLASS wc = {0};
    wc.lpfnWndProc = WndProc;
    wc.hInstance = hInst;
    wc.lpszClassName = "LineWindow";
    wc.hbrBackground = (HBRUSH)(COLOR_WINDOW+1);
    RegisterClass(&wc);
    
    HWND hwnd = CreateWindow("LineWindow", "Display Lines", WS_OVERLAPPEDWINDOW,
                             100, 100, 500, 400, NULL, NULL, hInst, NULL);
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
        case WM_PAINT: {
            PAINTSTRUCT ps;
            HDC hdc = BeginPaint(hwnd, &ps);
            MoveToEx(hdc, 50, 50, NULL);
            LineTo(hdc, 200, 50);
            MoveToEx(hdc, 50, 100, NULL);
            LineTo(hdc, 250, 150);
            MoveToEx(hdc, 100, 200, NULL);
            LineTo(hdc, 300, 200);
            EndPaint(hwnd, &ps);
            break;
        }
        case WM_DESTROY:
            PostQuitMessage(0);
            break;
    }
    return DefWindowProc(hwnd, msg, wParam, lParam);
}
