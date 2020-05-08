package dev.brj.pasteboard

import android.content.ClipboardManager
import android.inputmethodservice.InputMethodService
import android.view.View
import android.widget.ImageButton


class PasteButton : InputMethodService()  {
    override fun onCreateInputView(): View {
        val keyboardView = layoutInflater.inflate(R.layout.keyboard_view, null) //as KeyboardView

        val pasteButton = keyboardView.findViewById(R.id.pasteButton) as ImageButton
        pasteButton.setOnClickListener {
            val mClipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager

            val clippy = mClipboardManager.getPrimaryClip()?.getItemAt(0)
                ?.coerceToText(applicationContext)?.toString() ?: ""

            val ic = currentInputConnection
            ic.commitText(clippy, 1)
        }

        return keyboardView
    }
}