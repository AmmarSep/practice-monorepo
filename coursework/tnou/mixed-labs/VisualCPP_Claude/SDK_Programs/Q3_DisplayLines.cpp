/*
 * Question 3: Displaying Set of Lines
 *
 * This program demonstrates drawing various lines using GDI functions:
 * - LineTo for simple lines
 * - Polyline for connected lines
 * - Different pen styles and colors
 */

#include <windows.h>

const char g_szClassName[] = "DisplayLinesWindow";

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
        "TNOU - Display Lines (Q3)",
        WS_OVERLAPPEDWINDOW,
        CW_USEDEFAULT, CW_USEDEFAULT, 800, 600,
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

LRESULT CALLBACK WndProc(HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam)
{
    switch (msg)
    {
        case WM_PAINT:
        {
            PAINTSTRUCT ps;
            HDC hdc = BeginPaint(hwnd, &ps);

            // Title text
            SetBkMode(hdc, TRANSPARENT);
            TextOut(hdc, 20, 10, "Question 3: Displaying Various Lines", 37);

            // 1. Simple horizontal and vertical lines
            HPEN hPen1 = CreatePen(PS_SOLID, 2, RGB(255, 0, 0)); // Red solid line
            HPEN hOldPen = (HPEN)SelectObject(hdc, hPen1);

            TextOut(hdc, 20, 40, "1. Horizontal Line (Red):", 25);
            MoveToEx(hdc, 50, 60, NULL);
            LineTo(hdc, 300, 60);

            DeleteObject(hPen1);

            // 2. Vertical line
            HPEN hPen2 = CreatePen(PS_SOLID, 2, RGB(0, 255, 0)); // Green solid line
            SelectObject(hdc, hPen2);

            TextOut(hdc, 20, 80, "2. Vertical Line (Green):", 25);
            MoveToEx(hdc, 100, 100, NULL);
            LineTo(hdc, 100, 200);

            DeleteObject(hPen2);

            // 3. Diagonal lines
            HPEN hPen3 = CreatePen(PS_SOLID, 3, RGB(0, 0, 255)); // Blue solid line
            SelectObject(hdc, hPen3);

            TextOut(hdc, 320, 40, "3. Diagonal Lines (Blue):", 25);
            MoveToEx(hdc, 350, 60, NULL);
            LineTo(hdc, 450, 120);
            MoveToEx(hdc, 450, 60, NULL);
            LineTo(hdc, 350, 120);

            DeleteObject(hPen3);

            // 4. Dashed line
            HPEN hPen4 = CreatePen(PS_DASH, 1, RGB(128, 0, 128)); // Purple dashed line
            SelectObject(hdc, hPen4);

            TextOut(hdc, 20, 220, "4. Dashed Line (Purple):", 24);
            MoveToEx(hdc, 50, 240, NULL);
            LineTo(hdc, 300, 240);

            DeleteObject(hPen4);

            // 5. Dotted line
            HPEN hPen5 = CreatePen(PS_DOT, 1, RGB(255, 165, 0)); // Orange dotted line
            SelectObject(hdc, hPen5);

            TextOut(hdc, 20, 260, "5. Dotted Line (Orange):", 24);
            MoveToEx(hdc, 50, 280, NULL);
            LineTo(hdc, 300, 280);

            DeleteObject(hPen5);

            // 6. Polyline - connected lines
            HPEN hPen6 = CreatePen(PS_SOLID, 2, RGB(255, 0, 255)); // Magenta
            SelectObject(hdc, hPen6);

            TextOut(hdc, 320, 220, "6. Polyline (Magenta):", 22);
            POINT points1[] = {{350, 240}, {400, 260}, {450, 240}, {500, 280}, {550, 250}};
            Polyline(hdc, points1, 5);

            DeleteObject(hPen6);

            // 7. Triangle using polyline
            HPEN hPen7 = CreatePen(PS_SOLID, 3, RGB(139, 69, 19)); // Brown
            SelectObject(hdc, hPen7);

            TextOut(hdc, 20, 310, "7. Triangle (Brown):", 20);
            POINT triangle[] = {{100, 330}, {150, 400}, {50, 400}, {100, 330}};
            Polyline(hdc, triangle, 4);

            DeleteObject(hPen7);

            // 8. Star pattern
            HPEN hPen8 = CreatePen(PS_SOLID, 2, RGB(255, 215, 0)); // Gold
            SelectObject(hdc, hPen8);

            TextOut(hdc, 320, 310, "8. Star Pattern (Gold):", 23);
            POINT star[] = {
                {450, 330}, {470, 370}, {510, 370}, {480, 395},
                {490, 435}, {450, 410}, {410, 435}, {420, 395},
                {390, 370}, {430, 370}, {450, 330}
            };
            Polyline(hdc, star, 11);

            DeleteObject(hPen8);

            // 9. Grid pattern
            HPEN hPen9 = CreatePen(PS_SOLID, 1, RGB(192, 192, 192)); // Gray
            SelectObject(hdc, hPen9);

            TextOut(hdc, 20, 450, "9. Grid Pattern (Gray):", 23);
            for (int i = 0; i <= 5; i++)
            {
                // Horizontal lines
                MoveToEx(hdc, 50, 470 + i * 20, NULL);
                LineTo(hdc, 200, 470 + i * 20);
                // Vertical lines
                MoveToEx(hdc, 50 + i * 30, 470, NULL);
                LineTo(hdc, 50 + i * 30, 570);
            }

            DeleteObject(hPen9);

            // 10. Thick line
            HPEN hPen10 = CreatePen(PS_SOLID, 10, RGB(0, 128, 128)); // Teal
            SelectObject(hdc, hPen10);

            TextOut(hdc, 320, 450, "10. Thick Line (Teal):", 22);
            MoveToEx(hdc, 350, 490, NULL);
            LineTo(hdc, 550, 490);

            SelectObject(hdc, hOldPen);
            DeleteObject(hPen10);

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
 * cl Q3_DisplayLines.cpp user32.lib gdi32.lib
 *
 * Or:
 * g++ Q3_DisplayLines.cpp -o Q3_DisplayLines.exe -luser32 -lgdi32 -mwindows
 *
 * Expected Output:
 * ----------------
 * Window displays 10 different types of lines:
 * 1. Horizontal line (red)
 * 2. Vertical line (green)
 * 3. Diagonal lines (blue)
 * 4. Dashed line (purple)
 * 5. Dotted line (orange)
 * 6. Polyline - connected lines (magenta)
 * 7. Triangle using polyline (brown)
 * 8. Star pattern (gold)
 * 9. Grid pattern (gray)
 * 10. Thick line (teal)
 */
