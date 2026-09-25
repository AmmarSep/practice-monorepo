^+f:: ; Replace "^+f" with your desired hotkey combination (Ctrl+Shift+F)
FormatTime, CurrentDateTime,, dd/MM/yy hh:mm tt
SendInput %CurrentDateTime%
return
