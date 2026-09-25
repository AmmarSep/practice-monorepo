^+t:: ; Replace "^+t" with your desired hotkey combination (Ctrl+Shift+T)
FormatTime, CurrentDateTime,, h:mm tt
SendInput %CurrentDateTime%
return
