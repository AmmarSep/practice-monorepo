#include <windows.h>

LRESULT CALLBACK WndProc(HWND, UINT, WPARAM, LPARAM);

int WINAPI WinMain(HINSTANCE hInst, HINSTANCE, LPSTR, int nShow) {
    WNDCLASS wc = {0};
    wc.lpfnWndProc = WndProc;
    wc.hInstance = hInst;
    wc.lpszClassName = "RectWindow";
    wc.hbrBackground = (HBRUSH)(COLOR_WINDOW+1);
    RegisterClass(&wc);
    
    HWND hwnd = CreateWindow("RectWindow", "Colored Rectangles", WS_OVERLAPPEDWINDOW,
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
            HBRUSH hBrush;
            
            hBrush = CreateSolidBrush(RGB(255, 0, 0));
            SelectObject(hdc, hBrush);
            Rectangle(hdc, 50, 50, 150, 150);
            DeleteObject(hBrush);
            
            hBrush = CreateSolidBrush(RGB(0, 255, 0));
            SelectObject(hdc, hBrush);
            Rectangle(hdc, 200, 50, 300, 150);
            DeleteObject(hBrush);
            
            hBrush = CreateSolidBrush(RGB(0, 0, 255));
            SelectObject(hdc, hBrush);
            Rectangle(hdc, 50, 200, 150, 300);
            DeleteObject(hBrush);
            
            EndPaint(hwnd, &ps);
            break;
        }
        case WM_DESTROY:
            PostQuitMessage(0);
            break;
    }
    return DefWindowProc(hwnd, msg, wParam, lParam);
}
