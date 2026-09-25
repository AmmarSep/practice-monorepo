/*
 * Question 4: Displaying Rectangles with Different Colors
 *
 * This program demonstrates drawing rectangles using:
 * - Rectangle() function
 * - FillRect() function
 * - Different colors and brush styles
 */

#include <windows.h>

const char g_szClassName[] = "ColoredRectanglesWindow";

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
        "TNOU - Colored Rectangles (Q4)",
        WS_OVERLAPPEDWINDOW,
        CW_USEDEFAULT, CW_USEDEFAULT, 900, 700,
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

            // Title
            SetBkMode(hdc, TRANSPARENT);
            HFONT hFont = CreateFont(20, 0, 0, 0, FW_BOLD, FALSE, FALSE, FALSE,
                                     DEFAULT_CHARSET, OUT_DEFAULT_PRECIS,
                                     CLIP_DEFAULT_PRECIS, DEFAULT_QUALITY,
                                     DEFAULT_PITCH | FF_DONTCARE, "Arial");
            HFONT hOldFont = (HFONT)SelectObject(hdc, hFont);
            TextOut(hdc, 20, 10, "Question 4: Rectangles with Different Colors", 45);
            SelectObject(hdc, hOldFont);
            DeleteObject(hFont);

            // 1. Solid Red Rectangle
            HBRUSH hBrush1 = CreateSolidBrush(RGB(255, 0, 0));
            HBRUSH hOldBrush = (HBRUSH)SelectObject(hdc, hBrush1);
            Rectangle(hdc, 50, 50, 200, 150);
            TextOut(hdc, 70, 160, "Red Rectangle", 13);
            SelectObject(hdc, hOldBrush);
            DeleteObject(hBrush1);

            // 2. Solid Green Rectangle
            HBRUSH hBrush2 = CreateSolidBrush(RGB(0, 255, 0));
            SelectObject(hdc, hBrush2);
            Rectangle(hdc, 220, 50, 370, 150);
            TextOut(hdc, 240, 160, "Green Rectangle", 15);
            SelectObject(hdc, hOldBrush);
            DeleteObject(hBrush2);

            // 3. Solid Blue Rectangle
            HBRUSH hBrush3 = CreateSolidBrush(RGB(0, 0, 255));
            SelectObject(hdc, hBrush3);
            Rectangle(hdc, 390, 50, 540, 150);
            TextOut(hdc, 410, 160, "Blue Rectangle", 14);
            SelectObject(hdc, hOldBrush);
            DeleteObject(hBrush3);

            // 4. Yellow Rectangle
            HBRUSH hBrush4 = CreateSolidBrush(RGB(255, 255, 0));
            SelectObject(hdc, hBrush4);
            Rectangle(hdc, 560, 50, 710, 150);
            TextOut(hdc, 580, 160, "Yellow Rectangle", 16);
            SelectObject(hdc, hOldBrush);
            DeleteObject(hBrush4);

            // 5. Magenta Rectangle with thick border
            HPEN hPen5 = CreatePen(PS_SOLID, 5, RGB(0, 0, 0));
            HPEN hOldPen = (HPEN)SelectObject(hdc, hPen5);
            HBRUSH hBrush5 = CreateSolidBrush(RGB(255, 0, 255));
            SelectObject(hdc, hBrush5);
            Rectangle(hdc, 50, 200, 200, 300);
            TextOut(hdc, 60, 310, "Magenta (Border)", 16);
            SelectObject(hdc, hOldBrush);
            SelectObject(hdc, hOldPen);
            DeleteObject(hBrush5);
            DeleteObject(hPen5);

            // 6. Cyan Rectangle
            HBRUSH hBrush6 = CreateSolidBrush(RGB(0, 255, 255));
            SelectObject(hdc, hBrush6);
            Rectangle(hdc, 220, 200, 370, 300);
            TextOut(hdc, 240, 310, "Cyan Rectangle", 14);
            SelectObject(hdc, hOldBrush);
            DeleteObject(hBrush6);

            // 7. Orange Rectangle
            HBRUSH hBrush7 = CreateSolidBrush(RGB(255, 165, 0));
            SelectObject(hdc, hBrush7);
            Rectangle(hdc, 390, 200, 540, 300);
            TextOut(hdc, 410, 310, "Orange Rectangle", 16);
            SelectObject(hdc, hOldBrush);
            DeleteObject(hBrush7);

            // 8. Pink Rectangle
            HBRUSH hBrush8 = CreateSolidBrush(RGB(255, 192, 203));
            SelectObject(hdc, hBrush8);
            Rectangle(hdc, 560, 200, 710, 300);
            TextOut(hdc, 580, 310, "Pink Rectangle", 14);
            SelectObject(hdc, hOldBrush);
            DeleteObject(hBrush8);

            // 9. Purple Rectangle
            HBRUSH hBrush9 = CreateSolidBrush(RGB(128, 0, 128));
            SelectObject(hdc, hBrush9);
            Rectangle(hdc, 50, 350, 200, 450);
            SetTextColor(hdc, RGB(255, 255, 255));
            TextOut(hdc, 60, 460, "Purple Rectangle", 16);
            SetTextColor(hdc, RGB(0, 0, 0));
            SelectObject(hdc, hOldBrush);
            DeleteObject(hBrush9);

            // 10. Brown Rectangle
            HBRUSH hBrush10 = CreateSolidBrush(RGB(139, 69, 19));
            SelectObject(hdc, hBrush10);
            Rectangle(hdc, 220, 350, 370, 450);
            SetTextColor(hdc, RGB(255, 255, 255));
            TextOut(hdc, 240, 460, "Brown Rectangle", 15);
            SetTextColor(hdc, RGB(0, 0, 0));
            SelectObject(hdc, hOldBrush);
            DeleteObject(hBrush10);

            // 11. Gray Rectangle
            HBRUSH hBrush11 = CreateSolidBrush(RGB(128, 128, 128));
            SelectObject(hdc, hBrush11);
            Rectangle(hdc, 390, 350, 540, 450);
            SetTextColor(hdc, RGB(255, 255, 255));
            TextOut(hdc, 410, 460, "Gray Rectangle", 14);
            SetTextColor(hdc, RGB(0, 0, 0));
            SelectObject(hdc, hOldBrush);
            DeleteObject(hBrush11);

            // 12. Teal Rectangle
            HBRUSH hBrush12 = CreateSolidBrush(RGB(0, 128, 128));
            SelectObject(hdc, hBrush12);
            Rectangle(hdc, 560, 350, 710, 450);
            SetTextColor(hdc, RGB(255, 255, 255));
            TextOut(hdc, 580, 460, "Teal Rectangle", 14);
            SetTextColor(hdc, RGB(0, 0, 0));
            SelectObject(hdc, hOldBrush);
            DeleteObject(hBrush12);

            // 13. Hatched Rectangle (Cross pattern)
            HBRUSH hBrush13 = CreateHatchBrush(HS_CROSS, RGB(255, 0, 0));
            SelectObject(hdc, hBrush13);
            Rectangle(hdc, 50, 500, 200, 600);
            TextOut(hdc, 60, 610, "Cross Hatch", 11);
            SelectObject(hdc, hOldBrush);
            DeleteObject(hBrush13);

            // 14. Hatched Rectangle (Diagonal pattern)
            HBRUSH hBrush14 = CreateHatchBrush(HS_DIAGCROSS, RGB(0, 0, 255));
            SelectObject(hdc, hBrush14);
            Rectangle(hdc, 220, 500, 370, 600);
            TextOut(hdc, 240, 610, "Diagonal Hatch", 14);
            SelectObject(hdc, hOldBrush);
            DeleteObject(hBrush14);

            // 15. Gradient-like effect using multiple rectangles
            TextOut(hdc, 410, 500, "Gradient Effect", 15);
            for (int i = 0; i < 150; i++)
            {
                HBRUSH hBrushGrad = CreateSolidBrush(RGB(i, 255 - i, 128));
                RECT rectGrad = {390 + i, 520, 391 + i, 600};
                FillRect(hdc, &rectGrad, hBrushGrad);
                DeleteObject(hBrushGrad);
            }

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
 * cl Q4_ColoredRectangles.cpp user32.lib gdi32.lib
 *
 * Or:
 * g++ Q4_ColoredRectangles.cpp -o Q4_ColoredRectangles.exe -luser32 -lgdi32 -mwindows
 *
 * Expected Output:
 * ----------------
 * Window displays 15 different rectangles:
 * - Various solid colors (Red, Green, Blue, Yellow, etc.)
 * - Hatched patterns (Cross, Diagonal)
 * - Gradient effect
 * - Different border styles
 */
